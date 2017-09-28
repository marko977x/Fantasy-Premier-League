package logic;

import java.text.DecimalFormat;
import java.util.HashSet;

public class TeamRequirements {

	private int numOffensePlayer;
	private int numMiddlePlayer;
	private float maxPrice;
	private PlayerRequirements playerRequirements;
	public static final int NUMERIC_FACTOR = 1000;

	public int getNumOffensePlayer() {
		return this.numOffensePlayer;
	}

	public int getNumMiddlePlayer() {
		return this.numMiddlePlayer;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setNumOffensePlayer(int setNumOffensePlayer) {
		this.numOffensePlayer = setNumOffensePlayer;
	}

	public void setNumMiddlePlayer(int setNumMiddlePlayer) {
		this.numMiddlePlayer = setNumMiddlePlayer;
	}

	public PlayerRequirements getPlayerRequirements() {
		return playerRequirements;
	}

	public void setPlayerRequirements(PlayerRequirements playerRequirements) {
		this.playerRequirements = playerRequirements;
	}

	public float check(Team team) {
		if (team.getPrice() < this.maxPrice) {
			HashSet<Player> players = team.getPlayers();
			int numFoundOffense = 0;
			int numFoundMiddle = 0;
			float form = 1;
			for (Player player : players) {
				if (player.getPosition() == Player.POSITION_OFFENSE && numFoundOffense <= this.numOffensePlayer) {
					numFoundOffense++;
				}

				if (player.getPosition() == Player.POSITION_MIDDLE && numFoundMiddle <= this.numMiddlePlayer) {
					numFoundMiddle++;
				}
				form = form * playerRequirements.check(player);
			}
			float point = 1f / (this.numOffensePlayer + this.numMiddlePlayer);
			DecimalFormat decimalFormat = new DecimalFormat("#.#####");
			point = (point * numFoundOffense + point * numFoundMiddle) * form;
			point = Float.valueOf(decimalFormat.format(point));
			return point * NUMERIC_FACTOR;

		} else {
			return 0;
		}
	}

}
