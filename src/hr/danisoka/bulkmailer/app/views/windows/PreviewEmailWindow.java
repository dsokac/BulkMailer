/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.views.windows;

import hr.danisoka.bulkmailer.app.models.session.BulkEmailData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.JDialog;

/**
 *
 * @author Danijel
 */
public class PreviewEmailWindow extends JDialog {

    /**
     * Creates new form PreviewEmailWindow
     */
    public PreviewEmailWindow(BulkEmailData data) {
        initComponents();
        
        jfxPanel = new JFXPanel();
        jpWebViewContainer.add(jfxPanel);
        jfxPanel.setVisible(true);
        jpWebViewContainer.revalidate();
        
        this.data = data;
        setModalityType(ModalityType.APPLICATION_MODAL);
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
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jpWebViewContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));

        java.awt.GridBagLayout jpControlsContainerLayout = new java.awt.GridBagLayout();
        jpControlsContainerLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jpControlsContainerLayout.rowHeights = new int[] {0};
        jpControlsContainer.setLayout(jpControlsContainerLayout);

        btnFirst.setText("Prvi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jpControlsContainer.add(btnFirst, gridBagConstraints);

        btnPrevious.setText("Prethodni");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jpControlsContainer.add(btnPrevious, gridBagConstraints);

        lblCurrent.setText("##");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jpControlsContainer.add(lblCurrent, gridBagConstraints);

        jLabel1.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        jpControlsContainer.add(jLabel1, gridBagConstraints);

        lblTotal.setText("##");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpControlsContainer.add(lblTotal, gridBagConstraints);

        btnNext.setText("Sljedeći");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 0;
        jpControlsContainer.add(btnNext, gridBagConstraints);

        btnLast.setText("Zadnji");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 0;
        jpControlsContainer.add(btnLast, gridBagConstraints);

        jpWebViewContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpWebViewContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpControlsContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpControlsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpWebViewContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpControlsContainer;
    private javax.swing.JPanel jpWebViewContainer;
    private javax.swing.JLabel lblCurrent;
    private javax.swing.JLabel lblTotal;
    // End of variables declaration//GEN-END:variables

    private BulkEmailData data;
    private int current = 1;
    private JFXPanel jfxPanel;
    
    public void loadHtml() {
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
    }
}
