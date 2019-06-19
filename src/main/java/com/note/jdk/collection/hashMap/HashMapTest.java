package com.note.jdk.collection.hashMap;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-14
 */
public class HashMapTest {


    @Test
    public void newHashMapTest(){
        HashMap map = new HashMap(17);
        map.put(1,"123");
        System.out.println(JSON.toJSONString(map.keySet()));
    }
}
