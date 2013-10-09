package com.cte.drools;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "companyBean")
@ViewScoped
public class Company implements Submittable {

	private String name;
	private String address;
	private String legalid;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalid() {
		return legalid;
	}

	public void setLegalid(String legalid) {
		this.legalid = legalid;
	}

	public void submit() {
		System.out.println("Company submit");
	}

}
