package props;

public class ProductCategory {
    int cid;
    String categoryName;
    String categoryInfo;

    public ProductCategory() {
    }

    public ProductCategory(int cid, String categoryName, String categoryInfo) {
        this.cid = cid;
        this.categoryName = categoryName;
        this.categoryInfo = categoryInfo;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "cid=" + cid +
                ", categoryName='" + categoryName + '\'' +
                ", categoryInfo='" + categoryInfo + '\'' +
                '}';
    }
}
