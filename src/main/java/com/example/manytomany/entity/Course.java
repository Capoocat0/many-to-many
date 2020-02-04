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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author someone
 */
@Entity
@Table(catalog = "manytomany", schema = "public")
public class Course implements Serializable {

	private static final long serialVersionUID = 4959301416563126328L;

	@Id
	@Basic(optional = false)
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "display_name")
	private String displayName;

//	@JoinTable(name = "course_rating", joinColumns = {
//		@JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
//	}, inverseJoinColumns = {
//		@JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
//	})
	
	@ManyToMany(mappedBy = "courseCollection", fetch = FetchType.EAGER)
	private Collection<Student> studentCollection;

	public Course() {
	}

	protected Course(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Collection<Student> getStudentCollection() {
		return studentCollection;
	}

	public void setStudentCollection(Collection<Student> studentCollection) {
		this.studentCollection = studentCollection;
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
		if (!(object instanceof Course)) {
			return false;
		}
		Course other = (Course) object;
		return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
	}

	@Override
	public String toString() {
		return "com.example.manytomany.entity.Course[ id=" + id + " ]";
	}
}
