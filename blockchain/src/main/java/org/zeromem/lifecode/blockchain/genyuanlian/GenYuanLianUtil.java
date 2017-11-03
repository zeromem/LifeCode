package org.zeromem.lifecode.blockchain.genyuanlian;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zeromem
 * @date 2017/11/1
 */
public class GenYuanLianUtil {
    public static final String ETH_ADDRESS = "0x18967afa60342e482561ccbad6b6d8f725893c1e";
    public static final int ETH_LIMIT = 65536;
    private static final SimpleDateFormat UPLOAD_FORMATTER = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
    private static final SimpleDateFormat FETCH_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException, URISyntaxException {
//        upload("460039210469481", "17/11/01,06:30:43", 116.4751129,40.1136446);
//        upload("460039210469481", "17/11/01,08:31:29", 116.4751328,40.1137214);

        System.out.println(fetch("460039210469481", "2017-11-01 00:00:00", null));
    }

    public static void upload(String id, Date date, double lng, double lat) throws URISyntaxException, IOException {
        if (date == null) {
            return;
        }
        upload(id, UPLOAD_FORMATTER.format(date), lng, lat);
    }

    public static void upload(String id, String date, double lng, double lat) throws URISyntaxException, IOException {
        String param = new StringBuilder(id)
                .append(";;;;;")
                .append(date).append(';')
                .append(lng).append(';')
                .append(lat).append(';')
                .append(ETH_ADDRESS).append(';')
                .append(ETH_LIMIT).toString();

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.genyuanlian.org")
                .setPath("/data/uploadSensorData")
                .setParameter("param", param)
                .build();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri);

        CloseableHttpResponse response = client.execute(get);
        int code = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String res = EntityUtils.toString(entity, "UTF-8");
        System.out.println(code);
        System.out.println(res);
    }


    public static String fetch(String id, String start, String end) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("www.genyuanlian.org")
                .setPath("/zdlh/fetch")
                .setParameter("sensorId", id);

        if (start != null) {
            builder.setParameter("startTime", start);
        }
        if (end != null) {
            builder.setParameter("endTime", end);
        }
        URI uri = builder.build();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri);

        CloseableHttpResponse response = client.execute(get);
        int code = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String res = EntityUtils.toString(entity, "UTF-8");
        return res;
    }

    public static String fetch(String id, Date start, Date end, String sss) throws URISyntaxException, IOException {
        String s = (start == null) ? null : FETCH_FORMATTER.format(start);
        String e = (end == null) ? null : FETCH_FORMATTER.format(end);

        return fetch(id, s, e);
    }
}
