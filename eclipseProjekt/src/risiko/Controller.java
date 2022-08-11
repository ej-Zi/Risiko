package risiko;

import java.util.ArrayList;



public class Controller {
	private ArrayList<String> names;
	public Game game;
	private static int playerAtTurn; 
	
	public Controller() {
		names = new ArrayList<>();
		names.add("Player1");
		names.add("Player2");
		names.add("Player3");
		names.add("Player4");
		names.add("Player5");
		game = Game.getInstance(5, names);
	}
	
	public Player getPlayerAtTurn() {
		return game.players.get(playerAtTurn);
	}
	public void nextPlayer() {
		playerAtTurn = (playerAtTurn + 1) % game.players.size();
	}

}
