package swcnoops.server.session;

import swcnoops.server.datasource.Player;
import swcnoops.server.datasource.PlayerSettings;
import swcnoops.server.model.DeploymentRecord;
import swcnoops.server.model.PlayerMap;
import swcnoops.server.session.training.TrainingManager;

import java.util.List;
import java.util.Map;

public interface PlayerSession {
    Player getPlayer();

    String getPlayerId();

    void trainTroops(String constructor, String unitTypeId, int quantity, long time);

    void cancelTrainTroops(String constructor, String unitTypeId, int quantity, long time);

    void buyOutTrainTroops(String constructor, String unitTypeId, int quantity, long time);

    void removeDeployedTroops(Map<String, Integer> deployablesToRemove, long time);
    void removeDeployedTroops(List<DeploymentRecord> deployablesToRemove, long time);

    void playerBattleStart(long time);
    void processCompletedContracts(long time);

    PlayerMap getBaseMap();

    TrainingManager getTrainingManager();
    PlayerSettings getPlayerSettings();
}