package com.aathavan.dbinstall.model;

import lombok.Data;

public class ServerCredentials {

    private String serverip;
    private String username;
    private String companyname;
    private String companycode;
    private String portno;

    public String getServerip() {
        return serverip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getCompanycode() {
        return companycode;
    }

    public String getPortno() {
        return portno;
    }

    private String password;

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public void setPortno(String portno) {
        this.portno = portno;
    }

}
