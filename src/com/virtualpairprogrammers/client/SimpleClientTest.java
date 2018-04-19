package com.virtualpairprogrammers.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

public class SimpleClientTest {

	public static void main(String[] args) {

		// -- initialize Spring
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		CallHandlingService callHandler = container.getBean(CallHandlingService.class);

		// -- record call

		// create a dummy call
		String customerID = "CS003";
		Call newCall = new Call("Got a call from Spotify");

		// create some dummy actions
		Collection<Action> actions = new ArrayList<>();
		GregorianCalendar requiredBy = new GregorianCalendar();
		requiredBy.add(Calendar.DAY_OF_WEEK, 1);
		GregorianCalendar requiredBy_2 = new GregorianCalendar();
		requiredBy_2.add(Calendar.DAY_OF_WEEK, 5);
		actions.add(new Action("Investigate customer suggestion", requiredBy, customerID));
		actions.add(new Action("Decide on customer suggestion", requiredBy_2, customerID));
		actions.add(new Action("Call customer back", requiredBy_2, customerID));

		// record the call
		try {
			callHandler.recordCall(customerID, newCall, actions);
		} catch (Exception e) {
			System.out.println("Failed to log call for customer: " + customerID + " " + e);
			e.printStackTrace();
		}

		// -- display all know information for customerID

		// initialize Spring
		CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
		DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);

		System.out.println();
		System.out.println("Information for customer " + customerID);
		try {
			Customer customer = customerService.findCustomerById("CS003");
			System.out.println("Calls handled");
			Collection<Call> calls = customer.getCalls();
			for (Call call : calls) {
				System.out.println(call);
			}
		} catch (Exception e) {
			System.out.println("Caught exceotion: " + e);
			e.printStackTrace();
		}
		try {
			System.out.println();
			System.out.println("Open issues");
			Collection<Action> openActions = diaryService.getAllIncompleteActions(customerID);
			for (Action action : openActions) {
				System.out.println(action);

			}
		} catch (Exception e) {
			System.out.println("Caught exceotion: " + e);
			e.printStackTrace();
		}

		container.close();

	}

}
