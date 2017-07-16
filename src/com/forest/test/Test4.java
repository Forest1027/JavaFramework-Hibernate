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
		// hql内连接
		// 定义hql
		// String sql = "select * from t_customer c inner join t_order o on
		// c.id=o.customer_id";

		// 显式内连接
		String hql = "from Customer c inner join c.orders with c.id=1";
		// 创建查询
		// SQLQuery query = session.createSQLQuery(sql);
		Query query = session.createQuery(hql);
		// 获取结果
		List<Object[]> list = query.list();
		for (Object[] object : list) {
			for (Object object2 : object) {
				System.out.println(object2 + "\t");
			}
		}
	}

	@Test
	public void test2() {
		// 迫切内连接
		String hql = "select distinct c from com.forest.onetomany.Customer c inner join fetch c.orders";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		System.out.println(list);
	}

	@Test
	public void test3() {
		// 外连接
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
		// 迫切外连接
		String hql = "select distinct c from com.forest.onetomany.Customer c left outer join fetch c.orders";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		System.out.println(list);
	}

	@Test
	public void test5() {
		// 优化
		String hql = "update Customer c set c name='jane' where id = 1";
		Query query = session.createQuery(hql);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
	}

	@Test
	public void test6() {
		// 检索策略
		/*
		 * 研究一对多set上lazy的配置，默认fetch=select
		 * 1. @LazyCollection(LazyCollectionOption.FALSE) 场景下
		 */
		// 查询Customer的时候就将其关联的Order数据进行查询封装

		// 2.@LazyCollection(LazyCollectionOption.TRUE) 场景下
		// 获取其关联数据信息的时候才查询，获取的是关联数据的个数
		// 虽然延迟查询了，但是获取是Order的全信息的sql语句
		/*
		 * Customer customer = session.get(Customer.class, 1);
		 * System.out.println("-------"); Set<Order> orders =
		 * customer.getOrders(); System.out.println(orders);
		 */

		// 3. @LazyCollection(LazyCollectionOption.EXTRA) 场景下
		// 获取其关联数据信息的时候才查询，获取的是关联数据的个数
		// 发生select count(1) 的sql语句获取关联数据个数
		Customer customer = session.get(Customer.class, 1);
		System.out.println("-------");
		int size = customer.getOrders().size();
		System.out.println(size);
	}
}
