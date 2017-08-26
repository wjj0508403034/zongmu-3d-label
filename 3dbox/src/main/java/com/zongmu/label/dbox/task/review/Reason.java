package com.zongmu.label.dbox.task.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zongmu.label.dbox.core.entity.EntityModel;

@Entity
@Table
public class Reason extends EntityModel{

	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
