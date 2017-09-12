package test.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import test.annotation.Uncheck;

import java.util.Date;

/**
 * Created by shen.yang on 2017/9/11.
 */
public class User {

    private  int id;
    @JsonProperty("userName")
    private   String name;
    @JsonIgnore
    private  String mobile;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    public User() {
    }

    public User(int id, String name, String mobile,Date birthday) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.mobile = mobile;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
