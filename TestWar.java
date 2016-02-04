import java.util.Arrays;
import java.util.Scanner;

public class TestWar{

	public static void main(String[] args){
		
		//starting deck with all the cards
		Deck mutualDeck = new Deck();
		Deck played = new Deck();
		Deck playerOne = new Deck();
		Deck playerTwo = new Deck();
		
		mutualDeck.createFullDeck();
		mutualDeck.shuffleCards();
		mutualDeck.dealCards(playerOne, playerTwo);
		
		//a card from each players's deck is compared
		//the cards from playedDeck and enemyDeck is added to playedCards
		
		System.out.println("Welcome to the card game War!");
	
		while(!hasWinner(playerOne, playerTwo)){
			System.out.println("----------------------------------------------------------");
			System.out.println("Player 1's number of cards: "+playerOne.numOfCardsInDeck());
			System.out.println("Player 1's card: "+playerOne.getCard(0));
			
			System.out.println("Player 2's number of cards: "+playerTwo.numOfCardsInDeck());
			System.out.println("Player 2's card: "+playerTwo.getCard(0));
			
			System.out.println("Total: "+(int)(playerTwo.numOfCardsInDeck()+playerOne.numOfCardsInDeck())+"\n");
			
			played.playWarRound(playerOne, playerTwo);
			
		}
		
	}
	//check for a winner
	public static boolean hasWinner(Deck a, Deck b){
		boolean win = false;
		if((a.numOfCardsInDeck())<=0){
			System.out.println("\nPlayer 2 wins!");
			win = true;
		}else if((b.numOfCardsInDeck())<=0){
			System.out.println("\nPlayer 1 wins!");
			win = true;
		}
		return win;
	}
	
	
}
	

