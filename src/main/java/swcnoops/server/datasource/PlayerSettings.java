package swcnoops.server.datasource;

import swcnoops.server.model.*;
import swcnoops.server.session.inventory.Troops;
import swcnoops.server.session.training.BuildUnits;

public class PlayerSettings {
    private final String playerId;
    private Upgrades upgrades;
    private String name;
    private FactionType faction;
    public PlayerMap baseMap;
    private Deployables deployableTroops;
    private BuildUnits buildUnits;
    private Creature creature;
    private Troops troops;
    private DonatedTroops donatedTroops;
    private InventoryStorage inventoryStorage;
    private String currentQuest;
    private PlayerCampaignMission playerCampaignMissions;
    private PreferencesMap sharedPreferences;
    private String guildId;
    private UnlockedPlanets unlockedPlanets;

    private Scalars scalars;


    private long keepAlive;

    public PlayerSettings(String playerId) {
        this.playerId = playerId;
    }

    // TODO - not used at the moment, will probably be used for samples, will have to rename if do
    public Upgrades getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(Upgrades upgrades) {
        this.upgrades = upgrades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaction(FactionType faction) {
        this.faction = faction;
    }

    public FactionType getFaction() {
        return faction;
    }

    public void setBaseMap(PlayerMap baseMap) {
        this.baseMap = baseMap;
    }

    public PlayerMap getBaseMap() {
        return baseMap;
    }

    public Deployables getDeployableTroops() {
        if (this.deployableTroops == null)
            this.deployableTroops = new Deployables();

        return this.deployableTroops;
    }

    public void setDeployableTroops(Deployables deployableTroops) {
        this.deployableTroops = deployableTroops;
    }

    public BuildUnits getBuildContracts() {
        return this.buildUnits;
    }

    public void setBuildContracts(BuildUnits buildUnits) {
        this.buildUnits = buildUnits;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }

    public Troops getTroops() {
        return troops;
    }

    public void setTroops(Troops troops) {
        this.troops = troops;
    }

    public DonatedTroops getDonatedTroops() {
        return donatedTroops;
    }

    public void setDonatedTroops(DonatedTroops donatedTroops) {
        this.donatedTroops = donatedTroops;
    }

    public InventoryStorage getInventoryStorage() {
        return inventoryStorage;
    }

    public void setInventoryStorage(InventoryStorage inventoryStorage) {
        this.inventoryStorage = inventoryStorage;
    }

    public void setCurrentQuest(String currentQuest) {
        this.currentQuest = currentQuest;
    }

    public String getCurrentQuest() {
        return currentQuest;
    }

    public PlayerCampaignMission getPlayerCampaignMission() {
        return playerCampaignMissions;
    }

    public void setPlayerCampaignMissions(PlayerCampaignMission playerCampaignMissions) {
        this.playerCampaignMissions = playerCampaignMissions;
    }

    public PreferencesMap getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(PreferencesMap sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getGuildId() {
        return guildId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public UnlockedPlanets getUnlockedPlanets() {
        return unlockedPlanets;
    }

    public void setUnlockedPlanets(UnlockedPlanets unlockedPlanets) {
        this.unlockedPlanets = unlockedPlanets;
    }

    public Scalars getScalars() {
        return scalars == null ? new Scalars(0, 0, 0, 0, 0, 0, 100, 0, 0, 0) : this.scalars;
    }

    public void setScalars(Scalars scalars) {
        this.scalars = scalars;
    }

    public long getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(long keepAlive) {
        this.keepAlive = keepAlive;
    }
}
