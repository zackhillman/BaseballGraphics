
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Game  {

	private final   int INNINGS = 9;
	private final int OUTS = 3;
	
	private  Team homeTeam;
	private  Team awayTeam;
	
	public  boolean half; //Top = true Bottom = false
	public  int currentInning; 
	
	public  Player[] bases;

	public   int outs;
	
	public   ActionType currentEvent;
	
	public   boolean gameOver;

	public   DefaultListModel<String> gameLog;

	public Game(){
		
		bases = new Player[4];
		currentInning = 1;
		homeTeam = new Team();
		awayTeam = new Team(); 
		gameLog = new DefaultListModel<String>();
		half = true;
		gameOver = false;
		addDivider();
	}
	
	public   void add(Player p){
		if(!homeTeam.isFull())
			homeTeam.addPlayer(p);
		else
			awayTeam.addPlayer(p);
	}
	
	public   void edit(Player p){
		getTeamAtBat().editPlayer(p);
		System.out.println(p);
	}
	
	public   void hit(){
		if(!gameOver){
			currentEvent = getRandom();
			
			gameLog.addElement(currentEvent.toString());
			
			doAction(currentEvent);
		}
		
		
		//═
	}

	private   void addDivider(){
		String halfS = "Top";
		if(!half) halfS = "Bottom";
		String currentI= currentInning+getEnd(currentInning);
		gameLog.addElement("║"+halfS+" of the "+currentI+"║\n");
		
	//	textarea.setText("Here is some <b>bold text</b>");

	}
	
	public   int getHomeIndex(){
		return homeTeam.getBatterNumber()-1;
	}
	
	public   int getAwayIndex(){
		return awayTeam.getBatterNumber()-1;
	}
	
	
	private   String getEnd(int ci) {
		
		switch(ci){
			case 1: return "st";	
			case 2: return "nd";
			case 3: return "rd";
			default: return "th";
		}
		
	}

	private   ActionType getRandom(){
		Random r = new Random();
		int num = r.nextInt(99)+1;
		
		ActionType randomType = null;
	        int currentWeightSum = 0;
	        
	        for(ActionType currentValue: ActionType.values()) {
	           if (num > currentWeightSum && num <= (currentWeightSum + currentValue.getWeight())) {
	        	    randomType = currentValue;
	           }
	           currentWeightSum += currentValue.getWeight();
	        }
	        return randomType;
	}
	
	private   void doAction(ActionType a){
		System.out.println(a);
		getTeamAtBat().doHit(a);
		if(a == ActionType.OUT){
			doOut();
		}
		
		switch(a){
		
		case WALK: incrementBases(1);
			break;
		case SINGLE:incrementBases(1);
			break;
		case DOUBLE: incrementBases(2);
			break;
		case TRIPLE: incrementBases(3);
			break;
		case HOMERUN: incrementBases(4);
			break;
		}
	}

	private   void doOut(){
		outs++;
		if(outs==3){
			if(currentInning==INNINGS&&!half){
				gameOver = true;
			}else{
	
				half = !half;
				if(half)
					currentInning++;
				outs = 0;
				bases = new Player[4];
				addDivider();
			}
		}
	}
	
		private   void reset() {
		// TODO Auto-generated method stub
		
	}

		private   Team getTeamAtBat(){
			if(half){
			//	System.out.println("away");
				return awayTeam;
			}
		//		System.out.println("home");
				return homeTeam;
					}
		
		private   void incrementBases(int amt){
		
			Player[] newBases = new Player[4];
			for(int i = 0;i<4;i++){
				if(i+amt<3)
					 newBases[i+amt] =  bases[i];
				else if (bases[i]!=null||i+amt==7){
					getTeamAtBat().addScore(1);
				}
			}
			bases = newBases;
			if(amt<4)
				bases[amt-1]  = getTeamAtBat().getCurrent();
		}
	
		public   int getHomeScore(){
			return homeTeam.getScore();
		}
		
		public   int getAwayScore(){
			return awayTeam.getScore();
		}

		public   DefaultTableModel homeTeamTable(){
			return homeTeam.toTable();
		}
		
		public   DefaultTableModel awayTeamTable(){
			return awayTeam.toTable();
		}
		
		public   boolean awayTeamFull(){
			return awayTeam.isFull();
		}

		/**
		 * @return the innings
		 */
		public   int getInnings() {
			return INNINGS;
		}

		/**
		 * @return the oUTS
		 */
		public int getOUTS() {
			return OUTS;
		}

		/**
		 * @return the homeTeam
		 */
		public   Team getHomeTeam() {
			return homeTeam;
		}

		/**
		 * @return the awayTeam
		 */
		public   Team getAwayTeam() {
			return awayTeam;
		}

		/**
		 * @return the half
		 */
		public   boolean isHalf() {
			return half;
		}

		/**
		 * @return the currentInning
		 */
		public   int getCurrentInning() {
			return currentInning;
		}

		/**
		 * @return the bases
		 */
		public   Player[] getBases() {
			return bases;
		}

		/**
		 * @return the outs
		 */
		public   int getOuts() {
			return outs;
		}

		/**
		 * @return the currentEvent
		 */
		public   ActionType getCurrentEvent() {
			return currentEvent;
		}

		/**
		 * @return the gameOver
		 */
		public   boolean isGameOver() {
			return gameOver;
		}

		/**
		 * @return the gameLog
		 */
		public   DefaultListModel<String> getGameLog() {
			return gameLog;
		}

	
}
