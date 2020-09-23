package com.tiantian.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class Message {
    private Integer messageId;
    private String messageContent;
    private Date messageDate;
    private  Integer readerId;
    private  Integer bookId;

    private Reader reader;


}
