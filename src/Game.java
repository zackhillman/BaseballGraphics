
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Game {

	private final int INNINGS = 9;
	private final int OUTS = 3;

	private Team homeTeam;
	private Team awayTeam;

	private boolean half; // Top = true Bottom = false
	private int currentInning;

	private Player[] bases;

	private int currentOuts;

	private boolean gameOver;

	private DefaultListModel<String> gameLog;

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

	public void add(Player p) {
		if (!homeTeam.isFull())
			homeTeam.addPlayer(p);
		else
			awayTeam.addPlayer(p);
	}

	public void edit(Player p) {
		getTeamAtBat().editPlayer(p);
		System.out.println(p);
	}

	public void hit() {
		if (!gameOver) {
			ActionType currentEvent = getRandom();

			gameLog.addElement(currentEvent.toString());

			doAction(currentEvent);
		}

	}

	private void addDivider() {
		String halfS = "Top";
		if (!half)
			halfS = "Bottom";
		String currentI = currentInning + getEnd(currentInning);
		gameLog.addElement("║" + halfS + " of the " + currentI + "║\n");

	}

	public int getHomeIndex() {
		return homeTeam.getBatterNumber() - 1;
	}

	public int getAwayIndex() {
		return awayTeam.getBatterNumber() - 1;
	}

	private String getEnd(int ci) {

		switch (ci) {
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

	private Team getTeamAtBat() {
		if (half) {
			return awayTeam;
		}
		return homeTeam;
	}

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

	public int getHomeScore() {
		return homeTeam.getScore();
	}

	public int getAwayScore() {
		return awayTeam.getScore();
	}

	public DefaultTableModel homeTeamTable() {
		return homeTeam.toTable();
	}

	public DefaultTableModel awayTeamTable() {
		return awayTeam.toTable();
	}

	public boolean awayTeamFull() {
		return awayTeam.isFull();
	}

	/**
	 * @return the half
	 */
	public boolean getHalf() {
		return half;
	}

	/**
	 * @return the currentInning
	 */
	public int getCurrentInning() {
		return currentInning;
	}

	/**
	 * @return the bases
	 */
	public Player[] getBases() {
		return bases;
	}

	/**
	 * @return the outs
	 */
	public int getOuts() {
		return currentOuts;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @return the gameLog
	 */
	public DefaultListModel<String> getGameLog() {
		return gameLog;
	}

}
