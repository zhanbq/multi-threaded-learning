package com.bjsxt.base.sync007;

/**
 * volatile概念:volatile关键字的主要作用就是是变量在多线程间可见.
 * @author o
 *  在java中,每一个线程都会有一块工作内存区,其中存放着所有线程共享的主内存中的
 *  变量值的拷贝.当线程执行时,他在自己的工作内存区中操作这些变量.为了存取一个
 *  共享的变量,一个线程通常先获取锁定并去清除它内存区,当线程解锁时保证该工作内存中变量的值
 *  会写到共享的内存中.
 *  
 *  一个 线程可以执行的操作有(use),赋值(asslgn),装载(load),存储(store),锁定(lock),解锁(unlock).
 *  
 *  而主内存可以执行的操作有读(read) , 写(wirte) , 锁定(lock) , 解锁(unlock),每个操作都是原子的
 *  
 *  volatile的作用就是强制线程到主内存(共享内存)里去读取变量,而不是去线程工作内存区里面去读取,从而实现了
 *  多个线程间的变量可见.也就是满足线程安全的可见性.
 *  final也可以实现多线程间可见
 */
public class RunThread extends Thread{

	private volatile boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		while(isRunning == true){
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
	}
	
	
}
