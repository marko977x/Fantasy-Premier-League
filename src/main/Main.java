package main;

import java.awt.EventQueue;

import view.MainWindow;
import view.Presenter;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.setVisible(true);
					Presenter presenter = new Presenter();
					window.setPresenter(presenter);
					presenter.setMainWindow(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
