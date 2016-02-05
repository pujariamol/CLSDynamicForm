package com.cls.controller;

import static org.junit.Assert.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import static org.hamcrest.Matchers.equalTo;

import com.cls.ClsDynamicFormApplication;
import com.cls.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ClsDynamicFormApplication.class)
@WebAppConfiguration
public class CompanyControllerTest {

	final String BASE_URL = "http://localhost:8080/company/";

	@Test
	public void shouldCreateNewCompany() {

		final String COMPANY_NAME = "Test company";
		final String COMPANY_DESCRIPTION = "Company Description";

		Company company = new Company();
		company.setName(COMPANY_NAME);
		company.setDescription(COMPANY_DESCRIPTION);

		RestTemplate rest = new TestRestTemplate();

		ResponseEntity<String> response = rest.postForEntity(BASE_URL, company, Company.class, Collections.EMPTY_MAP);

		System.out.println("===================="+response.toString());
		 assertThat( response.getStatusCode() , equalTo(HttpStatus.CREATED));
		// assertThat( userCreated.getId() , notNullValue() );
		// assertThat( userCreated.getName() , equalTo(CUSTOMER_NAME) );
		// assertThat( userCreated.getAddress() , equalTo(CUSTOMER_ADDRESS) );
	}

}
