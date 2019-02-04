package com.almundo.rockfield.model;

public class Manager extends Employee {
	
	public Manager(String name) {
		super(name);
	}
	
	public String toString() {
		return "Manager " + name;
	}
}
