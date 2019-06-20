package com.note.jdk.collection.list;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-20
 */
public class LinkedListTest {

    @Test
    public void test() {
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("123");
        linkedList.add("456");

        for (String a : linkedList) {

            System.out.println(a);
        }
    }


    @Test
    public void dequeTest() {
        Deque<String> linkList = new LinkedList<String>();

        linkList.addFirst("1");
        linkList.addFirst("2");

        System.out.println("头插方式：" + linkList.toString());

        linkList.addLast("3");

        System.out.println("尾插方式：" + linkList.toString());

        System.out.println("获取头部元素：" + linkList.peek().toString());

        System.out.println("移除头元素 " + linkList.poll().toString() + " 移除元素后:" + linkList.toString());

        System.out.println("移除尾元素 " + linkList.pollLast() + " 移除元素后:" + linkList.toString());

        System.out.println("移除头元素 " + linkList.pop().toString() + " 移除元素后:" + linkList.toString());

        linkList.offer("4");
        linkList.push("5");

        System.out.println("头插方式：" + linkList.toString());

        linkList.offer("6");

        System.out.println("尾插方式：" + linkList.toString());
    }


    /**
     *
     */
    @Test
    public void queueTest(){
        Queue<String> linkList = new LinkedList<>();
        linkList.add("1");
        linkList.add("2");
        linkList.offer("3");

        System.out.println("尾插方式：" + linkList.toString());

        System.out.println("获取头部元素：" + linkList.element().toString());

        System.out.println("获取头部元素：" + linkList.peek().toString());

        System.out.println("移除头元素 " + linkList.poll().toString() + " 移除元素后:" + linkList.toString());

    }

}
