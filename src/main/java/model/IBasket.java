package model;

import props.Basket;
import props.Product;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IBasket {
    int basketInsert(Basket basket);
    int basketDelete(int sid);
    int basketUpdate(int sid);
    int stockControl(int productId, int count);
    String basketControl(int customerId);
    List<Basket> basketList();
    DefaultTableModel basketTableModel();
}


//listeleme
//basket product customer joinlenecek
//tablo oluşturma ve üstüne yazma