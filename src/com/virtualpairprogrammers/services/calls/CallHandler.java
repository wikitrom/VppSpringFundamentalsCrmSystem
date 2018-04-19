package com.virtualpairprogrammers.services.calls;

import java.util.Collection;

import com.virtualpairprogrammers.domain.Action;
import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

public class CallHandler implements CallHandlingService {

	private CustomerManagementService customerService;
	private DiaryManagementService diaryService;

	// -- constructors --

	// --- inject used services
	public CallHandler(CustomerManagementService customerService, DiaryManagementService diaryService) {
		this.customerService = customerService;
		this.diaryService = diaryService;

	}

	// -- methods --

	@Override
	public void recordCall(String customerId, Call newCall, Collection<Action> actions)
			throws CustomerNotFoundException {

		// log call
		if (newCall != null)
			customerService.recordCall(customerId, newCall);

		// log actions
		if (actions != null) {
			for (Action action : actions) {
				diaryService.recordAction(action);
			}
		}
	}

}
