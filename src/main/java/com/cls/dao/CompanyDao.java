package com.cls.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.cls.model.Company;

@Transactional
public interface CompanyDao extends CrudRepository<Company, Long> {

	public Company findById(Long valueOf);
	

}
