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
		//һ�Զ�
		//��ȡsession
		Session session = HibernateUtils.openSession();
		//��������
		session.beginTransaction();
		//����
			//����customer
		Customer c1 = new Customer();
		c1.setName("aa");
			//��������
		Order o1 = new Order();
		o1.setMoney(2000d);
		o1.setReceiverInfo("HongKong");
		Order o2 = new Order();
		o2.setMoney(5000d);
		o2.setReceiverInfo("Wuhan");
			//���ù���
		c1.getOrders().add(o1);
		c1.getOrders().add(o2);
		
		/*o1.setC(c1);
		o2.setC(c1);*/
			//������Ӳ���
		/*session.save(o1);
		session.save(o2);*/
		session.save(c1);
		//�ύ
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
