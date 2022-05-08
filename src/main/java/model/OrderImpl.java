package model;

import props.Order;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements IOrder{
    DB db=new DB();
    List<Order> ls = new ArrayList<>();
    @Override
    public int orderInsert(Order order) {
        int status=0;
        try{
            //int oid, int customerID, int total, String date, String uuid
            String sql="INSERT INTO orders values(null,?,?,?,?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1,order.getCustomerID());
            pre.setInt(2,order.getTotal());
            pre.setString(3,order.getDate());
            pre.setString(4,order.getUuid());
            status = pre.executeUpdate();
        }catch (Exception e){
            System.out.println("orderInsert Error : "+e);
        }
        finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Order> orderList() {
        List<Order> orderList = new ArrayList<>();
        try
        {
            String sql = "select * from orders";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                // int oid, int customerID, int total, String date, String uuid
                int oid=rs.getInt("oid");
                int customerID = rs.getInt("customerID");
                int total = rs.getInt("total");
                String  date = rs.getString("date");
                String uuid = rs.getString("uuid");
                Order order = new Order(oid,customerID,total,date,uuid);
                orderList.add(order);
            }
        }
        catch (Exception ex)
        {
            System.err.println("orderList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return orderList;
    }

    @Override
    public DefaultTableModel orderTableModel(String data) {
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("oid");
        md.addColumn("customerID");
        md.addColumn("total");
        md.addColumn("date");
        md.addColumn("uuid");

        for (Order item : ls) {
            Object[] row = {item.getOid(), item.getCustomerID(), item.getTotal(),
                    item.getDate(), item.getUuid()};
            md.addRow(row);
        }
        return md;
    }
}
