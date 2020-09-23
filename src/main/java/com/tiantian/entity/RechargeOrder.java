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
public class RechargeOrder {
    private Integer rechargeId;
    private Integer userId;
    private Integer rechargeMoney;
    private Timestamp rechargeDate;
    private Integer rechargeStatus;
    private String rechargeWay;


    @Override
    public String toString() {
        return "RechargeOrder{" +
                "rechargeId=" + rechargeId +
                ", userId=" + userId +
                ", rechargeMoney=" + rechargeMoney +
                ", rechargeDate=" + rechargeDate +
                ", rechargeStatus=" + rechargeStatus +
                ", rechargeWay='" + rechargeWay + '\'' +
                '}';
    }
}