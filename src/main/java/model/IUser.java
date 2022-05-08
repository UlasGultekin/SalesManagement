package model;


import props.User;

public interface IUser {
boolean userLogin(String email,String password);
int userInsert(User user);
int userUpdate(User user);
int userChangePassword(User user);
int userForgetPassword(User user);
boolean userGetEmail(String email);
boolean userPassword(String password);



}