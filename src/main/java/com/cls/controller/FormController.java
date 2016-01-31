package com.cls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cls.dao.FormDao;
import com.cls.model.Form;
import com.cls.util.Constants;

@RestController
@RequestMapping(value="/form")
public class FormController {
	
	@Autowired
	private FormDao formDao;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody Form form) {
		try {
			formDao.save(form);
		} catch (Exception ex) {
			return Constants.EXCEPTION_WHILE_CREATING_FORM + ex.getMessage();
		}
		return Constants.FORM_CREATED_SUCCESSFULLY;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Form getById(@PathVariable long id) {
		Form form;
		form = formDao.findOne(id);
		return form;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable long id, @RequestBody Form updatedForm) {
		try {
			Form form = formDao.findOne(id);
			form.setFormType(updatedForm.getFormType());
			form.setName(updatedForm.getName());
			form.setProject(updatedForm.getProject());
			form.setStatus(updatedForm.getStatus());
			formDao.save(form);
		} catch (Exception ex) {
			return Constants.ERROR_UPDATING_THE_FORM + ex.toString();
		}

		return Constants.FORM_UPDATED_SUCCESSFULLY;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		try {
			formDao.delete(id);
		} catch (Exception ex) {
			return Constants.ERROR_DELETING_THE_FORM + ex.toString();
		}

		return Constants.FORM_DELETED_SUCCESSFULLY;
	}

}
