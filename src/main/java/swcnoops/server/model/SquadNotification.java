package swcnoops.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SquadNotification {
    private long date;
    final private String id;
    private String message;
    private String name;
    private String playerId;
    final private SquadMsgType type;
    private SquadNotificationData data;
    private long orderNo;

    @JsonIgnore
    final private String guildId;
    @JsonIgnore
    final private String guildName;

    public SquadNotification(String guildId, String guildName, String id, String message, String name, String playerId, SquadMsgType type)
    {
        this(guildId, guildName, 0, -1, id, message, name, playerId, type, null);
    }

    public SquadNotification(String guildId, String guildName, long date, long orderNo, String id, String message,
                             String name, String playerId, SquadMsgType type, SquadNotificationData data)
    {
        this.guildId = guildId;
        this.guildName = guildName;
        this.date = date;
        this.orderNo = orderNo;
        this.id = id;
        this.message = message;
        this.name = name;
        this.playerId = playerId;
        this.type = type;
        this.data = data;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public SquadMsgType getType() {
        return type;
    }

    public SquadNotificationData getData() {
        return data;
    }

    public void setData(SquadNotificationData data) {
        this.data = data;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    @JsonIgnore
    public String getGuildId() {
        return guildId;
    }

    public String getGuildName() {
        return this.guildName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
