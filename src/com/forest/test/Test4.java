package com.forest.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.forest.onetomany.Customer;
import com.forest.onetomany.Order;
import com.forest.utils.HibernateUtils;

public class Test4 {
	private Session session;

	@Before
	public void before() {
		session = HibernateUtils.openSession();
		session.beginTransaction();
	}

	@After
	public void after() {
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test1() {
		// hql������
		// ����hql
		// String sql = "select * from t_customer c inner join t_order o on
		// c.id=o.customer_id";

		// ��ʽ������
		String hql = "from Customer c inner join c.orders with c.id=1";
		// ������ѯ
		// SQLQuery query = session.createSQLQuery(sql);
		Query query = session.createQuery(hql);
		// ��ȡ���
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			for (Object object2 : object) {
				System.out.println(object2 + "\t");
			}
		}
	}

	@Test
	public void test2() {
		// ����������
		String hql = "select distinct c from com.forest.onetomany.Customer c inner join fetch c.orders";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		System.out.println(list);
	}

	@Test
	public void test3() {
		// ������
		String hql = "from com.forest.onetomany.Customer c left outer join c.orders";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
	}

	@Test
	public void test4() {
		// ����������
		String hql = "select distinct c from com.forest.onetomany.Customer c left outer join fetch c.orders";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		System.out.println(list);
	}

	@Test
	public void test5() {
		// �Ż�
		String hql = "update Customer c set c name='jane' where id = 1";
		Query query = session.createQuery(hql);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
	}

	@Test
	public void test6() {
		// ��������
		/*
		 * �о�һ�Զ�set��lazy�����ã�Ĭ��fetch=select
		 * 1. @LazyCollection(LazyCollectionOption.FALSE) ������
		 */
		// ��ѯCustomer��ʱ��ͽ��������Order���ݽ��в�ѯ��װ

		// 2.@LazyCollection(LazyCollectionOption.TRUE) ������
		// ��ȡ�����������Ϣ��ʱ��Ų�ѯ����ȡ���ǹ������ݵĸ���
		// ��Ȼ�ӳٲ�ѯ�ˣ����ǻ�ȡ��Order��ȫ��Ϣ��sql���
		/*
		 * Customer customer = session.get(Customer.class, 1);
		 * System.out.println("-------"); Set<Order> orders =
		 * customer.getOrders(); System.out.println(orders);
		 */

		// 3. @LazyCollection(LazyCollectionOption.EXTRA) ������
		// ��ȡ�����������Ϣ��ʱ��Ų�ѯ����ȡ���ǹ������ݵĸ���
		// ����select count(1) ��sql����ȡ�������ݸ���
		Customer customer = session.get(Customer.class, 1);
		System.out.println("-------");
		int size = customer.getOrders().size();
		System.out.println(size);
	}
}
