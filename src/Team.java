
public class Team {

	private int playerCount;
	
	private CircleLinkedList<Player> battingOrder;
	private LinkedList<Player> substituteList;
	private final int TEAM_SIZE = 9;
	
	private int score;
	
	public Team (){
		battingOrder = new CircleLinkedList<Player>();
		substituteList = new LinkedList<Player>();
		playerCount = 0;
		score = 0;
	}
	
	public void addPlayer(Player p){
		if(playerCount>=TEAM_SIZE)
			throw new IllegalArgumentException("Team is full");
		battingOrder.add(p);
		playerCount++;
	}
	
	public int getPlayerCount(){
		return playerCount;
	}
	
	public void doHit(ActionType a){
			battingOrder.getCurrent().doEvent(a);
			battingOrder.doNext();
	}
	
	public void addScore(int amt){
		score+=amt;
	}
	
	public int getScore(){
		return score;
	}
	
	public Player getCurrent(){
		return battingOrder.getCurrent();
	}
	
	public String toString(){
		return battingOrder.toString();
	}
	
	public boolean isFull(){
		if(playerCount<TEAM_SIZE)
			return false;
		return true;
	}
	
}