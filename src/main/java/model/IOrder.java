package model;

import props.Order;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IOrder {
    int orderInsert(Order order);
    List<Order> orderList();
    DefaultTableModel orderTableModel(String data);
}
