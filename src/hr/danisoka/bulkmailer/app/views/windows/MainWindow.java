/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.views.windows;

import hr.danisoka.bulkmailer.app.views.windows.dialogs.CredentialsDialog;
import hr.danisoka.bulkmailer.app.BulkMailerApplication;
import hr.danisoka.bulkmailer.app.controllers.CredentialsController;
import hr.danisoka.bulkmailer.app.controllers.SessionDataController;
import hr.danisoka.bulkmailer.app.db.AppDatabase;
import hr.danisoka.bulkmailer.app.db.DAOs.impl.SessionDaoImpl;
import hr.danisoka.bulkmailer.app.listeners.AttemptListener;
import hr.danisoka.bulkmailer.app.listeners.SessionListener;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.views.windows.dialogs.SessionDialog;
import hr.danisoka.bulkmailer.app.views.windows.panels.SessionContainerPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MainWindow extends javax.swing.JFrame implements MailLoggerHandler.LoggerErrorListener, SessionListener, AttemptListener{

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        jmiFileChangeCreds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj.handleCredentials();
            }
        });
        jmiNewSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj.handleSessionCreation();
            }
        });
        jmiDeleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj.handleDeletingAll();
            }
        });
        int inc = 20;
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(inc);
        jScrollPane2.getHorizontalScrollBar().setUnitIncrement(inc);
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlSessions = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpSessionList = new javax.swing.JPanel();
        jmbMainBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmFileNew = new javax.swing.JMenu();
        jmiNewSession = new javax.swing.JMenuItem();
        jmiFileChangeCreds = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiDeleteAll = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnlSessions.setBorder(javax.swing.BorderFactory.createTitledBorder("Sesije skupnog e-maila"));
        jpnlSessions.setLayout(new javax.swing.BoxLayout(jpnlSessions, javax.swing.BoxLayout.Y_AXIS));

        jpSessionList.setLayout(new javax.swing.BoxLayout(jpSessionList, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(jpSessionList);

        jpnlSessions.add(jScrollPane2);

        jMenu1.setText("File");

        jmFileNew.setText("Novo");
        jmFileNew.setToolTipText("");

        jmiNewSession.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmiNewSession.setText("Sesija skupnog emaila");
        jmiNewSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewSessionActionPerformed(evt);
            }
        });
        jmFileNew.add(jmiNewSession);

        jMenu1.add(jmFileNew);

        jmiFileChangeCreds.setText("Ažuriraj pristupne podatke");
        jMenu1.add(jmiFileChangeCreds);

        jmbMainBar.add(jMenu1);

        jMenu2.setText("Edit");

        jmiDeleteAll.setText("Obriši sve");
        jMenu2.add(jmiDeleteAll);

        jmbMainBar.add(jMenu2);

        setJMenuBar(jmbMainBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnlSessions, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnlSessions, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNewSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewSessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiNewSessionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow main = new MainWindow();
                main.setVisible(true);
                main.loadSessions();
                main.handleCredentials();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu jmFileNew;
    private javax.swing.JMenuBar jmbMainBar;
    private javax.swing.JMenuItem jmiDeleteAll;
    private javax.swing.JMenuItem jmiFileChangeCreds;
    private javax.swing.JMenuItem jmiNewSession;
    private javax.swing.JPanel jpSessionList;
    private javax.swing.JPanel jpnlSessions;
    // End of variables declaration//GEN-END:variables

    private MainWindow obj = this;       
    private Map<Long, SessionContainerPanel> sessionItems = new HashMap<>();
    
    public void handleCredentials() {
        BulkMailerApplication app = BulkMailerApplication.getInstance();
        CredentialsDialog credDialog = new CredentialsDialog(this, true);
        CredentialsController controller = new CredentialsController(credDialog);
        credDialog.setController(controller);        
        
        credDialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                BulkMailerApplication app = BulkMailerApplication.getInstance();
                if(!app.areCredentialsPresent()) {
                    obj.dispatchEvent(new WindowEvent(obj, WindowEvent.WINDOW_CLOSING));                    
                }
            }                
        });
        
        credDialog.setVisible(true);
    }
    
    private void handleSessionCreation() {
        this.setEnabled(false);
        SessionDialog sessionDialog = new SessionDialog(this, true, this);
        SessionDataController controller = new SessionDataController(sessionDialog);
        controller.setErrorListener(sessionDialog);
        sessionDialog.setController(controller);

        sessionDialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                obj.setEnabled(true);
            }                
        });
        sessionDialog.setVisible(true);
    }

    @Override
    public void onErrorOccurred(Exception ex, String message) {
        JOptionPane.showMessageDialog(this, message, "Greška!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onSessionCreated(Session session) {
        initializeSessionView(session);
    }
    
    @Override
    public void onSessionUpdated(Session session) {
        SessionContainerPanel sessionView = sessionItems.get(session.getId());
        sessionView.updateView(session);
        jpSessionList.revalidate();
    }
    
    @Override
    public void onSessionDeleted(Session session) {
        removeSessionView(session);
    }

    @Override
    public void onSessionsArrived(List<Session> sessions) {
        for(Session session : sessions) {
            initializeSessionView(session);
        }
    }
    
    public void loadSessions() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                         
                AppDatabase db = AppDatabase.getInstance();
                SessionDaoImpl dao = db.sessionDaoImpl;
                List<Session> sessions;
                try {
                    sessions = dao.queryForAll();
                    obj.onSessionsArrived(sessions);
                } catch (SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    obj.onErrorOccurred(ex, ex.getMessage());
                }
            }
        });          
    }
    
    private void initializeSessionView(Session session) {
        SessionContainerPanel sessionContainer = new SessionContainerPanel(this, session, obj);
        sessionContainer.setVisible(true);         
        sessionItems.put(session.getId(), sessionContainer);
        jpSessionList.add(sessionContainer, BorderLayout.NORTH);
        jpSessionList.add(Box.createRigidArea(new Dimension(0, 14)));
        jpSessionList.revalidate();
    }
    
    private void removeSessionView(Session session) {
        SessionContainerPanel target = sessionItems.get(session.getId());
        jpSessionList.remove(target);
        sessionItems.remove(session.getId());
        jpSessionList.revalidate();
    }
    
    private void handleDeletingAll() {
        Object[] croatianOtions = new Object[]{"Da", "Ne"};
        if(JOptionPane.showOptionDialog(this, "Jesi li siguran da želiš obrisati sve sesije?", "Upozorenje", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, croatianOtions, croatianOtions[0]) == JOptionPane.YES_OPTION){
            SessionDataController controller = new SessionDataController(null);
            controller.setErrorListener(this);
            controller.deleteAll();
            JOptionPane.showMessageDialog(this, "Sve sesije su uspješno izbrisane.", "Obavijest", JOptionPane.INFORMATION_MESSAGE);
            Iterator<Long> iter = sessionItems.keySet().iterator();
            while(iter.hasNext()) {
                Long key = iter.next();
                SessionContainerPanel panel = sessionItems.get(key);
                jpSessionList.remove(panel);
                jpSessionList.revalidate();
            }
            sessionItems.clear();
        }
    }

    @Override
    public void onAttemptCreated(Session session) {
        this.sessionItems.get(session.getId()).updateAttemptCount();
    }
}
