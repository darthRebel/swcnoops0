package swcnoops.server.session;

import swcnoops.server.datasource.GuildSettings;
import swcnoops.server.model.SquadMsgType;
import swcnoops.server.model.SquadNotification;
import swcnoops.server.model.SquadRole;
import swcnoops.server.model.TroopRequestData;

import java.util.List;
import java.util.Map;

public interface GuildSession {
    String getGuildId();

    void login(PlayerSession playerSession);

    void join(PlayerSession playerSession);

    SquadNotification troopsRequest(PlayerSession playerSession, TroopRequestData troopRequestData, String message, long time);

    String getGuildName();

    SquadNotification troopDonation(Map<String, Integer> troopsDonated, String requestId, PlayerSession playerSession, String recipientId, long time);

    void warMatchmakingStart(List<String> participantIds, boolean isSameFactionWarAllowed);

    void leave(PlayerSession playerSession, SquadMsgType leaveType);

    GuildSettings getGuildSettings();

    void editGuild(String description, String icon, Integer minScoreAtEnrollment, boolean openEnrollment);

    boolean canEdit();

    void createNewGuild(PlayerSession playerSession);

    void addNotification(SquadNotification squadNotification);

    List<SquadNotification> getNotifications(long since);

    void saveNotification(SquadNotification squadNotification);

    void saveGuildChange(PlayerSession playerSession, SquadNotification leaveNotification);

    void changeSquadRole(PlayerSession memberSession, SquadRole squadRole, SquadMsgType squadMsgType);

    void joinRequest(PlayerSession playerSession, String message);

    void joinRequestAccepted(String acceptorId, PlayerSession memberSession);

    void joinRequestRejected(String playerId, PlayerSession memberSession);
}
