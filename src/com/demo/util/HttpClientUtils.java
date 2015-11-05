package com.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public abstract class HttpClientUtils {

    private static final Log logger = Logs.geterrorLogger();

    private static final int connectionTimeoutMillis = 30000;
    private static final int socketTimeoutMillis = 30000;
    private static final int maxTotalConnections = 2000;
    private static final int maxConnectionsPerRoute = 500;
    private static final int connManagerTimeout = 1000;

    private static HttpParams params;


    static {
        params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpConnectionParams.setConnectionTimeout(params, connectionTimeoutMillis);
        
        HttpConnectionParams.setSoTimeout(params, socketTimeoutMillis);
        ConnManagerParams.setTimeout(params, connManagerTimeout);
    }

    public static HttpParams getDefaultHttpParams() {
        return params.copy();
    }

	public static HttpClient getHttpClient() {
        long s = System.currentTimeMillis();
        try {
            return getHttpClient(params);
        } finally {
            logger.debug("End getHttpClient spend:{"+(System.currentTimeMillis() - s)+"}");
        }
    }

    public static HttpClient getHttpClient(HttpParams params) {
        return new DefaultHttpClient(params);
    }

    public static String getJsonByPost(String url) {
        return getJsonByPost(url, null, "UTF-8");
    }

    public static String getJsonByPost(String url, String charset) {
        return getJsonByPost(url, null, charset);
    }

    public static String getJsonByPost(String url, Map<String, String> params) {
        return getJsonByPost(url, params, "UTF-8");
    }

    public static String getJsonByPost(String url, Map<String, String> params, String charset) {
        return getJsonByPost(getHttpClient(), url, params, charset);
    }

    public static String getJsonByPost(HttpClient httpClient, String url, Map<String, String> params, String charset) {
        if (httpClient == null) {
            throw new NullPointerException("httpClient is null.");
        }
        String response = "{}";
        List<NameValuePair> paramList = null;
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            paramList = new ArrayList<NameValuePair>();
            for (Iterator<Map.Entry<String, String>> it = entrySet.iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && value != null) {
                    NameValuePair nvp = new BasicNameValuePair(key, value);
                    paramList.add(nvp);
                }
            }
        }

        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            if (paramList != null)
                httpPost.setEntity(new UrlEncodedFormEntity(paramList, charset));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                logger.warn("Unable to fetch page {"+httpPost.getURI()+"}, status code: {"+statusCode+"}");
            } else {
                response = EntityUtils.toString(httpEntity, charset);
                response = (response != null ? response.trim() : "{}");
                logger.debug("Fetch page {"+ httpPost.getURI()+"}, status code: {"+statusCode+"}");
            }
            if (httpEntity != null) {
                httpEntity.consumeContent();
            }
        } catch (Exception e) {
            logger.error("", e);
            if (httpPost != null) {
                httpPost.abort();
            }
        } finally {
            if (httpClient != null && httpClient.getConnectionManager() instanceof SingleClientConnManager) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        logger.debug("Begin HttpClientUtils.getJsonByPost response:{"+response+"}");
        return response;
    }
    public static String getStringByGet(String url){
    	return httpGet(url,"UTF-8");
    }
    public static String getStringByHeadGet(String url){
    	Map<String,String> headerMap=new HashMap<String, String>();
    	headerMap.put("Accept-Language", "zh-cn");
    	headerMap.put("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
    	return httpGet(getHttpClient(),url,"UTF-8",headerMap);
    }
    public static String httpGet(String url, String charset) {
        return httpGet(getHttpClient(), url, charset,null);
    }

    public static String httpGet(HttpClient httpClient, String url, String charset,Map<String,String> headerMap ) {
        String response = "";
        if (httpClient == null) {
            logger.error("httpClient is null.");
            return response;
        }
        	
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            if(headerMap!=null&&headerMap.size()>0){
            	Iterator<String> iterator=headerMap.keySet().iterator();
            	while (iterator.hasNext()) {
            		String key=iterator.next();
            		httpGet.setHeader(key, headerMap.get(key));
				}
            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                logger.warn("Unable to fetch page {"+httpGet.getURI()+"}, status code: {"+statusCode+"}");
            } else {
                response = EntityUtils.toString(httpEntity, charset);
                response = (response != null ? response.trim() : "{}");
                logger.debug("Fetch page {"+httpGet.getURI()+"}, status code: {"+statusCode+"}");
            }
            if (httpEntity != null) {
                httpEntity.consumeContent();
            }
        } catch (Exception e) {
            logger.error("", e);
            if (httpGet != null) {
                httpGet.abort();
            }
        } finally {
            if (httpClient != null && httpClient.getConnectionManager() instanceof SingleClientConnManager) {
                httpClient.getConnectionManager().shutdown();
            }
        }

        return response;
    }

    private static class SpaceConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {
        private long timeout;

        SpaceConnectionKeepAliveStrategy(long timeout) {
            this.timeout = timeout;
        }

        @Override
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                    }
                }
            }
            return timeout * 1000;
        }
    }
}
