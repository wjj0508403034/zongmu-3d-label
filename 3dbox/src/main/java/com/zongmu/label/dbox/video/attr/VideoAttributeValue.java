package com.zongmu.label.dbox.video.attr;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zongmu.label.dbox.core.entity.EntityModel;

@Entity
@Table
public class VideoAttributeValue extends EntityModel {

	private String value;

	@ManyToOne
	@JoinColumn(nullable = false)
	private VideoAttribute videoAttribute;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@JsonIgnore
	public VideoAttribute getVideoAttribute() {
		return videoAttribute;
	}

	public void setVideoAttribute(VideoAttribute videoAttribute) {
		this.videoAttribute = videoAttribute;
	}

}
