package com.LeetCode;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class LeetCode {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    /*
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }

    /*
    题目描述
    数组中占比超过一半的元素称为“主要元素”，也就是说
    ，数组某个元素出现的次数大于数组长度的一半，该元素可称为主要元素。给定一个整型数组，
    找到它的主要元素，如果没有则返回-1。
     */
    public static int majorElement(int[] arr) {
        long l = System.currentTimeMillis();
        if (arr.length < 1) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int len = arr.length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            } else {
                Integer integer = map.get(arr[i]);
                map.put(arr[i], integer + 1);
            }
        }
       /* for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                Integer integer = map.get(i);
                map.put(i, integer + 1);
            }
        }*/
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer value = entry.getValue();
            if (value >= len) {
                long l1 = System.currentTimeMillis() - l;
                System.out.println(l1 + "ms");
                return entry.getKey();
            }
        }
        long l1 = System.currentTimeMillis() - l;
        System.out.println(l1 + "ms");
        return -1;
    }

    /**
     * 测试线程
     */
    public void t1() {
        System.out.println("t1:" + threadLocal.get());
    }

    public void t2() {
        System.out.println("t2:" + threadLocal.get());
        t3();
    }

    public void t3() {
        System.out.println("t3:" + threadLocal.get());
    }

    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private static ThreadLocal<String> threadLocal = new ThreadLocal();
    static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(java.lang.String[] args) throws InterruptedException {
            //请动手实践运行一下
            List<String> list = new ArrayList<String>();
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("e");
        list.removeIf(next -> next.equals("d"));
    }

    static void lock(Object o) throws InterruptedException {
        synchronized (o) {
            o.notify();
            System.out.println("hello！！");
            o.wait();
            System.out.println("o ho !!");
        }
    }
}
