package com.virtualpairprogrammers.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.virtualpairprogrammers.domain.Action;

public class DiaryManagementMock implements DiaryManagementService {

	private Set<Action> actions;

	public DiaryManagementMock() {
		actions = new HashSet<Action>();

		// add some dummy actions
	}

	@Override
	public void recordAction(Action action) {
		actions.add(action);

	}

	@Override
	public List<Action> getAllIncompleteActions(String requiredUser) {
		// TODO: There must be a more efficient way... stream?
		List<Action> actionList = new ArrayList<>();
		for (Action action : actions) {
			if (action.getOwningUser().equals(requiredUser) && !action.isComplete())
				actionList.add(action);
		}
		return actionList;
	}

}
