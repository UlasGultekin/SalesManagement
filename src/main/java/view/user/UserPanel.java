/*
 * Created by JFormDesigner on Thu Apr 14 00:30:01 TRT 2022
 */

package view.user;

import java.awt.event.*;
import javax.swing.border.*;

import model.ProductCategoryImpl;
import model.UserImpl;
import utils.Util;
import view.MainApp;
//import view.UserChangePassword;

import java.awt.*;
import java.util.Locale;
import java.util.Random;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class UserPanel extends JFrame {
    UserImpl userImpl=new UserImpl();
    //public static String to="";
    //public static String sub="";
    //public static String msg="";
    public static String emailChange="";
    boolean status=false;
    public static String verificationCode="";


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



    private void btnChangePasswordClicked(ActionEvent e) {
        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        status=userImpl.userGetEmail(email);
        if(status){
            new UserChangePassword().setVisible(true);
            dispose();
        }else{
            lblError.setText("Please Enter a Valid Email Address");
        }
    }

    private void btnForgetPasswordClicked(ActionEvent e) {
        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        status=userImpl.userGetEmail(email);
        if(status){
            generatingVerificationCode();


            String to= UserImpl.emailAddress;
            String sub="Java Project Forget Password";
            String msg="\n\n\t\tVerification Code: "+verificationCode;

            Util.sendMail(to,sub,msg);



            new UserForgetPassword().setVisible(true);
            dispose();
        }else{
            lblError.setText("Please Enter a Valid Email Address");
        }
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
            btnChangePassword.setVisible(true);
            btnForgetPassword.setVisible(true);
            emailChange= txtEmail.getText();
            txtPassword.requestFocus();
            lblError.setText("Please Enter Password");
        }else {
            lblError.setText("");
            boolean status=userImpl.userLogin(email,password);
            if(status){
                new MainApp().setVisible(true);
                dispose();
            }else{
                lblError.setText("Email or Password Incorrect");
            }
        }
    }

    public void generatingVerificationCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        verificationCode = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

         System.out.println(verificationCode);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton();
        btnChangePassword = new JButton();
        btnForgetPassword = new JButton();
        lblError = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/mailcon.png")));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/passcon.png")));
        label2.setHorizontalAlignment(SwingConstants.CENTER);

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
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/login.png")));
        btnLogin.addActionListener(e -> btnLoginClicked(e));

        //---- btnChangePassword ----
        btnChangePassword.setText("Change Password");
        btnChangePassword.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        btnChangePassword.setIcon(new ImageIcon(getClass().getResource("/32change.png")));
        btnChangePassword.addActionListener(e -> btnChangePasswordClicked(e));

        //---- btnForgetPassword ----
        btnForgetPassword.setText("Forget Password?");
        btnForgetPassword.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        btnForgetPassword.setIcon(new ImageIcon(getClass().getResource("/32brain.png")));
        btnForgetPassword.addActionListener(e -> btnForgetPasswordClicked(e));

        //---- lblError ----
        lblError.setText(" ");

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/256shopy.png")));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setBackground(Color.yellow);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnForgetPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
                    .addGap(19, 19, 19))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(btnLogin)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(18, 18, Short.MAX_VALUE)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
                            .addGap(48, 48, 48))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEmail)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnLogin)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnChangePassword)
                        .addComponent(btnForgetPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1))
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
    private JButton btnChangePassword;
    private JButton btnForgetPassword;
    private JLabel lblError;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
