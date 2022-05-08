package model;


import props.ComboItem;
import props.Customer;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface ICustomer {
    int customerInsert(Customer customer);
    int customerDelete(int customerId);
    int customerUpdate(Customer customer);
    List<Customer> customerList();
    DefaultTableModel customerTableModel();
    List<ComboItem> listCustomer();
    boolean customerDeleteControl(int customerId);
}
