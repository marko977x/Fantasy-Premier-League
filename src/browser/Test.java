package browser;

public class Test {

	public static void main(String[] args) {

		BrowserHandler browserHandler = new BrowserHandler();
		BrowserConfiguration browserConfiguration = new BrowserConfiguration();
		BrowserResult browserResult = new BrowserResult();

		browserConfiguration.setExePath("E:\\Marko\\Java\\chromedriver.exe");
		browserHandler.setBrowserConfiguration(browserConfiguration);

		browserHandler.start();
		browserResult = browserHandler.openPage("https://fantasy.premierleague.com/a/statistics/ict_index");

		int i = 1;
		while (browserResult.isSuccessfull() == true) {
			browserResult = browserHandler
					.getText("#ismr-main > div > div.table.ism-scroll-table > table > tbody > tr:nth-child(" + i + ")"
							+ " > td.ism-table--el__primary > div > div.ism-media__body.ism-table--el__primary-text > a");
			System.out.println(browserResult.getData());
			try {
				browserResult = browserHandler
						.click("#ismr-main > div > div.paginationContainer.ism-pagination > a:nth-child(4)");
			} catch (Exception e) {
				System.out.println("That's it!");
			}
			i++;
		}

	}

}
