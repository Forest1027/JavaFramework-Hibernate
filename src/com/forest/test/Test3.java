package com.forest.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.junit.Test;

import com.forest.manytomany.Student;
import com.forest.manytomany.Teacher;
import com.forest.onetomany.Customer;
import com.forest.onetomany.Order;
import com.forest.onetoone.IDCard;
import com.forest.onetoone.Person;
import com.forest.utils.HibernateUtils;

public class Test3 {
	@Test
	public void test1() {
		// 一对多保存
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		// 创建客户
		Customer customer = new Customer();
		customer.setName("aa");
		// 创建订单
		Order order = new Order();
		order.setAddress("shanghai");
		// 关联
		customer.getOrders().add(order);
		order.setCustomer(customer);
		// 保存
		// session.save(order);
		session.save(customer);
		// 关闭
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test2() {
		// 一对多删除
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Customer customer = session.get(Customer.class, 1);
		session.delete(customer);
		// 关闭
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test3() {
		// 多对多级联保存
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Student s1 = new Student();
		s1.setName("aaa");
		Student s2 = new Student();
		s2.setName("bbb");

		Teacher t1 = new Teacher();
		t1.setName("xx");
		Teacher t2 = new Teacher();
		t2.setName("yy");

		s1.getTeachers().add(t1);
		s1.getTeachers().add(t2);

		s2.getTeachers().add(t1);
		s2.getTeachers().add(t2);

		session.save(s1);
		session.save(s2);
		// 关闭
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test4() {
		// 多对多级联删除
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		// 使用游离态删除
		/*
		 * Student student = new Student(); student.setId(1);
		 * session.delete(student);//结果，删除了id为1的学生，以及s_t中与该学生有关的记录
		 */
		// 查询到student后删除（持久态删除）
		Student student2 = session.get(Student.class, 1);
		session.delete(student2);// 结果，删除了两个学生和两个老师
		// 关闭
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test5() {
		// 一对一级联保存
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Person person = new Person();
		person.setName("max");

		IDCard idCard = new IDCard();
		idCard.setIdcardNum("123456");

		idCard.setPerson(person);
		session.save(idCard);
		// 关闭
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test6() {
		// 数据准备
		Session session = HibernateUtils.openSession();
		session.beginTransaction();

		Customer customer = new Customer();
		customer.setName("666");

		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setAddress("address" + i);
			order.setCustomer(customer);
			session.save(order);
		}

		// 关闭
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test7() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Customer c = new Customer();
		c.setId(1);
		
		List list = session.getNamedQuery("findOrderByCustomer").setParameter("c", c).list();
		System.out.println(list);
		// 关闭
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void test8() {
		//QBC条件查询
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Order.class);
		SimpleExpression gt = Restrictions.gt("id", 5);
		SimpleExpression like = Restrictions.like("address", "%5");
		LogicalExpression and = Restrictions.and(gt, like);
		criteria.add(and);
		List list = criteria.list();
		System.out.println(list);
		
		
		// 关闭
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void test9() {
		//QBC统计分组
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Order.class);
		//List list = criteria.setProjection(Projections.rowCount()).list();
		List list = criteria.setProjection(Projections.sum("id")).list();
		System.out.println(list);
		// 关闭
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void test10() {
		//离线条件检索
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		dc.add(Restrictions.gt("id", 6));
		
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Criteria criteria = dc.getExecutableCriteria(session);
		List list = criteria.list();
		System.out.println(list);
		
		// 关闭
		session.getTransaction().commit();
		session.close();
	}
}
