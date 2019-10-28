package lab4;
//c//
import java.io.*;
import java.util.*;

public class FastaObjects 
{
	
	public static class FastaSequences
	{
		String header;
		String sequence;
		
		FastaSequences(String header, String sequence)
		{
			this.header = header;
			this.sequence = sequence;
		}
		
		public String getHeader()
		{
			return header;
		}
		public String getSequence()
		{
			return sequence;
					
		}
		public float getGCRatio() 
		{
			//String sequenceNow = sequence.toUpperCase();
			//int length = sequence.length();
			int countC = 0;
			int countG = 0;
			for(int i = 0; i < sequence.length() ; i++) 
			{
				if(sequence.charAt(i) == 'G') 
						
				{
					countG++;
				}
				else if(sequence.charAt(i) == 'C') 
				{
					countC++;
				}
					
			}
			int gc_Ratio = ((countG + countC) / (sequence.length()));
			//return countC;
			return gc_Ratio;
		}
	}
	public static List<FastaSequences>readFastaFile(String filepath) throws Exception
		{
			BufferedReader fastafilereader = new BufferedReader(new FileReader(new File(filepath)));
			
			List<FastaSequences> fastaList = new ArrayList<FastaSequences>();
			FastaSequences fsRead = new FastaSequences(null, "");
			
			for(String line = fastafilereader.readLine(); line != null; line = fastafilereader.readLine()) 
			{
				
				if(line.startsWith(">"))
				{	
					if(fsRead.header==null)
					{
						fsRead.header = line.replace(">","");
					}
	
					else
					{
						fastaList.add(fsRead);
						fsRead = new FastaSequences(null, "");
					}
				}
				else if (line.equals("")) 
				{
					if (fsRead.header != null)
					{
						fastaList.add(fsRead);
						fsRead = new FastaSequences(null, "");
					}
				}
				else
					{
					
						fsRead.sequence = fsRead.sequence + line;
					
					}
	
				
			}
			if (fsRead.sequence.length() > 0) 
				
				{
					fastaList.add(fsRead);
				} 
			fastafilereader.close();
			return fastaList;
		}
	
	/*public static void writeUnique(File inFile, File outFile) throws Exception
	{
	//	List<SequenceParser>fastaList = FastaObjects.readFastaFile(infile);
		BufferedReader fastafilereader = new BufferedReader(new FileReader(inFile));
		Map<String,Integer> uniqueMap = new TreeMap<String,Integer>();
		String line = fastafilereader.readLine();
		String sequence = "";
		for(line = fastafilereader.readLine(); line != null; line = fastafilereader.readLine()) 
		{
			if((line.charAt(0) == '>') && sequence != "")
				{
					Integer count_uniq = uniqueMap.get(sequence);
					if(count_uniq == null)
					{
						count_uniq = 0;
					}
					count_uniq++;
					
					sequence = "";
					line = fastafilereader.readLine();
					uniqueMap.put(sequence, count_uniq);
				}
			else
			{
				sequence = sequence + line;
				line = fastafilereader.readLine();
			}
		}
		fastafilereader.close();
		Writer writer = new FileWriter(outFile);
		for(Map.Entry<String,Integer> entry : uniqueMap.entrySet())
		{
			writer.write(">" + entry.getValue() + "\n" + entry.getKey() + "\n");
		}
		writer.flush();
		writer.close();
	}*/
	public static void main(String[] args) throws Exception
	{
		
		//List<FastaSequences>fastaList = FastaSequences.readFastaFile("/Users/clairechristelmenard1/primateslab4.fa");
		
	//	System.out.println(fastaList.size());
	//	System.out.println("\n\nThis is a FASTA parser.\nPlease input the filepath to the FASTA file:");
	//	String input_filepath = System.console().readLine();
		List<FastaSequences>fastaList = FastaObjects.readFastaFile("/Users/clairechristelmenard1/primateslab4.fa");
		
		//writeUnique(inFile, outFile);
		for( FastaSequences fs : fastaList)
		{
			System.out.println(fs.getHeader());
			System.out.println(fs.getSequence());
			System.out.println(fs.getGCRatio());
		}
		/*
		
		File infile = new File("/Users/clairechristelmenard1/primateslab4.fa");
		File outfile = new File("/Users/clairechristelmenard1/primateslab4.fa.parsed.txt");
		writeUnique(infile, outfile);
		for(SequenceParser fa : fastaList)
		{
			System.out.println("found");
			System.out.println(fa.getHeader());
			System.out.println(fa.getSequence());
			System.out.println(fa.getGCRatio());
			
			writeUnique(infile, outfile);

		}*/
	}
	
}


