package hr.danisoka.bulkmailer.app.utils;

import com.google.gson.Gson;
import hr.danisoka.bulkmailer.app.AppConstants;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.models.attempts.AttemptJson;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public final class AttemptUtils {
    
    private static File getSessionFolder(Session session) {
        File attemptFolder = FileUtils.getDirectory(AppConstants.AppSettings.Folders.ATTEMPT_FOLDER);
        String sessionFolderName = String.format("session_%d", session.getId());
        File sessionFolder = FileUtils.getDirectory(new File(attemptFolder, sessionFolderName).getAbsolutePath());
        return sessionFolder;
    }
    
    public static void storeAttempt(Session session, AttemptJson attempt) throws IOException {
        File sessionFolder = getSessionFolder(session);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String attemptName = String.format("%s_attempt.json", sdf.format(attempt.getCreatedAt()));
        File attemptFile = new File(sessionFolder, attemptName);
        FileUtils.writeStringToFile(attemptFile, new Gson().toJson(attempt));
    }
    
    public static int getAttemptCount(Session session) {
        File sessionFolder = getSessionFolder(session);
        int count = sessionFolder.listFiles().length;
        return count;
    }
    
    public static void deleteAllAttempts(Session session) {
        File sessionFolder = getSessionFolder(session);
        FileUtils.deleteFilesRecursive(sessionFolder);
    }
    
    public static List<AttemptJson> getAllAttempts(Session session) throws IOException {
        List<AttemptJson> attempts = new ArrayList<>();
        File sessionFolder = getSessionFolder(session);
        File[] files = sessionFolder.listFiles();
        for(int i = 0; i < files.length; i++) {
            String content = FileUtils.readFileContentAsString(files[i]);
            attempts.add(new Gson().fromJson(content, AttemptJson.class));
        }
        return attempts;
    }
    
}
