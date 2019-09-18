package lab3;
import java.io.*;


public class fasta {

	public static void main(String[] args) throws Exception
	{
		
		BufferedReader fastafile = new BufferedReader(new FileReader(new File("/Users/clairechristelmenard1/primates.fa")));
		BufferedWriter writefasta = new BufferedWriter(new FileWriter(new File("/Users/clairechristelmenard1/output.txt")));
		
		writefasta.write("sequenceID\tnum_of_A\tnum_of_T\tnum_of_C\tnum_of_G\tsequence");
		
		for(String nextline = fastafile.readLine(); nextline != null; nextline = fastafile.readLine()) 
		{
			
			if(nextline.startsWith(">")) 
			{
				writefasta.write("\n" + nextline);
			}
			else 
			{
				int A = 0;
				int G = 0;
				int T = 0;
				int C = 0;
				for(int i = 0; i < nextline.length(); i++) 
				{
					if(nextline.charAt(i) == 'A') 
					{
						A++;
					}
					else if(nextline.charAt(i) == 'T') 
					{
							T++;
					}
					else if(nextline.charAt(i) == 'C') 
					{
								C++;
					}
					else if(nextline.charAt(i) == 'G') 
					{
									G++;
					}
				}
				writefasta.write("\t" + A);
				writefasta.write("\t" + T);
				writefasta.write("\t" + C);
				writefasta.write("\t" + G);
				writefasta.write("\t" + nextline);
			}
		}
		
		writefasta.close();
	}
					
		
}


