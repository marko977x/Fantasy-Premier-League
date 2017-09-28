package logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeamRequirementsTest {

	@Test
	public void testCheckEmptyTeam() {
		TeamRequirements teamRequirements = new TeamRequirements();
		Team team = new Team();
		float result = teamRequirements.check(team);
		assertEquals(0, result, 0.001);
	}

	@Test
	public void testCheckTeamWithPlayers() {
		TeamRequirements teamRequirements = new TeamRequirements();
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Team team = new Team();
		team.addPlayer(player1);
		team.addPlayer(player2);
		team.addPlayer(player3);
		player1.setPrice(8);
		player2.setPrice(9);
		player3.setPrice(7);
		teamRequirements.setMaxPrice(23);
		float result = teamRequirements.check(team);
		assertEquals(0, result, 0.001);
	}

	@Test
	public void testCheckPositionOfPlayers() {
		TeamRequirements teamRequirements = new TeamRequirements();
		PlayerRequirements playerRequirements = new PlayerRequirements();
		teamRequirements.setPlayerRequirements(playerRequirements);
		Team team = new Team();
		teamRequirements.setNumMiddlePlayer(4);
		teamRequirements.setNumOffensePlayer(2);
		teamRequirements.setMaxPrice(1);
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		team.addPlayer(player1);
		team.addPlayer(player2);
		team.addPlayer(player3);
		team.addPlayer(player4);
		player1.setPosition(Player.POSITION_MIDDLE);
		player2.setPosition(Player.POSITION_MIDDLE);
		player3.setPosition(Player.POSITION_MIDDLE);
		player4.setPosition(Player.POSITION_OFFENSE);
		player1.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player2.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player3.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player4.setIctIndex(Player.MAX_FORM_VALUE / 2);
		float result = teamRequirements.check(team);
		assertEquals(4f / 6f * 0.0625, result, 0.001);
	}

}
