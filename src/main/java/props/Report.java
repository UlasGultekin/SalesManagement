package props;

public class Report {
    private String customerName;
    private String productName;
    private String date;
    private int count;
    private String  categoryName;
    private int buyPrice;
    private int sellPrice;

    public Report() {
    }

    public Report(String customerName, String productName, String date, int count, String categoryName, int buyPrice, int sellPrice) {
        this.customerName = customerName;
        this.productName = productName;
        this.date = date;
        this.count = count;
        this.categoryName = categoryName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return "Report{" +
                "customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", date='" + date + '\'' +
                ", count=" + count +
                ", categoryName='" + categoryName + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
