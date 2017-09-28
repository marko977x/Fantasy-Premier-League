package logic;

public class PlayerRequirements {

	private int numGoals;
	private int numAssist;

	public int getNumGoals() {
		return this.numGoals;
	}

	public int getNumAssist() {
		return this.numAssist;
	}

	public void setNumGoals(int setNumGoals) {
		this.numGoals = setNumGoals;
	}

	public void setNumAssist(int setNumAssist) {
		this.numAssist = setNumAssist;
	}

	public float check(Player player) {
		float form = player.getForm();
		float result = form / Player.MAX_FORM_VALUE;
		return result;
	}

}
