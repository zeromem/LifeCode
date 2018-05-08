package cn.com.ctbri.ctbigdata.smarteyes.model;

import java.io.Serializable;

/**
 * Created by luyi on 2017/6/21.
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private int age;
	private String password;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
