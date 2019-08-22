package cn.fireim.base.framework.api.enitity.admin;

import lombok.Data;

import java.math.BigInteger;

/**
 * admin用户
 */
@Data
public class Admin {
    private BigInteger id;
    private String userName;
    private String passWord;
    private String offsetValue;
    private String nickName;
    private String birthday;
    private String address;
    private String mobilePhone;
    private String telPhone;
    private String email;
    private Byte sex;
    private Byte type;
    private Byte status;
    private String description;
    private String crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
    private String uptTime;
    private String uptUser;
    private String uptName;
    private String uptHost;
}
