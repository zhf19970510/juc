package com.zhf.juc.cas;

/**
 * CAS （无锁优化，自旋）
 * Compare And Swap
 * cas (V,Excepted, NewValue)
 *  - if V == E
 *      V = NEW
 *    ohterwise
 *      try again or fail
 *    - CPU原语支持
 *
 *
 * @author: 曾鸿发
 * @create: 2021-12-15 15:33
 * @description： CAS
 **/
public class CASDemo {
}
