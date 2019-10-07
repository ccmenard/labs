package lab4;

import java.io.*;
import java.util.*;

public class FastaObjects 
{
	
	static class SequenceParser
	{
		private String header;
		private String sequence;
	
		public SequenceParser(String header, String sequence)
		{
			this.header = header;
			this.sequence = sequence;
		}
		public String getHeader()
		{
			return this.header.replaceAll(">", "");
		}
		public String getSequence()
		{
			return this.sequence;
					
		}
		public double getGCRatio() 
		{
			sequence = sequence.toUpperCase();
			int len = sequence.length();
			double count = 0;
			for(int i = 0; i < len; i++) 
			{
				if(sequence.charAt(i) == 'G' || sequence.charAt(i) == 'C') 
				{
					count ++;
				}
				
			}
			return (double) count/len;
		}

		public static List<SequenceParser>readFastaFile(String file) throws Exception
		{
			BufferedReader fastafile = new BufferedReader(new FileReader(new File("/Users/clairechristelmenard1/primates.fa")));
			List<SequenceParser>readFastaFile = new ArrayList<SequenceParser>();
			//String line = fastafile.readLine();
			String header = "";
			String sequence = "";
			HashMap<String,String> sequenceMap = new HashMap<String,String>();
			
			for(String line = fastafile.readLine(); line != null; line = fastafile.readLine()) 
			{
				
				if(line.startsWith(">")==true)
				{	
					line = fastafile.readLine();
					header = line;
					sequence = "";
				}
	
				else
				{
					sequence = sequence + line;
					sequenceMap.put(header, sequence);
					line = fastafile.readLine();
				}
			}
			fastafile.close();
			for(Map.Entry<String,String> entry : sequenceMap.entrySet())
			{
				SequenceParser fa = new SequenceParser(entry.getKey(), entry.getValue());
				readFastaFile.add(fa);
			}
			return readFastaFile;
		}
	}
	public static void writeUnique(String infile, String outfile) throws Exception
	{
		List<SequenceParser>fastaList = FastaObjects.readFastaFile(infile);
		//BufferedReader fastafile = new BufferedReader(new FileReader(infile));
		Map<String,Integer> uniqueMap = new TreeMap<String,Integer>();
		String line = fastafile.readLine();
		String sequence = "";
		for(line = fastafile.readLine(); line != null; line = fastafile.readLine()) 
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
					line = fastafile.readLine();
					uniqueMap.put(sequence, count_uniq);
				}
			else
			{
				sequence = sequence + line;
				line = fastafile.readLine();
			}
		}
		fastafile.close();
		Writer writer = new FileWriter(outfile);
		for(Map.Entry<String,Integer> entry : uniqueMap.entrySet())
		{
			writer.write(">" + entry.getValue() + "\n" + entry.getKey() + "\n");
		}
		writer.flush();
		writer.close();
	}
	public static void main(String[] args) throws Exception
	{
		
		List<SequenceParser>fastaList = SequenceParser.readFastaFile("/Users/clairechristelmenard1/primateslab4.fa");
		File infile = new File("/Users/clairechristelmenard1/primateslab4.fa");
		File outfile = new File("/Users/clairechristelmenard1/primateslab4.fa.parsed.txt");
		//writeUnique(infile, outfile);
		for(SequenceParser fa : fastaList)
		{
			System.out.println("found");
			System.out.println(fa.getHeader());
			System.out.println(fa.getSequence());
			System.out.println(fa.getGCRatio());
			
			writeUnique(infile, outfile);

		}
	}
	
}

