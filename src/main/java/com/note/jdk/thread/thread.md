
- [Java之wait、await、sleep的区别和联系](https://blog.csdn.net/hudaJY/article/details/89057626)
````
一、wait() notify() notifyAll()
   调用wait()使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程运行时使得这个条件满足时，其他线程会调用notify()或者notifyAll()来唤醒该挂起的线程。
   wait()挂起期间，线程会释放锁。假若线程没有释放锁，那么其他线程就无法进入对象的同步方法或同步控制块中，也就无法执行notify() 和 notifyAll()方法来唤醒挂起的线程，从而造成死锁。
````
````
二、wait() 和 sleep() 的区别
同：
1. 都是线程同步时会用到的方法，使当前线程暂停运行，把机会交给其他线程
2.如果任何线程在等待期间被中断都会抛出InterruptedException
3.都是native() 方法
异：
1. wait() 是Object超类中的方法；而sleep()是线程Thread类中的方法
2. 对锁的持有不同，wait()会释放锁，而sleep()并不释放锁
3. 唤醒方法不完全相同，wait() 依靠notify或者notifyAll 、中断、达到指定时间来唤醒；而sleep()到达指定时间被唤醒.
````