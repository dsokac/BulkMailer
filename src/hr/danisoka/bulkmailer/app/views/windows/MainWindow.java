/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.views.windows;

import hr.danisoka.bulkmailer.app.BulkMailerApplication;
import hr.danisoka.bulkmailer.app.controllers.CredentialsController;
import hr.danisoka.bulkmailer.app.controllers.NewSessionController;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Danijel
 */
public class MainWindow extends javax.swing.JFrame implements MailLoggerHandler.LoggerErrorListener{

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
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmbMainBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmFileNew = new javax.swing.JMenu();
        jmiNewSession = new javax.swing.JMenuItem();
        jmiFileChangeCreds = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jmbMainBar.add(jMenu2);

        setJMenuBar(jmbMainBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
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
                main.handleCredentials();
                main.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jmFileNew;
    private javax.swing.JMenuBar jmbMainBar;
    private javax.swing.JMenuItem jmiFileChangeCreds;
    private javax.swing.JMenuItem jmiNewSession;
    // End of variables declaration//GEN-END:variables

    private MainWindow obj = this;       
    
    public void handleCredentials() {
        this.setEnabled(false);
        BulkMailerApplication app = BulkMailerApplication.getInstance();
        CredentialsWindow credWin = new CredentialsWindow();
        CredentialsController controller = new CredentialsController(credWin);
        credWin.setController(controller);
        credWin.setVisible(true);

            
        credWin.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                BulkMailerApplication app = BulkMailerApplication.getInstance();
                if(!app.areCredentialsPresent()) {
                    obj.dispatchEvent(new WindowEvent(obj, WindowEvent.WINDOW_CLOSING));
                }
                obj.setEnabled(true);
            }                
        });
    }
    
    private void handleSessionCreation() {
        this.setEnabled(false);
        NewSessionWindow newSession = new NewSessionWindow();
        NewSessionController controller = new NewSessionController(newSession, this);
        newSession.setController(controller);
        newSession.setVisible(true);
        newSession.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                obj.setEnabled(true);
            }                
        });
    }

    @Override
    public void onErrorOccurred(Exception ex, String message) {
        JOptionPane.showMessageDialog(this, message, "Greška!", JOptionPane.ERROR_MESSAGE);
    }
}
