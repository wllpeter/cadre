package com.ddb.xaplan.cadre.common.tool;

import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * Created by 王凯斌 on 2017/4/26.
 */
@Component
public class HttpUtils {


    public static Cookie getCookie(String name, String value, int expiry, String path, boolean isSecure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiry);
        cookie.setPath(path);
        cookie.setSecure(isSecure);
        return cookie;
    }

    public static String get(String url) throws IOException {

        return Request.Get(url)
                .execute().returnContent().asString();
    }

}
