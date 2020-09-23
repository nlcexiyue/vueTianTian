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
public class Reader {
    private Integer readerId;
    private String  readerName;
    private String readerAge;
    private Integer readerSex;
    private String readerEmail;
    private Long readerPhone;
    private Timestamp readerAddTime;
    private Integer readerStatus;
    private Double balance;
    private String password;


    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", readerName='" + readerName + '\'' +
                ", readerAge='" + readerAge + '\'' +
                ", readerSex=" + readerSex +
                ", readerEmail='" + readerEmail + '\'' +
                ", readerPhone=" + readerPhone +
                ", readerAddTime=" + readerAddTime +
                ", readerStatus=" + readerStatus +
                ", balance=" + balance +
                ", password='" + password + '\'' +
                '}';
    }
}
