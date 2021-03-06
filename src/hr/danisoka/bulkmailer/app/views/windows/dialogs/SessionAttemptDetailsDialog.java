package hr.danisoka.bulkmailer.app.views.windows.dialogs;

import hr.danisoka.bulkmailer.app.models.attempts.AttemptJson;
import hr.danisoka.bulkmailer.app.models.attempts.AttemptRecipient;
import java.text.SimpleDateFormat;
import javax.swing.table.TableModel;

public class SessionAttemptDetailsDialog extends javax.swing.JDialog {

    public SessionAttemptDetailsDialog(java.awt.Frame parent, boolean modal, AttemptJson attempt) {
        super(parent, modal);
        initComponents();
        this.attempt = attempt;
        populate();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblCreatedAt = new javax.swing.JLabel();
        lblCreatedAtValue = new javax.swing.JLabel();
        lblCompletedAt = new javax.swing.JLabel();
        lblCompletedAtValue = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        lblDurationValue = new javax.swing.JLabel();
        lblDurationMetric = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblStatusValue = new javax.swing.JLabel();
        lblStatusMessage = new javax.swing.JLabel();
        lblStatusMessageValue = new javax.swing.JLabel();
        lblProcessedItems = new javax.swing.JLabel();
        lblProcessedItemsCount = new javax.swing.JLabel();
        lblFailedItems = new javax.swing.JLabel();
        lblFailedItemsCount = new javax.swing.JLabel();
        lblUnprocessedItems = new javax.swing.JLabel();
        lblUnprocessedItemsCount = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTotalCount = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRecipientData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalji pokušaja");
        setMinimumSize(new java.awt.Dimension(780, 500));
        setPreferredSize(new java.awt.Dimension(780, 500));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        getContentPane().setLayout(layout);

        lblCreatedAt.setText("Kreirano: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblCreatedAt, gridBagConstraints);

        lblCreatedAtValue.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblCreatedAtValue, gridBagConstraints);

        lblCompletedAt.setText("Završeno: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblCompletedAt, gridBagConstraints);

        lblCompletedAtValue.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblCompletedAtValue, gridBagConstraints);

        lblDuration.setText("Trajanje: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblDuration, gridBagConstraints);

        lblDurationValue.setText("##");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        getContentPane().add(lblDurationValue, gridBagConstraints);

        lblDurationMetric.setText("sekundi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        getContentPane().add(lblDurationMetric, gridBagConstraints);

        lblStatus.setText("Status:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblStatus, gridBagConstraints);

        lblStatusValue.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblStatusValue, gridBagConstraints);

        lblStatusMessage.setText("Poruka stanja: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblStatusMessage, gridBagConstraints);

        lblStatusMessageValue.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblStatusMessageValue, gridBagConstraints);

        lblProcessedItems.setText("Broj obrađenih stavki: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblProcessedItems, gridBagConstraints);

        lblProcessedItemsCount.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblProcessedItemsCount, gridBagConstraints);

        lblFailedItems.setText("Broj stavki s greškom: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblFailedItems, gridBagConstraints);

        lblFailedItemsCount.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblFailedItemsCount, gridBagConstraints);

        lblUnprocessedItems.setText("Broj neobrađnih stavki: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblUnprocessedItems, gridBagConstraints);

        lblUnprocessedItemsCount.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblUnprocessedItemsCount, gridBagConstraints);

        lblTotal.setText("Broj stavki sesije: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTotal, gridBagConstraints);

        lblTotalCount.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblTotalCount, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(700, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 200));

        jtblRecipientData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "R. Br.", "E-mail", "Status", "Poruka o statusu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblRecipientData.setMinimumSize(new java.awt.Dimension(500, 64));
        jtblRecipientData.setPreferredSize(new java.awt.Dimension(500, 64));
        jScrollPane1.setViewportView(jtblRecipientData);
        if (jtblRecipientData.getColumnModel().getColumnCount() > 0) {
            jtblRecipientData.getColumnModel().getColumn(0).setResizable(false);
            jtblRecipientData.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtblRecipientData.getColumnModel().getColumn(1).setMinWidth(70);
            jtblRecipientData.getColumnModel().getColumn(1).setPreferredWidth(70);
            jtblRecipientData.getColumnModel().getColumn(2).setMinWidth(80);
            jtblRecipientData.getColumnModel().getColumn(2).setPreferredWidth(80);
            jtblRecipientData.getColumnModel().getColumn(3).setMinWidth(300);
            jtblRecipientData.getColumnModel().getColumn(3).setPreferredWidth(300);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 13;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtblRecipientData;
    private javax.swing.JLabel lblCompletedAt;
    private javax.swing.JLabel lblCompletedAtValue;
    private javax.swing.JLabel lblCreatedAt;
    private javax.swing.JLabel lblCreatedAtValue;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblDurationMetric;
    private javax.swing.JLabel lblDurationValue;
    private javax.swing.JLabel lblFailedItems;
    private javax.swing.JLabel lblFailedItemsCount;
    private javax.swing.JLabel lblProcessedItems;
    private javax.swing.JLabel lblProcessedItemsCount;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusMessage;
    private javax.swing.JLabel lblStatusMessageValue;
    private javax.swing.JLabel lblStatusValue;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalCount;
    private javax.swing.JLabel lblUnprocessedItems;
    private javax.swing.JLabel lblUnprocessedItemsCount;
    // End of variables declaration//GEN-END:variables

    private AttemptJson attempt;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY. HH:mm:ss");
    
    private void populate() {
        lblCreatedAtValue.setText(sdf.format(attempt.getCreatedAt()));
        lblCompletedAtValue.setText(attempt.getCompletedAt() != null ? sdf.format(attempt.getCompletedAt()) : "-");
        lblDurationValue.setText(attempt.getDurationInSeconds() != null ? String.valueOf(attempt.getDurationInSeconds().floatValue()) : "-");
        lblStatusValue.setText(attempt.getStatus());
        lblStatusMessageValue.setText(attempt.getStatusMessage());
        lblProcessedItemsCount.setText(String.valueOf(attempt.getStatistics().getProcessedItems()));
        lblFailedItemsCount.setText(String.valueOf(attempt.getStatistics().getFailedItems()));
        lblUnprocessedItemsCount.setText(String.valueOf(attempt.getStatistics().getUnprocessedItems()));
        lblTotalCount.setText(String.valueOf(attempt.getStatistics().getTotal()));
        if(attempt.getRecipients() != null) {
            TableModel model = jtblRecipientData.getModel();
            for(int i = 0; i < attempt.getRecipients().size(); i++) {
                AttemptRecipient current = attempt.getRecipients().get(i);
                model.setValueAt(i+1, i, 0);
                model.setValueAt(current.getEmail(), i, 1);
                model.setValueAt(current.getStatus(), i, 2);
                model.setValueAt(current.getStatusMessage(), i, 3);
            }
            jtblRecipientData.revalidate();
        }
    }
    
}
