[Java并发编程之DelayQueue阻塞队列详解](https://blog.csdn.net/qq_38293564/article/details/80591441)

- DelayQueue是一个支持延时获取元素的无界阻塞队列。队列使用PriorityQueue来实现。队列中的元素必须实现Delayed接口，在创建元素时可以指定多久才能从队列中获取当前元素。只有在延迟期满时才能从队列中提取元素。
- DelayQueue非常有用，可以运用在以下两个应用场景： 

        缓存系统的设计：使用DelayQueue保存缓存元素的有效期，使用一个线程循环查询DelayQueue，一旦能从DelayQueue中获取元素时，就表示有缓存到期了。 
        定时任务调度：使用DelayQueue保存当天要执行的任务和执行时间，一旦从DelayQueue中获取到任务就开始执行，比如Timer就是使用DelayQueue实现的。
