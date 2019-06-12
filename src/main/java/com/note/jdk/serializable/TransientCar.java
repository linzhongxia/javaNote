package com.note.jdk.serializable;

import java.io.Serializable;

/**
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-11
 */
public class TransientCar implements Serializable {

    private static final long serialVersionUID = -436664984551186615L;
    private double price;
    private transient String colour;

    public TransientCar(double price, String colour){
        this.price = price;
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString(){
        return colour +"car costs $"+price;
    }
}
