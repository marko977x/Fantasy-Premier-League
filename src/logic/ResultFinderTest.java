package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ResultFinderTest {

	@Test
	public void test() {
		ResultFinder resultFinder = new ResultFinder();
		ArrayList<Player> list = new ArrayList<Player>();
		TeamRequirements teamRequirements = new TeamRequirements();
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Player player5 = new Player();
		Player player6 = new Player();
		Player player7 = new Player();
		player1.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player2.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player3.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player4.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player5.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player6.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player7.setIctIndex(Player.MAX_FORM_VALUE / 2);
		player1.setPosition(Player.POSITION_MIDDLE);
		player2.setPosition(Player.POSITION_MIDDLE);
		player3.setPosition(Player.POSITION_MIDDLE);
		player4.setPosition(Player.POSITION_MIDDLE);
		player5.setPosition(Player.POSITION_OFFENSE);
		player6.setPosition(Player.POSITION_OFFENSE);
		player7.setPosition(Player.POSITION_OFFENSE);
		list.add(player1);
		list.add(player2);
		list.add(player3);
		list.add(player4);
		list.add(player5);
		list.add(player6);
		list.add(player7);
		teamRequirements.setNumMiddlePlayer(4);
		teamRequirements.setNumOffensePlayer(3);
		Result result = new Result();
		resultFinder.setAllPlayers(list);
		resultFinder.setTeamRequirements(teamRequirements);
		result = resultFinder.findOne();
		assertEquals(7, result.getTeam().size());
	}

}
