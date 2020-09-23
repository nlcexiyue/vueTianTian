package com.tiantian.tools;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/7/8
 * \* Time: 16:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Base64ImageUtils {
    /**
     * 将图片内容编码为字符串
     *
     * @param file
     * @return
     */
    public static String encodeImageToBase64(File file) {
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes).trim();
    }

    /**
     * 将图片内容解码为输入流
     *
     * @param base
     * @return
     */
    public static InputStream decodeBase64ToImage(String base) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodeBytes = null;
        try {
            decodeBytes = decoder.decodeBuffer(base);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(decodeBytes);
    }

}