package com.demo.test;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.util.Logs;
import com.demo.util.StringUtil;

/**
 * 用来玩耍
 */
public class Test {

    /*public static void main(String[] args) {
        // 登陆 Url
        String loginUrl = "https://www.91zhiwang.com/m/bonus/share?utm_campaign=aug_privilege&utm_medium=augbonus&from_uid=106716241862827&share_text=8&bpid=JU%2F1xt85tI7ZgMi%2FmDKKhA%3D%3D&from=singlemessage&isappinstalled=1";
        int max = 2500;
        String phone = "13466590075";
        int result = caihongbao(loginUrl, max, phone);
        if (result == 0)
            System.out.println("成功");
        else
            System.out.println("失败");
    }*/

    /**
     * 入口
     * @param hongbaoUrl 红包地址
     * @param max 期望额度
     * @param phone 手机号
     * @return
     */
    public static JSONObject caihongbao(String hongbaoUrl, int max, String phone) {
        HttpClient httpClient = new HttpClient();
        int times = 0;
        max = max * 100;
        Integer amount = 0;
        Map<String, Object> result = null;
        while (++times < retryTimes) {
            result = fetchBag(httpClient, hongbaoUrl);
            amount = (Integer)result.get("amount");
            if (amount != null && amount >= max) {
                break;
            }
            httpClient = new HttpClient();
        }

        if (amount == null || amount < max) {
            return null;
        }

        NameValuePair param = (NameValuePair)result.get("params");
        String key = "&from_uid=";
        hongbaoUrl = hongbaoUrl.substring(hongbaoUrl.indexOf(key) + key.length());
        String uid = hongbaoUrl.substring(0, hongbaoUrl.indexOf("&"));
        NameValuePair param1 = new NameValuePair("from_uid", uid);
        NameValuePair param2 = new NameValuePair("invite", "true");
        NameValuePair param3 = new NameValuePair("phone", phone);
        NameValuePair param4 = new NameValuePair("amount", max+"");
        return submitPhone(httpClient, result.get("cookie").toString(), new NameValuePair[]{param, param1, param2, param3, param4});
    }


    private final static int retryTimes = 100;
    private final static String OPEN_BAG_URL = "https://www.91zhiwang.com/m/bonus/open";

    private static Map<String, Object> fetchBag(HttpClient httpClient, String loginUrl) {
        Map<String, Object> result = new HashMap<String, Object>();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        GetMethod fetchPage = new GetMethod(loginUrl);
        PostMethod openBag = new PostMethod(OPEN_BAG_URL);
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(
                    CookiePolicy.BROWSER_COMPATIBILITY);
            httpClient.executeMethod(fetchPage);
            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
            }
            result.put("cookie", tmpcookies);

            String str = fetchPage.getResponseBodyAsString();
            String regex = "bpid";
            int a = str.indexOf(regex);
            str = str.substring(a+regex.length());
            str = str.substring(0, str.indexOf("\"}"));
            str = str.replace(":", "");
            str = str.trim().replace("\"", "");
            NameValuePair param = new NameValuePair(regex, str);
            openBag.addParameters(new NameValuePair[]{param});
            openBag.setRequestHeader("cookie", tmpcookies.toString());
            openBag.setRequestHeader("Referer", "http://www.91zhiwang.com");
            openBag.setRequestHeader("User-Agent", "www Spot");
            httpClient.executeMethod(openBag);
            String text = openBag.getResponseBodyAsString();
            Map<String, Object> json = (Map<String, Object>)JSON.parse(text);
            int amount = StringUtil.toInteger(json.get("amount").toString(), 0);
            Logs.geterrorLogger().error(" amount: " + amount);

            result.put("params", param);
            result.put("amount", amount);
        } catch (Exception e) {
            Logs.geterrorLogger().error(" fetchBag exception ", e);
        } finally {
            fetchPage.releaseConnection();
            openBag.releaseConnection();
        }
        return result;
    }

    private final static String SUBMIT_URL = "https://www.91zhiwang.com/m/bonus/share";

    private static JSONObject submitPhone(HttpClient httpClient, String cookies, NameValuePair[] nameValuePairs) {

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod fetchPage = new PostMethod(SUBMIT_URL);
        try {
            for (NameValuePair param : nameValuePairs) {
                System.out.println(param.getName()+":  "+ param.getValue());
            }
            fetchPage.addParameters(nameValuePairs);
            // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
            fetchPage.setRequestHeader("cookie", cookies);
            fetchPage.setRequestHeader("Referer", "http://www.91zhiwang.com");
            fetchPage.setRequestHeader("User-Agent", "www Spot");
            httpClient.executeMethod(fetchPage);

            // 打印出返回数据，检验一下是否成功
            String text = fetchPage.getResponseBodyAsString();
            JSONObject json = (JSONObject) JSONObject.parse(text);
            Logs.geterrorLogger().error(text);
            if (json != null) {
                String status = json.get("status").toString();
                return json;
                /*if ("success".equals(status)) {
                    return json;
                }*/
            }
        } catch (Exception e) {
            Logs.geterrorLogger().error(" fetchBag exception ", e);
        } finally {
            fetchPage.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(2);
        }
        return null;
    }



}
