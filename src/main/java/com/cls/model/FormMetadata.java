package com.cls.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import com.cls.util.Constants.ControlTypes;

@Entity
@Table(name = "form_metadata")
public class FormMetadata {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="form_id")
	private Form form;
	
	@NotNull
	private String label;
	
	//Can be Enum
	// textbox, textarea, checkbox, radio
	private ControlTypes type;
	
	@ColumnDefault(value="0")
	private int sequenceNumber;
	
	@ColumnDefault(value="false")
	private boolean isRequired;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ControlTypes getType() {
		return type;
	}

	public void setType(ControlTypes type) {
		this.type = type;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public long getId() {
		return id;
	}
	
	
	
	
}
