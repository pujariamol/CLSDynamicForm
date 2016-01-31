package com.cls.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cls.dao.CompanyDao;
import com.cls.model.Company;
import com.cls.util.Constants;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyDao companyDao;
	JsonNodeFactory factory = new JsonNodeFactory(false);

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=*")
	@ResponseBody
	public String create(@RequestBody Company company, HttpServletResponse response) {
		
		ObjectNode msg = factory.objectNode();
		try {
			System.out.println("asdf");
			

			msg.put("message", "Company created successfully!!");

			// JSONPObject j = new json
			// throw new Exception("Test---------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String resp = "{\"message\":\"Created New Company \"}";

		return msg.toString();
		// return Constants.COMPANY_CREATED_SUCCESSFULLY;
	}

	// @RequestMapping(method = RequestMethod.POST)
	// public String created(@PathVariable String payload) {
	// try{
	// System.out.println(payload);
	//// companyDao.save(company);
	// }catch(Exception ex){
	// return Constants.EXCEPTION_WHILE_CREATING_COMPANY + ex.getMessage() ;
	// }
	// return Constants.COMPANY_CREATED_SUCCESSFULLY;
	// }

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
