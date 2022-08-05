package swcnoops.server.session.research;

import swcnoops.server.game.*;
import swcnoops.server.session.PlayerMapItems;
import swcnoops.server.session.PlayerSession;
import swcnoops.server.session.map.MapItem;

public class OffenseLabFactory {
    public OffenseLab createForPlayer(PlayerSession playerSession) {
        OffenseLab offenseLab = this.createForMap(playerSession);
        return offenseLab;
    }

    private OffenseLab createForMap(PlayerSession playerSession) {
        OffenseLab offenseLab = null;
        PlayerMapItems playerMapItems = playerSession.getPlayerMapItems();
        for (MapItem mapItem : playerMapItems.getMapItems()) {
            BuildingData buildingData = mapItem.getBuildingData();
            if (buildingData != null && buildingData.getType() == BuildingType.troop_research) {
                offenseLab = createOffenseLab(playerSession, mapItem);
            }
        }
        return offenseLab;
    }

    static public OffenseLab createOffenseLab(PlayerSession playerSession, MapItem mapItem) {
        return (new OffenseLabImpl(playerSession, mapItem.getBuilding(), mapItem.getBuildingData()));
    }
}
