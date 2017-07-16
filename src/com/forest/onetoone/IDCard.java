package com.forest.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_idcard")
public class IDCard {
	@Id
	@GenericGenerator(strategy="uuid",name="myuuid")
	@GeneratedValue(generator="myuuid")
	private String id;
	private String idcardNum;
	@OneToOne(targetEntity=Person.class)
	//@JoinColumn(name="idcard_id")
	@Cascade(CascadeType.ALL)
	private Person person;
	public IDCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IDCard(String id, String idcardNum, Person person) {
		super();
		this.id = id;
		this.idcardNum = idcardNum;
		this.person = person;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcardNum() {
		return idcardNum;
	}
	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "IDCard [id=" + id + ", idcardNum=" + idcardNum + ", person=" + person + "]";
	}
	
}
