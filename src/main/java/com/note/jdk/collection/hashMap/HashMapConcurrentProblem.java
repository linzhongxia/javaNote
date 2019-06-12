package com.note.jdk.collection.hashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @link https://www.jianshu.com/p/1e9cf0ac07f4
 * https://blog.csdn.net/majinggogogo/article/details/80036544
 * https://blog.csdn.net/coslay/article/details/45601575
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-12
 */
public class HashMapConcurrentProblem extends Thread{

    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static AtomicInteger at = new AtomicInteger(0);


    @Override
    public void run() {
        while(at.get()<1000000){
            map.put(at.get(), at.get());
            at.incrementAndGet();
            System.out.println(at.get());
        }
    }

//    public static void main(String[] args) {
//        for(int i=0;i<10;i++){
//            Thread thread = new HashMapConcurrentProblem();
//            thread.start();
//        }
//    }


    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                            System.out.println("123");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
