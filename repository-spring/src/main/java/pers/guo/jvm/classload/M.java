package pers.guo.jvm.classload;

/**
 * @author abner
 * @version 1.0
 * @description: 指令重排
 * @date 2023/5/11 23:06
 */
public class M {

    public static void main(String[] args) {
        M m = new M();
    }
    /**
     * class 指令
     *
     * LINENUMBER 12 L0         第一步：申请好内存
     * NEW pers/guo/jvm/classload/M
     * DUP
     * INVOKESPECIAL pers/guo/jvm/classload/M.<init> ()V        第二步：调用构造方法
     * ASTORE 1             第三步：将构造方法创建的对象的引用值指向m
     * L1
     * LINENUMBER 13 L1
     * RETURN
     */
}
