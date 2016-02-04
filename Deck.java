import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	//a list of cards to work with in the class
	private ArrayList<Card> cards;
	
	//constructor for a deck of cards
	public Deck(){
		this.cards = new ArrayList<Card>();
	}
	//creates a full 52 cards
	public void createFullDeck(){
		for(Suit suit : Suit.values()){
			for(Value value : Value.values()){
				this.cards.add(new Card(suit, value));
			}
		}
	}
	public void shuffleCards(){
		//create a temporary deck to store shuffled cards
		ArrayList<Card> temp = new ArrayList<Card>();
		//a random to get indexes
		Random randInt = new Random();
		//an integer to store the random indexes
		int index = 0;
		int originalSize = this.cards.size() ;
		//a "for" loop to iterate 52 times
		for(int i = 0; i<originalSize;i++){
			//getting the index
			index = randInt.nextInt(this.cards.size());
			//add the random card to the temp deck
			temp.add(this.cards.get(index));
			//remove the same random card from the original deck
			this.cards.remove(index);
		}
		//add the temp deck to the empty original deck
		this.cards = temp;	
	}
	//add the card from a shared deck to a player's hand
	public void dealCards(Deck player1, Deck player2){
		for(int i=0;i<26;i++){
			player2.drawCard(this);
			player1.drawCard(this);
		}
	}
	//returns a card depending on what index is specified
	public Card getCard(int index){
		return this.cards.get(index);
	}
	public void removeCard(int index){
		this.cards.remove(index);
	}
	//adds a card to the player's deck (their hand)
	//then removes that card from the original deck
	public void drawCard(Deck aDeck){
		this.cards.add(aDeck.getCard(0));
		aDeck.removeCard(0);
	}
	//moves all cards from aDeck to the deck calling the method
	public void moveAllToBottomOfDeck(Deck aDeck){
		while(aDeck.numOfCardsInDeck()>0){
			this.cards.add(aDeck.getCard(0));
			aDeck.removeCard(0);
		}
	}
	public void moveTopCardToBottom(){
		Card temp;
		temp = this.getCard(0);
		this.removeCard(0);
		this.cards.add(temp);
		
	}
	public int numOfCardsInDeck(){
		return this.cards.size();
	}
	public void removeAllCards(){
		while(this.numOfCardsInDeck()>0){
			this.cards.remove(0);
		}
	}
	
	//2 cards are needed bc 1 card is drawn to mutual deck and 1 card is compared
	public boolean hasMoreThanOneCardLeft(){
		if(this.numOfCardsInDeck()>1){
			return true;
		}else{
			return false;
		}
	}
	//called on a deck with 1 card left
	public boolean finalCardIsTie(Deck hasRestOfCards){
		if((this.getCard(0).cardNumber()==hasRestOfCards.getCard(0).cardNumber())
		&& (this.numOfCardsInDeck()==1)){//if a player ties with one card left
			return true;
		}else{
			return false;
		}
	}
	//method to be called for instances of a tie
	//3 cards are put into the played deck
	public void tie(Deck one, Deck two){
		int i=0;
		while(one.hasMoreThanOneCardLeft() && two.hasMoreThanOneCardLeft() && (i<3)){
			this.drawCard(one);
			this.drawCard(two);
			i++;
		}
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("Player 1's number of cards: "+one.numOfCardsInDeck());
		System.out.println("Player 1's card: "+one.getCard(0));
		System.out.println("Player 2's number of cards: "+two.numOfCardsInDeck());
		System.out.println("Player 2's card: "+two.getCard(0));
		System.out.println("Total: "+(int)(two.numOfCardsInDeck()+one.numOfCardsInDeck()));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		//compare the next upcoming cards from both player's decks
		this.playWarRound(one, two);
	}
	
	//move the deck (played) to the winner's deck
	public void win(Deck winner, Deck loser){
		this.drawCard(loser);
		this.drawCard(winner);
		winner.moveAllToBottomOfDeck(this);
	}
	
	//the played deck gets a card from a player's deck 
	public void playWarRound(Deck pOne, Deck pTwo){
	
		if(pOne.getCard(0).cardNumber()>pTwo.getCard(0).cardNumber()){//you win round
	
			this.win(pOne, pTwo);//take all played cards from both players
		}
		
		else if(pOne.getCard(0).cardNumber()<pTwo.getCard(0).cardNumber()){//opponent wins round
	
			this.win(pTwo, pOne);//take all played cards from both players
			
		}
	//in the case of a "Tie"	
		else if(pOne.finalCardIsTie(pTwo)){//if the tie occurs on player 1's last card
		//get a new card for the player with 51 cards	
			pTwo.moveTopCardToBottom();
		}
		else if(pTwo.finalCardIsTie(pOne)){//if the tie occurs on player 2's last card
		//get a new card for the player with 51 cards
			pOne.moveTopCardToBottom();
		}
		else{
			this.tie(pOne, pTwo);
			System.out.println("Tie!");
		}
	}
	
	public String toString(){
		String cardListOutput = "";
		for(Card aCard : this.cards){
			cardListOutput+="\n"+ aCard.toString();
		}
		return cardListOutput;
	}
	
}
