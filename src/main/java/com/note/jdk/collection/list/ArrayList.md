- [Java集合：ArrayList详解](https://blog.csdn.net/v123411739/article/details/79190114)

        private void grow(int minCapacity) {
                  // 获取到ArrayList中elementData数组的内存空间长度
                  int oldCapacity = elementData.length;
                 // 扩容至原来的1.5倍
                 int newCapacity = oldCapacity + (oldCapacity >> 1);
                 // 再判断一下新数组的容量够不够，够了就直接使用这个长度创建新数组，
                  // 不够就将数组长度设置为需要的长度
                 if (newCapacity - minCapacity < 0)
                     newCapacity = minCapacity;
                 //若预设值大于默认的最大值检查是否溢出
                 if (newCapacity - MAX_ARRAY_SIZE > 0)
                     newCapacity = hugeCapacity(minCapacity);
                 // 调用Arrays.copyOf方法将elementData数组指向新的内存空间时newCapacity的连续空间
                 // 并将elementData的数据复制到新的内存空间
                 elementData = Arrays.copyOf(elementData, newCapacity);
        }
  