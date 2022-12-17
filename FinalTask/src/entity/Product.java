package entity;

public class Product {
    private int id;

    private String name;

    private int price;

    private String sellerName;

    private int categoryId;

    private boolean isDeleted;

    public Product(int id, String name, int price, String sellerName, int categoryId, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
        this.categoryId = categoryId;
        this.isDeleted = isDeleted;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
