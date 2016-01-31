package com.cls.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.cls.util.Constants.FormType;
import com.cls.util.Constants.Status;

@Entity
@Table(name = "FORM")
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToMany(mappedBy = "form")
	private List<FormMetadata> formMetadataList;

	private Status status;

	// this field can be used for creating reusable forms
	// e.g. participant demographics form which can be used by different
	// companies.
	// a default form can be used and can be edited to create project specific
	// participant demographics forms
	private FormType formType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<FormMetadata> getFormMetadataList() {
		return formMetadataList;
	}

	public void setFormMetadataList(List<FormMetadata> formMetadataList) {
		this.formMetadataList = formMetadataList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public FormType getFormType() {
		return formType;
	}

	public void setFormType(FormType formType) {
		this.formType = formType;
	}

}
