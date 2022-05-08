package props;

public class Basket {
    private int sid;
    private int customerID;
    private int productID;
    private String date;
    private int count;
    private int status;
    private String uuid;
    private int categoryId;

    public Basket() {
    }

    public Basket(int sid, int customerID, int productID, String date, int count, int status,String uuid,int categoryId) {
        //categoryid eklenecek ve yeniden düzenlenecek prop impl ve db

        this.sid = sid;
        this.customerID = customerID;
        this.productID = productID;
        this.date = date;
        this.count = count;
        this.status = status;
        this.uuid = uuid;
        this.categoryId = categoryId;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "sid=" + sid +
                ", customerID=" + customerID +
                ", productID=" + productID +
                ", date='" + date + '\'' +
                ", count=" + count +
                ", status=" + status +
                ", uuid='" + uuid + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
