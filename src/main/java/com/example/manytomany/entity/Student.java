package com.example.manytomany.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 * 學生
 *
 * @author 歐炫
 */
@Entity
@SuppressWarnings("PersistenceUnitPresent")
@Table(catalog = "manytomany", schema = "public", name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 5681823641952133327L;

	@Id
	@Basic(optional = false)
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@JoinTable(name = "course_rating", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Course> courses;

	public Student() {
	}

	protected Student(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Student)) {
			return false;
		}
		Student other = (Student) object;
		return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
	}

	@Override
	public String toString() {
		return "com.example.manytomany.entity.Student[ id=" + id + " ]";
	}
}
