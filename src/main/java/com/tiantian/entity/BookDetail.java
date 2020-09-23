package com.tiantian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class BookDetail {

    private Integer picid;
    private String picAddress;
    private Integer bookId;
    private String bookBref;

}
