package model;


import props.Report;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IReport {
    List<Report> reportList();
    DefaultTableModel reportTableModel(String data,int searchType);
    //searchType -1 null
    //searchType 1 customer
    //searchType 2 product
    //searchType 3 category
}
