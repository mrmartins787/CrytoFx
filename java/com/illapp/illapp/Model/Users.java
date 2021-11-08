package com.illapp.illapp.Model;

public class Users
{
    private String name, phone, ipassword,iwallet,ifullname,mail;

    public Users(){

    }
    public Users(String name, String phone, String password, String userwallet, String userfullname,String email ){
        this.name =name;
        this.phone=phone;
        this.ipassword=password;
        this.iwallet=userwallet;
        this.ifullname=userfullname;
        this.mail=email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return ipassword;
    }

    public void setPassword(String password) {
        this.ipassword = password;
    }


    //walllet

    public String getIwallet() {
        return iwallet;
    }

    public void setIwallet(String iwallet) {
        this.iwallet = iwallet;
    }

    //user full name
    public String getIfullname() {
        return ifullname;
    }

    public void setIfullname(String ifullname) {
        this.ifullname = ifullname;
    }

    //
    //user full name
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
