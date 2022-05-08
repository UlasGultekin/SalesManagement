package props;

public class Order {
    private int oid;
    private int customerID;
    private int total;
    private String date;
    private String uuid;

    public Order() {
    }

    public Order(int oid, int customerID, int total, String date, String uuid) {
        this.oid = oid;
        this.customerID = customerID;
        this.total = total;
        this.date = date;
        this.uuid = uuid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", customerID=" + customerID +
                ", total=" + total +
                ", date='" + date + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}