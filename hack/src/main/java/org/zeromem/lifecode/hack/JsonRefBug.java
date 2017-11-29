package org.zeromem.lifecode.hack;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author zeromem
 * @date 2017/11/29
 * 向json array中重复添加同一个对象，后续的object只会是引用
 */
public class JsonRefBug {
    public static void main(String[] args) {
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        jo.put("hello", "world");
        ja.add(jo);
        ja.add(jo);
        // 防止结果中出现ref，禁用循环引用检查
        // [{"hello":"world"},{"$ref":"$[0]"}]
        System.out.println(JSON.toJSONString(ja, SerializerFeature.DisableCircularReferenceDetect));
    }

}
