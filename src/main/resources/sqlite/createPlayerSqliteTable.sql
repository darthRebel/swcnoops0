CREATE TABLE IF NOT EXISTS Player
    (id text PRIMARY KEY,
     secret text,
     secondaryAccount text,
     missingSecret NUMERIC);

insert into player (id, secret) values ('2c2d4aea-7f38-11e5-a29f-069096004f69', '1118035f8f4160d5606e0c1a5e101ae5')
on conflict(id) do nothing;

CREATE TABLE IF NOT EXISTS PlayerSettings
    (id text PRIMARY KEY,
     faction text,
     name text,
     baseMap json,
     upgrades json,
     deployables json,
     contracts json,
     creature json,
     troops json,
     donatedTroops json,
     inventoryStorage json,
     campaigns json,
     preferences json,
     currentQuest text,
     guildId text,
     unlockedPlanets json,
     hqLevel NUMERIC,
     warMap	json,
     scalars json,
     xp integer,
     protectedUntil integer,
     keepAlive integer);

insert into PlayerSettings (id, upgrades) values ('2c2d4aea-7f38-11e5-a29f-069096004f69', '{}')
on conflict(id) do nothing;

CREATE TABLE IF NOT EXISTS Squads
    (id text PRIMARY KEY,
     faction text,
     name text,
     perks json,
     members json,
     warId text,
     description text,
     icon text,
     openEnrollment NUMERIC,
     minScoreAtEnrollment NUMERIC,
     warSignUpTime NUMERIC);

CREATE TABLE IF NOT EXISTS SquadMembers
    (guildId text,
     playerId text,
     isOfficer NUMERIC,
     isOwner NUMERIC,
     joinDate NUMERIC,
     troopsDonated NUMERIC,
     troopsReceived NUMERIC,
     warParty NUMERIC,
     primary key(guildId, playerId));

CREATE TABLE IF NOT EXISTS "SquadNotifications" (
	"guildId"	TEXT NOT NULL,
	"id"	TEXT NOT NULL,
	"orderNo"	NUMERIC NOT NULL,
	"date"	NUMERIC NOT NULL,
	"playerId"	TEXT,
	"name"	TEXT,
	"squadMessageType"	TEXT,
	"message"	TEXT,
	"squadNotification"	json,
	PRIMARY KEY("id", "guildId")
);

CREATE INDEX IF NOT EXISTS "SquadNotification_idx" ON "SquadNotifications" (
	"guildId"
);

CREATE INDEX IF NOT EXISTS "squadNotification_date_idx" ON "SquadNotifications" (
	"guildId",
	"date"
);

CREATE TABLE IF NOT EXISTS "War" (
	"warId"	TEXT,
	"squadIdA"	TEXT,
	"squadIdB"	TEXT,
	"prepGraceStartTime"	NUMERIC,
	"prepEndTime"	NUMERIC,
	"actionGraceStartTime"	NUMERIC,
	"actionEndTime"	NUMERIC,
	"cooldownEndTime"	NUMERIC,
	processedEndTime NUMERIC,
    "squadAScore"	INTEGER,
    "squadBScore"	INTEGER,
	PRIMARY KEY("warId")
);

CREATE INDEX IF NOT EXISTS "War_Squad1_idx" ON "War" (
	"squadIdA"
);

CREATE INDEX IF NOT EXISTS "War_Squad2_idx" ON "War" (
	"squadIdB"
);

CREATE TABLE IF NOT EXISTS "MatchMake" (
	"guildId"	TEXT,
	"warSignUpTime"	NUMERIC,
	"faction"	NUMERIC,
	participants JSON,
	PRIMARY KEY("guildId")
);

CREATE TABLE IF NOT EXISTS "WarParticipants" (
	"playerId"	TEXT,
	"warId"	TEXT,
	squadId TEXT,
	"warMap"	json,
	"donatedTroops"	json,
	"turns"	INTEGER,
	"attacksWon"	INTEGER,
	"defensesWon"	INTEGER,
	"score"	INTEGER,
	"victoryPoints"	INTEGER,
	attackExpirationDate NUMERIC,
	attackBattleId TEXT,
	defenseExpirationDate NUMERIC,
	defenseBattleId TEXT,
	"defenseRemaining"	INTEGER,
	PRIMARY KEY("playerId","warId")
);

CREATE INDEX IF NOT EXISTS "warDefenseBattleId_idx" ON "WarParticipants" (
	"defenseBattleId"
);

CREATE INDEX IF NOT EXISTS "warAttackBattleId_idx" ON "WarParticipants" (
	"attackBattleId"
);

CREATE TABLE IF NOT EXISTS "WarBattles" (
	"warId"	TEXT,
	"battleId"	TEXT,
	"attackerId"	TEXT,
	"defenderId"	TEXT,
	battleResponse  JSON,
	battleCompleteTime NUMERIC,
	PRIMARY KEY("battleId")
);

CREATE INDEX IF NOT EXISTS "warBattlesBattleId_idx" ON "WarBattles" (
	"battleId"
);

create table IF NOT EXISTS BattlesMaster
(
    battleId      TEXT
        constraint BattlesMaster_pk
            primary key,
    battleType    TEXT,
    playerId      TEXT,
    participantId TEXT
);

create unique index IF NOT EXISTS BattlesMaster_battleId_uindex
    on BattlesMaster (battleId);

create table IF NOT EXISTS DevBases
(
    Id not null
        constraint id
            primary key,
    buildings TEXT,
    hqlevel   INTEGER,
    xp        INTEGER
);

create table IF NOT EXISTS PvpBattleData
(
    battleId                   TEXT
        constraint PvpBattles_pk
            primary key,
    cs                         TEXT,
    _credits                   INTEGER,
    _materials                 INTEGER,
    _contraband                INTEGER,
    _crystals                  INTEGER,
    seededTroopsDeployed       TEXT,
    damagedBuildings           TEXT,
    unarmedTraps               TEXT,
    baseDamagePercent          INTEGER,
    stars                      INTEGER,
    isUserEnded                TEXT,
    planetId                   TEXT,
    attackerId                 TEXT,
    participantId              TEXT,
    battleDate                 LONG,
    potentialMedalGain         INTEGER,
    defenderPotentialMedalGain INTEGER,
    BattleLog                  TEXT,
    ReplayData                 TEXT
);

create table IF NOT EXISTS PvpBattles_ReplayData
(
    BattleId                  TEXT
        constraint PvpBattles_ReplayData_pk
            primary key,
    combatEncounter           TEXT,
    battleActions             TEXT,
    attackerDeploymentData    TEXT,
    defenderDeploymentData    TEXT,
    lootCreditsAvailable      INTEGER,
    lootMaterialsAvailable    INTEGER,
    lootContrabandAvailable   INTEGER,
    lootBuildingCreditsMap    TEXT,
    lootBuildingMaterialsMap  TEXT,
    lootBuildingContrabandMap TEXT,
    battleType                TEXT,
    battleLength              INTEGER,
    lowFPS                    INTEGER,
    lowFPSTime                INTEGER,
    battleVersion             TEXT,
    planetId                  TEXT,
    manifestVersion           TEXT,
    battleAttributes          TEXT,
    victoryConditions         TEXT,
    failureCondition          TEXT,
    donatedTroops             TEXT,
    donatedTroopsAttacker     TEXT,
    champions                 TEXT,
    disabledBuildings         TEXT,
    simSeedA                  INTEGER,
    simSeedB                  INTEGER,
    viewTimePreBattle         TEXT,
    attackerCreatureTraps     TEXT,
    defenderCreatureTraps     TEXT
);
