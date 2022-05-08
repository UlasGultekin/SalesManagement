package props;

public class Product {
    private int pid;
    private String name;
    private int categoryId;
    private int buyPrice;
    private int sellPrice;
    private String info;
    private int stock;

    public Product() {
    }

    public Product(int pid, String name, int categoryId, int buyPrice, int sellPrice, String info, int stock) {
        this.pid = pid;
        this.name = name;
        this.categoryId = categoryId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.info = info;
        this.stock = stock;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
