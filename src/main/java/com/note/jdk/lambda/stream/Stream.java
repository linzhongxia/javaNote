package com.note.jdk.lambda.stream;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-07-23
 */
public class Stream {

    @Test
    public void test() {
        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("red");

        colors.stream().filter(c -> c.equals("red"))
                .forEach(c -> c = "green");

        System.out.println(JSON.toJSONString(colors));
    }


    @Test
    public void filterTest() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("red"));
        colors.add(new Color("red"));

        colors.stream().filter(c -> c.getColor().equals("red")).forEach(color -> color.setColor("green"));
        System.out.println(JSON.toJSONString(colors));

    }


    @Test
    public void mapTest() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("red"));
        colors.add(new Color("red"));

        colors.stream().map(color -> {
            color.setColor("green");
            return color;
        }).filter(color -> color.getColor().equals("green")).forEach(color -> color.setColor("yellow"));

        System.out.println(JSON.toJSONString(colors));

    }


    @Test
    public void mapTest2(){
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("red"));
        colors.add(new Color("red"));

        Set<String> s = colors.stream().map( color -> color.getColor()).collect(Collectors.toSet());

        System.out.println(JSON.toJSONString(s));
    }

    @Test
    public void streamTest(){
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("red"));
        colors.add(new Color("red"));

    }


    class Color {
        private String color;

        public Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    @Test
    public  void testNull() {
        Set<String> set = null;

        set.stream().forEach(s-> {System.out.println(s);});
    }
}
