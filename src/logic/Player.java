package logic;

public class Player {

	private String name;
	private int numGoals;
	private int position;
	private float price;
	private float ictIndex;
	private float form;
	private String club;
	public static final int POSITION_OFFENSE = 1;
	public static final int POSITION_MIDDLE = 0;
	public static final String POSITION_DEFENDER = "DEF";
	public static final String POSITION_GOALKEEPER = "GKP";
	public static final String POSITION_MIDFIELDER = "MID";
	public static final float MAX_FORM_VALUE = 10;

	public String getName() {
		return this.name;
	}

	public int getNumGoals() {
		return this.numGoals;
	}

	public int getPosition() {
		return this.position;
	}

	public void setName(String setName) {
		this.name = setName;
	}

	public void setNumGoals(int setNumGoals) {
		this.numGoals = setNumGoals;
	}

	public void setPosition(int setPosition) {
		this.position = setPosition;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getIctIndex() {
		return this.ictIndex;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public void setIctIndex(float ictIndex) {
		this.ictIndex = ictIndex;
	}

	public String getIdentification() {
		return club + " " + name;
	}

	public float getForm() {
		return form;
	}

	public void setForm(float form) {
		this.form = form;
	}

}
