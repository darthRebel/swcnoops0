package swcnoops.server.commands.player;

import swcnoops.server.ServiceFactory;
import swcnoops.server.json.JsonParser;
import swcnoops.server.requests.CommandResult;
import swcnoops.server.requests.ResponseHelper;
import swcnoops.server.session.PlayerSession;

public class PlayerBuildingUpgradeAll extends PlayerChecksum<PlayerBuildingUpgradeAll, CommandResult> {
    private String buildingUid;

    @Override
    protected CommandResult execute(PlayerBuildingUpgradeAll arguments, long time) throws Exception {
        PlayerSession playerSession = ServiceFactory.instance().getSessionManager()
                .getPlayerSession(arguments.getPlayerId());

        playerSession.buildingUpgradeAll(arguments.getBuildingUid(), arguments.getCredits(),
                arguments.getMaterials(), arguments.getContraband(), arguments.getCrystals(), time);

        return ResponseHelper.SUCCESS_COMMAND_RESULT;
    }

    @Override
    protected PlayerBuildingUpgradeAll parseArgument(JsonParser jsonParser, Object argumentObject) {
        return jsonParser.fromJsonObject(argumentObject, PlayerBuildingUpgradeAll.class);
    }

    @Override
    public String getAction() {
        return "player.building.upgradeAll";
    }

    public String getBuildingUid() {
        return buildingUid;
    }
}
