/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.danisoka.bulkmailer.app.views.windows;

import hr.danisoka.bulkmailer.app.AppConstants;
import hr.danisoka.bulkmailer.app.contracts.NewSessionWinContract;
import hr.danisoka.bulkmailer.app.listeners.SessionListener;
import hr.danisoka.bulkmailer.app.loggers.MailLoggerHandler;
import hr.danisoka.bulkmailer.app.models.RawSessionData;
import hr.danisoka.bulkmailer.app.models.Session;
import hr.danisoka.bulkmailer.app.models.comboboxes.FileStringComboboxModel;
import hr.danisoka.bulkmailer.app.utils.FileUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Danijel
 */
public class SessionWindow extends javax.swing.JFrame implements NewSessionWinContract.View, MailLoggerHandler.LoggerErrorListener {

    /**
     * Creates new form NewSessionWindow
     */
    public SessionWindow(SessionListener listener) {
        initComponents();
        connectHolderTextboxesWithPreview();
        setupButtons();
        setupComboboxes();
        this.listener = listener;
    }
    
    public SessionWindow(Session session, SessionListener listener) {
        initComponents();
        this.session = session;
        connectHolderTextboxesWithPreview();
        setupButtons();
        setupComboboxes();
        this.listener = listener;        
        btnSave.setText("Spremi");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnDataUpload = new javax.swing.JButton();
        lblStudentDataFileName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblEmailColumn = new javax.swing.JLabel();
        jcbxEmailColumn = new javax.swing.JComboBox<>();
        lblGroupIndicator = new javax.swing.JLabel();
        jtbtnGroupIndicator = new javax.swing.JToggleButton();
        lblGroupColumn = new javax.swing.JLabel();
        jcbxGroupColumn = new javax.swing.JComboBox<>();
        btnTemplateUpload = new javax.swing.JButton();
        lblTemplateFileName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblHolderStart = new javax.swing.JLabel();
        txtHolderStart = new javax.swing.JTextField();
        lblHolderEnd = new javax.swing.JLabel();
        txtHolderEnd = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblExampleHolderStart = new javax.swing.JLabel();
        lblExampleHolderName = new javax.swing.JLabel();
        lblExampleHolderEnd = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jcbxStudentFile = new javax.swing.JComboBox<>();
        jcbxTemplateFiles = new javax.swing.JComboBox<>();

        setTitle("Sesija skupnog e-maila");
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.POPUP);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        layout.rowHeights = new int[] {0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0};
        getContentPane().setLayout(layout);

        btnDataUpload.setText("Upload podataka studenata");
        btnDataUpload.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        getContentPane().add(btnDataUpload, gridBagConstraints);

