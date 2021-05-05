package com.webapp.domain.pojo;

import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
	private Integer id;
    private String name;
    private String password;
    private String phone;
    private String language;

	public Test1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test1(Integer id, String name, String password, String phone, String language) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.language = language;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Test1 test1 = (Test1) o;
		return Objects.equals(id, test1.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static void main(String [] args) {
		Test1 t1 = new Test1(1,"1","1","1","zh");
		Test1 t2 = new Test1(2,"2","1","1","us");
		Test1 t3 = new Test1(2,"","1","1","zh");
		Test1 t4 = new Test1(4,"1","1","1","us");
		Test1 t5 = new Test1(5,"1","1","1","zh");
		Test1 t6 = new Test1(6,"1","1","1","us");
		Test1 t7 = new Test1(7,"1","1","1","zh");

		List<Test1> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		list.add(t7);
//		Map<String, List<Test1>> map  = list.stream().collect(Collectors.groupingBy(t -> t.getLanguage()));
//		for(Map.Entry p : map.entrySet()) {
//			System.out.println(p.getKey()+":"+p.getValue());
//		}

		Map<Integer,Test1> set = new HashMap<>();
		set.put(t3.getId(),t3);
		set.put(t4.getId(),t4);
		set.put(t5.getId(),t5);
		set.put(t1.getId(),t1);
		set.put(t2.getId(),t2);


		for (Map.Entry s: set.entrySet()) {
			System.out.println(s.getKey()+":"+((Test1) s.getValue()).getId());
		}

		Hashtable table = new Hashtable();






	}
}

