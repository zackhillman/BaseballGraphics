import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Game  {

	private final static int INNINGS = 9;
	private final static int BOX_LENGTH = 37;
	private final int OUTS = 3;
	
	private static Team homeTeam;
	private static Team awayTeam;
	
	public static boolean half; //Top = true Bottom = false
	public static int currentInning; 
	
	public static Player[] bases;
	
	private JTextField nameF;
	
	public static int outs;
	
	public static ActionType currentEvent;
	
	public static boolean gameOver;

	public static DefaultListModel<String> gameLog;

	public static void init(){
		
		bases = new Player[4];
		currentInning = 1;
		homeTeam = new Team();
		awayTeam = new Team(); 
		gameLog = new DefaultListModel<String>();
		half = true;
		gameOver = false;
		addDivider();
		//gameLog.
	}
	
	public static void add(Player p){
		if(!homeTeam.isFull())
			homeTeam.addPlayer(p);
		else
			awayTeam.addPlayer(p);
	}
	
	public static void edit(Player p){
		getTeamAtBat().editPlayer(p);
		System.out.println(p);
	}
	
	public static void hit(){
		if(!gameOver){
			currentEvent = getRandom();
			
			gameLog.addElement(currentEvent.toString());
			
			doAction(currentEvent);
		}
		
		
		//═
	}

	private static void addDivider(){
		String halfS = "Top";
		if(!half) halfS = "Bottom";
		String currentI= currentInning+getEnd(currentInning);
		gameLog.addElement("║"+halfS+" of the "+currentI+"║\n");
		
	//	textarea.setText("Here is some <b>bold text</b>");

	}
	
	
	private static String getEnd(int ci) {
		
		switch(ci){
			case 1: return "st";	
			case 2: return "nd";
			case 3: return "rd";
			default: return "th";
		}
		
	}

	private static ActionType getRandom(){
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
	
	private static void doAction(ActionType a){
		System.out.println(a);
		if(a == ActionType.OUT){
			outs++;
			if(outs==3){
				
				if(currentInning==INNINGS&&homeTeam.getScore()>awayTeam.getScore()){
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
	//	ActionType randomAction = a;
		getTeamAtBat().doHit(a);
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

		private static void reset() {
		// TODO Auto-generated method stub
		
	}

		private static Team getTeamAtBat(){
			if(half){
			//	System.out.println("away");
				return awayTeam;
			}
		//		System.out.println("home");
				return homeTeam;
					}
		
		private static void incrementBases(int amt){
		
			Player[] newBases = new Player[4];
			for(int i = 0;i<4;i++){
				if(i+amt<3)
					 newBases[i+amt] =  bases[i];
				else if (bases[i]!=null||i+amt==7)
					getTeamAtBat().addScore(1);
			}
		//	for(int i = 0;i<4;i++){
			bases = newBases;
			if(amt<4)
				bases[amt-1]  = getTeamAtBat().getCurrent();
		}
	
		public static int getHomeScore(){
			return homeTeam.getScore();
		}
		
		public static int getAwayScore(){
			return awayTeam.getScore();
		}
	
//		public static String[] getAllPlayers(){
//			String [] playerNames = new String[18];
//			int index = 0;
//			while(index < 9){
//				Player[] players = homeTeam.getOrder().toArray();
//				playerNames[index] = players[index].getName();
//				
//			}
//			return playerNames;
//		}
	
		public static DefaultTableModel homeTeamTable(){
			return homeTeam.toTable();
		}
		
		public static DefaultTableModel awayTeamTable(){
			return awayTeam.toTable();
		}
		
		public static boolean awayTeamFull(){
			return awayTeam.isFull();
		}

	
}
