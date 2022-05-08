/*
 * Created by JFormDesigner on Thu Apr 14 00:30:01 TRT 2022
 */

package view;

import java.awt.event.*;

import model.UserImpl;
import utils.Util;
import view.user.UserChangePassword;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class UserPanel extends JFrame {
    UserImpl userImpl=new UserImpl();
    public static String to="";
    public static String sub="";
    public static String msg="";

    public static void main(String[] args) {
        new UserPanel().setVisible(true);
    }

    public UserPanel() {
        initComponents();
        txtPassword.setText("JavaProject123");
        txtEmail.setText("javaproject96@gmail.com");

    }

    private void btnLoginClicked(ActionEvent e) {
        userLogin();
    }

    private void txtEmailKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            userLogin();
        }
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            userLogin();
        }
    }

    private void btnNewUserClicked(ActionEvent e) {
        new NewUser().setVisible(true);
        dispose();
    }

    private void btnChangePasswordClicked(ActionEvent e) {
        to= txtEmail.getText();
        sub="Java Project Change Password";
        msg="\n\n\t\tVerification Code: "+userImpl.verificationCode;
        Util.sendMail(to,sub,msg);

       new UserChangePassword().setVisible(true);
       dispose();
    }

    private void btnForgetPasswordClicked(ActionEvent e) {
        to= txtEmail.getText();
        sub="Java Project Forget Password";
        msg="\n\n\t\tVerification Code: "+userImpl.verificationCode;
        Util.sendMail(to,sub,msg);

        new UserChangePassword().setVisible(true);
        dispose();
    }

    public void userLogin(){
        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        String password= String.valueOf(txtPassword.getPassword()).trim();
        if(email.equals("")){
            txtEmail.requestFocus();
            lblError.setText("Please Enter E-Mail");
        }else if(!Util.isValidEmailAddress(email)){
            txtPassword.requestFocus();
            lblError.setText("E-Mail Format Error");
        }else if(password.length()==0){

            txtPassword.requestFocus();
            lblError.setText("Please Enter Password");
        }else {
            lblError.setText("");
            boolean status=userImpl.userLogin(email,password);
            if(status){
                lblError.setText("!!!! BAŞARILI !!!!");
            }else{
                lblError.setText("!!!! BAŞARISIZ !!!!");
            }
        }
    }









    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton();
        btnNewUser = new JButton();
        btnChangePassword = new JButton();
        btnForgetPassword = new JButton();
        lblError = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("EMAIL");

        //---- label2 ----
        label2.setText("PASSWORD");

        //---- txtEmail ----
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- txtPassword ----
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- btnLogin ----
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(e -> btnLoginClicked(e));

        //---- btnNewUser ----
        btnNewUser.setText("NEW USER");
        btnNewUser.addActionListener(e -> btnNewUserClicked(e));

        //---- btnChangePassword ----
        btnChangePassword.setText("CHANGE PASSWORD");
        btnChangePassword.addActionListener(e -> btnChangePasswordClicked(e));

        //---- btnForgetPassword ----
        btnForgetPassword.setText("FORGET PASSWORD");
        btnForgetPassword.addActionListener(e -> btnForgetPasswordClicked(e));

        //---- lblError ----
        lblError.setText(" ");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassword))
                            .addContainerGap(311, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnForgetPassword, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(btnChangePassword, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(btnNewUser, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(btnLogin, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                            .addContainerGap(375, Short.MAX_VALUE))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 56, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(lblError)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(label2))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
                    .addGap(34, 34, 34)
                    .addComponent(btnLogin)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnNewUser)
                    .addGap(18, 18, 18)
                    .addComponent(btnChangePassword)
                    .addGap(18, 18, 18)
                    .addComponent(btnForgetPassword)
                    .addContainerGap(31, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnNewUser;
    private JButton btnChangePassword;
    private JButton btnForgetPassword;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
