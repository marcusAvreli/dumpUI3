package dumpUI3.model.entity;

import java.io.Serializable;



public class Report implements Serializable, Comparable<Report>, Cloneable {
	private int id;
	private String name;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public int compareTo(Report arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
