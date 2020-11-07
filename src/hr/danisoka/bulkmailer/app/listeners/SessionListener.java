package hr.danisoka.bulkmailer.app.listeners;

import hr.danisoka.bulkmailer.app.models.Session;
import java.util.List;

public interface SessionListener {
    void onSessionCreated(Session session);
    void onSessionUpdated(Session session);
    void onSessionDeleted(Session session);
    void onSessionsArrived(List<Session> sessions);
}
