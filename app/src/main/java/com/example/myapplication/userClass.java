package com.example.myapplication;

public class userClass {

    private String userId;
    private String Email;
    private String password;
    private String Name;
    private boolean isLoggedIn;

    public userClass(String Name,String userId,String Email,String password,boolean isLoggedIn){
        this.userId = userId;
        this.password = password;
        this.Email = Email;
        this.Name = Name;
        this.isLoggedIn=isLoggedIn;
    }
    public userClass(){
        this.userId ="admin";
        this.password = "123";
        this.Email = "admin";
        this.Name = "admin";
        this.isLoggedIn=false;
    }

    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getEmail(){
        return this.Email;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setName(String Name){
        this.Name=Name;
    }
    public String getName(){
        return this.Name;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
