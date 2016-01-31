package com.cls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cls.dao.ProjectDao;
import com.cls.model.Project;
import com.cls.util.Constants;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
	
	@Autowired
	private ProjectDao projectDao;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestBody Project project) {
		try {
			projectDao.save(project);
		} catch (Exception ex) {
			return Constants.EXCEPTION_WHILE_CREATING_PROJECT + ex.getMessage();
		}
		return Constants.PROJECT_CREATED_SUCCESSFULLY;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Project getById(@PathVariable long id) {
		Project project;
		project = projectDao.findOne(id);
		return project;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable long id, @RequestBody Project updatedProject) {
		try {
			Project project = projectDao.findOne(id);
			project.setCompany(updatedProject.getCompany());
			project.setDescription(updatedProject.getDescription());
			project.setForms(updatedProject.getForms());
			project.setName(updatedProject.getName());
			project.setSiteName(updatedProject.getSiteName());
			project.setStatus(updatedProject.getStatus());
			projectDao.save(project);
		} catch (Exception ex) {
			return Constants.ERROR_UPDATING_THE_PROJECT + ex.toString();
		}

		return Constants.PROJECT_UPDATED_SUCCESSFULLY;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		try {
			projectDao.delete(id);
		} catch (Exception ex) {
			return Constants.ERROR_DELETING_THE_PROJECT + ex.toString();
		}

		return Constants.PROJECT_DELETED_SUCCESSFULLY;
	}

}
