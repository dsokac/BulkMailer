package hr.danisoka.bulkmailer.app.models.comboboxes;

import hr.danisoka.bulkmailer.app.utils.FileUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

public class FileStringComboboxModel extends DefaultComboBoxModel<String>{
    
    private Map<String, File> fileMap = new HashMap<>();
    
    public FileStringComboboxModel(File[] files, String[] names) {        
        super(names);
        for(int i = 0; i < files.length; i++) {
            fileMap.put(names[i], files[i]);
        }
    }
    
    public static String[] convertToStringArray(File[] files) {
        String[] names = new String[files.length];
        for(int i = 0; i < files.length; i++) {
            names[i] = FileUtils.extractFileName(files[i]);
        }
        return names;
    }
    
    public void addElementFile(File file) {
        String name = FileUtils.extractFileName(file);
        fileMap.put(name, file);
        addElement(name);
    }
    
    public File getElementFileAt(int index) {
        String name = getElementAt(index);
        return fileMap.get(name);
    }
    
    public File getSelectedFileItem() {
        String name = getSelectedItem().toString();
        return fileMap.get(name);
    }
    
    public void insertFileItemAt(File file, int index) {
        String name = FileUtils.extractFileName(file);
        fileMap.put(name, file);
        insertElementAt(name, index);
    }

    @Override
    public void removeAllElements() {
        super.removeAllElements(); 
        fileMap.clear();
    }

    @Override
    public void removeElement(Object anObject) {
        super.removeElement(anObject); 
        fileMap.remove(anObject.toString());
    }

    @Override
    public void removeElementAt(int index) {
        String name = getElementAt(index);
        fileMap.remove(name);
        super.removeElementAt(index);      
    }
    
    
   
}
