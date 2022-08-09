package swcnoops.server.commands.guild;

import swcnoops.server.ServiceFactory;
import swcnoops.server.commands.guild.response.SquadResult;
import swcnoops.server.json.JsonParser;
import swcnoops.server.model.SquadMsgType;
import swcnoops.server.model.SquadNotification;
import swcnoops.server.session.GuildSession;
import swcnoops.server.session.PlayerSession;
import swcnoops.server.session.SessionManager;

public class GuildJoin extends GuildCommandAction<GuildJoin, SquadResult> {
    private String guildId;

    @Override
    protected SquadResult execute(GuildJoin arguments, long time) throws Exception {
        SessionManager sessionManager = ServiceFactory.instance().getSessionManager();
        PlayerSession playerSession = sessionManager.getPlayerSession(arguments.getPlayerId());
        GuildSession guildSession = sessionManager.getGuildSession(playerSession.getPlayerSettings(), arguments.getGuildId());
        GuildSession oldSquad = playerSession.getGuildSession();
        if (oldSquad != null) {
            oldSquad.leave(playerSession);
            SquadNotification leaveNotification = oldSquad.createNotification(playerSession, SquadMsgType.leave);
            oldSquad.addNotification(leaveNotification);
        }
        guildSession.join(playerSession);

        SquadNotification joinNotification =
                guildSession.createNotification(playerSession, SquadMsgType.join);

        guildSession.addNotification(joinNotification);

        // TODO - save the notifications

        SquadResult squadResult = GuildCommandAction.createSquadResult(guildSession);
        return squadResult;
    }

    @Override
    protected GuildJoin parseArgument(JsonParser jsonParser, Object argumentObject) {
        return jsonParser.fromJsonObject(argumentObject, GuildJoin.class);
    }

    @Override
    public String getAction() {
        return "guild.join";
    }

    public String getGuildId() {
        return guildId;
    }
}
