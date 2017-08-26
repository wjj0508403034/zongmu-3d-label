package com.zongmu.label.dbox.video.attr;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.zongmu.label.dbox.core.entity.EntityModel;

@Entity
@Table
public class VideoAttribute extends EntityModel{

	@Column
	private String name;
	
	@OneToMany(mappedBy = "videoAttribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@OrderBy("id ASC")
	private List<VideoAttributeValue> items = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VideoAttributeValue> getItems() {
		return items;
	}

	public void setItems(List<VideoAttributeValue> items) {
		this.items = items;
	}
}
