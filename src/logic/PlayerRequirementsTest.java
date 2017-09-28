package logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerRequirementsTest {

	@Test
	public void test() {
		PlayerRequirements playerRequirements = new PlayerRequirements();
		Player player = new Player();
		player.setIctIndex(250);
		float result = playerRequirements.check(player);
		assertEquals(0.5, result, 0.001);
	}

}
