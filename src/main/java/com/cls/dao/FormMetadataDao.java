package com.cls.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.cls.model.FormMetadata;
@Transactional
public interface FormMetadataDao extends CrudRepository<FormMetadata, Long> {

}
