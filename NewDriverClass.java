package com.example.ambulanceservice_app;

public class NewDriverClass {
    private String Id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNum;
    private String userName;
    private String password;
    private String image;
    private String agreegate;
    private String gender;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAgreegate() {
        return agreegate;
    }

    public void setAgreegate(String agreegate) {
        this.agreegate = agreegate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public NewDriverClass(){}
    public NewDriverClass(String Id, String firstName, String lastName, String gender,
                          String emailId, String phoneNum,
                          String userName, String password,
                          String agreegate,
                          String image) {
        setAgreegate(agreegate);
        setId(Id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmailId(emailId);
        setGender(gender);
        setPhoneNum(phoneNum);
        setUserName(userName);
        setPassword(password);
        setImage(image);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
