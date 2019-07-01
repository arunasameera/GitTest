package com.aruna.dbservices.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name ="shift")
public class Shift {
	
	public Shift(){
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		timeCreated = new java.sql.Timestamp(now.getTime());
		isDeprecated=false;
		
		
	}
	
	
	
	@Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
	private UUID id;

	@Column(name = "time_created", nullable = false)
	private Timestamp timeCreated;

	@Column(name = "is_deprecated", nullable = false,columnDefinition = "boolean default true")
	private boolean isDeprecated;
	
	@Column(name = "name", nullable = false,length = 255)
	private String name;

	public Timestamp getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	

	public boolean isDeprecated() {
		return isDeprecated;
	}

	public void setDeprecated(boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
	
	

}
