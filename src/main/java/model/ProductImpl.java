package model;

import props.ComboItem;
import props.Product;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductImpl implements IProduct{
    DB db=new DB();
    List<Product> ls = new ArrayList<>();
    List<Product> lsSearch = new ArrayList<>();
    int inpCategoryId=-1;
    public ProductImpl(int categoryId) {
        inpCategoryId =categoryId;
        ls = productList(); //veritabanına baglan yeni veri cek
        lsSearch = ls; // yeni verileri  lsSearch  ye ekle
    }

    @Override
    public int productInsert(Product product) {
        ls=lsSearch;
        int status=0;
        try{
            //name cid buy info stock sellprice
            String sql="INSERT INTO Product values(null,?,?,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,product.getName());
            pre.setInt(2,product.getCategoryId());
            pre.setInt(3,product.getBuyPrice());
            pre.setString(4,product.getInfo());
            pre.setInt(5,product.getStock());
            pre.setInt(6,product.getSellPrice());
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("productInsert Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int productUpdate(Product product) {
        ls=lsSearch;
        int status = 0;
        try{
            String sql=" UPDATE product SET name= ?,cid = ?,buyPrice = ?, sellPrice =?, info =?, stock =?  where pid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1,product.getName());
            pre.setInt(2,product.getCategoryId());
            pre.setInt(3,product.getBuyPrice());
            pre.setInt(4,product.getSellPrice());
            pre.setString(5,product.getInfo());
            pre.setInt(6,product.getStock());
            pre.setInt(7,product.getPid());
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("productUpdate Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int productDelete(int pid) {
        int status=0;
        try{
            String sql="DELETE FROM product WHERE pid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,pid);
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("productDelete Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int getProductSellPrice(int pid) {
        int sellPrice=-1;
        try
        {
            String sql="select sellPrice from product WHERE pid = ? order by pid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,pid);
            ResultSet rs=pre.executeQuery();
            if(rs.next())
                sellPrice = rs.getInt("sellPrice");
        }
        catch (Exception ex)
        {
            System.err.println("getProductSellPrice Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return sellPrice;
    }

    @Override
    public Product getProduct(int pid) {
        Product product = null;
        try{
            String sql="select * from product where pid = ?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,pid);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                int productId=rs.getInt("pid");
                String pName = rs.getString("name");
                int pCategoryId = rs.getInt("cid");
                int pBuyPrice = rs.getInt("buyPrice");
                int pSellPrice = rs.getInt("sellPrice");
                String pInfo = rs.getString("info");
                int pStock = rs.getInt("stock");
                product= new Product(productId,pName,pCategoryId,pBuyPrice,pSellPrice,pInfo,pStock);
            }
        }
        catch (Exception ex)
        {
            System.err.println("getProduct Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
       return product;
    }

    @Override
    public List<Product> productList() {
        List<Product> productList = new ArrayList<>();
        try
        {
            String sql="";
            if(inpCategoryId ==-1)
                sql = "select * from product order by pid desc";
            else{
                sql="select * from product WHERE cid = ? order by pid desc";
            }
            PreparedStatement pre=db.connect().prepareStatement(sql);
            if(inpCategoryId !=-1)
                pre.setInt(1,inpCategoryId);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                int pid=rs.getInt("pid");
                String pName = rs.getString("name");
                int pCategoryId = rs.getInt("cid");
                int pBuyPrice = rs.getInt("buyPrice");
                int pSellPrice = rs.getInt("sellPrice");
                String pInfo = rs.getString("info");
                int pStock = rs.getInt("stock");
                Product product = new Product(pid,pName,pCategoryId,pBuyPrice,pSellPrice,pInfo,pStock);
                productList.add(product);
            }
        }
        catch (Exception ex)
        {
            System.err.println("productList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return productList;
    }

    @Override
    public DefaultTableModel productTable(String data) {
        ls=lsSearch;
        //en başta bir column isimleri oluşturulması gerekiyor.
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("id");
        md.addColumn("Name");
        md.addColumn("CategoryId");
        md.addColumn("BuyPrice");
        md.addColumn("SellPrice");
        md.addColumn("Info");
        md.addColumn("Stock");

        //1.ilkönce ne istendiğine gore konum al
        if (data != null && !data.equals("")) {//arama sonuclarını gonder
            List<Product> subLs = new ArrayList<>();
            for (Product item : ls) {
                if (item.getName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getInfo().toLowerCase(Locale.ROOT).contains(data)) {
                    subLs.add(item);
                }
            }
            ls = subLs;
        }

        for (Product item : ls) {
            Object[] row = {item.getPid(), item.getName(), item.getCategoryId(),
                    item.getBuyPrice(), item.getSellPrice(), item.getInfo(), item.getStock()};
            md.addRow(row);
        }
        return md;
    }

    @Override
    public boolean productDeleteControl(int pid) {
        boolean status =false;
        try
        {
            String sql = "select * from basket where status = 1 and productID = ?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,pid);
            ResultSet rs=pre.executeQuery();
            if(rs.next())
                status=true;
        }
        catch (Exception ex)
        {
            System.err.println("productDeleteControl Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return status ;
    }

    public List listCategory(){
        List<ComboItem> lsCategory = new ArrayList<>();
        try
        {
            String sql = "select * from category";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                String cid= String.valueOf(rs.getInt("cid"));
                String categoryName = rs.getString("categoryName");
                ComboItem comboItem=new ComboItem(cid,categoryName);
                lsCategory.add(comboItem);
            }
        }
        catch (Exception ex)
        {
            System.err.println("listCategory Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return lsCategory;
    }

    public List listCustomer(){
        List<ComboItem> lsCustomer = new ArrayList<>();
        try
        {
            String sql = "select * from customer";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                String customerId= String.valueOf(rs.getInt("customerId"));
                String customerName = rs.getString("name");
                ComboItem comboItem=new ComboItem(customerId,customerName);
                lsCustomer.add(comboItem);
            }
        }
        catch (Exception ex)
        {
            System.err.println("listCustomer Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return lsCustomer;
    }
}

