package com.tiantian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

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
public class Book {
    private Integer bookId;
    private String bookName;
    private Integer bookType;
    private String author;
    private Integer authorId;
    private Float bookPrice;
    private Timestamp releaseTime;
    private String updateSection;
    private String chapterNumber;
    private Integer condition;
    private Integer score;


    private Author author1;
    private List<Chapter> chapterList;
    private BookDetail bookDetail;



    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookType=" + bookType +
                ", author='" + author + '\'' +
                ", authorId=" + authorId +
                ", bookPrice=" + bookPrice +
                ", releaseTime=" + releaseTime +
                ", updateSection='" + updateSection + '\'' +
                ", chapterNumber='" + chapterNumber + '\'' +
                ", condition=" + condition +
                ", score=" + score +
                '}';
    }
}
