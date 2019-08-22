package cn.fireim.util;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpUtil {
    /**
     * 获取页面html码
     * @param url
     * @return
     */
   public static String getHtml(String url){
        try {
            return EntityUtils.toString(HttpClients.custom().build().execute(new HttpGet(url)).getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 模拟api请求
     * @param apiUrl
     * @param params
     * @return
     */
    public static String postApi(String apiUrl, Map<String,String> params, CookieStore cookieStore) throws IOException {
        HttpPost post=new HttpPost(apiUrl);
        List<NameValuePair> parms = new ArrayList<>();
        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String,String> entry =(Map.Entry) entries.next();
            parms.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(parms));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return EntityUtils.toString(HttpClients.custom().setDefaultCookieStore(cookieStore).build().execute(post).getEntity());
    }

    public static String postApi(String apiUrl, Map<String,String> params) throws IOException {
        HttpPost post=new HttpPost(apiUrl);
        List<NameValuePair> parms = new ArrayList<>();
        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String,String> entry =(Map.Entry) entries.next();
            parms.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(parms));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return EntityUtils.toString(HttpClients.custom().setDefaultCookieStore(new BasicCookieStore()).build().execute(post).getEntity());
    }
    public static String postApi(String apiUrl) throws IOException {
        return EntityUtils.toString(HttpClients.custom().setDefaultCookieStore(new BasicCookieStore()).build().execute(new HttpPost(apiUrl)).getEntity());
    }

    public static void main(String[] args) throws IOException {
        CookieStore cookieStore = new BasicCookieStore();
        Map<String,String> map = new HashMap<>();
        System.out.println(postApi("https://www.kuaidi100.com/query?type=zhongtong&postid=75118017144027",  map,cookieStore));
    }

}
