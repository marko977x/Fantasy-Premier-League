package data;

import java.text.ParseException;

public class Test {

	public static void main(String[] args) throws InterruptedException, ParseException {

		DataStorage dataStorage;
		DataFinder dataFinder = new DataFinder();
		dataStorage = dataFinder.findData();
		String json = dataStorage.toJsonString();
		System.out.println(json);

	}

}
