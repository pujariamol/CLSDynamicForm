package com.cls.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.cls.model.FormData;

@Transactional
public interface FormDataDao extends CrudRepository<FormData, Long> {

}
