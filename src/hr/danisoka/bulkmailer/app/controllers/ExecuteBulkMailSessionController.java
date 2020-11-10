/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.controllers;

import hr.danisoka.bulkmailer.app.contracts.ExecuteSessionContract;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.models.session.BulkEmailData;
import hr.danisoka.bulkmailer.app.models.session.BulkEmailItem;
import hr.danisoka.bulkmailer.app.models.session.MailRecipientData;
import hr.danisoka.bulkmailer.app.models.session.RecipientData;
import hr.danisoka.bulkmailer.app.strategies.BuildingMailDataFactory;
import hr.danisoka.bulkmailer.app.strategies.BuildingMailDataInterface;
import hr.danisoka.bulkmailer.app.utils.FileUtils;
import hr.danisoka.bulkmailer.app.utils.StringUtils;
import hr.danisoka.bulkmailer.app.views.windows.ExecuteBulkMailSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ExecuteBulkMailSessionController implements ExecuteSessionContract.Controller {

    private ExecuteSessionContract.View view;
    private MailLoggerHandler.LoggerErrorListener errorListener;
    
    private Session session;
    private List<MailRecipientData> data;
    private int numberOfTeamMemberDataShown;
    private BulkEmailData bulkEmailData;
    
    
    public ExecuteBulkMailSessionController(ExecuteSessionContract.View view, MailLoggerHandler.LoggerErrorListener errorListener) {
        this.view = view;
        this.errorListener = errorListener;
    }
    
    @Override
    public void processSending(Session session, String mode, String specifiedEmails) {
        this.session = session;   
        prepareDataForSending(specifiedEmails);
        this.view.onBulkMailDataReady(bulkEmailData, false);
    }
    
    @Override
    public void processPreviewing(Session session, String mode, String specifiedEmails) {
        this.session = session;
        prepareDataForSending(specifiedEmails);
        this.view.onBulkMailDataReady(bulkEmailData, true);
        
    }
    
    private void prepareDataForSending(String specifiedEmails) {
        if(this.bulkEmailData == null) {
            List<BulkEmailItem> emailItems = new ArrayList<>();

            mapDataToJavaObjects(specifiedEmails);
            for(MailRecipientData item : this.data) {
                String modifiedHtml = handleTemplateHtml(item);
                String populatedTemplateFileContent = replacePlaceholdersForEmailData(item, modifiedHtml);
                emailItems.add(new BulkEmailItem(item, populatedTemplateFileContent));
            }

            this.bulkEmailData = new BulkEmailData("ERP Sustav: Rad na virtualnom poduzeću", emailItems);
        }
    }
    
    private void mapDataToJavaObjects(String specifiedEmails) {
        try {
            if(data == null) {
                BuildingMailDataInterface strat = BuildingMailDataFactory.getStrategy(session, StringUtils.getItemsFromString(",", specifiedEmails));
                data = strat.buildMailData();   
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecuteBulkMailSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExecuteBulkMailSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String replacePlaceholdersForEmailData(MailRecipientData emailData, String templateFileContent) {
        if(session.hasGroup()) {
            String groupHolder = String.format("%s%s%s", session.getHolderStart(), session.getGroupColumn(), session.getHolderEnd());
            templateFileContent = templateFileContent.replace(groupHolder, emailData.getGroupValue());
        }
        String patternString = String.format("\\Q%s\\E(.+)\\Q%s\\E", session.getHolderStart(), session.getHolderEnd());
        Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE);
        Matcher m  = pattern.matcher(templateFileContent);
        int countHolders = -1;
        int teamMemberCount = -1;
        while(m.find()) {
           countHolders++;
           String placeholder = m.group(0);
           String placeholderKey = m.group(1);
           if(countHolders%numberOfTeamMemberDataShown == 0) {
               teamMemberCount++;
           }
           RecipientData current = emailData.getRecipientData().get(teamMemberCount);
           templateFileContent = templateFileContent.replaceFirst("\\Q" + placeholder + "\\E", current.<String>getValueAsType(placeholderKey));
        }
        return templateFileContent;
    }
    
    private String handleTemplateHtml(MailRecipientData emailData) {
        String templateFileContent = null;
        try {
            templateFileContent = FileUtils.getFileContent(new File(session.getTemplateFilePath()));
            Document doc = Jsoup.parse(templateFileContent);
            Element repetitiveEl = doc.selectFirst("#repetitiveElement");
            learnAboutTeamMember(repetitiveEl);
            Element tableEl = doc.selectFirst("table > tbody");
            for(int i = 1; i < emailData.getRecipientData().size(); i++) {
                tableEl.appendChild(repetitiveEl.clone());
            }
            templateFileContent = doc.html();
        } catch (IOException ex) {
            Logger.getLogger(ExecuteBulkMailSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return templateFileContent;
    }
    
    private void learnAboutTeamMember(Element repetitiveEl) {
        String repetitiveElString = repetitiveEl.html();
        String patternString = String.format("\\Q%s\\E(.+)\\Q%s\\E", session.getHolderStart(), session.getHolderEnd());
        Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE);
        Matcher m  = pattern.matcher(repetitiveElString);
        numberOfTeamMemberDataShown = 0;
        while(m.find()) {
           numberOfTeamMemberDataShown++;
        }
    }
}
