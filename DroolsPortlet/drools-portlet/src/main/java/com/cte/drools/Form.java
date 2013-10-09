package com.cte.drools;

public class Form implements Comparable<Form>{
	private String name;
	private String title;
	private String bean;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int compareTo(Form arg0) {
		return this.name.compareTo(arg0.name);
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}
	

}
