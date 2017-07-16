package com.forest.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_person")
public class Person {
	@Id
	@GenericGenerator(strategy="uuid",name="myuuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	private String name;
	@OneToOne(targetEntity=IDCard.class,mappedBy="person")
	@Cascade(CascadeType.ALL)
	private IDCard idCard;
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String id, String name, IDCard idCard) {
		super();
		this.id = id;
		this.name = name;
		this.idCard = idCard;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IDCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", idCard=" + idCard + "]";
	}
	
}
