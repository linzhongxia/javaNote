package com.note.jdk.reference.weakReference;


import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @link https://blog.csdn.net/qq_33663983/article/details/78349641
 * @author linzhongxia CMO体系-无界零售赋能事业部-商家研发部-商家营销部
 * @date 2019-06-11
 */
public class WeakReferenceTest {

    @Test
    public void strongReferenceCar(){
        Car car = new Car(22000,"silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i=0;

        while(true){
            System.out.println("here is the strong reference 'car' "+car);//use the strong reference in the while loop
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }


    @Test
    public void weakReferenceCar(){
        Car car = new Car(22000,"silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i=0;

        while(true){
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

    @Test
    public void weakReferenceCar3(){
        Car car = new Car(22000,"silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);
        Car car2;
        int i=0;

        while(true){
            if(weakCar.get()!=null){
                i++;
                System.out.println("Object is alive for "+i+" loops - "+weakCar);
                System.out.println("here is the strong reference 'car' "+weakCar.get());//use the strong reference in the while loop
            }else{
                System.out.println("Object has been collected.");
                break;
            }
        }
    }
}
