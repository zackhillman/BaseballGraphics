
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Game {

	
	private final int INNINGS = 9; //Holds value for max number of innings
	private int currentInning;  //Value of the current inning
	
	private final int OUTS = 3; //Holds value for max number of outs
	private int currentOuts; //Value of the current number of outs
	
	private Team homeTeam; //Holds reference to the home team
	private Team awayTeam; //Holds reference to the away team
	
	private boolean half; // Top = true Bottom = false
	
	private Player[] bases; //Stores information for the 4 bases
	
	private boolean gameOver; //If the game is over = true, else = false
	
	private DefaultListModel<String> gameLog; //Stores the information for the game log

	/**
	 * This is the constructor method, it instantiates the instance variables.
	 */
	public Game() {
		bases = new Player[4];
		currentInning = 1;
		homeTeam = new Team();
		awayTeam = new Team();
		gameLog = new DefaultListModel<String>();
		half = true;
		gameOver = false;
		addDivider();
	}

	/**
	 * This method adds a player to the home team until it is full.
	 * Once full, the players are added to the away team.
	 * @param p- the Player being added
	 */
	public void add(Player p) {
		if (!homeTeam.isFull())
			homeTeam.addPlayer(p);
		else
			awayTeam.addPlayer(p);
	}
	/**
	 * This method edits the current player at bat. The current player
	 * is replaces with a new batter. And the old batter is added to the sub list
	 * @param p- the New player being subbed in
	 */
	public void edit(Player p) {
		getTeamAtBat().editPlayer(p);
		System.out.println(p);
	}
	
	/**
	 * This method simulates a hit for the game. In adds the hit to the game log.
	 */
	public void hit() {
		if (!gameOver) {
			ActionType currentEvent = getRandom();
			gameLog.addElement(currentEvent.toString());
			doAction(currentEvent);
		}
	}
	/**
	 * This method adds a divider to the game log. It separates the top and bottom
	 * of innings.
	 */
	private void addDivider() {
		String halfS = "Top";
		if (!half)
			halfS = "Bottom";
		String currentI = currentInning + getEnd();
		gameLog.addElement("║" + halfS + " of the " + currentI + "║\n");
	}
	/**
	 * This method gets the home team's current batter's index
	 * @return- the table index of the player at bat
	 */
	public int getHomeIndex() {
		return homeTeam.getBatterNumber() - 1;
	}

	/**
	 * This method gets the away team's current batter's index
	 * @return- the table index of the player at bat
	 */
	public int getAwayIndex() {
		return awayTeam.getBatterNumber() - 1;
	}

	/**
	 * This method determines the correct ending bases on the current Inning
	 * @return- the correct ending as a string
	 */
	private String getEnd() {
		switch (currentInning) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	/**
	 * This method gets a random event based on the Enum's weightings
	 * @return- the random ActionType
	 */
	private ActionType getRandom() {
		Random r = new Random();
		int num = r.nextInt(99) + 1;

		ActionType randomType = null;
		int currentWeightSum = 0;

		for (ActionType currentValue : ActionType.values()) {
			if (num > currentWeightSum && num <= (currentWeightSum + currentValue.getWeight())) {
				randomType = currentValue;
			}
			currentWeightSum += currentValue.getWeight();
		}
		return randomType;
	}

	/**
	 * This method simulates play for the game
	 * @param a- the ActionType that occurred
	 */
	private void doAction(ActionType a) {
		getTeamAtBat().doHit(a);
		switch (a) {
		case OUT:
			doOut();
			break;
		case WALK:
			incrementBases(1);
			break;
		case SINGLE:
			incrementBases(1);
			break;
		case DOUBLE:
			incrementBases(2);
			break;
		case TRIPLE:
			incrementBases(3);
			break;
		case HOMERUN:
			incrementBases(4);
			break;
		}
	}
	/**
	 * This method is called when an out occurs. It tracks the current outs and
	 * changes the inning/half accordingly.
	 */
	private void doOut() {
		currentOuts++;
		if (currentOuts == OUTS) {
			if (currentInning == INNINGS && !half) {
				gameOver = true;
			} else {

				half = !half;
				if (half) {
					currentInning++;
				}
				currentOuts = 0;
				bases = new Player[4];
				addDivider();
			}
		}
	}
	/**
	 * This method gets the current team at bat
	 * @return if top of the inning: returns awayTeam
	 * 					otherwise: 	 return homeTeam
	 */
	private Team getTeamAtBat() {
		if (half) {
			return awayTeam;
		}
		return homeTeam;
	}
	/**
	 * This method increments the bases according to the amount specified
	 * @param amt- the number of bases the batter runs
	 */
	private void incrementBases(int amt) {

		Player[] newBases = new Player[4];
		for (int i = 0; i < 4; i++) {
			if (i + amt < 3)
				newBases[i + amt] = bases[i];
			else if (bases[i] != null || i + amt == 7) {
				getTeamAtBat().addScore(1);
			}
		}
		bases = newBases;
		if (amt < 4)
			bases[amt - 1] = getTeamAtBat().getCurrentBatter();
	}

	/**
	 * This method gets the home team's score
	 * @return- the Home team score
	 */
	public int getHomeScore() {
		return homeTeam.getScore();
	}
	/**
	 * This method gets the away team's score
	 * @return- the Away team score
	 */
	public int getAwayScore() {
		return awayTeam.getScore();
	}
	/**
	 * This method gets the table representation of the home team
	 * @return- the home team table model
	 */
	public DefaultTableModel homeTeamTable() {
		return homeTeam.toTable();
	}
	/**
	 * This method gets the table representation of the away team
	 * @return- the away team table model
	 */
	public DefaultTableModel awayTeamTable() {
		return awayTeam.toTable();
	}
	/**
	 * This method determines if the away team is full
	 * @return- true if the away team is full
	 * 			false if the away team is not full
	 */
	public boolean awayTeamFull() {
		return awayTeam.isFull();
	}

	/**
	 * This method gets the current half
	 * @return the true - half is top
	 * 				false - half is bottom
	 */
	public boolean getHalf() {
		return half;
	}

	/**
	 * This method gets the currentInning of the game
	 * @return- the currentInning
	 */
	public int getCurrentInning() {
		return currentInning;
	}

	/**
	 * This method gets the current bases  and the players on them
	 * @return- the base information as an array
	 */
	public Player[] getBases() {
		return bases;
	}

	/**
	 * This method gets the number of outs
	 * @return the outs
	 */
	public int getOuts() {
		return currentOuts;
	}

	/**
	 * This method checks if the game is over
	 * @return true - the game is over
	 * 			false - the game is not over
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * This method gets the game log
	 * @return the gameLog
	 */
	public DefaultListModel<String> getGameLog() {
		return gameLog;
	}

}
