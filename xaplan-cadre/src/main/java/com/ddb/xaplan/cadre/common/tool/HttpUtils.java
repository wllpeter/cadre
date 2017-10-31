package com.ddb.xaplan.cadre.common.tool;

import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

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

    public static String getCookieValue(HttpServletRequest request, String name) {
        if(name==null){
            return null;
        }
        for(Cookie c:request.getCookies()){
            if(name.equals(c.getName())){
                try{
                    return URLDecoder.decode(c.getValue(), "UTF-8");
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }

            }
        }
        return null;
    }

    public static String get(String url) throws IOException {

        return Request.Get(url)
                .execute().returnContent().asString();
    }

}
