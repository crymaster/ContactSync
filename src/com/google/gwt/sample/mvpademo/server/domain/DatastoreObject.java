package com.google.gwt.sample.mvpademo.server.domain;

import javax.persistence.Id;
import javax.persistence.PrePersist;

public class DatastoreObject {
	@Id
	private Long id;
	private Integer version = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@PrePersist
	void onPersist(){
		this.version++;
	}
}
