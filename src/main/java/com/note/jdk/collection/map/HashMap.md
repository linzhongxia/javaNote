
        /**
         * Transfers all entries from current table to newTable.
         */
        void transfer(Entry[] newTable, boolean rehash) {
            int newCapacity = newTable.length;
            for (Entry<K,V> e : table) {
                while(null != e) {
                    Entry<K,V> next = e.next;
                    if (rehash) {
                        e.hash = null == e.key ? 0 : hash(e.key);
                    }
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                }
            }
        }
    
- JDK1.7 resize是头插的方式 e.next = newTable[i]；原entry是从头部遍历复制到新的entry数组中。
- JDK1.7 A、B线程同时出发resize()方法，A线程执行到 Entry<K,V> next = e.next;CPU线程切换，B线程复制resize完成后，切换回A线程，由于线程B已经执行完毕，因此根据Java内存模型（JMM），现在table里面所有的Entry都是最新的。
 [详细可参考](http://www.importnew.com/25070.html)


      final Node<K,V>[] resize() {
            Node<K,V>[] oldTab = table;
            int oldCap = (oldTab == null) ? 0 : oldTab.length;
            int oldThr = threshold;
            int newCap, newThr = 0;
            if (oldCap > 0) {
                // 超过最大值就不再扩充了，就只好随你碰撞去吧
                if (oldCap >= MAXIMUM_CAPACITY) {
                    threshold = Integer.MAX_VALUE;
                    return oldTab;
                }
                // 没超过最大值，就扩充为原来的2倍
                else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                         oldCap >= DEFAULT_INITIAL_CAPACITY)
                    newThr = oldThr << 1; // double threshold
            }
            else if (oldThr > 0) // initial capacity was placed in threshold
                newCap = oldThr;
            else {               // zero initial threshold signifies using defaults
                newCap = DEFAULT_INITIAL_CAPACITY;
                newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
            }
            // 计算新的resize上限
            if (newThr == 0) {
        
                float ft = (float)newCap * loadFactor;
                newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                          (int)ft : Integer.MAX_VALUE);
            }
            threshold = newThr;
            @SuppressWarnings({"rawtypes"，"unchecked"})
                Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
            table = newTab;
            if (oldTab != null) {
                // 把每个bucket都移动到新的buckets中
                for (int j = 0; j < oldCap; ++j) {
                    Node<K,V> e;
                    if ((e = oldTab[j]) != null) {
                        oldTab[j] = null;
                        if (e.next == null)
                            newTab[e.hash & (newCap - 1)] = e;
                        else if (e instanceof TreeNode)
                            ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                        else { // 链表优化重hash的代码块
                            Node<K,V> loHead = null, loTail = null; // 原索引存放的引用
                            Node<K,V> hiHead = null, hiTail = null; // 原索引+oldCap存放的引用
                            Node<K,V> next;
                            do {
                                next = e.next;
                               /*
                               取余(%)操作中如果除数是2的幂次则等价于与其除数减一的与(&)操作
                             （也就是说 hash%length==hash&(length-1)的前提是 length 是2的 n 次方；）。
                              */
                                // 原索引 add by lzhx e.hash & oldCap-1 是扩容前添加元素判断数组索引的方式；扩容后数组是原来的2倍，直接判断二进制数新增的位数求与判断是高位数组还是低位数组
                                if ((e.hash & oldCap) == 0) {
                                    if (loTail == null)
                                        loHead = e;   //add by lzhx 作用更像是指明了低位链表的头，lohead的头e,遍历next就是整体的一个链表
                                    else
                                        loTail.next = e;   //add by lzhx loTail = e lotail作用更像是链表的指针或者游标的一个作用，下一个添加元素的位置在哪里
                                    loTail = e; 
                                } else { // 原索引+oldCap
                                    if (hiTail == null)
                                        hiHead = e;
                                    else
                                        hiTail.next = e;
                                    hiTail = e;
                                }
                            } while ((e = next) != null);
                            // 原索引放到bucket里
                            if (loTail != null) {
                                loTail.next = null;
                                newTab[j] = loHead;
                            }
                            // 原索引+oldCap放到bucket里
                            if (hiTail != null) {
                                hiTail.next = null;
                                newTab[j + oldCap] = hiHead;
                            }
                        }
                    }
                }
            }
            return newTab;
        }
        
        
- JDK1.8 HashMap底层实现加入红黑树，调高效率；
- JDK1.8 resize操作也发生了变化，主要是数组拆分到低位和高位数组的过程[可以参考jdk1.8 HashMap resize方法解析](https://www.jianshu.com/p/bdfe7ddd8f81)
底层数据迁移过程可参考 [底层数据迁移过程可参考 [https://www.cnblogs.com/pzx-java/p/9135341.html](https://www.cnblogs.com/pzx-java/p/9135341.html)
- JDK1.8 [详细代码块分析](https://blog.csdn.net/v123411739/article/details/78996181)
- JDK1.8 HashMap [线程不安全](https://blog.csdn.net/LovePluto/article/details/79712473)

**红黑树代码**