/*
 * Created by JFormDesigner on Thu Apr 14 02:47:49 TRT 2022
 */

package view.user;

import model.UserImpl;
import props.User;
import view.user.UserPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class UserForgetPassword extends JFrame {
    UserImpl userImpl=new UserImpl();
    public UserForgetPassword() {
        initComponents();
        txtEmail.setText(UserImpl.emailAddress);
    }

    private void thisWindowClosing(WindowEvent e) {
       new UserPanel().setVisible(true);
    }

    private void btnForgetPasswordClicked(ActionEvent e) {
        User user=fncDataValid();

            if(txtverificationCode.getText().equals(UserPanel.verificationCode)){
                userImpl.userForgetPassword(user);
            }else{
                lblError.setText("Verification Code is Wrong");
            }

    }

    public User fncDataValid(){

        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        String verificationCode=txtverificationCode.getText().trim();
        String password=txtPassword.getText();

        if(email.equals("")){
            lblError.setText("Please Enter Email");
            txtEmail.requestFocus();
        }else if(!email.equals(UserImpl.emailAddress)){
            lblError.setText("Please Enter a Valid Email Address");
            txtEmail.requestFocus();
        }else if(verificationCode.equals("")){
            lblError.setText("Please Enter Verification Code");
            txtverificationCode.requestFocus();
        }else if(password.equals("")){
            lblError.setText("Please Enter New Password");
            txtPassword.requestFocus();
        }else {
            lblError.setText("Password Change Succesfull");
            User user=new User(email,password);

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
        txtverificationCode = new JTextField();
        txtPassword = new JTextField();
        btnForgetPassword = new JButton();
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
        label1.setText("EMAIL");
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("VERIFICATION CODE");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label3 ----
        label3.setText("PASSWORD");
        label3.setHorizontalAlignment(SwingConstants.CENTER);

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
        label5.setText("FORGET PASSWORD");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
        label5.setForeground(Color.cyan);

        //---- btnForgetPassword ----
        btnForgetPassword.setText("CHANGE");
        btnForgetPassword.addActionListener(e -> btnForgetPasswordClicked(e));

        //---- lblError ----
        lblError.setText(" ");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                        .addComponent(txtverificationCode, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                        .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(143, 143, 143)
                            .addComponent(btnForgetPassword)
                            .addGap(0, 158, Short.MAX_VALUE))
                        .addComponent(lblError, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtverificationCode, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btnForgetPassword)
                    .addGap(50, 50, 50)
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
    private JTextField txtverificationCode;
    private JTextField txtPassword;
    private JButton btnForgetPassword;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
