package com.note.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @link https://www.cnblogs.com/vikde/p/8045226.html
 * https://blog.csdn.net/Kincym/article/details/84072648#LoadingCache_1   Guava---最全缓存cache讲解
 * Created by Co on 2019/6/13.
 */
public class GuavaCacheTest {

    /**
     * 参考：https://segmentfault.com/a/1190000007300118
     * https://www.cnblogs.com/vikde/p/8045226.html
     * https://blog.csdn.net/Kincym/article/details/84072648#LoadingCache_1   Guava---最全缓存cache讲解
     *
     * guava cache 不能load null
     * 1. 行参、实参区别
     * 2. https://www.jianshu.com/p/15d0a9ce37dd 和guava cache的 区别
     * 3. delayqueue 的原理和应用
     * 4. 为什么不能为NULL对象做缓存
     * 5. guava cache 队列的应用
     * 6. guava cache 删除策略是怎么运行的
     */


    /**
     * 笔记
     *
     *
     *
     */
    public void test(){

    }


    public static void main(String[] args) throws Exception {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
                .recordStats()
                //设置写缓存后n秒钟过期
                .expireAfterWrite(17, TimeUnit.SECONDS)
                //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
                //.expireAfterAccess(17, TimeUnit.SECONDS)
                //只阻塞当前数据加载线程，其他线程返回旧值
                //.refreshAfterWrite(13, TimeUnit.SECONDS)
                //设置缓存的移除通知
                .removalListener(notification -> {
                    System.out.println(notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(new DemoCacheLoader());

        //模拟线程并发
        new Thread(() -> {
            //非线程安全的时间格式化工具
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                for (int i = 0; i < 15; i++) {
                    String value = cache.get(1);
                    System.out.println(Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (Exception ignored) {
            }
        }).start();

        new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                for (int i = 0; i < 10; i++) {
                    String value = cache.get(1);
                    System.out.println(Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (Exception ignored) {
            }
        }).start();
        //缓存状态查看
        System.out.println(cache.stats().toString());
    }

    /**
     * 随机缓存加载,实际使用时应实现业务的缓存加载逻辑,例如从数据库获取数据
     */
    public static class DemoCacheLoader extends CacheLoader<Integer, String> {
        @Override
        public String load(Integer key) throws Exception {
            System.out.println(Thread.currentThread().getName() + " 加载数据开始");
            TimeUnit.SECONDS.sleep(8);
            Random random = new Random();
            System.out.println(Thread.currentThread().getName() + " 加载数据结束");
            return "value:" + random.nextInt(10000);
        }
    }
}
