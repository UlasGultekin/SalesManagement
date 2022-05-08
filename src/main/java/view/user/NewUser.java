/*
 * Created by JFormDesigner on Thu Apr 14 00:44:35 TRT 2022
 */

package view.user;

import model.UserImpl;
import props.Customer;
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
    }

    private void thisWindowClosing(WindowEvent e) {
       new UserPanel().setVisible(true);
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
        lblError = new JLabel();
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtEmail = new JTextField();
        label4 = new JLabel();
        txtPassword = new JTextField();
        btnAdd = new JButton();
        panel1 = new JPanel();
        label5 = new JLabel();

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
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("SURNAME");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("EMA\u0130L");
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        //---- lblError ----
        lblError.setText(" ");

        //---- label4 ----
        label4.setText("PASSWORD");
        label4.setHorizontalAlignment(SwingConstants.CENTER);

        //---- btnAdd ----
        btnAdd.setText("ADD");
        btnAdd.addActionListener(e -> btnAddClicked(e));

        //======== panel1 ========
        {
            panel1.setForeground(SystemColor.activeCaption);
            panel1.setBackground(SystemColor.activeCaption);

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 403, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 130, Short.MAX_VALUE)
            );
        }

        //---- label5 ----
        label5.setText("NEW USER");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
        label5.setForeground(Color.cyan);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label5, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 94, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtName)
                                .addComponent(txtSurname)
                                .addComponent(txtEmail)
                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(140, Short.MAX_VALUE))
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAdd)
                    .addGap(24, 24, 24)
                    .addComponent(lblError)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel lblError;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JLabel label4;
    private JTextField txtPassword;
    private JButton btnAdd;
    private JPanel panel1;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
