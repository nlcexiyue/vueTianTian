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
public class History {
    private Integer historyId;
    private String readerName;
    private String bookName;
    private Integer bookType;
    private Timestamp historyTime;


    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", readerName='" + readerName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookType=" + bookType +
                ", historyTime=" + historyTime +
                '}';
    }
}
