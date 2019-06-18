package com.note.jdk.collection.hashMap;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Co on 2019/6/18.
 */
public class HashMapTest {

    @Test
    public void keySetTest(){
        Map map = new HashMap();
        map.put(123,"123");

        System.out.print(JSON.toJSONString(map.keySet()));
    }

}
