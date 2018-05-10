package com.mfq.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    public static JSONObject httpGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);

        JSONObject var6;
        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    return null;
                }

                String resultStr = EntityUtils.toString(entity, "utf-8");
                JSONObject result = JSON.parseObject(resultStr);
                JSONObject var8 = result;
                return var8;
            }

            JSONObject result = new JSONObject();
            result.put("errcode", "999999");
            result.put("errmsg", "request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
            var6 = result;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var17) {
                    logger.error("IOException", var17);
                }
            }

        }

        return var6;
    }

    public static String httpGetString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
            String resultStr;
            if (response.getStatusLine().getStatusCode() != 200) {
                JSONObject result = new JSONObject();
                result.put("errcode", "999999");
                result.put("errmsg", "request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                resultStr = result.toJSONString();
                return resultStr;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resultStr = EntityUtils.toString(entity, "utf-8");
                String var7 = resultStr;
                return var7;
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var16) {
                    logger.error("IOException", var16);
                }
            }

        }

        return null;
    }

    /** @deprecated */
    @Deprecated
    public static JSONObject httpPost(String url, Object data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");

        JSONObject var10;
        try {
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                JSONObject result = new JSONObject();
                result.put("errcode", "999999");
                result.put("errmsg", "request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                JSONObject var22 = result;
                return var22;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }

            String resultStr = EntityUtils.toString(entity, "utf-8");
            JSONObject result = JSON.parseObject(resultStr);
            var10 = result;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var19) {
                    logger.error("IOException", var19);
                }
            }

        }

        return var10;
    }

    /** @deprecated */
    @Deprecated
    public static String httpPostString(String url, JSONObject data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");

        try {
            StringEntity requestEntity = new StringEntity(JSON.toJSONString(data), "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            requestEntity.setContentType("application/json");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost);
            String resultStr;
            if (response.getStatusLine().getStatusCode() != 200) {
                JSONObject result = new JSONObject();
                result.put("errcode", "999999");
                result.put("errmsg", "request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                resultStr = result.toJSONString();
                return resultStr;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resultStr = EntityUtils.toString(entity, "utf-8");
                String var9 = resultStr;
                return var9;
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var18) {
                    logger.error("IOException", var18);
                }
            }

        }

        return null;
    }

    public static String httpFormString(String url, JSONObject data) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        List<NameValuePair> formParams = new ArrayList();
        Iterator var7 = data.keySet().iterator();

        while(var7.hasNext()) {
            String key = (String)var7.next();
            formParams.add(new BasicNameValuePair(key, data.getString(key)));
        }

        HttpEntity requestEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
        httpPost.setEntity(requestEntity);

        String var10;
        try {
            response = httpClient.execute(httpPost, new BasicHttpContext());
            String resultStr;
            if (response.getStatusLine().getStatusCode() != 200) {
                JSONObject result = new JSONObject();
                result.put("errcode", "999999");
                result.put("errmsg", "request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                resultStr = result.toJSONString();
                return resultStr;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }

            resultStr = EntityUtils.toString(entity, "utf-8");
            var10 = resultStr;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var19) {
                    logger.error("IOException", var19);
                }
            }

        }

        return var10;
    }

    public static JSONObject httpGet(String url, int timeOut) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();
        httpGet.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                JSONObject result = new JSONObject();
                result.put("errcode", "999999");
                result.put("errmsg", "request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                JSONObject var21 = result;
                return var21;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                JSONObject result = JSON.parseObject(resultStr);
                JSONObject var9 = result;
                return var9;
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var18) {
                    logger.error("IOException", var18);
                }
            }

        }

        return null;
    }

    public static JSONObject httpGet(String url, Map<String, Object> paramMap, int timeOut) throws IOException {
        StringBuffer st = new StringBuffer(url);
        if (!url.contains("?")) {
            st.append("?1=1");
        }

        Iterator var4 = paramMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Object> entry = (Entry)var4.next();
            if (entry.getValue() != null) {
                st.append("&").append((String)entry.getKey()).append("=").append(entry.getValue().toString());
            }
        }

        return httpGet(st.toString(), timeOut);
    }

    public static JSONObject httpGet(String url, Map<String, Object> paramMap) throws IOException {
        StringBuffer st = new StringBuffer(url);
        if (!url.contains("?")) {
            st.append("?1=1");
        }

        Iterator var3 = paramMap.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<String, Object> entry = (Entry)var3.next();
            if (entry.getValue() != null) {
                st.append("&").append((String)entry.getKey()).append("=").append(entry.getValue().toString());
            }
        }

        return httpGet(st.toString());
    }

    public static String httpGetRequest(String url, Map<String, Object> paramMap) {
        StringBuffer st = new StringBuffer(url);
        if (!url.contains("?")) {
            st.append("?1=1");
        }

        int length = 0;
        Iterator var4 = paramMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Object> entry = (Entry)var4.next();
            st.append("&").append(((String)entry.getKey()).toString()).append("=").append(entry.getValue().toString());
            if (((String)entry.getKey()).toString().equals("jsoncallback")) {
                length = entry.getValue().toString().length();
            }
        }

        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet get = new HttpGet(st.toString());
            logger.info("url:{}", st.toString());
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
            get.setConfig(requestConfig);
            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(get);

            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    String resultStr = EntityUtils.toString(entity, "utf-8");
                    logger.info("return:{}", resultStr);
                    result = resultStr.substring(length + 1);
                    result = result.substring(0, result.length() - 1);
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception var26) {
            ;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException var24) {
                logger.error("IOException", var24);
            }

        }

        return result;
    }

    public static void clearVarnish(String url) {
        HttpPurge httpPurge = new HttpPurge(url.toString());
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPurge.setConfig(requestConfig);

        try {
            response = httpClient.execute(httpPurge, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.debug("刷新varnish缓存失败");
            }

            HttpEntity entity = response.getEntity();
            logger.debug(response.getStatusLine().toString());
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                logger.debug(resultStr);
                EntityUtils.consume(entity);
            }
        } catch (IOException var17) {
            logger.debug("刷新varnish缓存异常{}", var17);
        } catch (RuntimeException var18) {
            httpPurge.abort();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var16) {
                    logger.error("IOException", var16);
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
    }
}
