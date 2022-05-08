package model;


import props.ComboItem;
import props.Customer;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements ICustomer {
    DB db = new DB();
    List<Customer> ls = new ArrayList<>();
    List<Customer> lsSearch = new ArrayList<>();
    public CustomerImpl(){
        ls = customerList();
        lsSearch = ls;
    }

    @Override
    public int customerInsert(Customer customer) {
        int status = 0;
        try {
            String sql = "insert into Customer values(null, ?, ?, ?, ?, ?)";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getSurname());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getPhone());
            pre.setString(5, customer.getAddress());
            status = pre.executeUpdate();
        }catch (Exception ex){
            System.out.println("customerUpdate Error" + ex);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int customerDelete(int customerId) {
        int status = 0;
        try {
            String sql = " delete from Customer where customerId = ? ";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setInt(1, customerId);
            status = pre.executeUpdate();
        }catch (Exception ex){
            System.out.println("customerDelete Error" + ex);
        }
        return status;
    }

    @Override
    public int customerUpdate(Customer customer) {
        ls = lsSearch;
        int status = 0;
        try {
            String sql = "update Customer set name = ?, surname = ?, email = ?, phone = ?, address = ? where customerID = ?";
            PreparedStatement pre = db.connect().prepareStatement(sql);
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getSurname());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getPhone());
            pre.setString(5, customer.getAddress());
            pre.setInt(6, customer.getCustomerId());
            status = pre.executeUpdate();
        }catch (Exception ex){
            System.out.println(" customerUpdate Error " + ex );
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public List<Customer> customerList() {
        List<Customer> customerList = new ArrayList<>();
        try {
        String sql = "select * from Customer order by customerId desc";
        PreparedStatement pre = db.connect().prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                int customerId = rs.getInt("CustomerId");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                Customer customer = new Customer(customerId, name, surname, email, phone, address );
                customerList.add(customer);
            }
        }catch (Exception ex){
            System.out.println( "customerList Error" + ex.toString() );
        }finally {
            db.close();
        }
        return customerList;
    }

    @Override
    public DefaultTableModel customerTableModel() {
        ls = lsSearch;
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("CustomerId");
        md.addColumn("Name");
        md.addColumn("Surname");
        md.addColumn("Email");
        md.addColumn("Phone");
        md.addColumn("Address");
        for ( Customer item : ls ) {
            Object[] row = {item.getCustomerId(), item.getName(), item.getSurname(), item.getEmail(), item.getPhone(), item.getAddress()};
            md.addRow(row);
        }
        return md;
    }

    @Override
    public List<ComboItem> listCustomer() {
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
            System.err.println("listCategory Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return lsCustomer;
    }

    @Override
    public boolean customerDeleteControl(int customerId) {
        boolean status =false;
        try
        {
            String sql = "select * from basket where status = 1 and customerID = ?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,customerId);
            ResultSet rs=pre.executeQuery();
            if(rs.next())
                status=true;
        }
        catch (Exception ex)
        {
            System.err.println("customerDeleteControl Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return status ;
    }
}