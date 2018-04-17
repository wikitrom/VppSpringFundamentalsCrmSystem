package com.virtualpairprogrammers.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

// ----- More of a complete class than a mock

public class CustomerManagementMock implements CustomerManagementService {

	private HashMap<String, Customer> customers;

	// -- constructors --

	public CustomerManagementMock() {
		customers = new HashMap<>();

		// populate with some dummy data
		customers.put("CS001", new Customer("CS001", "Ericsson AB", "dummy note 1"));
		customers.put("CS002", new Customer("CS002", "Enea", "dummy note 2"));
		customers.put("CS003", new Customer("CS003", "Spotify", "dummy note 3"));

	}

	// -- methods --

	@Override
	public void newCustomer(Customer newCustomer) {
		customers.put(newCustomer.getCustomerId(), newCustomer);
	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		// TODO: perhaps add a check if customer exist by calling findCustomerById
		customers.replace(changedCustomer.getCustomerId(), changedCustomer);
	}

	@Override
	public void deleteCustomer(Customer oldCustomer) {
		customers.remove(oldCustomer.getCustomerId());
	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
//		try {
			Customer thisCustomer = getFullCustomerDetail(customerId);
			// FIX: Should set customer calls to NULL before returning - use a copy
			return thisCustomer;
//		} catch (CustomerNotFoundException e) {
//			throw new CustomerNotFoundException();
//		}
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		List<Customer> foundCustomers;
		// my solution using stream()
//		foundCustomers = customers.entrySet().stream()
//				.filter(cust -> cust.getValue().getCompanyName().matches(".*" + name + ".*")).map(c -> c.getValue())
//				.collect(Collectors.toList());

		// alternative solution (from VPP)
		foundCustomers = new ArrayList<>();
		for (Customer nextCustomer : customers.values()) {
			if (nextCustomer.getCompanyName().equals(name)) {
				foundCustomers.add(nextCustomer);
			}
		}
		return foundCustomers;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// my solution using stream()
//		 List<Customer> allCustomers = customers.entrySet().stream().map(c -> c.getValue()).collect(Collectors.toList());
//		 return allCustomers;

		// alternative (better solution) from VPP
		return new ArrayList<Customer>(customers.values());

	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
		Customer thisCustomer = customers.get(customerId);
		if (thisCustomer != null)
			throw new CustomerNotFoundException();
		return thisCustomer;
	}

	@Override
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
//		try {
			getFullCustomerDetail(customerId).addCall(callDetails);
//		} catch (CustomerNotFoundException e) {
//			throw new CustomerNotFoundException();
//		}
	}

}
