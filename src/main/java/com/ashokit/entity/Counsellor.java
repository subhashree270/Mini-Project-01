package com.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Counsellor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	private String cname;
	private String cemail;
	private String cpwd;
	private Long cphno;
	
	public Counsellor() {
		
	}

	public Counsellor(Integer cid, String cname, String cemail, String cpwd, Long cphno) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cemail = cemail;
		this.cpwd = cpwd;
		this.cphno = cphno;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCpwd() {
		return cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	public Long getCphno() {
		return cphno;
	}

	public void setCphno(Long cphno) {
		this.cphno = cphno;
	}

	
}
