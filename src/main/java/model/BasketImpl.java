package model;

import props.Basket;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BasketImpl implements IBasket{
    ProductImpl productImpl = new ProductImpl(-1);
    int inpCustomerId;
    DB db=new DB();
    List<Basket> ls = new ArrayList<>();

    public BasketImpl(int inpCustomerId) {
        this.inpCustomerId=inpCustomerId;
        ls = basketList();
    }

    @Override
    public int basketInsert(Basket basket) {
        int status=0;
        try{
            //int sid, int customerID, int productID, String date, int count, int status,String uuid, int categoryId)
            String sql="INSERT INTO basket values(null,?,?,?,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,basket.getCustomerID());
            pre.setInt(2,basket.getProductID());
            pre.setString(3,basket.getDate());
            pre.setInt(4,basket.getCount());
            pre.setInt(5,basket.getStatus());
            pre.setString(6,basket.getUuid());
            pre.setInt(7,basket.getCategoryId());
            status = pre.executeUpdate();
        }catch (Exception e){
            System.err.println("basketInsert Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int basketDelete(int sid) {
        int status=0;
        try{
            String sql="DELETE FROM basket WHERE sid = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,sid);
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("basketDelete Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int basketUpdate(int customerID) {
        int status = 0;
        try{
            String sql=" UPDATE basket SET status= ?  where customerID=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,1);
            pre.setInt(2,customerID);
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("basketUpdate Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public int stockControl(int productId, int count) {
        int status=0;
        //sorgu at productan stock al ve count ile karşılaştır
        //count stocktan  buyk ise false dondur
        //degilse true dondur

        try{
            String sql="select stock from product where pid =  ? ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,productId);
            ResultSet rs=pre.executeQuery();
            if(rs.next()) {
                int stock = rs.getInt("stock");
                System.out.println("stock:"+stock);
                if(count >stock){
                    System.err.println("count stocktan buyuk");
                    status=0;
                }
                else
                    status=1;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public String basketControl(int customerId) {
        try
        {
            String sql="select * from basket where status = 0 and customerID = ? ";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,customerId);
            ResultSet rs=pre.executeQuery();
            if(rs.next()) {
                String uuid = rs.getString("uuid");
                return uuid;
            }
        }
        catch (Exception ex)
        {
            System.err.println("basketControl Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return null;
    }

    @Override
    public List<Basket> basketList() {
        List<Basket> basketList = new ArrayList<>();
        try
        {
            String sql="";
            if(inpCustomerId ==-1)
                sql = "select * from basket where status=?";
            else{
                sql="select * from basket where customerID=? and status=?";
            }
            PreparedStatement pre=db.connect().prepareStatement(sql);
            if(inpCustomerId !=-1) {
                pre.setInt(1, inpCustomerId);
                pre.setInt(2, 0);
            }
            else
                pre.setInt(1,0);



            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                //int sid, int customerID, int productID, String date, int count, int status,String uuid,int categoryId
                int sid=rs.getInt("sid");
                int customerID = rs.getInt("customerID");
                int productID = rs.getInt("productID");
                String  date = rs.getString("date");
                int count = rs.getInt("count");
                int status = rs.getInt("status");
                String uuid = rs.getString("uuid");
                int categoryId = rs.getInt("categoryId");
                Basket basket = new Basket(sid,customerID,productID,date,count,status,uuid,categoryId);
                basketList.add(basket);
            }
        }
        catch (Exception ex)
        {
            System.err.println("basketList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return basketList;
    }

    @Override
    public DefaultTableModel basketTableModel() {
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("sid");
        md.addColumn("customerID");
        md.addColumn("productID");
        md.addColumn("date");
        md.addColumn("count");
        md.addColumn("status");
        md.addColumn("uuid");
        md.addColumn("categoryId");

        for (Basket item : ls) {
            Object[] row = {item.getSid(), item.getCustomerID(), item.getProductID(),item.getDate(),
                    item.getCount(), item.getStatus(), item.getUuid(),item.getCategoryId()};
            md.addRow(row);
        }
        return md;
    }


}
