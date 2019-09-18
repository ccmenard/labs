package lab2;
import java.util.Random;


public class AAQuiz
{

	public static String[] SHORT_NAMES = {"A","R","N","D","C","Q","E","G","H",
			"I","L","K","M","F","P","S","T","W","Y","V"};

	public static String[] FULL_NAMES = {"alanine","arginine","asparagine","aspartic acid","cysteine",
			"glutamine","glutamic acid","glycine","histidine","isoleucine","leucine","lysine","methionine",
			"phenylalanine","proline","serine","threonine","tryptophan","tyrosine","valine"} ;
	
	public static void main( String[] args)
	{
		long startTime = System.currentTimeMillis();
		double elapsedSeconds = 0; 
		//System.out.println(startTime);
		
		Random random = new Random();
		boolean keepgoing = true;
		int correctinput = 0;
		while (keepgoing == true && elapsedSeconds < 30)
		{
			int randomnumber = random.nextInt(FULL_NAMES.length);
			System.out.println("What is the one letter abbrv for " + FULL_NAMES[randomnumber] + " ?");
			String input = System.console().readLine().toUpperCase();
			
			if(input.equals(SHORT_NAMES[randomnumber]))
			{
				System.out.println("You are correct. " + elapsedSeconds);
				correctinput = correctinput + 1;
				elapsedSeconds = ((System.currentTimeMillis() - startTime) / 1000f);
				keepgoing = true;
				
			}
			else
			{
				System.out.println("You are wrong. End of quiz. " + elapsedSeconds);
				keepgoing = false;
				
			}
		}
		if (elapsedSeconds >= 30)
		{
			System.out.println("You are out of time. Correct number of answers: " + correctinput);
			
		}
	}
	
}
 
