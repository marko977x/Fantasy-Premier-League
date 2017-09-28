package data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import logic.Player;

public class DataStorage {

	private JsonObject data;
	private JsonArray players;
	private String source;
	public static final int VERSION = 1;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public DataStorage() {
		data = new JsonObject();
		players = new JsonArray();
		data.addProperty("version", VERSION);
		data.addProperty("source", source);
		data.add("players", players);
	}

	public DataStorage(JsonObject data) {
		this.data = data;
	}

	public void addPlayer(String name, String club, float form, int position, double price) {
		JsonObject player = new JsonObject();
		player.addProperty("name", name);
		player.addProperty("club", club);
		player.addProperty("form", form);
		player.addProperty("position", position);
		player.addProperty("price", price);
		players.add(player);
	}

	public String toJsonString() {
		return data.toString();
	}

	public static DataStorage fromJsonString(String string) {
		JsonParser parser = new JsonParser();
		JsonObject data = (JsonObject) parser.parse(string);
		DataStorage dataStorage = new DataStorage(data);
		return dataStorage;
	}

	public ArrayList<Player> getPlayersList() {
		ArrayList<Player> list = new ArrayList<Player>();
		for (int i = 0; i < players.size(); i++) {
			Player player = createPlayerFromJson(players.get(i).getAsJsonObject());
			list.add(player);
		}
		return list;
	}

	private Player createPlayerFromJson(JsonObject jsonObject) {
		Player player = new Player();
		player.setClub(jsonObject.get("club").getAsString());
		player.setForm(jsonObject.get("form").getAsFloat());
		player.setName(jsonObject.get("name").getAsString());
		player.setPosition(jsonObject.get("position").getAsInt());
		player.setPrice(jsonObject.get("price").getAsInt());
		return player;
	}

	public void writeJsonToDisk() {
		try (FileWriter file = new FileWriter(
				"E:\\Marko\\Java\\Fantasy Premier League\\FantasyPremierLeague\\resource\\data.txt")) {
			file.write(this.data.toString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPlayers(JsonArray players) {
		this.players = players;
	}

}
