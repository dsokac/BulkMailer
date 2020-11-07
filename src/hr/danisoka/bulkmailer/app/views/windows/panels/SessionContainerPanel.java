/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.views.windows.panels;

import hr.danisoka.bulkmailer.app.controllers.SessionDataController;
import hr.danisoka.bulkmailer.app.listeners.SessionListener;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.views.windows.SessionWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class SessionContainerPanel extends javax.swing.JPanel {

    /**
     * Creates new form SessionContainerPanel
     */
    public SessionContainerPanel(Session session, SessionListener listener) {
        initComponents();
        this.listener = listener;
        this.session = session;        
        populateView();
        setupButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblSessionName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jcboxDataFile = new javax.swing.JCheckBox();
        jcboxTemplateFile = new javax.swing.JCheckBox();
        jcboxGroup = new javax.swing.JCheckBox();
        lblAttempt = new javax.swing.JLabel();
        lblAttemptValue = new javax.swing.JLabel();
        lblCreatedAt = new javax.swing.JLabel();
        lblCreatedAtValue = new javax.swing.JLabel();
        btnStartSession = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnPreview = new javax.swing.JButton();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        setMinimumSize(new java.awt.Dimension(686, 160));
        setPreferredSize(new java.awt.Dimension(686, 160));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        layout.rowHeights = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        setLayout(layout);

        lblSessionName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSessionName.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(lblSessionName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jSeparator1, gridBagConstraints);

        jcboxDataFile.setText("Datoteka podataka studenata");
        jcboxDataFile.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        add(jcboxDataFile, gridBagConstraints);

        jcboxTemplateFile.setText("Datoteka predloška e-maila");
        jcboxTemplateFile.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        add(jcboxTemplateFile, gridBagConstraints);

        jcboxGroup.setText("Slanje u timovima");
        jcboxGroup.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        add(jcboxGroup, gridBagConstraints);

        lblAttempt.setText("Broj slanja:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(lblAttempt, gridBagConstraints);

        lblAttemptValue.setText("##");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(lblAttemptValue, gridBagConstraints);

        lblCreatedAt.setText("Kreirano:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(lblCreatedAt, gridBagConstraints);

        lblCreatedAtValue.setText("12.11.2020. 14:30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(lblCreatedAtValue, gridBagConstraints);

        btnStartSession.setBackground(new java.awt.Color(204, 255, 204));
        btnStartSession.setText("Šalji skupni e-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        add(btnStartSession, gridBagConstraints);

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Obriši sesiju");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(btnDelete, gridBagConstraints);

        btnUpdate.setText("Ažuriraj sesiju");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(btnUpdate, gridBagConstraints);

        btnReports.setText("Otvori izvješća");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(btnReports, gridBagConstraints);

        btnPreview.setText("Prikaži informacije");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        add(btnPreview, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPreview;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnStartSession;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox jcboxDataFile;
    private javax.swing.JCheckBox jcboxGroup;
    private javax.swing.JCheckBox jcboxTemplateFile;
    private javax.swing.JLabel lblAttempt;
    private javax.swing.JLabel lblAttemptValue;
    private javax.swing.JLabel lblCreatedAt;
    private javax.swing.JLabel lblCreatedAtValue;
    private javax.swing.JLabel lblSessionName;
    // End of variables declaration//GEN-END:variables

    private Session session;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY. HH:mm");
    private SessionListener listener;
    
    private void populateView() {
        lblSessionName.setText(session.getName());
        jcboxDataFile.setSelected(session.getDataFilePath() != null && !session.getDataFilePath().isEmpty());
        jcboxTemplateFile.setSelected(session.getTemplateFilePath()!= null && !session.getTemplateFilePath().isEmpty());
        jcboxGroup.setSelected(session.hasGroup());
        lblAttemptValue.setText(session.getAttempts() != null ? String.valueOf(session.getAttempts().size()) : "-");
        lblCreatedAtValue.setText(sdf.format(session.getCreatedAt()));
    }
    
    public void updateView(Session session) {
        this.session = session;
        populateView();
    }
    
    private void setupButtons() {
        btnStartSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBulkMailSession();
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBulkMailSession();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBulkMailSession();
            }
        });
        
        btnReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBulkMailSessionReports();
            }
        });
        
        btnPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBulkMailSessionInfo();
            }
        });
    }
    
    private void startBulkMailSession() {
        
    }
    
    private void updateBulkMailSession() {
        SessionWindow sessionWin = new SessionWindow(session, listener);
        SessionDataController controller = new SessionDataController(sessionWin);
        controller.setErrorListener(sessionWin);
        sessionWin.setController(controller);
        sessionWin.populateValues();
        sessionWin.setVisible(true);
    }
    
    private void deleteBulkMailSession() {
        if(JOptionPane.showConfirmDialog(this, String.format("Jesi li siguran da želiš obrisati sesiju '%s'?", session.getName()), "Upozorenje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
            SessionContainerPanel obj = this;
            SessionDataController controller = new SessionDataController(null);
            controller.setErrorListener(new MailLoggerHandler.LoggerErrorListener() {
                @Override
                public void onErrorOccurred(Exception ex, String message) {
                    JOptionPane.showMessageDialog(obj, message, "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            });
            if(controller.deleteSession(session, false)) {
                JOptionPane.showMessageDialog(this, String.format("Sesija '%s' je uspješno izbrisana.", session.getName()), "Obavijest", JOptionPane.INFORMATION_MESSAGE);
                listener.onSessionDeleted(session);
            } else {
                JOptionPane.showMessageDialog(this, String.format("Sesija '%s' nije izbrisana.", session.getName()), "Upozorenje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void showBulkMailSessionReports() {
        
    }
    
    private void showBulkMailSessionInfo() {
        
    }
}
