package data;

import java.text.ParseException;

import browser.BrowserConfiguration;
import browser.BrowserHandler;
import browser.BrowserResult;
import logic.Player;

public class DataFinder {

	public static String EXE_PATH = "E://Marko//Java//chromedriver.exe";
	public static String URL = "https://fantasy.premierleague.com/a/statistics/ict_index";
	public static String ROW_SELECTOR_BASE = "#ismr-main > div > div.table.ism-scroll-table > table > tbody > tr";
	public static String BUTTON_SELECTOR = "#ismr-main > div > div.paginationContainer.ism-pagination > a:nth-child(4)";
	public static String CLUB_SELECTOR = " > td.ism-table--el__primary > div > div.ism-media__body.ism-table--el__primary-text > span.ism-table--el__strong";
	public static String NAME_SELECTOR = " > td.ism-table--el__primary > div > div.ism-media__body.ism-table--el__primary-text > a";
	public static String POSITION_SELECTOR = " > td.ism-table--el__primary > div > div.ism-media__body.ism-table--el__primary-text > span.ism-table--el__pos";
	public static String FORM_SELECTOR = " > td:nth-child(5)";
	public static String PRICE_SELECTOR = " > td:nth-child(3)";

	public DataStorage findData() throws InterruptedException, ParseException {
		BrowserHandler browserHandler = new BrowserHandler();
		BrowserConfiguration browserConfiguration = new BrowserConfiguration();
		browserConfiguration.setExePath(EXE_PATH);
		BrowserResult browserResult;
		browserHandler.setBrowserConfiguration(browserConfiguration);
		DataStorage dataStorage = new DataStorage();
		browserResult = browserHandler.start();

		if (browserResult.isSuccessfull()) {
			browserResult = browserHandler.openPage(URL);
			if (browserResult.isSuccessfull()) {
				while (browserResult.isSuccessfull()) {
					readPage(dataStorage, browserHandler);
					browserResult = browserHandler.click(BUTTON_SELECTOR);
				}
			}
		}

		browserHandler.stop();
		return dataStorage;
	}

	public void readPlayer(DataStorage dataStorage, BrowserHandler browserHandler, String rowSelector)
			throws ParseException {
		BrowserResult browserResult;
		browserResult = browserHandler.getText(rowSelector + POSITION_SELECTOR);
		if (!browserResult.getData().equals(Player.POSITION_DEFENDER)
				&& !browserResult.getData().equals(Player.POSITION_GOALKEEPER)) {
			browserResult = browserHandler.getText(rowSelector + CLUB_SELECTOR);
			String clubName = browserResult.getData();
			browserResult = browserHandler.getText(rowSelector + NAME_SELECTOR);
			String playerName = browserResult.getData();
			browserResult = browserHandler.getText(rowSelector + FORM_SELECTOR);
			String formValue = browserResult.getData();
			float form = Float.parseFloat(formValue);
			browserResult = browserHandler.getText(rowSelector + POSITION_SELECTOR);
			int playerPosition;
			if (browserResult.getData().equals(Player.POSITION_MIDFIELDER)) {
				playerPosition = 0;
			} else {
				playerPosition = 1;
			}
			browserResult = browserHandler.getText(rowSelector + PRICE_SELECTOR);
			String priceString = browserResult.getData();
			priceString = priceString.substring(1, priceString.length());
			double price = Double.parseDouble(priceString);

			dataStorage.addPlayer(playerName, clubName, form, playerPosition, price);
		}
	}

	public String createRowSelector(int index) {
		return ROW_SELECTOR_BASE + ":nth-child" + "(" + index + ")";
	}

	public void readPage(DataStorage dataStorage, BrowserHandler browserHandler) throws ParseException {
		int numPlayers = browserHandler.getCount(ROW_SELECTOR_BASE);
		int i = 1;
		while (i <= numPlayers) {
			String selector = createRowSelector(i);
			readPlayer(dataStorage, browserHandler, selector);
			i++;
		}
	}

}
