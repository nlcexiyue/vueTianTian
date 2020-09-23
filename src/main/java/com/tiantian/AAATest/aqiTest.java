package com.tiantian.AAATest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/12
 * \* Time: 16:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class aqiTest {

    public static void main(String[] args) {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // mysql驱动
            con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.2.177:3306/novel?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8",
                    "root", "root");
            while(true){
                Statement ps =  (Statement) con.createStatement();
                String sql = "select * from city";
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    // 循环输出结果集
                    String cityCode = rs.getString("code");
                    String cityId = rs.getString("id");
                    String url = "http://www.nmc.cn/rest/weather?stationid=";
                    url = url + cityCode;
                    Date date = new Date();
                    long time = date.getTime();
                    url = url  + "&_="+time;
                    ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
                    String body = forEntity.getBody();

                    JSONObject jsonObject = JSON.parseObject(body);
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    JSONObject jsonObjectAir = jsonObjectData.getJSONObject("air");
                    if (jsonObjectAir != null){
                        String forecasttime = jsonObjectAir.getString("forecasttime");
                        Statement ps2 =  (Statement) con.createStatement();
                        String sql2 = "select * from air where forecasttime = \'"+forecasttime+"\' and city_id = \'"+cityId+"\'";
                        ResultSet rs2 = ps2.executeQuery(sql2);
                        if(!rs2.next()){
                            String aq = jsonObjectAir.getString("aq");
                            String aqi = jsonObjectAir.getString("aqi");
                            String text = jsonObjectAir.getString("text");
                            Statement ps1 =  (Statement) con.createStatement();
                            String sql1 = "insert into air values (null ,\'"+cityId+"\',\'"+aq+"\',\'"+aqi+"\',\'"+text +"\',\'"+forecasttime+"\' )";
                            ps1.execute(sql1);
                        }
                    }
                    Thread.sleep(1000);
                }
                // 完成后关闭
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("MYSQL error" + e.getMessage());
        }






    }
}