package logic;

import java.util.ArrayList;
import java.util.HashSet;

public class Team {

	private String name;
	private HashSet<Player> players;
	public static final int MAX_PLAYERS = 7;
	public static final int MIN_PLAYERS = 5;
	public static final int MIN_POINTS = 1;

	public Team() {
		this.players = new HashSet<Player>();
	}

	public String getName() {
		return this.name;
	}

	public HashSet<Player> getPlayers() {
		return this.players;
	}

	public void setName(String setName) {
		this.name = setName;
	}

	public float getPrice() {
		float totalPrice = 0;
		for (Player player : players) {
			totalPrice = totalPrice + player.getPrice();
		}
		return totalPrice;
	}

	public void addPlayer(Player player) {
		int d = 0;
		for (Player pl : players) {
			if (pl.getIdentification().equals(player.getIdentification())) {
				d = 1;
			}
		}
		if (d == 0) {
			players.add(player);
		}
	}

	public ArrayList<String> getPlayerIdentifications() {
		ArrayList<String> playerIdentifications = new ArrayList<String>();
		for (Player player : players) {
			playerIdentifications.add(player.getIdentification());
		}
		return playerIdentifications;
	}

	public int size() {
		return players.size();
	}

	public int getNumberOfMidfielders(Team team) {
		int numberOfMidfielders = 0;
		for (Player player : team.players) {
			if (player.getPosition() == 0) {
				numberOfMidfielders++;
			}
		}
		return numberOfMidfielders;
	}

	public int getNumberOfAttackers(Team team) {
		int numberOfAttackers = 0;
		for (Player player : team.players) {
			if (player.getPosition() == 1) {
				numberOfAttackers++;
			}
		}
		return numberOfAttackers;
	}

}
