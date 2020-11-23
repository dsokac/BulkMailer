package hr.danisoka.bulkmailer.app.listeners;

public interface ProgressListener {
    void setProgressAction(String actionName, int totalTaskCount);
    void updateProgress(int currentTask);
}
