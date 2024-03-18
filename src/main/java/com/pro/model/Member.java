package com.pro.model;


public class Member {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Member(String name) {
		super();
		this.name = name;
			
	}

	@Override
	public String toString() {
		return "Member [name=" + name + "]";
	}
	

}


