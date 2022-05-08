package model;


import props.ProductCategory;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IProductCategory {
    int categoryInsert(ProductCategory category);
    int categoryDelete(int cid);
    int categoryUpdate(ProductCategory category);
    List<ProductCategory> categorytList();
    DefaultTableModel categorySearch(String data);
    DefaultTableModel categoryTableModel();
    boolean categoryDeleteControl(int cid);
}
