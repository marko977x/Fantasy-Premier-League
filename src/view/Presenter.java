package view;

import java.text.ParseException;
import java.util.ArrayList;

import data.DataFinder;
import data.DataStorage;
import logic.Player;
import logic.PlayerRequirements;
import logic.Result;
import logic.ResultFinder;
import logic.Team;
import logic.TeamRequirements;

public class Presenter {

	private DataFinder dataFinder;
	private ResultFinder resultFinder;
	private MainWindow mainWindow;

	public Presenter() {
		this.dataFinder = new DataFinder();
		this.resultFinder = new ResultFinder();
		this.mainWindow = new MainWindow();
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void findClicked(float price, int attPlayers, int midPlayers) {
		TeamRequirements teamRequirements = new TeamRequirements();
		PlayerRequirements playerRequirements = new PlayerRequirements();
		teamRequirements.setMaxPrice(price);
		teamRequirements.setNumMiddlePlayer(midPlayers);
		teamRequirements.setNumOffensePlayer(attPlayers);
		teamRequirements.setPlayerRequirements(playerRequirements);
		DataStorage dataStorage;
		dataStorage = mainWindow.loadDataFromDisk();
		if (dataStorage != null) {
			ArrayList<Player> list = dataStorage.getPlayersList();
			resultFinder.setAllPlayers(list);
			resultFinder.setTeamRequirements(teamRequirements);
			ArrayList<Result> resultList = resultFinder.findAll(5);
			mainWindow.setResultsList(resultList);
		} else {

		}

	}

	public void teamSelected(Team team, int attPlayers, int midPlayers) {
		mainWindow.setTeam(team, attPlayers, midPlayers);
	}

	public void updateClicked() {
		DataStorage dataStorage = new DataStorage();
		try {
			dataStorage = this.dataFinder.findData();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dataStorage.writeJsonToDisk();
	}

}
