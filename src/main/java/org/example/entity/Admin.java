package org.example.entity;

import lombok.Data;

@Data
public class Admin {
    public Integer id;
    public String adminAccount;
    public String adminName;
    public String password;
    public String mail;
    public String phoneNo;
    public String birthday;
    public String CreatTime;
    public String address;
    public String fileName;
    public Integer status;
    public Integer errorCount;

}
