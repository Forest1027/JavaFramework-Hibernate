package com.forest.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.forest.domain.Customer;
import com.forest.domain.Order;
import com.forest.utils.HibernateUtils;

public class Test2 {
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		/*List list = session.createQuery("from Customer").list();
		//session.clear();
		System.out.println("------------");
		Customer c = session.get(Customer.class, 1);
		session.evict(c);
		System.out.println("------------");*/
		Customer c1 = session.get(Customer.class, 1);
		System.out.println("------------");
		c1.setName("test1");
		session.refresh(c1);
		/*c.setName("test1");
		session.getTransaction().commit();
		session.close();*/
	}
	@Test
	public void test2() {
		//一对多
		//获取session
		Session session = HibernateUtils.openSession();
		//开启事务
		session.beginTransaction();
		//操作
			//创建customer
		Customer c1 = new Customer();
		c1.setName("aa");
			//创建订单
		Order o1 = new Order();
		o1.setMoney(2000d);
		o1.setReceiverInfo("HongKong");
		Order o2 = new Order();
		o2.setMoney(5000d);
		o2.setReceiverInfo("Wuhan");
			//设置关联
		c1.getOrders().add(o1);
		c1.getOrders().add(o2);
		
		/*o1.setC(c1);
		o2.setC(c1);*/
			//进行添加操作
		/*session.save(o1);
		session.save(o2);*/
		session.save(c1);
		//提交
		session.getTransaction().commit();
	}
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Customer customer = new Customer();
		customer.setName("bb");
		Order order = new Order();
		order.setMoney(2500d);
		order.setReceiverInfo("Singapore");
		order.setC(customer);
		session.save(order);
		session.getTransaction().commit();
	}
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Customer customer = session.get(Customer.class, 2);
		session.delete(customer);
		session.getTransaction().commit();
		session.close();
	}
}
