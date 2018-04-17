package com.virtualpairprogrammers.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerManagementMock;

public class CustomerManagementServiceTest {

	CustomerManagementService mockServ;
	Customer testCustomer;

	@Before
	public void setUp() throws Exception { //
		mockServ = new CustomerManagementMock();
		testCustomer = new Customer();
		testCustomer.setCustomerId("1-DUM-1");
		testCustomer.setCompanyName("Dummy Company");
		testCustomer.setEmail("dummy@dummy.org");
		testCustomer.setTelephone("+465555555");
		testCustomer.setNotes("Dummy Note");
	}

	@Test
	public void testNewCustomer() {
		mockServ.newCustomer(testCustomer);
	}

	@Test
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCustomerById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCustomersByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllCustomers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFullCustomerDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecordCall() {
		fail("Not yet implemented");
	}

}