        lblStudentDataFileName.setText("Datoteka studenata:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        getContentPane().add(lblStudentDataFileName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator1, gridBagConstraints);

        lblEmailColumn.setText("E-mail pozicija:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblEmailColumn, gridBagConstraints);

        jcbxEmailColumn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nije odabrano" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jcbxEmailColumn, gridBagConstraints);

        lblGroupIndicator.setText("Timovi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblGroupIndicator, gridBagConstraints);

        jtbtnGroupIndicator.setText("NE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jtbtnGroupIndicator, gridBagConstraints);

        lblGroupColumn.setText("Stupac za tim:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblGroupColumn, gridBagConstraints);

        jcbxGroupColumn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nije odabrano" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jcbxGroupColumn, gridBagConstraints);

        btnTemplateUpload.setText("Upload predloška e-maila");
        btnTemplateUpload.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 20;
        getContentPane().add(btnTemplateUpload, gridBagConstraints);

        lblTemplateFileName.setText("Predložak e-maila:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 20;
        getContentPane().add(lblTemplateFileName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator2, gridBagConstraints);

        lblHolderStart.setText("Početak placeholdera: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblHolderStart, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtHolderStart, gridBagConstraints);

        lblHolderEnd.setText("Kraj placeholdera: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(lblHolderEnd, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtHolderEnd, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), java.awt.Color.darkGray));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 4, 0, 4, 0};
        jPanel1Layout.rowHeights = new int[] {0};
        jPanel1.setLayout(jPanel1Layout);

        lblExampleHolderStart.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblExampleHolderStart.setText("@@");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblExampleHolderStart, gridBagConstraints);

        lblExampleHolderName.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lblExampleHolderName.setText("Poduzece");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblExampleHolderName, gridBagConstraints);

        lblExampleHolderEnd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblExampleHolderEnd.setText("@@");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblExampleHolderEnd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        btnSave.setText("Kreiraj");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(btnSave, gridBagConstraints);

        btnCancel.setText("Odustani");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(btnCancel, gridBagConstraints);

        jcbxStudentFile.setEditable(true);
        jcbxStudentFile.setToolTipText("");
        jcbxStudentFile.setMinimumSize(new java.awt.Dimension(300, 20));
        jcbxStudentFile.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        getContentPane().add(jcbxStudentFile, gridBagConstraints);

        jcbxTemplateFiles.setEditable(true);
        jcbxTemplateFiles.setMinimumSize(new java.awt.Dimension(300, 20));
        jcbxTemplateFiles.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        getContentPane().add(jcbxTemplateFiles, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDataUpload;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTemplateUpload;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jcbxEmailColumn;
    private javax.swing.JComboBox<String> jcbxGroupColumn;
    private javax.swing.JComboBox<String> jcbxStudentFile;
    private javax.swing.JComboBox<String> jcbxTemplateFiles;
    private javax.swing.JToggleButton jtbtnGroupIndicator;
    private javax.swing.JLabel lblEmailColumn;
    private javax.swing.JLabel lblExampleHolderEnd;
    private javax.swing.JLabel lblExampleHolderName;
    private javax.swing.JLabel lblExampleHolderStart;
    private javax.swing.JLabel lblGroupColumn;
    private javax.swing.JLabel lblGroupIndicator;
    private javax.swing.JLabel lblHolderEnd;
    private javax.swing.JLabel lblHolderStart;
    private javax.swing.JLabel lblStudentDataFileName;
    private javax.swing.JLabel lblTemplateFileName;
    private javax.swing.JTextField txtHolderEnd;
    private javax.swing.JTextField txtHolderStart;
    // End of variables declaration//GEN-END:variables

    private File csvFile;
    private File templateFile;
    private NewSessionWinContract.Controller controller;
    private List<String> studentsDataHeaders;
    private SessionListener listener;
    private boolean dataUploaded = false;
    private boolean templateUploaded = false;
    private Session session;
    private SessionWindow obj = this;
    
    public void setController(NewSessionWinContract.Controller controller) {
        this.controller = controller;
    }
    
    public void populateValues() {
        jcbxStudentFile.setSelectedItem(FileUtils.extractFileName(new File(session.getDataFilePath())));
        jcbxTemplateFiles.setSelectedItem(FileUtils.extractFileName(new File(session.getTemplateFilePath())));
        jcbxEmailColumn.setSelectedItem(session.getEmailColumn());
        jcbxGroupColumn.setSelectedItem(session.getGroupColumn());
        jtbtnGroupIndicator.setSelected(session.hasGroup());
        txtHolderStart.setText(session.getHolderStart());
        txtHolderEnd.setText(session.getHolderEnd());
    }
    
    private void connectHolderTextboxesWithPreview() {
        lblExampleHolderStart.setText(txtHolderStart.getText());
        lblExampleHolderEnd.setText(txtHolderEnd.getText());
        
        txtHolderStart.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String value = txtHolderStart.getText();
                lblExampleHolderStart.setText(value);
            }            
        });
        
        txtHolderEnd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String value = txtHolderEnd.getText();
                lblExampleHolderEnd.setText(value);
            }
        });
    }
    
    private void handleToggleButton(boolean value) {
        jtbtnGroupIndicator.setSelected(value);
        jtbtnGroupIndicator.setText(value ? "DA" :  "NE");
        if(!value && jcbxGroupColumn.getSelectedIndex() != 0) {
            jcbxGroupColumn.setSelectedIndex(0);
        }
    }
    
    private void setupButtons() {
        jtbtnGroupIndicator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean value = jtbtnGroupIndicator.isSelected();
                handleToggleButton(value);
            }
        });
        
        btnDataUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCsvDataUpload();
            }
        });
        
        btnTemplateUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTemplateUpload();
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancelDialog();
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveDialog();
            }
        });
    }
    
    private void setupComboboxes() {
        populateCombobox(jcbxStudentFile, AppConstants.AppSettings.Folders.CSV_FOLDER);
        populateCombobox(jcbxTemplateFiles, AppConstants.AppSettings.Folders.TEMPLATES_FOLDER);
        jcbxStudentFile.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                FileStringComboboxModel model = (FileStringComboboxModel)jcbxStudentFile.getModel();
                File csv = model.getSelectedFileItem();
                
                studentsDataHeaders = controller.fetchHeadersFromStudentsData(csv);
                handleCombobox(jcbxEmailColumn, studentsDataHeaders, "Nije odabrano");
                handleCombobox(jcbxGroupColumn, studentsDataHeaders, "Nije odabrano");
                
                obj.controller.analyzeStudentData(csv);
            }
        });
        jcbxTemplateFiles.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                FileStringComboboxModel model = (FileStringComboboxModel)jcbxTemplateFiles.getModel();
                File template = model.getSelectedFileItem();
                obj.controller.analyzeTemplate(template);
            }
        });
    }
    
    private void handleCsvDataUpload() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV filter", "csv");
        chooser.setFileFilter(filter);
        chooser.setLocale(Locale.forLanguageTag("HR"));
        int outcome = chooser.showDialog(this, null);
        if(outcome == JFileChooser.APPROVE_OPTION) {
            csvFile = chooser.getSelectedFile();
            
            FileStringComboboxModel model = (FileStringComboboxModel)jcbxStudentFile.getModel();
            model.insertFileItemAt(csvFile, 0);
            jcbxStudentFile.setModel(model);
            jcbxStudentFile.setSelectedIndex(0);
            
            studentsDataHeaders = controller.fetchHeadersFromStudentsData(csvFile);
            handleCombobox(jcbxEmailColumn, studentsDataHeaders, "Nije odabrano");
            handleCombobox(jcbxGroupColumn, studentsDataHeaders, "Nije odabrano");
            
            controller.analyzeStudentData(csvFile);
            dataUploaded = true;
        }
    }
    
    private void handleTemplateUpload() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML filter", "html", "xhtml", "htm");
        chooser.setFileFilter(filter);
        chooser.setLocale(Locale.forLanguageTag("HR"));
        int outcome = chooser.showDialog(this, null);
        if(outcome == JFileChooser.APPROVE_OPTION) {
            templateFile = chooser.getSelectedFile();
            
            FileStringComboboxModel model = (FileStringComboboxModel)jcbxTemplateFiles.getModel();
            model.insertFileItemAt(templateFile, 0);
            jcbxTemplateFiles.setModel(model);
            jcbxTemplateFiles.setSelectedIndex(0);
            
            controller.analyzeTemplate(templateFile);
            templateUploaded = true;
        }
    } 
    
    private void handleCancelDialog() {
        this.setVisible(false);
    }
    
    private void handleSaveDialog() {
        FileStringComboboxModel studentFileModel = (FileStringComboboxModel)jcbxStudentFile.getModel();
        FileStringComboboxModel templateFileModel = (FileStringComboboxModel)jcbxTemplateFiles.getModel();
        
        RawSessionData sessionData = new RawSessionData();
        sessionData.setName(null); //TODO implementirati
        sessionData.setDataFile(csvFile == null ? studentFileModel.getSelectedFileItem() : csvFile, jcbxStudentFile.getSelectedItem().toString());
        sessionData.setEmailColumn(jcbxEmailColumn.getSelectedIndex() == 0 ? null : jcbxEmailColumn.getSelectedItem().toString());
        sessionData.setGrouped(jtbtnGroupIndicator.isSelected());
        sessionData.setGroupColumn(jcbxGroupColumn.getSelectedIndex() == 0 ? null : jcbxGroupColumn.getSelectedItem().toString());
        sessionData.setTemplateFile(templateFile== null ? templateFileModel.getSelectedFileItem() : templateFile, jcbxTemplateFiles.getSelectedItem().toString());
        sessionData.setHolder(txtHolderStart.getText(), txtHolderEnd.getText());
        sessionData.setDataUploaded(dataUploaded);
        sessionData.setTemplateUploaded(templateUploaded);
        controller.createSession(sessionData);
    }
    
    private void handleCombobox(JComboBox cbx, List<String> items, String initialItem) {
        cbx.removeAllItems();
        if(initialItem != null) {
            cbx.addItem(initialItem);
        }
        for(String item : items) {
            cbx.addItem(item);
        }
    }

    @Override
    public void updateEmailColumnCombobox(String selectedValue) {
        jcbxEmailColumn.setSelectedItem(selectedValue);
    }

    @Override
    public void updateGroupColumnCombobox(String selectedValue) {
        jcbxGroupColumn.setSelectedItem(selectedValue);
    }

    @Override
    public void updateGroupIndicator(boolean value) {
        handleToggleButton(value);
    }

    @Override
    public void updateHolderStart(String value) {
        txtHolderStart.setText(value);
        lblExampleHolderStart.setText(txtHolderStart.getText());
    }

    @Override
    public void updateHolderEnd(String value) {
        txtHolderEnd.setText(value);
        lblExampleHolderEnd.setText(txtHolderEnd.getText());
    }

    @Override
    public void sessionCreated(Session session) {
        if(listener != null) {
            listener.onSessionCreated(session);
        }
        JOptionPane.showMessageDialog(this, "Nova sesija za skupni e-mail je kreirana.", "Obavijest", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
    }

    @Override
    public void onErrorOccurred(Exception ex, String message) {
        JOptionPane.showMessageDialog(this, message, "Greška!", JOptionPane.ERROR_MESSAGE);
    }
    
    private void populateCombobox(JComboBox cmbox, String dirPath) {
        List<String> items = new ArrayList<>();
        File dir = FileUtils.getDirectory(dirPath);
        File[] dirContent = dir.listFiles();
        
        ComboBoxModel<String> model = new FileStringComboboxModel(dirContent, FileStringComboboxModel.convertToStringArray(dirContent));
        cmbox.setModel(model);
        cmbox.setSelectedIndex(-1);
    }
    
}
