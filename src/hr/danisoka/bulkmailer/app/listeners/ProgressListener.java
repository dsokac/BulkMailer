package hr.danisoka.bulkmailer.app.listeners;

public interface ProgressListener {
    void setProgressAction(String actionName, int totalTaskCount, boolean indeterminate);
    void updateProgress(int currentTask);
}
