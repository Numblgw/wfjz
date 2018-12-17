package com.numb.wfjz.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName PropertyUtils
 * @Description 加载global.properties文件
 * @Author Numblgw
 * @Date 2018/12/12 15:36
 */
@Slf4j
public class PropertyUtils {

    private static Properties props;

    static {
        log.info("开始加载properties文件内容...");
        props = new Properties();
        InputStream inputStream = null;
        try {
            //通过类加载器进行获取properties文件流
            inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream("global.properties");
            props.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("global.properties文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("出现IOException");
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("global.properties文件流关闭出现异常");
            }
        }
        log.info("加载properties文件内容完成...");
        log.info("properties文件内容：" + props);
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
