package hr.danisoka.bulkmailer.app.views.windows.dialogs;

import hr.danisoka.bulkmailer.app.models.session.BulkEmailData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class PreviewEmailDialog extends javax.swing.JDialog {

    public PreviewEmailDialog(java.awt.Frame parent, boolean modal, BulkEmailData data) {
        super(parent, modal);
        initComponents();
        
        jfxPanel = new JFXPanel();
        jpWebViewContainer.add(jfxPanel);
        jfxPanel.setVisible(true);
        jpWebViewContainer.revalidate();
        this.data = data;
        setupButtons();
        setupPages();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpControlsContainer = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        lblCurrent = new javax.swing.JLabel();
        lblSlash = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jpWebViewContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jpControlsContainer.setMinimumSize(new java.awt.Dimension(1000, 50));
        jpControlsContainer.setPreferredSize(new java.awt.Dimension(1000, 50));
        java.awt.GridBagLayout jpControlsContainerLayout = new java.awt.GridBagLayout();
        jpControlsContainerLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jpControlsContainerLayout.rowHeights = new int[] {0, 10, 0, 10, 0};
        jpControlsContainer.setLayout(jpControlsContainerLayout);

        btnFirst.setText("Prvi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jpControlsContainer.add(btnFirst, gridBagConstraints);

        btnPrevious.setText("Prethodni");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jpControlsContainer.add(btnPrevious, gridBagConstraints);

        lblCurrent.setText("##");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        jpControlsContainer.add(lblCurrent, gridBagConstraints);

        lblSlash.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        jpControlsContainer.add(lblSlash, gridBagConstraints);

        lblTotal.setText("##");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        jpControlsContainer.add(lblTotal, gridBagConstraints);

        btnNext.setText("Sljedeći");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 2;
        jpControlsContainer.add(btnNext, gridBagConstraints);

        btnLast.setText("Zadnji");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 2;
        jpControlsContainer.add(btnLast, gridBagConstraints);

        getContentPane().add(jpControlsContainer);

        jpWebViewContainer.setLayout(new javax.swing.BoxLayout(jpWebViewContainer, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jpWebViewContainer);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JPanel jpControlsContainer;
    private javax.swing.JPanel jpWebViewContainer;
    private javax.swing.JLabel lblCurrent;
    private javax.swing.JLabel lblSlash;
    private javax.swing.JLabel lblTotal;
    // End of variables declaration//GEN-END:variables

    private BulkEmailData data;
    private int current = 1;
    private JFXPanel jfxPanel;
    
    public void loadHtml() {
        Platform.setImplicitExit(false);
        // Creation of scene and future interactions with JFXPanel
        // should take place on the JavaFX Application Thread
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().loadContent(data.getEmailItems().get(current - 1).getParsedContent());
            jfxPanel.revalidate();
        });
    }
    
    private void setupButtons() {
        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePage(1);
                loadHtml();
            }
        });
        
        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newPage = current - 1;
                if(newPage < 1) {
                    newPage = 1;
                }
                updatePage(newPage);
                loadHtml();
            }
        });
        
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newPage = current + 1;
                if(newPage > data.getEmailItems().size()) {
                    newPage = data.getEmailItems().size();
                }
                updatePage(newPage);
                loadHtml();
            }
        });
        
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePage(data.getEmailItems().size());
                loadHtml();
            }
        });
    }
    
    private void setupPages() {
        updatePage(current);
        lblTotal.setText(String.valueOf(this.data.getEmailItems().size()));
    }
    
    private void updatePage(int current) {
        this.current = current;
        lblCurrent.setText(String.valueOf(current));
        
        if(current == 1) {
            btnFirst.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        } else if(current > 1 && current < data.getEmailItems().size()) {
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        } else if(current == data.getEmailItems().size()) {
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        }
        
        if(current == 1 && data.getEmailItems().size() == current) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        } 
    }        
}