package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class ResultFinder {

	private ArrayList<Player> allPlayers;
	private TeamRequirements teamRequirements;

	public ArrayList<Player> getAllPlayers() {
		return allPlayers;
	}

	public void setAllPlayers(ArrayList<Player> allPlayers) {
		this.allPlayers = allPlayers;
	}

	public ArrayList<Result> findAll(int numResults) {
		ArrayList<Result> list = new ArrayList<Result>();
		HashSet<Result> set = new HashSet<Result>();
		while (set.size() < numResults) {
			Result result = findOne();
			if (!set.contains(result) && result != null) {
				set.add(result);
			}
		}

		list.addAll(set);
		set.clear();

		return list;
	}

	public Result findOne() {
		Team team = new Team();
		int limitForLoop = 2000;
		int i = 0;
		while (team.size() < teamRequirements.getNumMiddlePlayer() + teamRequirements.getNumOffensePlayer()
				&& i < limitForLoop) {
			Player player = chooseRandomPlayer();
			if (player.getPosition() == 0
					&& team.getNumberOfMidfielders(team) < teamRequirements.getNumMiddlePlayer()) {
				team.addPlayer(player);
			}
			if (player.getPosition() == 1 && team.getNumberOfAttackers(team) < teamRequirements.getNumOffensePlayer()) {
				team.addPlayer(player);
			}
			i++;
		}
		Result result = new Result();
		result.setTeam(team);
		result.setScore(teamRequirements.check(team));
		if (result.getScore() > Team.MIN_POINTS) {
			return result;
		} else {
			return null;
		}
	}

	public int chooseRandomPlayerIndex() {
		Random randomNum = new Random();
		return randomNum.nextInt(allPlayers.size());
	}

	public TeamRequirements getTeamRequirements() {
		return teamRequirements;
	}

	public void setTeamRequirements(TeamRequirements teamRequirements) {
		this.teamRequirements = teamRequirements;
	}

	public Player chooseRandomPlayer() {
		return allPlayers.get(chooseRandomPlayerIndex());
	}

}
