package com.forest.onetomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "t_customer", catalog = "test")
@NamedQuery(name="myHql",query="from Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=20)
	private String name;
	
	@OneToMany(targetEntity=Order.class,mappedBy="customer",orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	//研究一对多set上lazy的配置，默认fetch=select
	//1. @LazyCollection(LazyCollectionOption.FALSE) 场景下
	//@Fetch(FetchMode.SELECT)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//2. @LazyCollection(LazyCollectionOption.TRUE) 场景下 
	//@LazyCollection(LazyCollectionOption.TRUE)
	//3. @LazyCollection(LazyCollectionOption.EXTRA) 场景下
	//@LazyCollection(LazyCollectionOption.EXTRA)
	//1.@Fetch(FetchMode.JOIN)
	@Fetch(FetchMode.SUBSELECT)
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<Order> orders = new HashSet<>();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String name, Set<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", orders=" + orders + "]";
	}

}
