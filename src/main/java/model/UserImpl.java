package model;


import props.User;
import utils.DB;
import utils.Util;
import view.user.UserChangePassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class UserImpl implements IUser{
    DB db=new DB();
    public static String name="";
    public static String emailAddress="";
    public static String userPassword="";
    public static int verificationCode = new Random().nextInt(900000) + 100000;

    @Override
    public boolean userLogin(String email, String password) {
        boolean status=false;
        try {
            String sql="select * from user where email=? and password=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,email);
            pre.setString(2, Util.MD5(password));
            ResultSet rs=pre.executeQuery();
            status=rs.next();
            if(status){
                name=rs.getString("name")+" "+rs.getString("surname");
            }
        }catch (Exception e){
            System.out.println("UserLogin Error: "+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int userInsert(User user) {
        int status=0;
        try {
            String sql="insert into user values(null,?,?,?,?)";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,user.getName());
            pre.setString(2,user.getSurname());
            pre.setString(3,user.getEmail());
            pre.setString(4,Util.MD5(user.getPassword()));
            status=pre.executeUpdate();
        }catch (Exception e){
            System.out.println("UserInsert Error: "+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int userUpdate(User user) {
        int status=0;
        try {
            String sql="update user set name=?,  surname=?,  email=?,  password=? where uid=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,user.getName());
            pre.setString(2,user.getSurname());
            pre.setString(3,user.getEmail());
            pre.setString(4, Util.MD5(user.getPassword()));
            pre.setInt(5, user.getUid());
            status= pre.executeUpdate();
        }catch (Exception e){
            System.err.println("UserUpdate Error: "+ e);
        }finally {
            db.close();
        }

        return status;
    }

    @Override
    public int userChangePassword(User user) {
        int status=0;
        try {
            String sql="update user set password=? where email=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,Util.MD5(UserChangePassword.rePassword));
            pre.setString(2,emailAddress);
            status=pre.executeUpdate();


        }catch (Exception e){
            System.out.println("userChangePassword Error: "+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public int userForgetPassword(User user) {
        int status=0;
        try {
            String sql="update user set password=? where email=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,Util.MD5(UserChangePassword.rePassword));
            pre.setString(2,emailAddress);
            status=pre.executeUpdate();

        }catch (Exception e){
            System.out.println("userForgetPassword Error: "+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public boolean userGetEmail(String email) {
        boolean status=false;
        try {
            String sql="Select * from user where email=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,email);

            ResultSet rs=pre.executeQuery();
            status= rs.next();
            if(status){
                emailAddress=rs.getString("email");
            }

        }catch (Exception e){
            System.err.println("userGetEmail Error"+e);
        }finally {
            db.close();
        }
        return status;
    }

    @Override
    public boolean userPassword(String password) {
        boolean status=false;
        try {
            String sql="Select  from user where password=?";
            PreparedStatement pre=db.connect().prepareStatement(sql);
            pre.setString(1,password);

            ResultSet rs=pre.executeQuery();
            status= rs.next();
            if(status)
                userPassword=rs.getString("password");

        }catch (Exception e){
            System.err.println("userPassword Error"+e);
        }finally {
            db.close();
        }
        return status;
    }
}