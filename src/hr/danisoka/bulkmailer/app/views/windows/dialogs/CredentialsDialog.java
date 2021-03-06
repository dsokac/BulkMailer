package hr.danisoka.bulkmailer.app.views.windows.dialogs;

import hr.danisoka.bulkmailer.app.contracts.CredentialsWinContract;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CredentialsDialog extends javax.swing.JDialog implements CredentialsWinContract.View{

    public CredentialsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblErrorMessage.setVisible(false);
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

        jSeparator2 = new javax.swing.JSeparator();
        lblDialogInstruction = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnSignIn = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        lblErrorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Korisnički podaci za e-mail");
        setModal(true);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        lblDialogInstruction.setText("<html>Unesite <b>puni e-email</b> i lozinku koje koristite za prijavu na e-mail putem<br/>kojeg želite poslati podatke.<br/><br/>Trenutno je podržan samo FOI  email. Znači unesite <b>domenu @foi.unizg.hr</b>.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(lblDialogInstruction, gridBagConstraints);

        lblUsername.setText("E-mail:  ");
        lblUsername.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblUsername, gridBagConstraints);

        txtUsername.setToolTipText("");
        txtUsername.setMinimumSize(new java.awt.Dimension(200, 20));
        txtUsername.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtUsername, gridBagConstraints);

        lblPassword.setText("Lozinka: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblPassword, gridBagConstraints);

        txtPassword.setMinimumSize(new java.awt.Dimension(200, 20));
        txtPassword.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtPassword, gridBagConstraints);

        btnSignIn.setBackground(new java.awt.Color(0, 255, 0));
        btnSignIn.setText("Prijavi se");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        getContentPane().add(btnSignIn, gridBagConstraints);

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Odustani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(btnCancel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator4, gridBagConstraints);

        lblErrorMessage.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorMessage.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblErrorMessage, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSignIn;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblDialogInstruction;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    private CredentialsWinContract.Controller controller;
    private Color initialBackground;
    private Color initialForeground;
    
    public void setupButtons() {
        CredentialsDialog obj = this;
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obj.setVisible(false);
            }
        });
        
        initialBackground = btnSignIn.getBackground();
        initialForeground = btnSignIn.getForeground();
        
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                obj.controller.storeCredentials(username, password);                
            }
        });
        
        txtUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                manageSignInAccess();
            }

            @Override
            public void focusLost(FocusEvent e) {
                manageSignInAccess();
            }
        });
        
        txtPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                manageSignInAccess();
            }

            @Override
            public void focusLost(FocusEvent e) {
                manageSignInAccess();
            }
        });
        
        txtPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                manageSignInAccess();
            }
        });
        
        txtUsername.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                manageSignInAccess();
            }
        });
    }
    
    public void setController(CredentialsWinContract.Controller controller) {
        this.controller = controller;
    }
    
    private void manageSignInAccess() {
        if(!txtUsername.getText().isEmpty() && !new String(txtPassword.getPassword()).isEmpty()) {
            lblErrorMessage.setText(null);
            lblErrorMessage.setVisible(false);
            manageSignInButton(true);
        } else {
            if(lblErrorMessage.isVisible()) {
                manageSignInButton(false);
            }
        }
    }

    @Override
    public void showFormErrorMessage(String message) {
        txtUsername.setText(null);
        txtPassword.setText(null);
        
        lblErrorMessage.setText(message);
        lblErrorMessage.setVisible(true);
        manageSignInButton(false);
    }

    @Override
    public void credentialsStored() {
        this.setVisible(false);
    }
    
    
    
    private void manageSignInButton(boolean enabled) {
        if(enabled) {
            btnSignIn.setBackground(initialBackground);
            btnSignIn.setForeground(initialForeground);
        } else {
            btnSignIn.setBackground(new Color(240, 240, 240));
            btnSignIn.setForeground(new Color(0, 0, 0));
        }
        btnSignIn.setEnabled(enabled);
    }
    
}
