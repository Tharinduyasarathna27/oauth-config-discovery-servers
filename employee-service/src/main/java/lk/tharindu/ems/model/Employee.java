package lk.tharindu.ems.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Integer id;
	 String name;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 Address address;
	 
	 @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
	 List<Telephone> telephones;

	 @Transient
	 Allocation[] allocation;
	 

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employee_project",
	joinColumns = @JoinColumn(name="eid",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="pid", referencedColumnName = "id"))
	 List<Project> projects;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Telephone> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Allocation[] getAllocation() {
		return allocation;
	}

	public void setAllocation(Allocation[] allocation) {
		this.allocation = allocation;
	}
}
