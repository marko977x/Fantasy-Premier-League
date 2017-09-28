package logic;

import java.util.ArrayList;

public class Result {

	private float score;
	private Team team;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getIdentification() {
		String identifications = "";
		ArrayList<String> identificationsList = team.getPlayerIdentifications();
		identificationsList.sort(String::compareToIgnoreCase);
		for (String identification : identificationsList) {
			identifications = identifications + " " + identification;
		}
		return identifications;
	}

}
