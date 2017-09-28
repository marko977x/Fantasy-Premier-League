package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.DataStorage;
import logic.Player;
import logic.Result;
import logic.Team;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;

public class MainWindow {

	private JFrame frame;
	private JTextField priceField;
	private JComboBox<String> teamCombo;
	private JComboBox<Integer> attCombo;
	private JComboBox<Integer> midCombo;
	private JButton findButton;
	private JTextPane txtpnPrice;
	private JTextPane txtpnAtt;
	private JTextPane txtpnMid;
	private JTree formationTree;
	private ArrayList<Result> resultsList;
	private Presenter presenter;
	private JButton btnUpdate;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		teamCombo = new JComboBox<String>();
		teamCombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!resultsList.isEmpty()) {
					try {
						frame.remove(formationTree);
					} catch (Exception e) {
					}
					int index = teamCombo.getSelectedIndex();
					Result result = resultsList.get(index);
					Team team = result.getTeam();
					int attPlayers = ((Integer) attCombo.getSelectedItem()).intValue();
					int midPlayers = ((Integer) midCombo.getSelectedItem()).intValue();
					presenter.teamSelected(team, attPlayers, midPlayers);
				}
			}
		});
		teamCombo.setToolTipText("");
		teamCombo.setBounds(179, 12, 245, 20);
		frame.getContentPane().add(teamCombo);

		attCombo = new JComboBox<Integer>();
		attCombo.setName("");
		attCombo.setToolTipText("");
		attCombo.addItem(1);
		attCombo.addItem(2);
		attCombo.addItem(3);
		attCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		attCombo.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		attCombo.setBounds(55, 71, 114, 20);
		frame.getContentPane().add(attCombo);

		midCombo = new JComboBox<Integer>();
		midCombo.setBounds(55, 103, 114, 20);
		midCombo.addItem(3);
		midCombo.addItem(4);
		midCombo.addItem(5);
		frame.getContentPane().add(midCombo);

		findButton = new JButton("Find");
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					teamCombo.removeAllItems();
				} catch (Exception e) {
					e.printStackTrace();
				}
				float price = Float.parseFloat(priceField.getText());
				int attPlayers = ((Integer) attCombo.getSelectedItem()).intValue();
				int midPlayers = ((Integer) midCombo.getSelectedItem()).intValue();
				presenter.findClicked(price, attPlayers, midPlayers);
			}
		});
		findButton.setBounds(65, 227, 89, 23);
		frame.getContentPane().add(findButton);

		priceField = new JTextField();
		priceField.setBounds(55, 45, 114, 19);
		frame.getContentPane().add(priceField);
		priceField.setColumns(10);

		txtpnPrice = new JTextPane();
		txtpnPrice.setBackground(UIManager.getColor("Button.background"));
		txtpnPrice.setText("Price");
		txtpnPrice.setBounds(10, 43, 40, 21);
		frame.getContentPane().add(txtpnPrice);

		txtpnAtt = new JTextPane();
		txtpnAtt.setText("Att");
		txtpnAtt.setBackground(UIManager.getColor("Button.background"));
		txtpnAtt.setBounds(12, 71, 41, 21);
		frame.getContentPane().add(txtpnAtt);

		txtpnMid = new JTextPane();
		txtpnMid.setBackground(UIManager.getColor("Button.background"));
		txtpnMid.setText("Mid");
		txtpnMid.setBounds(10, 102, 47, 21);
		frame.getContentPane().add(txtpnMid);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				presenter.updateClicked();
			}
		});
		btnUpdate.setBounds(65, 11, 89, 23);
		frame.getContentPane().add(btnUpdate);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void setTeam(Team team, int attPlayer, int midPlayers) {
		DefaultMutableTreeNode teamRoot = new DefaultMutableTreeNode("Team");
		DefaultMutableTreeNode attNode = new DefaultMutableTreeNode("Attack");
		DefaultMutableTreeNode midNode = new DefaultMutableTreeNode("Midfield");
		teamRoot.add(midNode);
		teamRoot.add(attNode);
		HashSet<Player> players = team.getPlayers();
		for (Player player : players) {
			if (player.getPosition() == Player.POSITION_MIDDLE) {
				midNode.add(new DefaultMutableTreeNode(player.getName()));
			} else {
				attNode.add(new DefaultMutableTreeNode(player.getName()));
			}
		}
		formationTree = new JTree(teamRoot);
		formationTree.setBounds(179, 43, 245, 207);
		frame.getContentPane().add(formationTree);
	}

	public DataStorage loadDataFromDisk() {
		JsonParser parser = new JsonParser();
		try {
			JsonElement element = parser.parse(new FileReader("resource/data.txt"));
			JsonObject data = element.getAsJsonObject();
			DataStorage dataStorage = new DataStorage(data);
			JsonArray players = data.get("players").getAsJsonArray();
			dataStorage.setPlayers(players);
			return dataStorage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setResultsList(ArrayList<Result> resultList) {
		this.resultsList = getAscendingOrderedList(resultList);
		for (int i = 0; i < this.resultsList.size(); i++) {
			String item = resultToString(this.resultsList.get(i), i);
			teamCombo.addItem(item);
		}
	}

	public String resultToString(Result result, int i) {
		String score = String.valueOf(result.getScore());
		String position = String.valueOf(i + 1);
		String item = position + "." + " " + score;
		return item;
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	public ArrayList<Result> getAscendingOrderedList(ArrayList<Result> resultList) {
		int minIndex = 0;
		Result currentResult = new Result();
		for (int i = 0; i < resultList.size() - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < resultList.size(); j++) {
				if (resultList.get(minIndex).getScore() < resultList.get(j).getScore()) {
					minIndex = j;
				}
			}
			currentResult = resultList.get(minIndex);
			resultList.set(minIndex, resultList.get(i));
			resultList.set(i, currentResult);
		}
		return resultList;
	}
}
