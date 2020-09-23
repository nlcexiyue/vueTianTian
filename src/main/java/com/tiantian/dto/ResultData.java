package com.tiantian.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/3/17
 * \* Time: 9:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ResultData implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public ResultData(Object data) {
        this.code = 0;
        this.msg = "ok";
        this.data = data;
    }

    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResultData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * ok
     *
     * @return code=200, msg=ok
     */
    public static  ResultData ok() {
        return new ResultData(0, "ok");
    }

    /**
     * 正常返回数据
     *
     * @param data
     *            数据
     * @return code=200, msg=ok, data=data
     */
    public static  ResultData ok(Object data) {
        return new ResultData(data);
    }

    /**
     * 正常返回数据
     *
     * @param code
     * 			 	状态码
     * @param data
     *          	数据
     * @return code=code, data=data
     */
    public static ResultData ok(Integer code, Object data) {
        return new ResultData(code, null, data);
    }

    /**
     * 自定义返回结果
     *
     * @param code
     *            状态码
     * @param msg
     *            相应消息
     * @param data
     *            相应具体内容
     *
     * @return code=code, msg=msg, data=data
     */
    public static  ResultData result(Integer code, String msg, Object data) {
        return new ResultData(code, msg, data);
    }

    /**
     * 返回500
     *
     * @param msg
     *            相应消息
     * @return code=500
     */
    public static  ResultData error() {
        return new ResultData(500, null, null);
    }

    /**
     * 自定义错误状态
     *
     * @param code
     *            状态码
     * @param msg
     *            错误信息
     * @return
     */
    public static ResultData error(Integer code, String msg) {
        return new ResultData(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultUtil [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}
