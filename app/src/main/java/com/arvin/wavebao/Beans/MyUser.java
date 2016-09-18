package com.arvin.wavebao.Beans;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class MyUser extends BmobUser {

	private static final long serialVersionUID = 1L;
	private Integer age;
	private Boolean sex;

	private List<String> hobby;		// 对应服务端Array类型：String类型的集合

	private BmobFile pic;

	public BmobFile getPic(){
		return this.pic;
	}
	public void setPic(BmobFile pic){
		if(pic == null)
			return;
		this.pic = pic;
	}

	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return getUsername()+"\n"+getObjectId()+"\n"+age+"\n"+getSessionToken()+"\n"+getEmailVerified();
	}
}
