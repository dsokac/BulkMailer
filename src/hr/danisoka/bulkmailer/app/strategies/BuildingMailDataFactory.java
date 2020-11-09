package hr.danisoka.bulkmailer.app.strategies;

import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.strategies.impl.SingleBuildingMailData;
import hr.danisoka.bulkmailer.app.strategies.impl.TeamBuildingMailData;
import java.util.List;

public final class BuildingMailDataFactory {
    
    public static BuildingMailDataInterface getStrategy(Session session, List<String> emails) {
        return session.hasGroup() ? new TeamBuildingMailData(session, emails) : new SingleBuildingMailData(session, emails);
    }
    
}
