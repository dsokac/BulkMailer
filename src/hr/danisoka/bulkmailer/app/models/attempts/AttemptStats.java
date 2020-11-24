package hr.danisoka.bulkmailer.app.models.attempts;

public class AttemptStats {
    private Integer processedItems = 0;
    private Integer failedItems = 0;
    private Integer unprocessedItems = 0;
    
    public AttemptStats() {}

    public AttemptStats(Integer processedItems, Integer failedItems, Integer unprocessedItems) {
        this.processedItems = processedItems;
        this.failedItems = failedItems;
        this.unprocessedItems = unprocessedItems;
    }

    public Integer getProcessedItems() {
        return processedItems;
    }

    public void setProcessedItems(Integer processedItems) {
        this.processedItems = processedItems;
    }

    public Integer getFailedItems() {
        return failedItems;
    }

    public void setFailedItems(Integer failedItems) {
        this.failedItems = failedItems;
    }

    public Integer getUnprocessedItems() {
        return unprocessedItems;
    }

    public void setUnprocessedItems(Integer unprocessedItems) {
        this.unprocessedItems = unprocessedItems;
    }
    
    public void incrementProcessedItem() {
        this.processedItems++;
    }
    
    public void incrementFailedItem() {
        this.failedItems++;
    }
    
    public void calculateUnprocessed(int totalSize) {
        this.unprocessedItems = totalSize - (processedItems + failedItems);
    }
}
