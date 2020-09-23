package com.tiantian.AAATest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/13
 * \* Time: 11:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class AllTest {
    public static void main(String[] args) {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // mysql驱动
            con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.2.177:3306/novel?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8",
                    "root", "root");
            while (true) {
                int a = 1;
                Statement ps = (Statement) con.createStatement();
                String sql = "select * from city";
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    a++;
                    if (a == 200) {
                        Thread.sleep(10000);
                        a = 1;
                    }


                    // 循环输出结果集
                    String cityCode = rs.getString("code");
                    String cityId = rs.getString("id");

                    Connection finalCon = con;
                    Thread thread = new Thread(() -> {
                        String url = "http://www.nmc.cn/rest/weather?stationid=";
                        url = url + cityCode;
                        Date date1 = new Date();
                        long time = date1.getTime();
                        url = url + "&_=" + time;
                        ResponseEntity<String> forEntity = new RestTemplate().getForEntity(url, String.class);
                        String body = forEntity.getBody();
                        JSONObject jsonObject = JSON.parseObject(body);
                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                        JSONObject jsonObjectReal = jsonObjectData.getJSONObject("real");
                        JSONObject jsonObjectPredict = jsonObjectData.getJSONObject("predict");
                        JSONObject jsonObjectAir = jsonObjectData.getJSONObject("air");

                        if (jsonObjectAir != null) {
                            String forecasttime = jsonObjectAir.getString("forecasttime");
                            Statement ps2 = null;
                            try {
                                ps2 = (Statement) finalCon.createStatement();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            String sql2 = "select * from air where forecasttime = \'" + forecasttime + "\' and city_id = \'" + cityId + "\'";
                            ResultSet rs2 = null;
                            try {
                                rs2 = ps2.executeQuery(sql2);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (!rs2.next()) {
                                    String aq = jsonObjectAir.getString("aq");
                                    String aqi = jsonObjectAir.getString("aqi");
                                    String text = jsonObjectAir.getString("text");


                                    Statement ps1 = (Statement) finalCon.createStatement();
                                    String sql1 = "insert into air values (null ,\'" + cityId + "\',\'" + aq + "\',\'" + aqi + "\',\'" + text + "\',\'" + forecasttime + "\' )";
                                    ps1.execute(sql1);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }


                        if (jsonObjectPredict != null) {
                            JSONArray detail = jsonObjectPredict.getJSONArray("detail");
                            for (Object o : detail) {
                                JSONObject jsonObject1 = JSON.parseObject(o.toString());
                                String date = jsonObject1.getString("date");

                                Statement ps2 = null;
                                try {
                                    ps2 = (Statement) finalCon.createStatement();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                String sql2 = "select * from predict where date = \'" + date + "\' and city_id = \'" + cityId + "\'";
                                ResultSet rs2 = null;
                                try {
                                    rs2 = ps2.executeQuery(sql2);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    if (!rs2.next()) {
                                        String pt = jsonObject1.getString("pt");
                                        JSONObject jsonObjectDay = jsonObject1.getJSONObject("day");
                                        JSONObject jsonObjectDayWeather = jsonObjectDay.getJSONObject("weather");
                                        String infoDay = jsonObjectDayWeather.getString("info");
                                        String imgDay = jsonObjectDayWeather.getString("img");
                                        String temperatureDay = jsonObjectDayWeather.getString("temperature");
                                        JSONObject jsonObjectDayWind = jsonObjectDay.getJSONObject("wind");
                                        String directDay = jsonObjectDayWind.getString("direct");
                                        String powerDay = jsonObjectDayWind.getString("power");

                                        JSONObject jsonObjectNight = jsonObject1.getJSONObject("night");
                                        JSONObject jsonObjectNightWeather = jsonObjectNight.getJSONObject("weather");
                                        String infoNight = jsonObjectNightWeather.getString("info");
                                        String imgNight = jsonObjectNightWeather.getString("img");
                                        String temperatureNight = jsonObjectNightWeather.getString("temperature");
                                        JSONObject jsonObjectNightWind = jsonObjectNight.getJSONObject("wind");
                                        String directNight = jsonObjectNightWind.getString("direct");
                                        String powerNight = jsonObjectNightWind.getString("power");

                                        Statement ps1 = (Statement) finalCon.createStatement();
                                        String sql1 = "insert into predict values (null ,\'" + cityId + "\',\'" + imgDay + "\',\'" + infoDay + "\',\'" + temperatureDay + "\',\'" + directDay + "\',\'" + powerDay + "\',\'"
                                                + imgNight + "\',\'" + infoNight + "\',\'" + temperatureNight + "\',\'" + directNight + "\',\'" + powerNight + "\',\'" + date + "\',\'" + pt + "\' )";
                                        ps1.execute(sql1);
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }

                        }


                        if (jsonObjectReal != null) {
                            String publishTime = jsonObjectReal.getString("publish_time");

                            Statement ps3 = null;
                            try {
                                ps3 = (Statement) finalCon.createStatement();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            String sql3 = "select id from `real` where publish_time = \'" + publishTime + "\' and city_id = \'" + cityId + "\'";
                            ResultSet rs3 = null;
                            try {
                                rs3 = ps3.executeQuery(sql3);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (!rs3.next()) {
                                    JSONObject jsonObjectWeather = jsonObjectReal.getJSONObject("weather");
                                    String airpressure = jsonObjectWeather.getString("airpressure");
                                    String feelst = jsonObjectWeather.getString("feelst");
                                    String humidity = jsonObjectWeather.getString("humidity");
                                    String info = jsonObjectWeather.getString("info");
                                    String rain = jsonObjectWeather.getString("rain");
                                    String temperature = jsonObjectWeather.getString("temperature");
                                    String temperatureDiff = jsonObjectWeather.getString("temperatureDiff");
                                    JSONObject jsonObjectWind = jsonObjectReal.getJSONObject("wind");
                                    String direct = jsonObjectWind.getString("direct");
                                    String power = jsonObjectWind.getString("power");
                                    String speed = jsonObjectWind.getString("speed");

                                    JSONObject jsonObjectWarn = jsonObjectReal.getJSONObject("warn");
                                    String alert = jsonObjectWarn.getString("alert");
                                    String fmeans = jsonObjectWarn.getString("fmeans");
                                    String issuecontent = jsonObjectWarn.getString("issuecontent");
                                    String pic = jsonObjectWarn.getString("pic");
                                    String pic2 = jsonObjectWarn.getString("pic2");
                                    String signallevel = jsonObjectWarn.getString("signallevel");
                                    String signaltype = jsonObjectWarn.getString("signaltype");
                                    String url1 = jsonObjectWarn.getString("url");

                                    Statement ps1 = (Statement) finalCon.createStatement();
                                    String sql1 = "insert into `real` values (null ,\'" + cityId + "\',\'" + airpressure + "\',\'" + feelst + "\',\'" + humidity + "\',\'" + info + "\',\'" + rain + "\',\'"
                                            + temperature + "\',\'" + temperatureDiff + "\',\'" + direct + "\',\'" + power + "\',\'" + speed + "\',\'" + publishTime + "\' )";
                                    ps1.execute(sql1);
                                    Statement ps2 = (Statement) finalCon.createStatement();
                                    String sql2 = "insert into warn values (null ,\'" + cityId + "\',\'" + alert + "\',\'" + fmeans + "\',\'" + issuecontent + "\',\'" + pic + "\',\'" + pic2 + "\',\'"
                                            + signallevel + "\',\'" + signaltype + "\',\'" + url1 + "\' )";
                                    ps2.execute(sql2);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();

//                    Thread.sleep(2000);
                }
                // 完成后关闭
//                rs.close();
//                con.close();
            }
        } catch (Exception e) {
            System.out.println("MYSQL error" + e.getMessage());
        }


    }
}