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
public class Collect {
    private Integer collectId;
    private String bookName;
    private Timestamp collectDate;
    private String readerName;



    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", bookName='" + bookName + '\'' +
                ", collectDate=" + collectDate +
                ", readerName='" + readerName + '\'' +
                '}';
    }
}
