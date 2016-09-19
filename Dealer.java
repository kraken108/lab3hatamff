package cards;

import java.util.ArrayList;

public class Dealer {

	private int score;
	Hand playerHand = new Hand();
	ArrayList<Card> CardList;
	
	public Dealer(){
		score=0;
		CardList = new ArrayList<Card>();		
	}
	
	public int getScore(){
		return score;
	}
	
	public void resetScore(){
		score=0;
	}
	
	public void drawCard(Card aCard){
		playerHand.addCard(aCard);
		CardList.add(aCard);
		score+=aCard.getRankValue();
	}
	
	public String toString(){	
		
		String info="";
		int i=0;
		int counter=1;
		for(Card c: CardList){
			info += CardList.get(i);
			i++;
		}
		return info;
	}
	
}
