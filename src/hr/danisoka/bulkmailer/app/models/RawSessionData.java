package hr.danisoka.bulkmailer.app.models;

import java.io.File;

public class RawSessionData {
    
    private String name;
    private File dataFile;
    private String dataFileName;
    private String emailColumn;
    private boolean grouped;
    private String groupColumn;
    private File templateFile;
    private String templateFileName;
    private String holderStart;
    private String holderEnd;
    private boolean dataUploaded;
    private boolean templateUploaded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getDataFile() {
        return dataFile;
    }

    public void setDataFile(File dataFile, String dataFileName) {
        this.dataFile = dataFile;
        this.dataFileName = dataFileName;
    }

    public String getDataFileName() {
        return dataFileName;
    }

    public String getEmailColumn() {
        return emailColumn;
    }

    public void setEmailColumn(String emailColumn) {
        this.emailColumn = emailColumn;
    }

    public String getGroupColumn() {
        return groupColumn;
    }

    public void setGroupColumn(String groupColumn) {
        this.groupColumn = groupColumn;
    }

    public File getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(File templateFile, String templateFileName) {
        this.templateFile = templateFile;
        this.templateFileName = templateFileName;
    }

    public String getHolderStart() {
        return holderStart;
    }

    public void setHolder(String holderStart, String holderEnd) {
        this.holderStart = holderStart;
        this.holderEnd = holderEnd;
    }

    public String getHolderEnd() {
        return holderEnd;
    }

    public boolean isGrouped() {
        return grouped;
    }

    public void setGrouped(boolean grouped) {
        this.grouped = grouped;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }   

    public boolean isDataUploaded() {
        return dataUploaded;
    }

    public void setDataUploaded(boolean dataUploaded) {
        this.dataUploaded = dataUploaded;
    }

    public boolean isTemplateUploaded() {
        return templateUploaded;
    }

    public void setTemplateUploaded(boolean templateUploaded) {
        this.templateUploaded = templateUploaded;
    }

        
    
}
