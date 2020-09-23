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
public class Author {
    private Integer authorId;
    private String authorName;
    private Integer authorSex;
    private long authorPhone;
    private String authorLogo;
    private String authorHobby;
    private String authorFeature;
    private Timestamp authorAddTime;
    private Integer authorStatus;
    private String authorPwd;

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorSex=" + authorSex +
                ", authorPhone=" + authorPhone +
                ", authorLogo='" + authorLogo + '\'' +
                ", authorHobby='" + authorHobby + '\'' +
                ", authorFeature='" + authorFeature + '\'' +
                ", authorAddTime=" + authorAddTime +
                ", authorStatus=" + authorStatus +
                ", authorPwd='" + authorPwd + '\'' +
                '}';
    }
}
