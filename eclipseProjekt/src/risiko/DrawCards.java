package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout.Constraints;
import javax.swing.border.Border;


public class DrawCards extends JPanel{
	Dimension screenSize;
	ArrayList<Card> selectedCards;
	JToggleButton checkCardsBtn;
	Controller controller;
	ImageIcon cardsSel;
	ImageIcon cardUnsel;
	Player player;
	
	
	public DrawCards(Controller controller) {
		this.controller = controller;
		//controller.cardTest();;
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Color backgroundColor = new Color(237,223,176);
		int width = screenSize.width*8/10;
		int height = screenSize.height;
		this.setPreferredSize(new Dimension(width, height));
		Font font  = new Font("Algerian", Font.ROMAN_BASELINE, 17);
		Font font2  = new Font("Algerian", Font.ROMAN_BASELINE, 25);

		
		JPanel cardPanel = new JPanel();
		ImageIcon infUnsIcon = new ImageIcon("assets\\InfCardUnsel.png");	
		ImageIcon infSelIcon = new ImageIcon("assets\\InfCardSel.png");	
		ImageIcon kavUnsIcon = new ImageIcon("assets\\KavCardUnsel.png");	
		ImageIcon kavSelIcon = new ImageIcon("assets\\KavCardSel.png");	
		ImageIcon artUnsIcon = new ImageIcon("assets\\ArtCardUnsel.png");	
		ImageIcon artSelIcon = new ImageIcon("assets\\ArtCardSel.png");	
		ImageIcon jokerUnsIcon = new ImageIcon("assets\\JokCardUnsel.png");	
		ImageIcon jokerSelIcon = new ImageIcon("assets\\JokCardSel.png");	
		cardUnsel = new ImageIcon("assets\\cardBtnUnsel.png");	
		cardsSel = new ImageIcon("assets\\cardBtnSel.png");	
		ImageIcon bgIcon = new ImageIcon("assets\\drawCardsBG.jpg");
		bgIcon = scaleIcon(bgIcon, width, height);
		JLabel contentPane = new JLabel(bgIcon);
		contentPane.setLayout(new GridBagLayout());
		
	//	this.setBackground(backgroundColor);
		
		selectedCards = new ArrayList<Card>();
		checkCardsBtn = new JToggleButton("Abgeben", cardUnsel);
		checkCardsBtn.setContentAreaFilled(false);
		checkCardsBtn.setHorizontalTextPosition(JButton.CENTER);
		checkCardsBtn.setVerticalTextPosition(JButton.CENTER);
		checkCardsBtn.setBorder(BorderFactory.createEmptyBorder());
		checkCardsBtn.setForeground(Color.white);
		
		
		checkCardsBtn.setFont(font2);
		checkCardsBtn.setEnabled(false);
		checkCardsBtn.addActionListener(e -> checkCards());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridy = 1;
	    gbc.insets = new Insets(50,0,0,0);
		contentPane.add(checkCardsBtn, gbc);

		cardPanel.setSize(width, height);
		cardPanel.setOpaque(false);
		cardPanel.setVisible(true);
	
		GridLayout gridLayout = new GridLayout();
		cardPanel.setLayout(gridLayout);
		
		int numOfCards = controller.getPlayerObject().getCardsInHand().size();
		player = controller.getPlayerObject();
		
		int scaleWidth = 0;
		int scaleHeight = 0;
		
		if(numOfCards != 0) {
			gridLayout.setHgap(bgIcon.getIconWidth()/100);
			gridLayout.setVgap(bgIcon.getIconHeight()/100);
			scaleWidth =  bgIcon.getIconWidth()/numOfCards * 2/4;
			scaleHeight = bgIcon.getIconHeight()/numOfCards;
		}
		
		for(int i = 0; i < numOfCards; ++i) {
			JToggleButton cardBtn = new JToggleButton();
			
			Card card = controller.getPlayerObject().getCardsInHand()
					.get(i);
			Territory territory = controller.getPlayerObject().getCardsInHand()
					.get(i).getTerritory();
			String nameTerritory = territory.getName();
			String symbol = controller.getPlayerObject().getCardsInHand()
					.get(i).getSymbol();
			cardBtn.setFont(font);
			cardBtn.setText(nameTerritory);
			if(player.getOccupiedTerritories().contains(territory)) 
				cardBtn.setForeground(Color.white);
			
			switch (symbol) {
			case "Infanterie":
				cardBtn.setIcon(scaleIcon(infUnsIcon, scaleWidth, scaleHeight));
				cardBtn.setSelectedIcon(scaleIcon(infSelIcon, scaleWidth, scaleHeight));
				break;
			case "Kavallerie":
				cardBtn.setIcon(scaleIcon(kavUnsIcon, scaleWidth, scaleHeight));
				cardBtn.setSelectedIcon(scaleIcon(kavSelIcon, scaleWidth, scaleHeight));
				break;
			case "Artillerie":
				cardBtn.setIcon(scaleIcon(artUnsIcon, scaleWidth, scaleHeight ));
				cardBtn.setSelectedIcon(scaleIcon(artSelIcon, scaleWidth, scaleHeight));
				break;
			case "Joker":
				cardBtn.setIcon(scaleIcon(jokerUnsIcon, scaleWidth, scaleHeight));
				cardBtn.setSelectedIcon(scaleIcon(jokerSelIcon, scaleWidth, scaleHeight));
				break;
			}
			
			cardBtn.setBorder(BorderFactory.createEmptyBorder());
			cardBtn.setContentAreaFilled(false);
			cardBtn.setHorizontalTextPosition(JButton.CENTER);
			cardBtn.setVerticalTextPosition(JButton.CENTER);
			cardBtn.addActionListener(e -> buttonPressed(card,cardBtn));
			cardPanel.add(cardBtn);
		}
		contentPane.add(cardPanel);
		this.setLayout(new BorderLayout());
		this.add(contentPane);
		this.setOpaque(false);
		this.setVisible(true);
	}
	
	//TODO prints rausnehmen
	public void buttonPressed(Card card, JToggleButton cardButton) {
		if(cardButton.isSelected()) 
			selectedCards.add(card);
		else
			selectedCards.remove(card);
		for(Card c : selectedCards) {
			System.out.println(c.getTerritory().getName());
			}
		System.out.println("---");		
		if(selectedCards.size() == 3 && controller.game.validCards(selectedCards.get(0),
			selectedCards.get(1), selectedCards.get(2))) {
			checkCardsBtn.setEnabled(true);
		}
		else
			checkCardsBtn.setEnabled(false);
	}
	
	public void checkCards() {
		if(checkCardsBtn.isSelected()) {
			checkCardsBtn.setForeground(Color.black);
			checkCardsBtn.setSelectedIcon(cardsSel);
			controller.game.tradeCards(player, selectedCards.get(0),
					selectedCards.get(1), selectedCards.get(2));
			
		}
		else
			checkCardsBtn.setForeground(Color.white);
			checkCardsBtn.setIcon(cardUnsel);
	}
	
	public ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image modImage = image.getScaledInstance
				(width, height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(modImage);
		return icon;
	}
}
