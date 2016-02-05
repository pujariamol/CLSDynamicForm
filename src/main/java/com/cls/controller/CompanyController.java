package com.cls.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cls.dao.CompanyDao;
import com.cls.model.Company;
import com.cls.util.Constants;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyDao companyDao;
	JsonNodeFactory factory = new JsonNodeFactory(false);

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody Company company) {

		ObjectNode msg = factory.objectNode();
		try {
			System.out.println("asdf");
			msg.put("message", "Company created successfully!!");
		} catch (Exception e) {
			msg.put("message", "Error in creating company!!");
			e.printStackTrace();
		}
		return new ResponseEntity<String>(msg.toString(),HttpStatus.CREATED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAll() {
		ObjectNode msg = factory.objectNode();
		ArrayNode companyList = factory.arrayNode();

		try {
			List<Company> companies = (List<Company>) companyDao.findAll();
			for (Company company : companies) {
				ObjectNode obj = factory.objectNode();
				obj.put("name", company.getName());
				obj.put("description", company.getDescription());
				companyList.add(obj);
			}
			msg.put("companies", companyList);
		} catch (Exception e) {
			msg.put("message", "Error fetching company list!!");
			e.printStackTrace();
		}
		return msg.toString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Company getById(@PathVariable String id) throws IOException {
		Company company;
		company = companyDao.findById(Long.valueOf(id));
		return company;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable long id, @RequestBody Company updatedCompany) {
		try {
			Company company = companyDao.findOne(id);
			company.setDescription(updatedCompany.getDescription());
			company.setName(updatedCompany.getName());
			company.setStatus(updatedCompany.getStatus());
			companyDao.save(company);
		} catch (Exception ex) {
			return Constants.ERROR_UPDATING_THE_COMPANY + ex.toString();
		}

		return Constants.COMPANY_UPDATED_SUCCESSFULLY;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		try {
			companyDao.delete(id);
		} catch (Exception ex) {
			return Constants.ERROR_DELETING_COMPANY + ex.toString();
		}

		return Constants.COMPANY_DELETED_SUCCESSFULLY;
	}

}
