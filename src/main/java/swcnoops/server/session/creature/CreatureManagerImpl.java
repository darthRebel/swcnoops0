package swcnoops.server.session.creature;

import swcnoops.server.ServiceFactory;
import swcnoops.server.datasource.Creature;
import swcnoops.server.game.BuildingData;
import swcnoops.server.game.CrystalHelper;
import swcnoops.server.game.GameDataManager;
import swcnoops.server.game.TrapData;
import swcnoops.server.model.Building;
import swcnoops.server.model.CurrencyType;
import swcnoops.server.model.Position;
import swcnoops.server.session.CurrencyDelta;
import swcnoops.server.session.PlayerSession;
import swcnoops.server.session.map.StrixBeacon;

public class CreatureManagerImpl implements CreatureManager {
    private CreatureDataMap creatureDataMap;
    private Creature creature;
    final private PlayerSession playerSession;

    protected CreatureManagerImpl(PlayerSession playerSession, CreatureDataMap creatureDataMap, Creature creature) {
        this.playerSession = playerSession;
        this.creatureDataMap = creatureDataMap;
        this.creature = creature;
    }

    @Override
    public void recaptureCreature(String creatureTroopUnitId, long time) {
        if (hasCreature()) {
            this.creature.setCreatureUnitId(creatureTroopUnitId);
            this.creature.recapture(time + creatureDataMap.trapData.getRearmTime());
        }
    }

    @Override
    public boolean hasCreature() {
        return this.getCreatureStatus() != CreatureStatus.Invalid;
    }

    @Override
    public String getBuildingKey() {
        return this.creatureDataMap.building.key;
    }

    @Override
    public BuildingData getBuildingData() {
        return this.creatureDataMap.buildingData;
    }

    @Override
    public String getBuildingUid() {
        return this.creatureDataMap.building.uid;
    }

    @Override
    public long getRecaptureEndTime() {
        return this.creature.getRecaptureEndTime();
    }

    @Override
    public boolean isCreatureAlive() {
        if (this.getCreatureStatus() != CreatureStatus.Alive) {
            if (this.creature.hasBeenRecaptured()) {
                this.buyout(0, 0);
            }
        }

        return this.getCreatureStatus() == CreatureStatus.Alive;
    }

    @Override
    public boolean isRecapturing() {
        return this.getCreatureStatus() == CreatureStatus.Recapturing;
    }

    @Override
    public String getCreatureUnitId() {
        return this.getCreature().getCreatureUnitId();
    }

    @Override
    public String getSpecialAttackUid() {
        return this.creatureDataMap.trapData.getEventData();
    }

    @Override
    public CurrencyDelta buyout(int crystals, long time) {
        long endTime = this.getRecaptureEndTime();
        this.creature.setCreatureStatus(CreatureStatus.Alive);
        int secondsToBuy = (int)(endTime - time);
        int expectedCrystals = CrystalHelper.secondsToCrystals(secondsToBuy, this.getBuildingData());
        int givenCrystals = CrystalHelper.calculateGivenCrystalDeltaToRemove(this.playerSession, crystals);
        return new CurrencyDelta(givenCrystals, expectedCrystals, CurrencyType.crystals, true);
    }

    @Override
    public CurrencyDelta cancel(long time, int credits, int materials, int contraband) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CreatureStatus getCreatureStatus() {
        return this.creature.getCreatureStatus();
    }

    @Override
    public Creature getCreature() {
        return this.creature;
    }

    @Override
    public CurrencyDelta collect(PlayerSession playerSession, int credits, int materials, int contraband, long time, boolean collectAll) {
        return null;
    }

    @Override
    public void moveTo(Position newPosition) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Building getBuilding() {
        return this.creatureDataMap.building;
    }

    @Override
    public void changeBuildingData(BuildingData buildingData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void upgradeComplete(PlayerSession playerSession, String unitId, String tag, long endTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void buildComplete(PlayerSession playerSession, String unitId, String tag, long endTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void creatureTrapComplete(StrixBeacon strixBeacon, long endTime) {
        GameDataManager gameDataManager = ServiceFactory.instance().getGameDataManager();
        TrapData trapData = gameDataManager.getTrapDataByUid(strixBeacon.getBuildingData().getTrapId());
        this.creatureDataMap = new CreatureDataMap(strixBeacon.getBuilding(), strixBeacon.getBuildingData(), trapData);
        String unitId = CreatureManagerFactory.getDefaultCreatureUnitId(strixBeacon.getBuildingData().getFaction());
        this.creature.setCreatureUnitId(unitId);
        this.buyout(0, endTime);
    }

    @Override
    public void setupForConstruction() {
    }

    @Override
    public void upgradeCancelled(long time) {
    }
}
