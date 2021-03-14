package com.example.userinformation;

class User{
String id;
String username;
String userNumber;
String userAddress;
private  User(){}
    User(String id, String username, String userNumber, String userAddress) {
        this.id = id;
        this.username = username;
        this.userNumber = userNumber;
        this.userAddress = userAddress;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}