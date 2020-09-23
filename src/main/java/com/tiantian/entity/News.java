package com.tiantian.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer id;

    private String newsName;

    private String newsAuthor;

    private String newsAbstract;

    private Integer newsStatus;

    private String newsImg;

    private String newsLook;

    private String newsTop;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date newsTime;

    private String content;


}