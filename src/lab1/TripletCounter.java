package lab1;

import java.util.Random;
public class TripletCounter 
{

	public static void main(String[] args)
	{
	int tAcounter = 0;
	Random random = new Random();
	String tripleA = "AAA";
	String bases = "ACTG";
	// assign ATCG to 0-3

	for (int x=0; x < 1000; x++)
	{
		String triplet = bases.charAt(random.nextInt(bases.length())) + "" 
		+ bases.charAt(random.nextInt(bases.length())) 
				+ bases.charAt(random.nextInt(bases.length()));
		System.out.println(triplet);
		//print 1000 3mers
	
	if( triplet.equals(tripleA) )
		{	
		//System.out.println("Yes");
		tAcounter = tAcounter +1;  
		}
		
	else
		{
		//System.out.println("No");
		}	
		
		
		
		
	}
	System.out.println(tAcounter);
	}
	
}

// I got 16 "AAA"'s out of 1000 3mers with the same distribution for A,T,C&G. With an even distribution, the probability 
//of getting "AAA" is (0.25)^3 or 15.6 out of 1000, so 16 is rather close.
// Claire Menard cmenard@uncc.edu

