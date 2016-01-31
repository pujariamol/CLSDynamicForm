package com.cls.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.cls.util.Constants.Status;

@Entity
@Table(name = "FORM_DATA")
public class FormData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "form_metadata_id")
	private FormMetadata formMetadata;

	@ManyToOne
	@JoinColumn(name = "form_id")
	private Form form;

	@NotNull
	private long participantId;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project projectId;

	private Status status;
	
	
	
	
	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(long participantId) {
		this.participantId = participantId;
	}

	public FormMetadata getFormMetadata() {
		return formMetadata;
	}

	public void setFormMetadata(FormMetadata formMetadata) {
		this.formMetadata = formMetadata;
	}

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	public long getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
