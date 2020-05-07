package com.xiaowei.wpmvp.java;

public class GC {

    //引用计数法  缺点：对象如果重复引用就会导致不为0

    //可达性分析算法 缺点:gcroot为节点

    //标记清除算法： 缺点:有内存碎片存在，效率比较低，循环所有的对象

    //复制算法：  没有内存碎片，效率比较高  java堆的内存空间被挤压，无法承载大量的对象

    //标记压缩算法：  没有内存碎片，空间承载量相对较大，效率一般

    //分代垃圾收集算法:将复制算法应用到分代垃圾收集算法，分为新生代和老年代

    //内存泄漏
    //1、handler  2、bitmap  3、cursor  4、io流

    // handler内存泄漏最多的地方在网络上
    // 解决方案:softrefrence weakreference static  对吗？  对，说原因   我都不用，同样不会存在内存泄漏


}
