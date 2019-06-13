package mobots.mbot.model;

public class Action {

	private ActionType actionType;
	private String id;
	private String target;

	public Action() {
	}

	public Action(String id, ActionType actionType, String target) {
		this.id = id;
		this.actionType = actionType;
		this.target = target;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Action [actionType=" + actionType + ", id=" + id + ", target=" + target + "]";
	}

}
