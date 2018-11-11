package cc.vipazoo.www.ui.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("username")
    private String name;
    @SerializedName("email")
    private String email_address;
    @SerializedName("password")
    private String passwd;
    private String token;
    public User()
    {
        name = null;
        passwd = null;
        email_address = null;
        token = null;
    }
    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String gettoken()
    {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public void settoken(String token)
    {
        this.token = token;
    }
}
