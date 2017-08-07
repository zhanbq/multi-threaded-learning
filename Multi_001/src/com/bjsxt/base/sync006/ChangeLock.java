package com.bjsxt.base.sync006;
/**
 * 锁对象的改变问题
 * @author alienware
 *  当使用一个对象进行加锁的时候,要注意对象本事发生改变的时候,那么持有的锁就不同.
 *  如果对象本身不发生改变,那么依然是同步的,即使是对象的属性发生了改变.
 */
public class ChangeLock {

	private String lock = "lock";
	
	private void method(){
		synchronized (lock) {
			try {
				System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
//				lock = "change lock"; //这里改变对象所 输出的结果不同 正常持有相同的锁的时候 t2线程必须等待t1线程结束才会开始,如果改变对象锁,那么锁失效
				Thread.sleep(2000);
				System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
	
		final ChangeLock changeLock = new ChangeLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				changeLock.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				changeLock.method();
			}
		},"t2");
		t1.start(); 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
	
}
