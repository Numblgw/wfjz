package com.numb.wfjz.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroUtils {
    /**
     * shiro 的MD5加密，通过用户的密码和用户名作为盐进行加密
     *
     * @param credentials 凭证（密码）
     * @param salt        盐（用户名）
     * @return 加密后的结果
     */
    public static String toMD5(String credentials, String salt) {
        return new SimpleHash("MD5", credentials, ByteSource.Util.bytes(salt),
                Integer.valueOf(PropertyUtils.getProperty("shiro.hashIterations"))).toString();
    }
}
