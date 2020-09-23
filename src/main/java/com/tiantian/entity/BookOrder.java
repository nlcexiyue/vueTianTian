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
public class BookOrder {
    private Integer orderid;
    private Double orderPrice;
    private Timestamp orderDate;
    private  Integer  userId;
    private  Integer bookId;
    private  Integer discountId;
    private  Integer orderStatus;
    private  Integer payStatus;
    private  String payWay;


    private Book book;

    @Override
    public String toString() {
        return "BookOrder{" +
                "orderid=" + orderid +
                ", orderPrice=" + orderPrice +
                ", orderDate=" + orderDate +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", discountId=" + discountId +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", payWay=" + payWay +
                '}';
    }
}
