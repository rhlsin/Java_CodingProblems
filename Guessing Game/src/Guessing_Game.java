import java.util.Random;
import java.util.Scanner;

public class Guessing_Game 
{
	public static void main(String[] args)
	{
		Random Rand = new Random();
		int numberToGuess = Rand.nextInt(1000);
		int numberofTries = 0;
		Scanner input = new Scanner(System.in);
		int guess;
		boolean win = false;
		
		while (win==false)
		{
		
		System.out.println("Guess the no between 1 to 1000");
		guess = input.nextInt();
		numberofTries++;
		
		if (guess == numberToGuess)
		{
			win = true;
		}
		else if (guess < numberToGuess)
		{
			System.out.println("Your Guess is to Low ");

		}
		
		else if (guess > numberToGuess)
		{
			System.out.println("Your Guess is to High ");

		}
		
	}
	
	System.out.println("You win! ");
	System.out.println("The number was "+ numberToGuess);
	System.out.println("It took you " + numberofTries +"tries");
	}


	public static void Main(String[] args)
	{
		//Guessing_Game G= new Guessing_Game();
		System.out.println("You win! ");
	}
}
