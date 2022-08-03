package swcnoops.server.commands;

import swcnoops.server.Config;
import swcnoops.server.ServiceFactory;
import swcnoops.server.commands.player.PlayerPvpGetNextTarget;
import swcnoops.server.commands.player.response.PlayerPvpGetNextTargetCommandResult;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PlayerPvpGetNextTargetTest {
    static {
        ServiceFactory.instance(new Config());
        ServiceFactory.instance().getPlayerDatasource().initOnStartup();
        ServiceFactory.instance().getGameDataManager().initOnStartup();
    }

    @Test
    public void testNextLayout() throws Exception {
        PlayerPvpGetNextTarget playerPvpGetNextTarget = new PlayerPvpGetNextTarget();
        PlayerPvpGetNextTargetCommandResult response = playerPvpGetNextTarget.execute(null, 1);
        assertNotNull(response);
    }
}
