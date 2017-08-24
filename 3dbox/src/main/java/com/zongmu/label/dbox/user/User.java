package com.zongmu.label.dbox.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.zongmu.label.dbox.core.entity.EntityModel;

@Entity
@Table
public class User extends EntityModel {

	@Column
	private String userName;

	@Column
	private String email;

	@Column
	private String phone;

	@Column
	private String password;

	@Column
	private String resetPasswordActiveCode;

	@Column
	private DateTime resetPasswordDate;

	@Column
	private BusinessRole businessRole = BusinessRole.Marker;
	
	@Column
	private UserStatus status = UserStatus.UnActive;

	@Column
	private String icon;

	@Column
	private String qq;

	@Column
	private String wechat;

	@Column
	private int loginFailedCount;

	@Column
	private DateTime lockedDate;

	@Column
	private Sex sex;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetPasswordActiveCode() {
		return resetPasswordActiveCode;
	}

	public void setResetPasswordActiveCode(String resetPasswordActiveCode) {
		this.resetPasswordActiveCode = resetPasswordActiveCode;
	}

	public DateTime getResetPasswordDate() {
		return resetPasswordDate;
	}

	public void setResetPasswordDate(DateTime resetPasswordDate) {
		this.resetPasswordDate = resetPasswordDate;
	}

	public BusinessRole getBusinessRole() {
		return businessRole;
	}

	public void setBusinessRole(BusinessRole businessRole) {
		this.businessRole = businessRole;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public int getLoginFailedCount() {
		return loginFailedCount;
	}

	public void setLoginFailedCount(int loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}

	public DateTime getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(DateTime lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
