package com.tiantian.pachong;

import java.sql.SQLException;
import java.util.TimerTask;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/7/21
 * \* Time: 11:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PaChongTask extends TimerTask {


    @Override
    public void run() {
        PaChong paChong = new PaChong();
        try {
            paChong.csdn();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}