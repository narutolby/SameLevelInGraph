/*
package com.baidu.test;

import java.util.Scanner;

import org.junit.Test;

import com.baidu.SameLevel;

*/
/**
 * @author huyang01
 * 
 * @since 2013-01-10
 *
 * 单元测试
 *//*

public class SameLevelTest {
	
	//正确字母输入
	private final String scannerLetterInputOK = "4 5\na b c d\na b\na c\nb c\nc d\nb d\na d";
	//错误字母输入
	private final String scannerLetterInputError = "4 5\na b c d\na b\na c\nb c\nc d\nb d\nc d";

	//正确数字输入
	private final String scannerNumberInputOK = "10 11\n0 1 2 3 4 5 6 7 8 9\n0 1\n1 2\n2 4\n1 3\n3 5\n3 6\n4 8\n5 7\n6 7\n7 8\n8 9 \n3 7";
	//错误数字输入
	private final String scannerNumberInputError = "10 11\n0 1 2 3 4 5 6 7 8 9\n0 1\n1 2\n2 4\n1 3\n3 5\n3 6\n4 8\n5 7\n6 7\n7 8\n8 9 \n0 3";
	
	*/
/*
     * 创建正向与反向临接表
     * 输入格式参考如下：
     *  4 5        //输入节点个数与有向边数
     *  a b c d    //输入各个节点
     *  a b        // 以下四行有输入的有向边
     *  a c
     *  b c
     *  b d
     *  c d
     *  a d        //输入需要判断层次的任意两个节点
     *  
     *  相同层次的任意两个数为true，不同层次为false
     *  a d 同层
     *//*

	@Test
	public void TestLetterOK() {
		SameLevel sameLevel = new SameLevel();
		Scanner sc = new Scanner(scannerLetterInputOK);
        boolean created = sameLevel.createGraph(sc);
        if(!created){
            throw new RuntimeException("Create graph failed!");
        }
        Character a = sc.next().toCharArray()[0],b = sc.next().toCharArray()[0];
        boolean ret = sameLevel.isSameLevel(a,b);
        System.out.println(ret);
	}
	
	@Test
	public void TestLetterError() {
		SameLevel sameLevel = new SameLevel();
		Scanner sc = new Scanner(scannerLetterInputError);
        boolean created = sameLevel.createGraph(sc);
        if(!created){
            throw new RuntimeException("Create graph failed!");
        }
        Character a = sc.next().toCharArray()[0],b = sc.next().toCharArray()[0];
        boolean ret = sameLevel.isSameLevel(a,b);
        System.out.println(ret);
	}
	
	*/
/*
     * 创建正向与反向临接表
     * 输入格式参考如下：
     *  10 11        //输入节点个数与有向边数
     *  0 1 2 3 4 5 6 7 8 9    //输入各个节点
     *  0 1			// 以下11行有输入的有向边
     *  1 2
     *  2 4
     *  1 3
     *  3 5
     *  3 6
     *  4 8
     *  5 7
     *  6 7 
     *  7 8
     *  8 9 
     *  3 7        //输入需要判断层次的任意两个节点
     *  
     *  相同层次的任意两个数为true，不同层次为false
     *  0 1 8 9 同层
     *  2 4	同层
     *  3 7 同层
     *//*

	@Test
	public void TestNumberError() {
		SameLevel sameLevel = new SameLevel();
		Scanner sc = new Scanner(scannerNumberInputError);
        boolean created = sameLevel.createGraph(sc);
        if(!created){
            throw new RuntimeException("Create graph failed!");
        }
        Character a = sc.next().toCharArray()[0],b = sc.next().toCharArray()[0];
        boolean ret = sameLevel.isSameLevel(a,b);
        System.out.println(ret);
	}
	
	@Test
	public void TestNumberOK() {
		SameLevel sameLevel = new SameLevel();
		Scanner sc = new Scanner(scannerNumberInputOK);
        boolean created = sameLevel.createGraph(sc);
        if(!created){
            throw new RuntimeException("Create graph failed!");
        }
        Character a = sc.next().toCharArray()[0],b = sc.next().toCharArray()[0];
        boolean ret = sameLevel.isSameLevel(a,b);
        System.out.println(ret);
	}
}
*/
