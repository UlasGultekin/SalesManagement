/*
 * Created by JFormDesigner on Thu Apr 14 00:44:35 TRT 2022
 */

package view;

import model.UserImpl;
import props.User;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class NewUser extends JFrame {
    UserImpl userImpl=new UserImpl();

    public NewUser() {
        initComponents();
        //UserImpl userImpl=new UserImpl();
    }

    private void thisWindowClosing(WindowEvent e) {
       new view.UserPanel().setVisible(true);
    }

    private void btnAddClicked(ActionEvent e) {
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

    public void fncTextClear(){
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtEmail = new JTextField();
        btnAdd = new JButton();
        txtPassword = new JTextField();
        lblError = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("NAME");

        //---- label2 ----
        label2.setText("SURNAME");

        //---- label3 ----
        label3.setText("EMA\u0130L");

        //---- label4 ----
        label4.setText("PASSWORD");

        //---- btnAdd ----
        btnAdd.setText("ADD");
        btnAdd.addActionListener(e -> btnAddClicked(e));

        //---- lblError ----
        lblError.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(17, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6))
                                .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPassword, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                    .addComponent(txtEmail, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(213, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))))
                    .addGap(57, 57, 57)
                    .addComponent(btnAdd)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                    .addComponent(lblError)
                    .addGap(46, 46, 46))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JButton btnAdd;
    private JTextField txtPassword;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
