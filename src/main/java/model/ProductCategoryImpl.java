package model;

import props.ProductCategory;
import utils.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductCategoryImpl implements IProductCategory{
    DB db=new DB();
    List<ProductCategory> ls=new ArrayList<>();
    List<ProductCategory> lsSearch=new ArrayList<>();
    List<ProductCategory> categoryList=new ArrayList<>();

    public ProductCategoryImpl(){
        ls = categorytList();
        lsSearch = ls;
    }


    @Override
    public int categoryInsert(ProductCategory category) {
        int status=0;

        try {
            String sql= "insert into category values(null,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,category.getCategoryName());
            pre.setString(2,category.getCategoryInfo());

            status= pre.executeUpdate();
        }catch (Exception ex){
            System.out.println("Category Insert  Error "+ex);
            if(ex.toString().contains("UNIQUE")){
                status=-1;
            }

        }
        finally {
            db.close();
        }

        return status;

    }

    @Override
    public int categoryDelete(int cid) {
        int status=0;
        try{
            String sql="delete from category where cid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);

            pre.setInt(1,cid);

            status= pre.executeUpdate();


        } catch (Exception ex) {
            System.err.println("categoryDelete Error: "+ex); //err k�rm�z� g�steriyor.
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return status;
    }

    @Override
    public int categoryUpdate(ProductCategory category) {
        int status=0;

        try {
            String sql = "UPDATE category SET categoryName=?,categoryInfo=? where cid=?";
            PreparedStatement pre = db.connect().prepareStatement(sql);

            pre.setString(1,category.getCategoryName());

            pre.setString(2,category.getCategoryInfo());

            pre.setInt(3,category.getCid());


            //soru i�aretlerine g�nderilecek olan datan�n g�nderim y�nteminin bir ad�da bind y�ntemi olarak ge�er.
            status= pre.executeUpdate();



        } catch (Exception ex) {
            System.err.println("categoryUpdate Error: "+ex); //err k�rm�z� g�steriyor.
            ex.printStackTrace();
        } finally {
            db.close(); //a��k olan
        }
        return status;
    }

    @Override
    public List<ProductCategory> categorytList() {
        try {
            String sql="select * from category order by cid desc";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            ResultSet rs= pre.executeQuery();

           categoryList.clear();
            while(rs.next()){
                int cid=rs.getInt("cid");
                String categoryName=rs.getString("CategoryName");
                String categoryInfo= rs.getString("CategoryInfo");

                ProductCategory category =new ProductCategory(cid,categoryName,categoryInfo);
                categoryList.add(category);

            }

        }catch (Exception ex){
            System.out.println("categoryList Error"+ex);
            ex.printStackTrace();


        }finally {
            db.close();
        }


        return categoryList;
    }

    @Override
    public DefaultTableModel categorySearch(String data) {
        ls=lsSearch;
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("cid");  //kolon ekledik
        model.addColumn("Category Name");
        model.addColumn("Category Info");


        if (data!=null && !data.equals("")){
            data=data.toLowerCase(Locale.ROOT);
            List<ProductCategory> subls=new ArrayList<>();
            for (ProductCategory item: ls){
                if (item.getCategoryName().toLowerCase(Locale.ROOT).contains(data)
                        || item.getCategoryInfo().toLowerCase(Locale.ROOT).contains(data)
                         )
                {
                    subls.add(item);
                }
            }
            ls = subls;
        }

        for ( ProductCategory item : ls ) {
            Object[] row = { item.getCid(), item.getCategoryName(), item.getCategoryInfo() };
            model.addRow(row);
        }

        return model;

    }

    @Override
    public DefaultTableModel categoryTableModel(){  //Model olu�turduk datatablemodel ile s�t�n ve say�rlar olu�turduk
        categorytList();

        DefaultTableModel tableModel= new DefaultTableModel();

        tableModel.addColumn("cid");  //kolon ekledik
        tableModel.addColumn("Category Name");
        tableModel.addColumn("Category Info");


        for(ProductCategory item: categorytList()){ //car t�r�nde bir nesne getiriyor.
            Object[] row={item.getCid(),item.getCategoryName(),item.getCategoryInfo()};//item.getCid(),

            tableModel.addRow(row);
        }

        return  tableModel;
    }

    @Override
    public boolean categoryDeleteControl(int cid) {
        boolean status =false;
        try
        {
            String sql = "select * from basket where status = 1 and categoryId = ?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setInt(1,cid);
            ResultSet rs=pre.executeQuery();
            if(rs.next())
                status=true;
        }
        catch (Exception ex)
        {
            System.err.println("categoryDeleteControl Error: "+ex.toString());
            ex.printStackTrace();
        }
        finally {
            db.close();
        }
        return status ;
    }
}
