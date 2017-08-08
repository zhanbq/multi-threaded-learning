package com.bjsxt.base.sync002;
/**
 * 多个线程多个锁:多个线程,每个线程都可以拿到自己指定的锁,分别获得所之后,执行synchronized方法体的内容
 * 
 * 示例总结:关键字synchronized取得是锁都是对象锁,而不是把一段代码(方法)当做锁,
 * 所以示例代码中那个线程先执行synchronized关键字的方法,那个线程就持有该方法所属对象的锁(lock),两个对象,
 * 线程获得的就是两个不同的锁,他们互相不影响
 * 
 * 有一种情况则是相同的锁:
 *     即在静态方法上加synchronized关键字,表示锁定.class类,类一级别的锁(独占.class类)
 * 
 * 
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock），
 * 
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
 * @author zhanbq
 *
 */
public class MultiThread {

	private int num = 0;
//	private static int num = 0; 
	
	/** static */
	/**
	 * 加static 是类锁 线程独占
	 * @param tag
	 */
	public synchronized void printNum(String tag){
		try {
			
			if(tag.equals("a")){
				num = 100;
				System.out.println("tag a, set num over!");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag b, set num over!");
			}
			
			System.out.println("tag " + tag + ", num = " + num);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//注意观察run方法输出顺序
	public static void main(String[] args) {
		
		//俩个不同的对象
		final MultiThread m1 = new MultiThread();
		final MultiThread m2 = new MultiThread();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m1.printNum("a");
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override 
			public void run() {
				m2.printNum("b");
			}
		});		
		
		t1.start();
		t2.start();
		
	}
	
	
	
	
}
