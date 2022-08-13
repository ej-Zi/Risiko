package risiko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class controlerTry {

	private Map<Integer,String> helpText = new HashMap<>();
	private Map<Integer, String> playerCoat = new HashMap<>();
	
	public controlerTry() {
		
		this.createHelpText();
		this.addPlayerCoat();
		
		
	}

	public Map<Integer, String> getPlayerCoat() {
		return playerCoat;
	}

	public void setPlayerCoat(Map<Integer, String> playerCoat) {
		this.playerCoat = playerCoat;
	}

	public Map<Integer, String> getHelpText() {
		return helpText;
	}

	public void setHelpText(Map<Integer, String> helpText) {
		this.helpText = helpText;
	}

	public void createHelpText() {
	
		helpText.put(0, "Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen m�chten. Dr�cken Sie danach den Knopf 'Einheit setzen'.");
		
		helpText.put(1, " Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen m�chten. Bestimmen Sie im Anschluss mit dem Z�hler die Anzahl der Einheiten, "
						+ "die Sie auf das Gebiet setzen m�chten und dr�cken Sie danach 'Armeen setzen'.");
		
		helpText.put(2, "Klicken Sie auf das Gebiet, dass "
						+ "Von dem aus Sie angreifen m�chten und danach auf das Gebiet, das Sie angreifen m�chten  ."
						+ "Bestimmen Sie mit dem Z�hler die Anzahl der Einheiten,"
						+ "mit denen Sie angreifen m�chten und dr�cken Sie danach 'Gegner angreifen'. "
						+ "Wenn Sie nicht angreifen m�chten, dr�cken Sie 'Phase beenden'.");
		
		helpText.put(3, "Klicken Sie auf das Gebiet, dessen Armeen "
						+ "Sie Verschieben m�chten und danach auf das Gebiet, wohin Sie die Armeen senden m�chten. "
						+ "Bestimmen Sie danach mit dem Z�hler die Anzahl der Einheiten, "
						+ "die Sie bewegen m�chten und dr�cken Sie im Anschluss'Armeen bewegen'. "
						+ "Wenn Sie keine Armeen verschieben m�chten, dr�cken Sie 'Phase beenden'.");
	
	}
	
	public void addPlayerCoat() {
	
		playerCoat.put(0, "assets\\coa1.png");
		playerCoat.put(1, "assets\\coa2.png");
		playerCoat.put(2, "assets\\coa3.png");
		playerCoat.put(3, "assets\\coa4.png");
		playerCoat.put(4, "assets\\coa5.png");
	
	}
}
