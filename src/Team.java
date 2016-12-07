import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

public class Team {

	private int playerCount; //Count for the number of players on the team (Max: 9)

	private CircleLinkedList<Player> battingOrder; //The circle linked list for batting order
	private LinkedList<Player> substituteList; //Holds the substituted players
	private final int TEAM_SIZE = 9; //Final variable for max team size
	//Holds the information for the JTable's column names
	private final Object[] COLUMN_NAMES = new Object[] {"Name","Outs","Singles","Doubles","Triples","Homeruns","Walks"}; 
	private int currentBatter; //Holds reference to the current batter
	private int score; //Tracks the current score for the team

	/**
	 * This is the constructor method, it instantiates the instance variables and
	 * sets the currentBatter to 1.
	 */
	public Team() {
		battingOrder = new CircleLinkedList<Player>();
		substituteList = new LinkedList<Player>();
		playerCount = 0;
		score = 0;
		currentBatter = 1;
	}

	/**
	 * This method adds a player to the team.
	 * @param p- the New player to be added
	 */
	public void addPlayer(Player p) {
		if (playerCount >= TEAM_SIZE)
			throw new IllegalArgumentException("Team is full");
		battingOrder.add(p);
		playerCount++;
	}

	/**
	 * This method replaces the currentBatter with a new Player
	 * @param p- the New batter to replace
	 */
	public void editPlayer(Player p) {
		substituteList.add(battingOrder.getCurrent());
		System.out.println(battingOrder.getCurrent());
		battingOrder.set(p);
	}

	/**
	 * This method simulates a hot for the team.
	 * @param a- the hit type
	 */
	public void doHit(ActionType a) {
		battingOrder.getCurrent().addHit(a);
		battingOrder.doNext();
		if (currentBatter == 9)
			currentBatter = 1;
		else
			currentBatter++;
	}

	/**
	 * This method adds to the team's score
	 * @param amt- the amount to increment the score by
	 */
	public void addScore(int amt) {
		score += amt;
	}
	
	/**
	 * This method gets the teams current score
	 * @return- the team's score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * This method gets the team's current batter by getting
	 * the battingOrder's current batter
	 * @return- the current Batter 
	 */
	public Player getCurrentBatter() {
		return battingOrder.getCurrent();
	}

	/**
	 * This method determines if the team is full
	 * @return- true if the team is full
	 * 			false if the team is not full
	 */
	public boolean isFull() {
		if (playerCount < TEAM_SIZE)
			return false;
		return true;
	}

	/**
	 * This method gets the current Batter number (1-9)
	 * @return- the current batter number
	 */
	public int getBatterNumber() {
		return currentBatter;
	}
	/**
	 * This method converts the team into a table by iterating through the circle linked list
	 * @return- The DefaultTableModel for this team's batting order and sub list
	 */
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
