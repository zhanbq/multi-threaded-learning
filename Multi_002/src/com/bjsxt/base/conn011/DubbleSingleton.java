package com.bjsxt.base.conn011;

/**
 * - ## 单例模式
      > 最常见的就是饥饿模式和懒汉模式,
      一个直接实例化对象,
      一个在调用方法时实例化对象.
      在多线程模式中,
      考虑到性能和线程的安全问题,我们一般选择下面两种比较经典的单例模式,
      这两种模式均不适用
      在性能提高的同时,有保证的线程安全.
      
      dubble check instance
      
      static inner class 静态内部类模式 原生支持多线程 最常用
 */
public class DubbleSingleton {

	private static DubbleSingleton ds;
	
	public  static DubbleSingleton getDs(){
		if(ds == null){
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DubbleSingleton.class) {
				if(ds == null){
					ds = new DubbleSingleton();
				}
			}
		}
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DubbleSingleton.getDs().hashCode());
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}
