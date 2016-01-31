package com.cls.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.cls.model.Form;

@Transactional
public interface FormDao extends CrudRepository<Form, Long> {

}
