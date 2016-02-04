
public class Card {
	private Suit suit;
	private Value value;
	
	//constructor
	public Card(Suit suit, Value value){
		this.suit = suit;
		this.value = value;
	}
	//overriding toString
	public String toString(){
		return this.value+" of "+this.suit;
	}
	
	//returns just the value of the card
	public Value getValue(){
		return this.value;
	}
	
	//returns the literal integer value of the card
	public int cardNumber(){
		int number = 0;
		switch(this.getValue()){
			case TWO: number=2; break;
			case THREE: number=3; break;
			case FOUR: number=4; break;
			case FIVE: number=5; break;
			case SIX: number=6; break;
			case SEVEN: number=7; break;
			case EIGHT: number=8; break;
			case NINE: number=9; break;
			case TEN: number=10; break;
			case JACK: number=11; break;
			case QUEEN: number=12; break;
			case KING: number=13; break;
			case ACE: number=14; break;
		}
	return number;	
	}
}
