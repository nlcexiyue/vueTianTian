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
public class Discount {
    private Integer actId;
    private String actName;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer actStatus;


    @Override
    public String toString() {
        return "Discount{" +
                "actId=" + actId +
                ", actName='" + actName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", actStatus=" + actStatus +
                '}';
    }
}