package hr.danisoka.bulkmailer.app.models.session;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class RecipientData {
    private Dictionary<String, Object> data = new Hashtable<>();
    
    public RecipientData() {}
    
    public void setValue(String key, Object value) {
        data.put(key, value);
    }
    
    public Object getValue(String key) {
        return data.get(key);
    }
    
    public <T> T getValueAsType(String key) {
        return (T) getValue(key);
    }
    
    public void clear() {
        this.data = new Hashtable<>();
    }
    
    public static RecipientData convertFrom(List<String> headers, List<String> data) {
        RecipientData obj = new RecipientData();
        for(int i = 0; i < headers.size(); i++) {
            String key = headers.get(i);
            Object value = data.get(i);
            obj.setValue(key, value);
        }
        return obj;
    }
}
