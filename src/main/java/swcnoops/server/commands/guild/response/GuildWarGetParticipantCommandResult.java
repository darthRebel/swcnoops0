package swcnoops.server.commands.guild.response;

import swcnoops.server.model.DonatedTroops;
import swcnoops.server.requests.AbstractCommandResult;

public class GuildWarGetParticipantCommandResult extends AbstractCommandResult {
    public Object champions;
    public Object creatureTraps;
    public Object currentlyDefending;
    public DonatedTroops donatedTroops;
    public Object equipment;
    public String faction;
    public String id;
    public Object lastContractUpdateTime;
    public int level;
    public String name;
    public Object rewards;
    public int score;
    public Object scoutingStatus;
    public int turns;
    public int victoryPoints;
    public Object warMap;
    public Object warStats;

    @Override
    public Object getResult() {
        if (this.id == null)
            return null;

        return super.getResult();
    }
}
