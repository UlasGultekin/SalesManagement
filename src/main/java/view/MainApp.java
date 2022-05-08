/*
 * Created by JFormDesigner on Thu Apr 14 02:40:21 TRT 2022
 */

package view;

import java.beans.*;
import javax.swing.event.*;
import com.github.lgooddatepicker.components.*;
import model.*;
import props.*;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.TableModel;

/**
 * @author mrf
 */
public class MainApp extends JFrame {

    ProductImpl productImpl=new ProductImpl(-1);
    ProductCategoryImpl productCategoryImpl=new ProductCategoryImpl();
    BasketImpl basketImpl=new BasketImpl(-1);
    int row=-1;
    int value;
    String datest="1900-12-01";
    String datend="2500-12-01";

    public static void main(String[] args) {
        MainApp mainApp=new MainApp();
        mainApp.setVisible(true);
    }
    UserImpl userImpl=new UserImpl();
    CustomerImpl customerImpl = new CustomerImpl(); //Customer
    ProductCategory productCategory=new ProductCategory();
    ReportImpl reportImpl = new ReportImpl(datest,datend);

    public void startCategoryTable(){
        tblCategory.setModel(productCategoryImpl.categoryTableModel());
    }

    public MainApp() {
        initComponents();
        tblCustomer.setModel(customerImpl.customerTableModel());//Customer
        tblCategory.setModel(productCategoryImpl.categoryTableModel());
        tblProducts.setModel(productImpl.productTable(null));
        tblSale.setModel(productImpl.productTable(null));
        tblReport.setModel(reportImpl.reportTableModel(null,-1));
        listedProductCategory();
        listedCustomer();
    }

    private void listedCustomer() {
        List<ComboItem> listCustomer=productImpl.listCustomer();
        for (ComboItem item:listCustomer) {
            cmbSaleCustomer.addItem(new ComboItem(item.getValue(), item.getKey()));
        }
    }

    private void listedProductCategory() {
        List<ComboItem> lsCategory=productImpl.listCategory();
        for (ComboItem item:lsCategory) {
            cmbAddProductCategory.addItem(new ComboItem(item.getValue(), item.getKey()));
            cmbEditProductCategory.addItem(new ComboItem(item.getValue(), item.getKey()));
            cmbList.addItem(new ComboItem(item.getValue(), item.getKey()));
        }
    }

