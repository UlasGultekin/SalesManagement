/*
 * Created by JFormDesigner on Thu Apr 14 01:19:47 TRT 2022
 */

package view.user;

import model.UserImpl;
import props.User;
import utils.Util;
import view.user.UserPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class UserChangePassword extends JFrame {


    UserImpl userImpl=new UserImpl();
    public static String rePassword="";

    public UserChangePassword() {
        initComponents();
        txtEmail.setText(UserImpl.emailAddress);
    }

    private void thisWindowClosing(WindowEvent e) {
        new UserPanel().setVisible(true);
    }



    private void btnChangePasswordClicked(ActionEvent e) {

        User user=fncDataValid();

        int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update?","Update Window",JOptionPane.YES_OPTION);
        if(answer==0){
                userImpl.userChangePassword(user);
        }else{
            JOptionPane.showMessageDialog(this,"Please Choose!");
        }
    }

    public User fncDataValid(){

        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        String oldPassword=txtOldPassword.getText();
        String newPassword=txtNewPassword.getText();
        rePassword=txtRePassword.getText();

        if(email.equals("")){
           lblError.setText("Please Enter Email");
            txtEmail.requestFocus();
        }else if(!email.equals(UserImpl.emailAddress)){
            lblError.setText("Please Enter a Valid Email Address");
            txtEmail.requestFocus();
        }else if(oldPassword.equals("")){
            lblError.setText("Please Enter Old Password");
            txtOldPassword.requestFocus();
        }else if(!oldPassword.equals(UserImpl.userPassword)){
            lblError.setText("Password Want to Change Does Not Match");
            txtOldPassword.requestFocus();
        }else if(newPassword.equals("")){
            lblError.setText("Please Enter New Password");
            txtNewPassword.requestFocus();
        }else if(rePassword.equals("")){
            lblError.setText("Please Enter RePassword");
            txtRePassword.requestFocus();
        }else if(!newPassword.equals(rePassword)){
            lblError.setText("New Password and RePassword do not Match");
            txtNewPassword.requestFocus();
        }else {
            lblError.setText("Password Change Succesfull");
            User user=new User(email,rePassword);

            return user;
        }
        return null;
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        panel1 = new JPanel();
        label5 = new JLabel();
        txtEmail = new JTextField();
        txtNewPassword = new JTextField();
        txtRePassword = new JTextField();
        btnChangePassword = new JButton();
        lblError = new JLabel();
        label4 = new JLabel();
        txtOldPassword = new JTextField();

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
        label1.setText("EMAIL");
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("NEW PASSWORD");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("REPASSWORD");
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        //======== panel1 ========
        {
            panel1.setForeground(SystemColor.activeCaption);
            panel1.setBackground(SystemColor.activeCaption);

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGap(0, 130, Short.MAX_VALUE)
            );
        }

        //---- label5 ----
        label5.setText("CHANGE PASSWORD");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
        label5.setForeground(Color.cyan);

        //---- btnChangePassword ----
        btnChangePassword.setText("CHANGE");
        btnChangePassword.addActionListener(e -> btnChangePasswordClicked(e));

        //---- lblError ----
        lblError.setText(" ");

        //---- label4 ----
        label4.setText("OLD PASSWORD");
        label4.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblError, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNewPassword, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                        .addComponent(txtRePassword, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(144, 144, 144)
                                    .addComponent(btnChangePassword))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtOldPassword, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOldPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNewPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRePassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btnChangePassword)
                    .addGap(18, 18, 18)
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
    private JPanel panel1;
    private JLabel label5;
    private JTextField txtEmail;
    private JTextField txtNewPassword;
    private JTextField txtRePassword;
    private JButton btnChangePassword;
    private JLabel lblError;
    private JLabel label4;
    private JTextField txtOldPassword;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
