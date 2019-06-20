package com.note.jdk.collection.map;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-14
 */
public class HashMapTest {


    /**
     * 如果初始化容量了，那么阀值是初始化容量向上取2的N次方的值
     */
    @Test
    public void keySetTest(){
        HashMap map = new HashMap(17);
        map.put(1,"123");
        System.out.println(JSON.toJSONString(map.keySet()));
    }


}
