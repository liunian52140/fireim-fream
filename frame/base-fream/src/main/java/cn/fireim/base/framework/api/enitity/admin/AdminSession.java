package cn.fireim.base.framework.api.enitity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdminSession {
    private BigInteger id;
    private String userName;
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

    public  AdminSession(Admin admin){
        this.id = admin.getId();
        this.userName = admin.getUserName();
        this.nickName = admin.getNickName();
        this.birthday = admin.getBirthday();
        this.address = admin.getAddress();
        this.mobilePhone = admin.getMobilePhone();
        this.telPhone = admin.getTelPhone();
        this.email = admin.getEmail();
        this.sex = admin.getSex();
        this.type = admin.getType();
        this.status = admin.getStatus();
        this.description = admin.getDescription();
        this.crtTime = admin.getCrtTime();
        this.crtUser = admin.getCrtUser();
        this.crtName = admin.getCrtName();
        this.crtHost = admin.getCrtHost();
        this.uptTime = admin.getUptTime();
        this.uptUser = admin.getUptUser();
        this.uptName = admin.getUserName();
        this.uptHost = admin.getUptHost();
    }
}
