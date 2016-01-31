package com.cls.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.cls.model.Project;

@Transactional
public interface ProjectDao extends CrudRepository<Project, Long> {

}
