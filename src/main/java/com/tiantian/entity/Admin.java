package com.tiantian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/3/17
 * \* Time: 9:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Integer id;

    private String adminName;

    private String adminPwd;

    private Integer adminGender;

    private Long adminPhone;

    private Timestamp adminAddTime;

    private Integer adminStatus;

    private Integer adminType;

    public Admin(String adminName, String adminPwd, Integer adminGender, Long adminPhone) {
        this.adminName = adminName;
        this.adminPwd = adminPwd;
        this.adminGender = adminGender;
        this.adminPhone = adminPhone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminGender=" + adminGender +
                ", adminPhone=" + adminPhone +
                ", adminAddTime=" + adminAddTime +
                ", adminStatus=" + adminStatus +
                ", adminType=" + adminType +
                '}';
    }
}
