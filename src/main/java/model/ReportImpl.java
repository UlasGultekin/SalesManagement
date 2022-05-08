package model;

import props.Report;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReportImpl implements IReport{
    DB db=new DB();
    List<Report> ls = new ArrayList<>();
    List<Report> lsSearch = new ArrayList<>();
    String firstDate = "2022-01-01";
    String lastDate = "2024-01-01";

    public ReportImpl(String firstDate, String lastDate) {
        if(firstDate !=null && lastDate !=null){
            this.firstDate=firstDate;
            this.lastDate=lastDate;
        }
        ls = reportList();
        lsSearch = ls;
    }

    @Override
    public List<Report> reportList() {
        List<Report> reportList = new ArrayList<>();
        try
        {
            String sql ="select  cr.name [customerName], p.name[productName],bs.date,bs.count,cy.categoryName,p.buyPrice,p.sellPrice from basket bs\n" +
                    "join product p on p.pid = bs.productID\n" +
                    "join customer cr on cr.customerId = bs.customerID\n" +
                    "join category cy on cy.cid = bs.categoryId\n" +
                    "where bs.status=1 and date BETWEEN ? AND ? ";

            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,firstDate);
            pre.setString(2,lastDate);
            ResultSet rs=pre.executeQuery();
            while(rs.next())
            {
                String customerName = rs.getString("customerName").toLowerCase(Locale.ROOT);
                String productName = rs.getString("productName").toLowerCase(Locale.ROOT);
                String date = rs.getString("date");
                int count=rs.getInt("count");
                String categoryName = rs.getString("categoryName").toLowerCase(Locale.ROOT);
                int buyPrice = rs.getInt("buyPrice");
                int sellPrice = rs.getInt("sellPrice");
                Report report = new Report(customerName, productName, date, count, categoryName, buyPrice, sellPrice);
                reportList.add(report);
            }
        }
        catch (Exception ex)
        {
            System.err.println("reportList Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return reportList;
    }

    @Override
    public DefaultTableModel reportTableModel(String data, int searchType) {
        ls=lsSearch;
        DefaultTableModel md = new DefaultTableModel();
        md.addColumn("Customer");
        md.addColumn("Product");
        md.addColumn("Date");
        md.addColumn("Count");
        md.addColumn("Category");
        md.addColumn("Buy Price");
        md.addColumn("Sell Price");

        if (data != null && !data.equals("")) {
            List<Report> subLs = new ArrayList<>();
            for (Report item : ls) {
                if(searchType!=-1){
                    if(searchType!=1 && item.getCustomerName().toLowerCase(Locale.ROOT).contains(data)){
                        subLs.add(item);
                    }
                    else if(searchType!=2 && item.getProductName().toLowerCase(Locale.ROOT).contains(data)){
                        subLs.add(item);
                    }
                    else if(searchType!=3 && item.getCategoryName().toLowerCase(Locale.ROOT).contains(data)){
                        subLs.add(item);
                    }
                }
            }
            ls = subLs;
        }
        for (Report item : ls) {
            Object[] row = {item.getCustomerName(), item.getProductName(), item.getDate(),item.getCount(),
                    item.getCategoryName(), item.getBuyPrice(), item.getSellPrice()};
            md.addRow(row);
        }
        return md;
    }
}
