package hr.danisoka.bulkmailer.app.listeners;

import hr.danisoka.bulkmailer.app.models.Session;

public interface AttemptListener {
    void onAttemptCreated(Session session);
}
