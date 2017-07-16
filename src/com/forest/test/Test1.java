package com.forest.test;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.forest.domain.Customer;
import com.forest.utils.HibernateUtils;

public class Test1 {
	@Test
	public void test() {
		Session session = HibernateUtils.openSession();
		//����
		Query query = session.createQuery("from Customer");
		query.setFirstResult(10);
		query.setMaxResults(10);
		
		List<Customer> list = query.list();
		System.out.println(list);
		//�ύ
		session.close();
	}
	@Test
	public void test1() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		for (int i = 0; i < 100; i++) {
			Customer customer = new Customer();
			customer.setName("����"+i);
			//customer.setAddress("����԰"+i+"��");
			session.save(customer);
		}
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery("select name,address from Customer");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(objects[0]+"--"+objects[1]);
		}
		session.close();
	}
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery("select new List(name,address) from Customer");
		List<List> list = query.list();
		System.out.println(list);
	}
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery("select new Map(name,address) from Customer");
		List<Map> list = query.list();
		System.out.println(list);
	}
	@Test
	public void test5() {
		Session session = HibernateUtils.openSession();
		/*Query query = session.createQuery("select new List(name,address) from Customer where address=?");
		query.setParameter(0, "����԰1��");
		List list = query.list();*/
		Query query = session.createQuery("select new List(name,address) from Customer where address=:a");
		query.setParameter("a", "����԰1��");
		List list2 = query.list();
		System.out.println(list2);//[[����, ����԰1��], [����, ����԰1��], [����, ����԰1��], [����1, ����԰1��]]
	}
	@Test
	public void test6() {
		Session session = HibernateUtils.openSession();
		SQLQuery query = session.createSQLQuery("select * from t_customer where address=?");
		query.setParameter(0, "����԰1��");
		SQLQuery entity = query.addEntity(Customer.class);
		List list = entity.list();
		System.out.println(list);
		/*[Customer [id=1, name=����, address=����԰1��], 
		 * Customer [id=2, name=����, address=����԰1��], 
		 * Customer [id=3, name=����, address=����԰1��], 
		 * Customer [id=5, name=����1, address=����԰1��]]*/
	}
	@Test
	public void test7() {
		Session session = HibernateUtils.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("name","����0"));
		Customer object = (Customer)criteria.uniqueResult();
		System.out.println(object);
//		List list = criteria.list();
//		System.out.println(list);
	}
}
