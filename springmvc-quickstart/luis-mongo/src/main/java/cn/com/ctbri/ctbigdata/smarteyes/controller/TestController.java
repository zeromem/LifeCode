package cn.com.ctbri.ctbigdata.smarteyes.controller;

import cn.com.ctbri.ctbigdata.smarteyes.dao.UserDao;
import cn.com.ctbri.ctbigdata.smarteyes.model.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elite on 2017/5/11.
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserDao userDaoImpl;

    @RequestMapping(value = "/getJson", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTest(){
        System.out.println("in");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","ok");

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("maxAge", 50);
        String collectionName ="users";
        List<User> list=userDaoImpl.findAll(params,collectionName);
        System.out.println("user.count()=="+list.size());
        jsonObject.put("mongo",list.size());

        return jsonObject.toJSONString();
    }
}
