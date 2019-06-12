package com.note.jdk.serializable;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-12
 */
public class TransientTest {
    @Test
    public void transientJsonTest(){
        Car car = new Car(123,"红色");

        System.out.println(JSON.toJSONString(car));

        TransientCar transientCar = new TransientCar(123,"红色");

        System.out.println(JSON.toJSONString(transientCar));
    }
}
