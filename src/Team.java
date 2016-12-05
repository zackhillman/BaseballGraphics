import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Team {

	private int playerCount;

	private CircleLinkedList<Player> battingOrder;
	private LinkedList<Player> substituteList;
	private final int TEAM_SIZE = 9;
	private final Object[] COLUMN_NAMES = new Object[]
			{"Name","Outs","Singles","Doubles","Triples","Homeruns","Walks"};

	private int currentBatter;
	private int score;

	public Team() {
		battingOrder = new CircleLinkedList<Player>();
		substituteList = new LinkedList<Player>();
		playerCount = 0;
		score = 0;
		currentBatter = 1;
	}

	public void addPlayer(Player p) {
		if (playerCount >= TEAM_SIZE)
			throw new IllegalArgumentException("Team is full");
		battingOrder.add(p);
		playerCount++;
	}

	public void editPlayer(Player p) {
		substituteList.add(battingOrder.getCurrent());
		System.out.println(battingOrder.getCurrent());
		battingOrder.set(p);

	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void doHit(ActionType a) {
		battingOrder.getCurrent().addHit(a);
		battingOrder.doNext();

		if (currentBatter == 9)
			currentBatter = 1;
		else
			currentBatter++;
	}

	public void addScore(int amt) {
		score += amt;
	}

	public int getScore() {
		return score;
	}

	public Player getCurrentBatter() {
		return battingOrder.getCurrent();
	}

	public boolean isFull() {
		if (playerCount < TEAM_SIZE)
			return false;
		return true;
	}

	public CircleLinkedList<Player> getOrder() {
		return battingOrder;
	}

	public int getBatterNumber() {
		return currentBatter;
	}

	public DefaultTableModel toTable() {

		Iterator<Player> boIter = battingOrder.iterator();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(COLUMN_NAMES);
		while (boIter.hasNext())
			model.addRow(boIter.next().toArray());
		if (isFull())
			model.addRow(new Object[] { "Substitute List" });
		Iterator<Player> subIter = substituteList.iterator();
		while (subIter.hasNext())
			model.addRow(subIter.next().toArray());

		return model;
	}

}
