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
public class Chapter {
    private Integer chapterId;
    private Integer bookId;
    private String chapterName;
    private String chapterContent;
    private Integer chapterWordNumber;
    private Timestamp updateTime;


    @Override
    public String toString() {
        return "Chapter{" +
                "chapterId=" + chapterId +
                ", bookId=" + bookId +
                ", chapterName='" + chapterName + '\'' +
                ", chapterContent='" + chapterContent + '\'' +
                ", chapterWordNumber=" + chapterWordNumber +
                ", updateTime=" + updateTime +
                '}';
    }
}