    private Product productAddValidate(){
        try {
            if (txtAddProductName.getText().equals("") || txtAddProductName.getText().equals(null)) {
                txtAddProductName.requestFocus();
                lblAddProduct.setText("Product Name Empty");
            } else if ( txtAddProductBuying.getText().equals("") || txtAddProductBuying.getText().equals(null)) {
                txtAddProductBuying.requestFocus();
                lblAddProduct.setText("Product Buy Price Empty");
            } else if (cmbAddProductCategory.getSelectedItem().equals("")) {
                cmbAddProductCategory.requestFocus();
                lblAddProduct.setText("Product Category Empty");
            } else if (txtAddProductSelling.getText().equals("") || txtAddProductSelling.getText().equals(null)) {
                txtAddProductSelling.requestFocus();
                lblAddProduct.setText("Product Sell Price Empty");
            } else if (txtAddProductStatement.getText().equals("")) {
                txtAddProductStatement.requestFocus();
                lblAddProduct.setText("Product İnfo Empty");
            } else if (txtAddProductStock.getText().equals("")) {
                txtAddProductStock.requestFocus();
                lblAddProduct.setText("Product İnfo Empty");
            }
            else {

                //int pid, String name, int categoryId, int buyPrice, int sellPrice, String info, int stock
                String name=txtAddProductName.getText().toLowerCase(Locale.ROOT).trim();
                //categoryId
                int categoryId = Integer.parseInt(((ComboItem)cmbAddProductCategory.getSelectedItem()).getValue());

                int buyPrice= Integer.parseInt(txtAddProductBuying.getText().toLowerCase(Locale.ROOT).trim());
                int sellPrice= Integer.parseInt(txtAddProductSelling.getText().toLowerCase(Locale.ROOT).trim());
                String info=txtAddProductStatement.getText().toLowerCase(Locale.ROOT).trim();
                int stock= Integer.parseInt(txtAddProductStock.getText().toLowerCase(Locale.ROOT).trim());

                Product product=new Product(0,name,categoryId,buyPrice,sellPrice,info,stock);
                return product;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    private Product productUpdateValidate(){
        try {
            if (txtEditProductName.getText().equals("") || txtEditProductName.getText().equals(null)) {
                txtEditProductName.requestFocus();
                lblEditProduct.setText("Product Name Empty");
            } else if ( txtEditProductBuying.getText().equals("") || txtEditProductBuying.getText().equals(null)) {
                txtEditProductBuying.requestFocus();
                lblEditProduct.setText("Product Buy Price Empty");
            } else if (cmbEditProductCategory.getSelectedItem().equals("")) {
                cmbEditProductCategory.requestFocus();
                lblEditProduct.setText("Product Category Empty");
            } else if (txtEditProductSelling.getText().equals("") || txtEditProductSelling.getText().equals(null)) {
                txtEditProductSelling.requestFocus();
                lblEditProduct.setText("Product Sell Price Empty");
            } else if (txtEditProductStatement.getText().equals("")) {
                txtEditProductStatement.requestFocus();
                lblEditProduct.setText("Product İnfo Empty");
            } else if (txtEditProductStock.getText().equals("")) {
                txtEditProductStock.requestFocus();
                lblEditProduct.setText("Product İnfo Empty");
            }
            else {

                //int pid, String name, int categoryId, int buyPrice, int sellPrice, String info, int stock
                int pid=Integer.parseInt(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 0)));
                String name=txtEditProductName.getText().toLowerCase(Locale.ROOT).trim();
                //categoryId
                int categoryId = Integer.parseInt(((ComboItem)cmbEditProductCategory.getSelectedItem()).getValue());

                int buyPrice= Integer.parseInt(txtEditProductBuying.getText().toLowerCase(Locale.ROOT).trim());
                int sellPrice= Integer.parseInt(txtEditProductSelling.getText().toLowerCase(Locale.ROOT).trim());
                String info=txtEditProductStatement.getText().toLowerCase(Locale.ROOT).trim();
                int stock= Integer.parseInt(txtEditProductStock.getText().toLowerCase(Locale.ROOT).trim());

                Product product=new Product(pid,name,categoryId,buyPrice,sellPrice,info,stock);
                lblEditProduct.setText("");
                return product;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void fncClearProduct() {
        txtAddProductName.setText(null);
        txtAddProductBuying.setText(null);
        txtAddProductSelling.setText(null);
        txtAddProductStatement.setText(null);
        txtAddProductStock.setText(null);
        txtEditProductName.setText(null);
        txtEditProductBuying.setText(null);
        txtEditProductSelling.setText(null);
        txtEditProductStatement.setText(null);
        txtEditProductStock.setText(null);
    }


    public void rowValueCategory() {
        int column = 0;
        row = tblCategory.getSelectedRow();
        value = (int) tblCategory.getModel().getValueAt(row, column);
        String cid = String.valueOf(tblCategory.getValueAt(row, 0));
        String categoryName = String.valueOf(tblCategory.getValueAt(row, 1));
        String categoryInfo = String.valueOf(tblCategory.getValueAt(row, 2));

        //System.out.println("val" + value);
        txtCategoryName.setText(categoryName);
        txtCategoryInfo.setText(categoryInfo);

    }

    private void btnAddUserClicked(ActionEvent e) {
        User user=fncDataValid();
        if(user!=null){
            int status= userImpl.userInsert(user);
            if(status>0){
                System.out.println("Ekleme Başarılı");
                fncTextClear();
            }else{
                if(status==-1){
                    lblError.setText("Email or Phone Number Already Exists.");
                }else {
                    lblError.setText("Insert Error");
                }
            }
        }
    }

    public User fncDataValid(){
        String name=txtName.getText().trim();
        String surname=txtSurname.getText().trim();
        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        String password=txtPassword.getText().trim();

        if(name.equals("")){
            lblError.setText("Please Enter Name");
            txtName.requestFocus();;
        }else if(surname.equals("")){
            lblError.setText("Please Enter Surname");
            txtSurname.requestFocus();
        }else if(email.equals("")){
            lblError.setText("Please Enter Email");
            txtEmail.requestFocus();
        }else if(password.equals("")){
            lblError.setText("Please Enter Phone");
            txtPassword.requestFocus();
        }else {
            lblError.setText("");
            User user=new User(0,name,surname,email,password);

            return user;
        }
        return null;
    }
    private ProductCategory fncDataValidCategory(){
        String categoryName = txtCategoryName.getText().trim();
        String categoryInfo = txtCategoryInfo.getText().trim();
        ProductCategory c = new ProductCategory(0,categoryName,categoryInfo);
        return c;


    }
    private Customer fncEditCustomerDatavalidate(){
        String name = txtEditCustomerName.getText().toLowerCase(Locale.ROOT).trim();
        String surname = txtEditCustomerSurname.getText().toLowerCase(Locale.ROOT).trim();
        String email=txtEditCustomerEmail.getText().toLowerCase(Locale.ROOT).trim();
        String phone=txtEditCustomerPhone.getText().toLowerCase(Locale.ROOT).trim();
        String address=txtEditCustomerAddress.getText().toLowerCase(Locale.ROOT).trim();

        if(name.equals("")){
            txtEditCustomerName.requestFocus();
            lblCustomerError.setText("Name is cannot be empty");
        }
        else if(surname.equals("")){
            txtEditCustomerSurname.requestFocus();
            lblCustomerError.setText("Surname is cannot be empty");
        }
        else if(email.equals("")){
            txtEditCustomerEmail.requestFocus();
            lblCustomerError.setText("Email is cannot be empty");
        }
        else if(!Util.isValidEmailAddress(email)){
            lblCustomerError.setText("E-mail invalid");
            txtEditCustomerEmail.requestFocus();
        }
        else if(phone.equals("")){
            txtEditCustomerPhone.requestFocus();
            lblCustomerError.setText("Phone is cannot be empty");
        }
        else if(address.equals("")){
            txtEditCustomerAddress.requestFocus();
            lblCustomerError.setText("Address is cannot be empty");
        }
        else{
            lblCustomerError.setText("");
            Customer customer = new Customer(0,name,surname,email,phone,address);
            return customer;
        }
        return null;
    }
    public String rowValueCustomer(int row,int column){

        String selectedCellValue = String.valueOf(tblCustomer.getValueAt(row , column)) ;
        txtEditCustomerName.setText((String) tblCustomer.getValueAt(row , 1));
        txtEditCustomerSurname.setText((String) tblCustomer.getValueAt(row , 2));
        txtEditCustomerEmail.setText((String) tblCustomer.getValueAt(row , 3));
        txtEditCustomerPhone.setText( String.valueOf(tblCustomer.getValueAt(row , 4)));
        txtEditCustomerAddress.setText( String.valueOf(tblCustomer.getValueAt(row , 5)));

        return selectedCellValue;
    }

    public void fncTextClear(){
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        //AddCustomer
        txtAddCustomerName.setText("");
        txtAddCustomerSurname.setText("");
        txtAddCustomerEmail.setText("");
        txtAddCustomerPhone.setText("");
        txtAddCustomerAddress.setText("");
        //EditCustomer
        txtEditCustomerName.setText("");
        txtEditCustomerSurname.setText("");
        txtEditCustomerEmail.setText("");
        txtEditCustomerPhone.setText("");
        txtEditCustomerAddress.setText("");
    }

    //Customer
    private Customer fncAddCustomerDatavalidate(){
        String name = txtAddCustomerName.getText().toLowerCase(Locale.ROOT).trim();
        String surname = txtAddCustomerSurname.getText().toLowerCase(Locale.ROOT).trim();
        String email=txtAddCustomerEmail.getText().toLowerCase(Locale.ROOT).trim();
        String phone=txtAddCustomerPhone.getText().toLowerCase(Locale.ROOT).trim();
        String address=txtAddCustomerAddress.getText().toLowerCase(Locale.ROOT).trim();
        if(name.equals("")){
            txtAddCustomerName.requestFocus();
            lblCustomerError.setText("Name is cannot be empty");
        }
        else if(surname.equals("")){
            txtAddCustomerSurname.requestFocus();
            lblCustomerError.setText("Surname is cannot be empty");
        }
        else if(email.equals("")){
            txtAddCustomerEmail.requestFocus();
            lblCustomerError.setText("Email is cannot be empty");
        }
        else if(!Util.isValidEmailAddress(email)){
            lblCustomerError.setText("E-mail invalid");
            txtAddCustomerEmail.requestFocus();
        }
        else if(phone.equals("")){
            txtAddCustomerPhone.requestFocus();
            lblCustomerError.setText("Phone is cannot be empty");
        }
        else if(address.equals("")){
            txtAddCustomerAddress.requestFocus();
            lblCustomerError.setText("Address is cannot be empty");
        }
        else{
            lblCustomerError.setText("");
            Customer customer = new Customer(0,name,surname,email,phone,address);
            return customer;
        }
        return null;
    }


    private void btnAddCategoryClick(ActionEvent e) {
        ProductCategory c=fncDataValidCategory();
        int status=productCategoryImpl.categoryInsert(c);
        if (status>0){
            System.out.println("Ekleme Başarılı");
            tblCategory.setModel(productCategoryImpl.categoryTableModel());
            txtCategoryName.setText("");
            txtCategoryInfo.setText("");

        }
        else {
            if (status==-1){
                System.out.println("Insert Error");

            }


        }
    }

    private void btnUpdateCategoryClick(ActionEvent e) {
        //view.MainApp mainApp=new view.MainApp();
       // row=tblCategory.getSelectedRow();

        String categoryName= txtCategoryName.getText();
        String categoryInfo= txtCategoryInfo.getText();
        ProductCategory productCategory1= new ProductCategory(value,categoryName,categoryInfo);
        if(row!=-1) {
            int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to update the customer");
            if (answer==0){
                productCategoryImpl.categoryUpdate(productCategory1);
                tblCategory.setModel(productCategoryImpl.categoryTableModel());
                txtCategoryName.setText("");
                txtCategoryInfo.setText("");

                row=-1;
            }

        }
        JOptionPane.showMessageDialog(this,"Please Choose");
    }

    private void btnDeleteCategoryClick(ActionEvent e) {
        if (tblCategory.getSelectedRow() != -1) {
            int cid=(Integer.parseInt(String.valueOf(tblCategory.getValueAt(tblCategory.getSelectedRow() , 0))));
            if(productCategoryImpl.categoryDeleteControl(cid)){
                JOptionPane.showMessageDialog(this,"You cannot delete this category. this category has a sale!");
            }else{
                int input = JOptionPane.showConfirmDialog(this, "Request delete are you sure?","Deletion process",JOptionPane.YES_NO_OPTION);
                if(input==0){
                    productCategoryImpl.categoryDelete(value);
                    tblCategory.setModel(productCategoryImpl.categoryTableModel()); //tabloyu refresh et
                    txtCategoryName.setText("");
                    txtCategoryInfo.setText("");
                    row=-1;
                }
            }
        }
        else
            JOptionPane.showMessageDialog(this,"Please make a choice!");
    }

    private void tblCategoryMouseClicked(MouseEvent e) {
        rowValueCategory();
    }

    private void txtSearchCategoryKeyReleased(KeyEvent e) {
        String txtSearch=txtSearchCategory.getText().toLowerCase(Locale.ROOT).trim();
        tblCategory.setModel( productCategoryImpl.categorySearch(txtSearch));
    }

    private void btnProductListEditClick(ActionEvent e) {
        if (tblProducts.getSelectedRow() != -1) {
            txtEditProductName.setText(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 1)));

            //bunda hata cıkıyordu duzenlenecek
            String  categoryId0 =String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 2));
            //System.out.println("ct "+categoryId0);
            //cmbEditProductCategory.setSelectedIndex(Integer.parseInt(categoryId0)-1);

            txtEditProductBuying.setText(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 3)));
            txtEditProductSelling.setText(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 4)));
            txtEditProductStatement.setText(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 5)));
            txtEditProductStock.setText(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 6)));
        }
        else
            JOptionPane.showMessageDialog(this,"Please make a choice!");
    }

    private void btnProductListDeleteClick(ActionEvent e) {
        // TODO add your code here
        if (tblProducts.getSelectedRow() != -1) {
            int pid=(Integer.parseInt(String.valueOf(tblProducts.getValueAt(tblProducts.getSelectedRow() , 0))));
            if(productImpl.productDeleteControl(pid)){
                JOptionPane.showMessageDialog(this,"You cannot delete this product. this product has a sale!");
            }else{
                int input = JOptionPane.showConfirmDialog(this, "Request delete are you sure?","Deletion process",JOptionPane.YES_NO_OPTION);
                if(input==0){
                    productImpl.productDelete(pid);
                    ProductImpl pr=new ProductImpl(-1);
                    tblProducts.setModel(pr.productTable(null));
                }
            }
        }
        else
            JOptionPane.showMessageDialog(this,"Please make a choice!");
        fncClearProduct();
    }

    private void btnAddProductClick(ActionEvent e) {
        // TODO add your code here
        Product p=productAddValidate();
        if(p !=null){
            int status = productImpl.productInsert(p);
            if(status > 0){
                ProductImpl productImpl=new ProductImpl(-1);
                tblProducts.setModel(productImpl.productTable(null));
                lblAddProduct.setText("Product Insert succes !");
            }
            else {
                lblAddProduct.setText("Product Insert Error !");
            }
            fncClearProduct();
            pack();
        }
    }

    private void btnEditProductClick(ActionEvent e) {
        Product p=productUpdateValidate();
        if(p !=null){
            int status = productImpl.productUpdate(p);
            System.out.println(status);
            if(status > 0){
                ProductImpl productImpl2=new ProductImpl(-1);
                tblProducts.setModel(productImpl2.productTable(null));
                lblEditProduct.setText("Product Update succes !");
            }
            else {
                lblEditProduct.setText("Product Update Error !");
            }
            fncClearProduct();
            pack();
        }
    }

    private void btnExitClick(ActionEvent e) {
        System.exit(0);
    }

    private void btnCustomerListEdit(ActionEvent e) {
        // TODO add your code here

    }

    private void btnCustomerListDelete(ActionEvent e) {

        if (tblCustomer.getSelectedRow() != -1) {
            int customerId=(Integer.parseInt(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 0))));
            if(customerImpl.customerDeleteControl(customerId)){
                JOptionPane.showMessageDialog(this,"You cannot delete this customer. this customer has a sale!");
            }else{
                int input = JOptionPane.showConfirmDialog(this, "Request delete are you sure?","Deletion process",JOptionPane.YES_NO_OPTION);
                if(input==0){
                    customerImpl.customerDelete(customerId);
                    CustomerImpl customer0=new CustomerImpl();
                    tblCustomer.setModel(customer0.customerTableModel());
                }
            }
        }
        else
            JOptionPane.showMessageDialog(this,"Please make a choice!");
    }

    private void btnAddCustomer(ActionEvent e) {
        // TODO add your code here
        Customer c = fncAddCustomerDatavalidate();
        if(c != null){
            int status = customerImpl.customerInsert(c);
            if( status > 0 ){
                String name = txtAddCustomerName.getText();
                String surname = txtAddCustomerSurname.getText();
                String email = txtAddCustomerEmail.getText();
                String phone = txtAddCustomerPhone.getText();
                String address = txtAddCustomerAddress.getText();
                //tablo yenilensin diye
                CustomerImpl customer = new CustomerImpl();
                tblCustomer.setModel(customer.customerTableModel());
                lblCustomerError.setText("Succesful !");
                fncTextClear();
            }
            else {
                if(status == -1)
                    lblCustomerError.setText("Another customer with the same email information!");
                else if(status == -2)
                    lblCustomerError.setText("Another customer with the same phone information!");
                else {
                    lblCustomerError.setText("Insert Error !");
                    fncTextClear();
                }
            }
            pack();
        }
    }

    private void btnEditCustomer(ActionEvent e) {
        // TODO add your code here
        Customer ce = fncEditCustomerDatavalidate();
        if (ce != null) {
            int status = customerImpl.customerUpdate (ce);
            if (tblCustomer.getSelectedRow() != -1) {
                System.out.println(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0));
                int input = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit?", "edit process", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no, 2=cancel
                if (input == 0) {
                    String name = txtEditCustomerName.getText();
                    String surname = txtEditCustomerSurname.getText();
                    String email = txtEditCustomerEmail.getText();
                    String phone = txtEditCustomerPhone.getText();
                    String address = txtEditCustomerAddress.getText();
                    Customer customer = new Customer(Integer.parseInt(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0))), name, surname, email, phone, address);
                    customerImpl.customerUpdate(customer);
                    //tablo yenilensin diye
                    CustomerImpl customer0 = new CustomerImpl();
                    tblCustomer.setModel(customer0.customerTableModel());
                    fncTextClear();
                }
                System.out.println("input :" + input);
            } else
                JOptionPane.showMessageDialog(this, "Please Choose");

            pack();
            fncTextClear();
            JOptionPane.showMessageDialog(this, "Edit Succesful");
            //lblError.setText("Updated proceses succesful");
        }
    }

    private void tblCustomerMouseClicked(MouseEvent e) {
        // TODO add your code here
        System.out.println(rowValueCustomer(tblCustomer.getSelectedRow(),tblCustomer.getSelectedColumn()));
    }

    private void btnCustomerListEditMouseReleased(MouseEvent e) {
        // TODO add your code here
        if (tblCustomer.getSelectedRow() != -1) {
            txtEditCustomerName.setText(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 1)));
            txtEditCustomerSurname.setText(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 2)));
            txtEditCustomerEmail.setText(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 3)));
            txtEditCustomerPhone.setText(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 4)));
            txtEditCustomerAddress.setText(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 5)));
        }
        else
            JOptionPane.showMessageDialog(this,"Please Choose" );
    }

    private void btnSaleListClick(ActionEvent e) {
        int categoryId = Integer.parseInt(((ComboItem)cmbList.getSelectedItem()).getValue());
        String categoryValue = ((ComboItem)cmbList.getSelectedItem()).getKey();
        System.out.println(categoryId);
        System.out.println(categoryValue);
        ProductImpl product2=new ProductImpl(categoryId);

        tblSale.setModel(product2.productTable(null));

    }

    private void tblSaleMouseClicked(MouseEvent e) {
        if (tblSale.getSelectedRow() != -1) {
            txtSaleSelect.setText(String.valueOf(tblSale.getValueAt(tblSale.getSelectedRow() , 1)));
        }
        else
            JOptionPane.showMessageDialog(this,"Please Choose" );
    }

    private void btnSaleProcessClick(ActionEvent e) {
        if (tblSale.getSelectedRow() != -1) {
            //int sid, int customerID, int productID, String date,int count , int status,String uuid
            int customerId =Integer.parseInt(((ComboItem)cmbSaleCustomer.getSelectedItem()).getValue());
            int productID = Integer.parseInt(String.valueOf(tblSale.getValueAt(tblSale.getSelectedRow() , 0)));
            String date=utils.Util.dateTimeNow();
            int count ;
            count=Integer.parseInt(txtSalePiece.getText());
            int status=0;

            //customerId ye gore uuid ve sepet düzenlendi
            //sepete sorgu at
            // şayet aynı customerId den statusu 0 olan varsa onun uuid sini al aşagıdaki uuid ye ata
            //yoksa yeni bir uuid üret ve aşagıdaki uuid ye ata
            String uuid="";
            if(basketImpl.basketControl(customerId)!=null)
                uuid=basketImpl.basketControl(customerId);
            else
               uuid=UUID.randomUUID().toString();
            int categoryId=Integer.parseInt(String.valueOf(tblSale.getValueAt(tblSale.getSelectedRow() , 2)));
            //stock kontrolu
            //count stocktan buyuk ise sepete ekleme yapmaz
            if(basketImpl.stockControl(productID,count)==1){
                Basket basket=new Basket(0,customerId,productID,date,count,status,uuid,categoryId);
                basketImpl.basketInsert(basket);
            }else
                lblSales.setText("Stock is insufficient");
        }
        else
            JOptionPane.showMessageDialog(this,"Please Choose" );
    }

    private void btnOpenBasketClick(ActionEvent e) {
        BasketScreen basketScreen=new BasketScreen();
        basketScreen.setVisible(true);
    }

    private void txtSearchReportKeyReleased(KeyEvent e) {
        String data=txtSearchReport.getText().trim();
        Boolean customer=rdbCustomer.isSelected();
        Boolean product=rdbProduct.isSelected();
        Boolean category=rdbCategory.isSelected();
        if (category){
            tblReport.setModel(reportImpl.reportTableModel(txtSearchReport.getText().toLowerCase(Locale.ROOT),1));
            lblError.setText("");

        }else if (customer) {
            tblReport.setModel(reportImpl.reportTableModel(txtSearchReport.getText().toLowerCase(Locale.ROOT),2));
            lblError.setText("");
        }
        else if (product) {
            tblReport.setModel(reportImpl.reportTableModel(txtSearchReport.getText().toLowerCase(Locale.ROOT),3));
            lblError.setText("");
        }
        else {
            tblReport.setModel(reportImpl.reportTableModel(null,-1));
            lblError.setText("Please Select Area!!!");

        }

    }

    private void tabbedPane1StateChanged(ChangeEvent e) {
        if(tabbedPane1.getSelectedIndex()==5){
            txtSearchReport.setText("");
        }    
    }

    private void datePicker1PropertyChangeClick(PropertyChangeEvent e) {
        if(datePicker1.getDate() != null ){
                datest = datePicker1.getDate().toString();
                tblReportUpdated();
        }
    }
        
    private void datePicker2PropertyChangeClick(PropertyChangeEvent e) {
            if(datePicker2.getDate() != null ){
                datend = datePicker2.getDate().toString();
                tblReportUpdated();
            }
    }
    private void tblReportUpdated() {
        reportImpl=new ReportImpl(datest,datend);
        tblReport.setModel(reportImpl.reportTableModel(null,-1));
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        panel19 = new JPanel();
        scrollPane4 = new JScrollPane();
        tblCustomer = new JTable();
        panel20 = new JPanel();
        btnCustomerListEdit = new JButton();
        btnCustomerListDelete = new JButton();
        pnlAddCustomer = new JPanel();
        lblAddCName = new JLabel();
        l = new JLabel();
        lblAddCSurname = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        txtAddCustomerName = new JTextField();
        txtAddCustomerSurname = new JTextField();
        txtAddCustomerEmail = new JTextField();
        txtAddCustomerPhone = new JTextField();
        txtAddCustomerAddress = new JTextField();
        btnAddCustomer = new JButton();
        pnlEditCustomer = new JPanel();
        label30 = new JLabel();
        label31 = new JLabel();
        label32 = new JLabel();
        label33 = new JLabel();
        label34 = new JLabel();
        txtEditCustomerName = new JTextField();
        txtEditCustomerSurname = new JTextField();
        txtEditCustomerEmail = new JTextField();
        txtEditCustomerPhone = new JTextField();
        txtEditCustomerAddress = new JTextField();
        btnEditCustomer = new JButton();
        lblCustomerError = new JLabel();
        panel2 = new JPanel();
        panel10 = new JPanel();
        label1 = new JLabel();
        rdbCustomer = new JRadioButton();
        rdbProduct = new JRadioButton();
        label2 = new JLabel();
        txtSearchReport = new JTextField();
        label62 = new JLabel();
        label66 = new JLabel();
        datePicker1 = new DatePicker();
        datePicker2 = new DatePicker();
        rdbCategory = new JRadioButton();
        panel12 = new JPanel();
        scrollPane2 = new JScrollPane();
        tblReport = new JTable();
        panel3 = new JPanel();
        panel15 = new JPanel();
        cmbList = new JComboBox();
        btnSaleList = new JButton();
        lblSaleList = new JLabel();
        scrollPane9 = new JScrollPane();
        tblSale = new JTable();
        lblSales = new JLabel();
        panel29 = new JPanel();
        label48 = new JLabel();
        txtSaleSelect = new JTextField();
        label49 = new JLabel();
        txtSalePiece = new JTextField();
        label50 = new JLabel();
        cmbSaleCustomer = new JComboBox();
        btnSaleProcess = new JButton();
        btnOpenBasket = new JButton();
        panel4 = new JPanel();
        panel11 = new JPanel();
        scrollPane3 = new JScrollPane();
        tblProducts = new JTable();
        panel16 = new JPanel();
        btnProductListEdit = new JButton();
        btnProductListDelete = new JButton();
        panel17 = new JPanel();
        txtAddProductName = new JTextField();
        label36 = new JLabel();
        label52 = new JLabel();
        label43 = new JLabel();
        label53 = new JLabel();
        label54 = new JLabel();
        label55 = new JLabel();
        cmbAddProductCategory = new JComboBox();
        txtAddProductBuying = new JTextField();
        txtAddProductSelling = new JTextField();
        txtAddProductStock = new JTextField();
        txtAddProductStatement = new JTextField();
        btnAddProduct = new JButton();
        lblAddProduct = new JLabel();
        panel18 = new JPanel();
        txtEditProductName = new JTextField();
        label37 = new JLabel();
        label57 = new JLabel();
        label44 = new JLabel();
        label58 = new JLabel();
        label59 = new JLabel();
        label60 = new JLabel();
        cmbEditProductCategory = new JComboBox();
        txtEditProductBuying = new JTextField();
        txtEditProductSelling = new JTextField();
        txtEditProductStock = new JTextField();
        txtEditProductStatement = new JTextField();
        btnEditProduct = new JButton();
        lblEditProduct = new JLabel();
        panel6 = new JPanel();
        pnlUserAdd = new JPanel();
        lblError = new JLabel();
        lblName = new JLabel();
        lblSurname = new JLabel();
        lblEmail = new JLabel();
        lblPassword = new JLabel();
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JTextField();
        btnAddUser = new JButton();
        panel5 = new JPanel();
        panel23 = new JPanel();
        scrollPane5 = new JScrollPane();
        tblCategory = new JTable();
        label3 = new JLabel();
        txtSearchCategory = new JTextField();
        panel25 = new JPanel();
        label38 = new JLabel();
        label39 = new JLabel();
        txtCategoryName = new JTextField();
        scrollPane6 = new JScrollPane();
        txtCategoryInfo = new JTextArea();
        btnAddCategory = new JButton();
        btnUpdateCategory = new JButton();
        btnDeleteCategoryList = new JButton();
        btnExit = new JButton();
        label45 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {
            tabbedPane1.setForeground(Color.blue);
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(Font.BOLD|Font.ITALIC));
            tabbedPane1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.red, Color.yellow, Color.green, Color.blue));
            tabbedPane1.setBackground(Color.red);
            tabbedPane1.addChangeListener(e -> tabbedPane1StateChanged(e));

            //======== panel1 ========
            {

                //======== panel19 ========
                {

                    //======== scrollPane4 ========
                    {
                        scrollPane4.setViewportView(tblCustomer);
                    }

                    //======== panel20 ========
                    {
                        panel20.setBorder(new TitledBorder("Operations"));

                        //---- btnCustomerListEdit ----
                        btnCustomerListEdit.setText("Edit");
                        btnCustomerListEdit.setIcon(new ImageIcon(getClass().getResource("/edit32.png")));
                        btnCustomerListEdit.setForeground(Color.red);
                        btnCustomerListEdit.setFont(btnCustomerListEdit.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                        btnCustomerListEdit.setBackground(Color.yellow);
                        btnCustomerListEdit.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                btnCustomerListEditMouseReleased(e);
                            }
                        });

                        //---- btnCustomerListDelete ----
                        btnCustomerListDelete.setText("Delete");
                        btnCustomerListDelete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
                        btnCustomerListDelete.setForeground(Color.red);
                        btnCustomerListDelete.setFont(btnCustomerListDelete.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                        btnCustomerListDelete.setBackground(Color.yellow);
                        btnCustomerListDelete.addActionListener(e -> btnCustomerListDelete(e));

                        GroupLayout panel20Layout = new GroupLayout(panel20);
                        panel20.setLayout(panel20Layout);
                        panel20Layout.setHorizontalGroup(
                            panel20Layout.createParallelGroup()
                                .addGroup(panel20Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel20Layout.createParallelGroup()
                                        .addComponent(btnCustomerListEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel20Layout.createSequentialGroup()
                                            .addComponent(btnCustomerListDelete)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                        );
                        panel20Layout.setVerticalGroup(
                            panel20Layout.createParallelGroup()
                                .addGroup(panel20Layout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCustomerListEdit, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCustomerListDelete, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15))
                        );
                    }

                    GroupLayout panel19Layout = new GroupLayout(panel19);
                    panel19.setLayout(panel19Layout);
                    panel19Layout.setHorizontalGroup(
                        panel19Layout.createParallelGroup()
                            .addGroup(panel19Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panel20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25))
                    );
                    panel19Layout.setVerticalGroup(
                        panel19Layout.createParallelGroup()
                            .addGroup(panel19Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel19Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panel20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //======== pnlAddCustomer ========
                {
                    pnlAddCustomer.setBorder(new TitledBorder(null, "Add Customer", TitledBorder.LEADING, TitledBorder.TOP));

                    //---- lblAddCName ----
                    lblAddCName.setText("Name");

                    //---- l ----
                    l.setText("E-Mail");

                    //---- lblAddCSurname ----
                    lblAddCSurname.setText("Surname");

                    //---- label28 ----
                    label28.setText("Phone");

                    //---- label29 ----
                    label29.setText("Address");

                    //---- btnAddCustomer ----
                    btnAddCustomer.setText("Add");
                    btnAddCustomer.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                    btnAddCustomer.setForeground(Color.red);
                    btnAddCustomer.setFont(btnAddCustomer.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                    btnAddCustomer.setBackground(Color.yellow);
                    btnAddCustomer.addActionListener(e -> btnAddCustomer(e));

                    GroupLayout pnlAddCustomerLayout = new GroupLayout(pnlAddCustomer);
                    pnlAddCustomer.setLayout(pnlAddCustomerLayout);
                    pnlAddCustomerLayout.setHorizontalGroup(
                        pnlAddCustomerLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlAddCustomerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlAddCustomerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlAddCustomerLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnAddCustomer))
                                    .addGroup(pnlAddCustomerLayout.createSequentialGroup()
                                        .addGroup(pnlAddCustomerLayout.createParallelGroup()
                                            .addGroup(pnlAddCustomerLayout.createSequentialGroup()
                                                .addComponent(lblAddCName)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, pnlAddCustomerLayout.createSequentialGroup()
                                                .addGroup(pnlAddCustomerLayout.createParallelGroup()
                                                    .addComponent(lblAddCSurname)
                                                    .addComponent(label28, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label29, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(l, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                                .addGap(24, 24, 24)))
                                        .addGroup(pnlAddCustomerLayout.createParallelGroup()
                                            .addComponent(txtAddCustomerAddress, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddCustomerSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddCustomerName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddCustomerEmail, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddCustomerPhone, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28))
                    );
                    pnlAddCustomerLayout.setVerticalGroup(
                        pnlAddCustomerLayout.createParallelGroup()
                            .addGroup(pnlAddCustomerLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlAddCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAddCName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddCustomerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(pnlAddCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAddCSurname, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddCustomerSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlAddCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(l, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddCustomerEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlAddCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label28, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddCustomerPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlAddCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label29, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddCustomerAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddCustomer))
                    );
                }

                //======== pnlEditCustomer ========
                {
                    pnlEditCustomer.setBorder(new TitledBorder(null, "Edit Customer", TitledBorder.LEADING, TitledBorder.TOP));

                    //---- label30 ----
                    label30.setText("Name");

                    //---- label31 ----
                    label31.setText("E-Mail");

                    //---- label32 ----
                    label32.setText("Surname");

                    //---- label33 ----
                    label33.setText("Phone");

                    //---- label34 ----
                    label34.setText("Address");

                    //---- btnEditCustomer ----
                    btnEditCustomer.setText("Edit");
                    btnEditCustomer.setIcon(new ImageIcon(getClass().getResource("/edit32.png")));
                    btnEditCustomer.setForeground(Color.red);
                    btnEditCustomer.setFont(btnEditCustomer.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                    btnEditCustomer.setBackground(Color.yellow);
                    btnEditCustomer.addActionListener(e -> btnEditCustomer(e));

                    GroupLayout pnlEditCustomerLayout = new GroupLayout(pnlEditCustomer);
                    pnlEditCustomer.setLayout(pnlEditCustomerLayout);
                    pnlEditCustomerLayout.setHorizontalGroup(
                        pnlEditCustomerLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlEditCustomerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlEditCustomerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnEditCustomer))
                                    .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                                        .addGroup(pnlEditCustomerLayout.createParallelGroup()
                                            .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                                                .addComponent(label30)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, pnlEditCustomerLayout.createSequentialGroup()
                                                .addGroup(pnlEditCustomerLayout.createParallelGroup()
                                                    .addComponent(label32)
                                                    .addComponent(label33, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label34, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label31, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                                .addGap(24, 24, 24)))
                                        .addGroup(pnlEditCustomerLayout.createParallelGroup()
                                            .addComponent(txtEditCustomerAddress, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEditCustomerSurname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEditCustomerName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEditCustomerEmail, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEditCustomerPhone, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28))
                    );
                    pnlEditCustomerLayout.setVerticalGroup(
                        pnlEditCustomerLayout.createParallelGroup()
                            .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlEditCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label30, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditCustomerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(pnlEditCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label32, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditCustomerSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlEditCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label31, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditCustomerEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlEditCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label33, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditCustomerPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlEditCustomerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label34, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditCustomerAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditCustomer))
                    );
                }

                //---- lblCustomerError ----
                lblCustomerError.setText("text");

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(lblCustomerError, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(pnlAddCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pnlEditCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(panel19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(3, Short.MAX_VALUE))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(panel19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(pnlAddCustomer, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlEditCustomer, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCustomerError)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Customers", panel1);

            //======== panel2 ========
            {

                //======== panel10 ========
                {
                    panel10.setBorder(new TitledBorder("Filters"));

                    //---- label1 ----
                    label1.setText("Search Location");

                    //---- rdbCustomer ----
                    rdbCustomer.setText("Customers");
                    rdbCustomer.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    //---- rdbProduct ----
                    rdbProduct.setText("Products");
                    rdbProduct.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    //---- label2 ----
                    label2.setText("Search");

                    //---- txtSearchReport ----
                    txtSearchReport.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            txtSearchReportKeyReleased(e);
                            txtSearchReportKeyReleased(e);
                            txtSearchReportKeyReleased(e);
                        }
                    });

                    //---- label62 ----
                    label62.setIcon(new ImageIcon(getClass().getResource("/tkvm.png")));

                    //---- label66 ----
                    label66.setText("and");

                    //---- datePicker1 ----
                    datePicker1.setBackground(new Color(255, 204, 204));
                    datePicker1.addPropertyChangeListener(e -> datePicker1PropertyChangeClick(e));

                    //---- datePicker2 ----
                    datePicker2.setBackground(new Color(255, 204, 204));
                    datePicker2.addPropertyChangeListener(e -> datePicker2PropertyChangeClick(e));

                    //---- rdbCategory ----
                    rdbCategory.setText("Category");
                    rdbCategory.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    GroupLayout panel10Layout = new GroupLayout(panel10);
                    panel10.setLayout(panel10Layout);
                    panel10Layout.setHorizontalGroup(
                        panel10Layout.createParallelGroup()
                            .addGroup(panel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel10Layout.createParallelGroup()
                                    .addGroup(panel10Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(label62)
                                        .addGap(18, 18, 18)
                                        .addComponent(datePicker1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(label66)
                                        .addGap(51, 51, 51)
                                        .addComponent(datePicker2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel10Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(GroupLayout.Alignment.LEADING, panel10Layout.createSequentialGroup()
                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdbCustomer)
                                            .addGap(104, 104, 104)
                                            .addComponent(rdbProduct)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(rdbCategory))
                                        .addGroup(GroupLayout.Alignment.LEADING, panel10Layout.createSequentialGroup()
                                            .addComponent(label2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSearchReport, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(81, Short.MAX_VALUE))
                    );
                    panel10Layout.setVerticalGroup(
                        panel10Layout.createParallelGroup()
                            .addGroup(panel10Layout.createSequentialGroup()
                                .addGroup(panel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(rdbCustomer)
                                    .addComponent(rdbProduct)
                                    .addComponent(rdbCategory))
                                .addGap(18, 18, 18)
                                .addGroup(panel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(txtSearchReport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addGroup(panel10Layout.createParallelGroup()
                                    .addComponent(label62, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(datePicker1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datePicker2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label66)))
                                .addGap(20, 20, 20))
                    );
                }

                //======== panel12 ========
                {
                    panel12.setBorder(new TitledBorder("Results"));

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(tblReport);
                    }

                    GroupLayout panel12Layout = new GroupLayout(panel12);
                    panel12.setLayout(panel12Layout);
                    panel12Layout.setHorizontalGroup(
                        panel12Layout.createParallelGroup()
                            .addGroup(panel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane2)
                                .addContainerGap())
                    );
                    panel12Layout.setVerticalGroup(
                        panel12Layout.createParallelGroup()
                            .addGroup(panel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(panel12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(panel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Reports", panel2);

            //======== panel3 ========
            {

                //======== panel15 ========
                {
                    panel15.setBorder(new TitledBorder("Sales Screen"));

                    //---- btnSaleList ----
                    btnSaleList.setText("List");
                    btnSaleList.setIcon(new ImageIcon(getClass().getResource("/list.png")));
                    btnSaleList.setForeground(Color.red);
                    btnSaleList.setFont(btnSaleList.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                    btnSaleList.setBackground(Color.yellow);
                    btnSaleList.addActionListener(e -> btnSaleListClick(e));

                    //---- lblSaleList ----
                    lblSaleList.setText("text");

                    //======== scrollPane9 ========
                    {

                        //---- tblSale ----
                        tblSale.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                tblSaleMouseClicked(e);
                            }
                        });
                        scrollPane9.setViewportView(tblSale);
                    }

                    //---- lblSales ----
                    lblSales.setText("text");

                    //======== panel29 ========
                    {
                        panel29.setBorder(new TitledBorder("Sales Process"));

                        //---- label48 ----
                        label48.setText("Selected");

                        //---- label49 ----
                        label49.setText("Piece");

                        //---- label50 ----
                        label50.setText("Customer");

                        //---- btnSaleProcess ----
                        btnSaleProcess.setText("Complete");
                        btnSaleProcess.setIcon(new ImageIcon(getClass().getResource("/complate.png")));
                        btnSaleProcess.setForeground(Color.red);
                        btnSaleProcess.setFont(btnSaleProcess.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                        btnSaleProcess.setBackground(Color.yellow);
                        btnSaleProcess.addActionListener(e -> btnSaleProcessClick(e));

                        GroupLayout panel29Layout = new GroupLayout(panel29);
                        panel29.setLayout(panel29Layout);
                        panel29Layout.setHorizontalGroup(
                            panel29Layout.createParallelGroup()
                                .addGroup(panel29Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel29Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label50, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                        .addComponent(label49, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label48, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel29Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSaleSelect)
                                        .addComponent(txtSalePiece)
                                        .addComponent(cmbSaleCustomer, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                    .addGap(27, 27, 27)
                                    .addComponent(btnSaleProcess)
                                    .addContainerGap(92, Short.MAX_VALUE))
                        );
                        panel29Layout.setVerticalGroup(
                            panel29Layout.createParallelGroup()
                                .addGroup(panel29Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel29Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnSaleProcess)
                                        .addGroup(panel29Layout.createSequentialGroup()
                                            .addGroup(panel29Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label48)
                                                .addComponent(txtSaleSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panel29Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label49)
                                                .addComponent(txtSalePiece, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panel29Layout.createParallelGroup()
                                                .addComponent(label50)
                                                .addComponent(cmbSaleCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                    .addContainerGap(34, Short.MAX_VALUE))
                        );
                    }

                    //---- btnOpenBasket ----
                    btnOpenBasket.setText("Basket");
                    btnOpenBasket.setIcon(new ImageIcon(getClass().getResource("/basket.png")));
                    btnOpenBasket.setForeground(Color.red);
                    btnOpenBasket.setFont(btnOpenBasket.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                    btnOpenBasket.setBackground(Color.yellow);
                    btnOpenBasket.addActionListener(e -> btnOpenBasketClick(e));

                    GroupLayout panel15Layout = new GroupLayout(panel15);
                    panel15.setLayout(panel15Layout);
                    panel15Layout.setHorizontalGroup(
                        panel15Layout.createParallelGroup()
                            .addGroup(panel15Layout.createSequentialGroup()
                                .addGroup(panel15Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel15Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(cmbList, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSaleList))
                                    .addGroup(panel15Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(panel15Layout.createParallelGroup()
                                            .addGroup(panel15Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGroup(panel15Layout.createSequentialGroup()
                                                    .addComponent(lblSaleList, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(329, 329, 329))
                                                .addComponent(lblSales, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, panel15Layout.createSequentialGroup()
                                                .addComponent(panel29, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnOpenBasket)
                                                .addGap(12, 12, 12))))
                                    .addComponent(scrollPane9, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(81, Short.MAX_VALUE))
                    );
                    panel15Layout.setVerticalGroup(
                        panel15Layout.createParallelGroup()
                            .addGroup(panel15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel15Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSaleList))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSaleList)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane9, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSales)
                                .addGroup(panel15Layout.createParallelGroup()
                                    .addGroup(panel15Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panel29, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel15Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(btnOpenBasket)))
                                .addContainerGap(40, Short.MAX_VALUE))
                    );
                }

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(panel15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Sales Management", panel3);

            //======== panel4 ========
            {

                //======== panel11 ========
                {
                    panel11.setBorder(new TitledBorder("Product List"));

                    //======== scrollPane3 ========
                    {
                        scrollPane3.setViewportView(tblProducts);
                    }

                    //======== panel16 ========
                    {
                        panel16.setBorder(new TitledBorder("Operations"));

                        //---- btnProductListEdit ----
                        btnProductListEdit.setText("Edit");
                        btnProductListEdit.setIcon(new ImageIcon(getClass().getResource("/edit32.png")));
                        btnProductListEdit.setForeground(Color.red);
                        btnProductListEdit.setFont(btnProductListEdit.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                        btnProductListEdit.setBackground(Color.yellow);
                        btnProductListEdit.addActionListener(e -> btnProductListEditClick(e));

                        //---- btnProductListDelete ----
                        btnProductListDelete.setText("Delete");
                        btnProductListDelete.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
                        btnProductListDelete.setFont(btnProductListDelete.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                        btnProductListDelete.setForeground(Color.red);
                        btnProductListDelete.setBackground(Color.yellow);
                        btnProductListDelete.addActionListener(e -> {
			btnProductListDeleteClick(e);
			btnProductListDeleteClick(e);
		});

                        GroupLayout panel16Layout = new GroupLayout(panel16);
                        panel16.setLayout(panel16Layout);
                        panel16Layout.setHorizontalGroup(
                            panel16Layout.createParallelGroup()
                                .addGroup(panel16Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(panel16Layout.createParallelGroup()
                                        .addComponent(btnProductListDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnProductListEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(28, 28, 28))
                        );
                        panel16Layout.setVerticalGroup(
                            panel16Layout.createParallelGroup()
                                .addGroup(panel16Layout.createSequentialGroup()
                                    .addComponent(btnProductListEdit, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnProductListDelete)
                                    .addGap(12, 12, 12))
                        );
                    }

                    GroupLayout panel11Layout = new GroupLayout(panel11);
                    panel11.setLayout(panel11Layout);
                    panel11Layout.setHorizontalGroup(
                        panel11Layout.createParallelGroup()
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    panel11Layout.setVerticalGroup(
                        panel11Layout.createParallelGroup()
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                }

                //======== panel17 ========
                {
                    panel17.setBorder(new TitledBorder(null, "Add New Product", TitledBorder.LEADING, TitledBorder.TOP));

                    //---- label36 ----
                    label36.setText("Category");

                    //---- label52 ----
                    label52.setText("Product");

                    //---- label43 ----
                    label43.setText("Buying");

                    //---- label53 ----
                    label53.setText("Selling");

                    //---- label54 ----
                    label54.setText("Stock");

                    //---- label55 ----
                    label55.setText("Statement");

                    //---- btnAddProduct ----
                    btnAddProduct.setText("Add");
                    btnAddProduct.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                    btnAddProduct.setForeground(Color.red);
                    btnAddProduct.setFont(btnAddProduct.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                    btnAddProduct.setBackground(Color.yellow);
                    btnAddProduct.addActionListener(e -> btnAddProductClick(e));

                    //---- lblAddProduct ----
                    lblAddProduct.setText("text");

                    GroupLayout panel17Layout = new GroupLayout(panel17);
                    panel17.setLayout(panel17Layout);
                    panel17Layout.setHorizontalGroup(
                        panel17Layout.createParallelGroup()
                            .addGroup(panel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel17Layout.createParallelGroup()
                                    .addGroup(panel17Layout.createSequentialGroup()
                                        .addComponent(lblAddProduct, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                        .addComponent(btnAddProduct))
                                    .addGroup(panel17Layout.createSequentialGroup()
                                        .addGroup(panel17Layout.createParallelGroup()
                                            .addGroup(panel17Layout.createSequentialGroup()
                                                .addComponent(label55, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAddProductStatement, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel17Layout.createSequentialGroup()
                                                .addComponent(label54, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAddProductStock, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel17Layout.createSequentialGroup()
                                                .addComponent(label53, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAddProductSelling, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel17Layout.createSequentialGroup()
                                                .addComponent(label43, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAddProductBuying, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(panel17Layout.createSequentialGroup()
                                                    .addComponent(label52, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtAddProductName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panel17Layout.createSequentialGroup()
                                                    .addComponent(label36, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(cmbAddProductCategory))))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    panel17Layout.setVerticalGroup(
                        panel17Layout.createParallelGroup()
                            .addGroup(panel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label52, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label36, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbAddProductCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label43, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddProductBuying, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label53, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddProductSelling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label54, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddProductStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel17Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label55, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddProductStatement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel17Layout.createParallelGroup()
                                    .addGroup(panel17Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(lblAddProduct))
                                    .addGroup(panel17Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
                    );
                }

                //======== panel18 ========
                {
                    panel18.setBorder(new TitledBorder(null, "Edit Product", TitledBorder.LEADING, TitledBorder.TOP));

                    //---- label37 ----
                    label37.setText("Category");

                    //---- label57 ----
                    label57.setText("Product");

                    //---- label44 ----
                    label44.setText("Buying");

                    //---- label58 ----
                    label58.setText("Selling");

                    //---- label59 ----
                    label59.setText("Stock");

                    //---- label60 ----
                    label60.setText("Statement");

                    //---- btnEditProduct ----
                    btnEditProduct.setText("Edit");
                    btnEditProduct.setIcon(new ImageIcon(getClass().getResource("/edit32.png")));
                    btnEditProduct.setForeground(Color.red);
                    btnEditProduct.setFont(btnEditProduct.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                    btnEditProduct.setBackground(new Color(255, 255, 51));
                    btnEditProduct.addActionListener(e -> {
			btnEditProductClick(e);
			btnEditProductClick(e);
		});

                    //---- lblEditProduct ----
                    lblEditProduct.setText("text");

                    GroupLayout panel18Layout = new GroupLayout(panel18);
                    panel18.setLayout(panel18Layout);
                    panel18Layout.setHorizontalGroup(
                        panel18Layout.createParallelGroup()
                            .addGroup(panel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel18Layout.createParallelGroup()
                                    .addGroup(panel18Layout.createSequentialGroup()
                                        .addGroup(panel18Layout.createParallelGroup()
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(lblEditProduct, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                                .addComponent(btnEditProduct, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(label60, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtEditProductStatement, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(label59, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtEditProductStock, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(label58, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtEditProductSelling, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(label44, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtEditProductBuying, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(9, 9, 9))
                                    .addGroup(panel18Layout.createSequentialGroup()
                                        .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(label57, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtEditProductName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel18Layout.createSequentialGroup()
                                                .addComponent(label37, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cmbEditProductCategory)))
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    );
                    panel18Layout.setVerticalGroup(
                        panel18Layout.createParallelGroup()
                            .addGroup(panel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label57, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label37, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEditProductCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label44, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditProductBuying, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label58, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditProductSelling, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label59, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditProductStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel18Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label60, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEditProductStatement, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel18Layout.createParallelGroup()
                                    .addGroup(panel18Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(lblEditProduct)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel18Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEditProduct)
                                        .addContainerGap())))
                    );
                }

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(panel4Layout.createParallelGroup()
                                .addComponent(panel11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addComponent(panel17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panel18, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(4, Short.MAX_VALUE))
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel11, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel4Layout.createParallelGroup()
                                .addComponent(panel17, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addComponent(panel18, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Products", panel4);

            //======== panel6 ========
            {
                panel6.setForeground(Color.cyan);

                //======== pnlUserAdd ========
                {
                    pnlUserAdd.setBorder(new TitledBorder("User Add"));

                    //---- lblError ----
                    lblError.setText(" ");

                    //---- lblName ----
                    lblName.setText("Name");
                    lblName.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- lblSurname ----
                    lblSurname.setText("Surname");
                    lblSurname.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- lblEmail ----
                    lblEmail.setText("Email");
                    lblEmail.setHorizontalAlignment(SwingConstants.CENTER);

                    //---- lblPassword ----
                    lblPassword.setText("Password");
                    lblPassword.setHorizontalAlignment(SwingConstants.CENTER);

                    GroupLayout pnlUserAddLayout = new GroupLayout(pnlUserAdd);
                    pnlUserAdd.setLayout(pnlUserAddLayout);
                    pnlUserAddLayout.setHorizontalGroup(
                        pnlUserAddLayout.createParallelGroup()
                            .addGroup(pnlUserAddLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlUserAddLayout.createParallelGroup()
                                    .addGroup(pnlUserAddLayout.createSequentialGroup()
                                        .addGroup(pnlUserAddLayout.createParallelGroup()
                                            .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlUserAddLayout.createParallelGroup()
                                            .addGroup(pnlUserAddLayout.createSequentialGroup()
                                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlUserAddLayout.createSequentialGroup()
                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlUserAddLayout.createSequentialGroup()
                                        .addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    pnlUserAddLayout.setVerticalGroup(
                        pnlUserAddLayout.createParallelGroup()
                            .addGroup(pnlUserAddLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(pnlUserAddLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlUserAddLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlUserAddLayout.createParallelGroup()
                                    .addGroup(pnlUserAddLayout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                                    .addGroup(pnlUserAddLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlUserAddLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlUserAddLayout.createParallelGroup()
                                            .addGroup(pnlUserAddLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlUserAddLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    );
                }

                //---- btnAddUser ----
                btnAddUser.setText("Add User");
                btnAddUser.setIcon(new ImageIcon(getClass().getResource("/adduser.png")));
                btnAddUser.setForeground(Color.red);
                btnAddUser.setFont(btnAddUser.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                btnAddUser.setBackground(Color.yellow);
                btnAddUser.addActionListener(e -> btnAddUserClicked(e));

                GroupLayout panel6Layout = new GroupLayout(panel6);
                panel6.setLayout(panel6Layout);
                panel6Layout.setHorizontalGroup(
                    panel6Layout.createParallelGroup()
                        .addGroup(panel6Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(panel6Layout.createParallelGroup()
                                .addComponent(pnlUserAdd, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddUser, GroupLayout.Alignment.TRAILING))
                            .addContainerGap(191, Short.MAX_VALUE))
                );
                panel6Layout.setVerticalGroup(
                    panel6Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                            .addContainerGap(33, Short.MAX_VALUE)
                            .addComponent(pnlUserAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAddUser, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(154, 154, 154))
                );
            }
            tabbedPane1.addTab("User Settings", panel6);

            //======== panel5 ========
            {

                //======== panel23 ========
                {
                    panel23.setBorder(new TitledBorder("Category List"));

                    //======== scrollPane5 ========
                    {

                        //---- tblCategory ----
                        tblCategory.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                tblCategoryMouseClicked(e);
                            }
                        });
                        scrollPane5.setViewportView(tblCategory);
                    }

                    //---- label3 ----
                    label3.setText("Search");

                    //---- txtSearchCategory ----
                    txtSearchCategory.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            txtSearchCategoryKeyReleased(e);
                        }
                    });

                    GroupLayout panel23Layout = new GroupLayout(panel23);
                    panel23.setLayout(panel23Layout);
                    panel23Layout.setHorizontalGroup(
                        panel23Layout.createParallelGroup()
                            .addGroup(panel23Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel23Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchCategory, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                    );
                    panel23Layout.setVerticalGroup(
                        panel23Layout.createParallelGroup()
                            .addGroup(panel23Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(panel23Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSearchCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE))
                    );
                }

                //======== panel25 ========
                {
                    panel25.setBorder(new TitledBorder("Add and Update Category"));

                    //---- label38 ----
                    label38.setText("Category Name");

                    //---- label39 ----
                    label39.setText("Explanation");

                    //======== scrollPane6 ========
                    {
                        scrollPane6.setViewportView(txtCategoryInfo);
                    }

                    GroupLayout panel25Layout = new GroupLayout(panel25);
                    panel25.setLayout(panel25Layout);
                    panel25Layout.setHorizontalGroup(
                        panel25Layout.createParallelGroup()
                            .addGroup(panel25Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel25Layout.createParallelGroup()
                                    .addGroup(panel25Layout.createSequentialGroup()
                                        .addComponent(label38, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCategoryName, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel25Layout.createSequentialGroup()
                                        .addComponent(label39, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane6)))
                                .addGap(108, 108, 108))
                    );
                    panel25Layout.setVerticalGroup(
                        panel25Layout.createParallelGroup()
                            .addGroup(panel25Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel25Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label38, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCategoryName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel25Layout.createParallelGroup()
                                    .addGroup(panel25Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label39, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel25Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))))
                    );
                }

                //---- btnAddCategory ----
                btnAddCategory.setText("Add");
                btnAddCategory.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                btnAddCategory.setForeground(Color.red);
                btnAddCategory.setFont(btnAddCategory.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                btnAddCategory.setBackground(Color.yellow);
                btnAddCategory.addActionListener(e -> btnAddCategoryClick(e));

                //---- btnUpdateCategory ----
                btnUpdateCategory.setText("Update");
                btnUpdateCategory.setIcon(new ImageIcon(getClass().getResource("/edit32.png")));
                btnUpdateCategory.setForeground(Color.red);
                btnUpdateCategory.setFont(btnUpdateCategory.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                btnUpdateCategory.setBackground(Color.yellow);
                btnUpdateCategory.addActionListener(e -> btnUpdateCategoryClick(e));

                //---- btnDeleteCategoryList ----
                btnDeleteCategoryList.setText("Delete");
                btnDeleteCategoryList.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
                btnDeleteCategoryList.setFont(btnDeleteCategoryList.getFont().deriveFont(Font.BOLD|Font.ITALIC));
                btnDeleteCategoryList.setForeground(Color.red);
                btnDeleteCategoryList.setBackground(Color.yellow);
                btnDeleteCategoryList.addActionListener(e -> btnDeleteCategoryClick(e));

                GroupLayout panel5Layout = new GroupLayout(panel5);
                panel5.setLayout(panel5Layout);
                panel5Layout.setHorizontalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addGap(138, 138, 138)
                            .addComponent(btnAddCategory)
                            .addGap(18, 18, 18)
                            .addComponent(btnUpdateCategory, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(btnDeleteCategoryList, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                            .addGap(242, 242, 242))
                        .addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel23, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
                panel5Layout.setVerticalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel25, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panel5Layout.createParallelGroup()
                                .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUpdateCategory, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDeleteCategoryList))
                                .addComponent(btnAddCategory))
                            .addGap(16, 16, 16))
                );
            }
            tabbedPane1.addTab("Categories", panel5);

            tabbedPane1.setSelectedIndex(0);
        }

        //---- btnExit ----
        btnExit.setText("Exit");
        btnExit.setIcon(new ImageIcon(getClass().getResource("/exitn.png")));
        btnExit.setForeground(Color.red);
        btnExit.setFont(btnExit.getFont().deriveFont(Font.BOLD|Font.ITALIC));
        btnExit.setBackground(Color.gray);
        btnExit.addActionListener(e -> btnExitClick(e));

        //---- label45 ----
        label45.setText("Admin");

        //---- label4 ----
        label4.setText("Shopping Registration Program");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setForeground(Color.red);
        label4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));

        //---- label5 ----
        label5.setIcon(new ImageIcon(getClass().getResource("/shop.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                    .addGap(51, 51, 51)
                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                    .addGap(93, 93, 93)
                    .addComponent(label45, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnExit))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label45)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
                    .addGap(43, 43, 43))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rdbCustomer);
        buttonGroup1.add(rdbProduct);
        buttonGroup1.add(rdbCategory);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panel19;
    private JScrollPane scrollPane4;
    private JTable tblCustomer;
    private JPanel panel20;
    private JButton btnCustomerListEdit;
    private JButton btnCustomerListDelete;
    private JPanel pnlAddCustomer;
    private JLabel lblAddCName;
    private JLabel l;
    private JLabel lblAddCSurname;
    private JLabel label28;
    private JLabel label29;
    private JTextField txtAddCustomerName;
    private JTextField txtAddCustomerSurname;
    private JTextField txtAddCustomerEmail;
    private JTextField txtAddCustomerPhone;
    private JTextField txtAddCustomerAddress;
    private JButton btnAddCustomer;
    private JPanel pnlEditCustomer;
    private JLabel label30;
    private JLabel label31;
    private JLabel label32;
    private JLabel label33;
    private JLabel label34;
    private JTextField txtEditCustomerName;
    private JTextField txtEditCustomerSurname;
    private JTextField txtEditCustomerEmail;
    private JTextField txtEditCustomerPhone;
    private JTextField txtEditCustomerAddress;
    private JButton btnEditCustomer;
    private JLabel lblCustomerError;
    private JPanel panel2;
    private JPanel panel10;
    private JLabel label1;
    private JRadioButton rdbCustomer;
    private JRadioButton rdbProduct;
    private JLabel label2;
    private JTextField txtSearchReport;
    private JLabel label62;
    private JLabel label66;
    private DatePicker datePicker1;
    private DatePicker datePicker2;
    private JRadioButton rdbCategory;
    private JPanel panel12;
    private JScrollPane scrollPane2;
    private JTable tblReport;
    private JPanel panel3;
    private JPanel panel15;
    private JComboBox cmbList;
    private JButton btnSaleList;
    private JLabel lblSaleList;
    private JScrollPane scrollPane9;
    private JTable tblSale;
    private JLabel lblSales;
    private JPanel panel29;
    private JLabel label48;
    private JTextField txtSaleSelect;
    private JLabel label49;
    private JTextField txtSalePiece;
    private JLabel label50;
    private JComboBox cmbSaleCustomer;
    private JButton btnSaleProcess;
    private JButton btnOpenBasket;
    private JPanel panel4;
    private JPanel panel11;
    private JScrollPane scrollPane3;
    private JTable tblProducts;
    private JPanel panel16;
    private JButton btnProductListEdit;
    private JButton btnProductListDelete;
    private JPanel panel17;
    private JTextField txtAddProductName;
    private JLabel label36;
    private JLabel label52;
    private JLabel label43;
    private JLabel label53;
    private JLabel label54;
    private JLabel label55;
    private JComboBox cmbAddProductCategory;
    private JTextField txtAddProductBuying;
    private JTextField txtAddProductSelling;
    private JTextField txtAddProductStock;
    private JTextField txtAddProductStatement;
    private JButton btnAddProduct;
    private JLabel lblAddProduct;
    private JPanel panel18;
    private JTextField txtEditProductName;
    private JLabel label37;
    private JLabel label57;
    private JLabel label44;
    private JLabel label58;
    private JLabel label59;
    private JLabel label60;
    private JComboBox cmbEditProductCategory;
    private JTextField txtEditProductBuying;
    private JTextField txtEditProductSelling;
    private JTextField txtEditProductStock;
    private JTextField txtEditProductStatement;
    private JButton btnEditProduct;
    private JLabel lblEditProduct;
    private JPanel panel6;
    private JPanel pnlUserAdd;
    private JLabel lblError;
    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JTextField txtPassword;
    private JButton btnAddUser;
    private JPanel panel5;
    private JPanel panel23;
    private JScrollPane scrollPane5;
    private JTable tblCategory;
    private JLabel label3;
    private JTextField txtSearchCategory;
    private JPanel panel25;
    private JLabel label38;
    private JLabel label39;
    private JTextField txtCategoryName;
    private JScrollPane scrollPane6;
    private JTextArea txtCategoryInfo;
    private JButton btnAddCategory;
    private JButton btnUpdateCategory;
    private JButton btnDeleteCategoryList;
    private JButton btnExit;
    private JLabel label45;
    private JLabel label4;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
