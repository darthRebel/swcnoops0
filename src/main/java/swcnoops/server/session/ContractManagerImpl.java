package swcnoops.server.session;

import java.util.*;

public class ContractManagerImpl implements ContractManager {
    final private Map<String, ContractConstructor> contractConstructors = new HashMap<>();
    final private List<AbstractBuildContract> allTroopContracts = new ArrayList<>();
    final private Starport starport;
    public ContractManagerImpl(Starport starport) {
        this.starport = starport;
    }

    @Override
    public void trainTroops(String buildingId, String unitTypeId, int quantity, long startTime) {
        moveCompletedTroopsToStarport(startTime);

        // create contracts for the things that we want to build
        ContractConstructor contractConstructor = getOrCreateContractConstructor(buildingId);
        List<AbstractBuildContract> troopBuildContracts = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            TroopBuildContract troopBuildContract = new TroopBuildContract(buildingId, unitTypeId, contractConstructor);
            troopBuildContracts.add(troopBuildContract);
        }

        contractConstructor.addContracts(troopBuildContracts, startTime);
        this.allTroopContracts.addAll(troopBuildContracts);
        contractConstructor.recalculateContractEndTimes(startTime);
        this.sortTroopContracts();
    }

    @Override
    public void cancelTrainTroops(String buildingId, String unitTypeId, int quantity, long time) {
        // we move completed troops last because if they managed to remove contracts
        // at the head of the queue, then they must of got there before it was completed
        ContractConstructor contractConstructor = getOrCreateContractConstructor(buildingId);
        List<AbstractBuildContract> cancelledContracts = contractConstructor.cancelContracts(unitTypeId, quantity);
        if (cancelledContracts != null)
            this.allTroopContracts.removeAll(cancelledContracts);
        contractConstructor.recalculateContractEndTimes(time);
        this.sortTroopContracts();
        moveCompletedTroopsToStarport(time);
    }

    private void sortTroopContracts() {
        this.allTroopContracts.sort((a, b) -> a.compareEndTime(b));
    }

    private void moveCompletedTroopsToStarport(long clientTime) {
        Iterator<AbstractBuildContract> troopContractIterator = this.allTroopContracts.iterator();
        while(troopContractIterator.hasNext()) {
            AbstractBuildContract troopContract = troopContractIterator.next();
            // troopContracts are sorted in endTime order
            if (troopContract.getEndTime() > clientTime) {
                break;
            }

            // is there enough space to move this completed troop to the transport
            if (troopContract.getContractGroup().getBuildableData().getSize() < this.starport.getAvailableCapacity()) {
                troopContractIterator.remove();
                troopContract.getParent().removeCompletedContract(troopContract);
                this.starport.addTroopContract(troopContract);
            }
        }
    }

    @Override
    public void buyOutTrainTroops(String buildingId, String unitTypeId, int quantity, long time) {
        //we move completed troops last because if they managed to buy out contracts
        //then they must of got there before it was completed
        ContractConstructor contractConstructor = getOrCreateContractConstructor(buildingId);
        List<AbstractBuildContract> boughtOutContracts = contractConstructor.buyOutContract(unitTypeId, quantity, time);
        if (boughtOutContracts != null)
            moveBoughtOutContractsToStarport(boughtOutContracts);
        contractConstructor.recalculateContractEndTimes(time);
        this.sortTroopContracts();
        moveCompletedTroopsToStarport(time);
    }

    private void moveBoughtOutContractsToStarport(List<AbstractBuildContract> boughtOutContracts) {
        for (AbstractBuildContract buildContract : boughtOutContracts) {
            this.allTroopContracts.remove(buildContract);
            this.starport.addTroopContract(buildContract);
        }
    }

    private ContractConstructor getOrCreateContractConstructor(String buildingId) {
        ContractConstructor contractConstructor = this.contractConstructors.get(buildingId);
        if (contractConstructor == null) {
            contractConstructor = new ContractConstructor(buildingId);
            this.contractConstructors.put(contractConstructor.getBuildingId(), contractConstructor);
        }
        return contractConstructor;
    }

    @Override
    public List<AbstractBuildContract> getAllTroopContracts() {
        return this.allTroopContracts;
    }
}
