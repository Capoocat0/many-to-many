package com.example.manytomany.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author someone
 */
@Entity
@Table(catalog = "manytomany", schema = "public")
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
	@ManyToMany(fetch = FetchType.EAGER)//(mappedBy = "studentCollection")
	private Collection<Course> courseCollection;

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

	public Collection<Course> getCourseCollection() {
		return courseCollection;
	}

	public void setCourseCollection(Collection<Course> courseCollection) {
		this.courseCollection = courseCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
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
