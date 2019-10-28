//Claire Menard Completed Lab4
package lab4;

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
	}
	public static List <FastaSequences> readFastaFile(String filepath) throws Exception
		{
			BufferedReader fastafilereader = new BufferedReader(new FileReader(new File(filepath)));
			
			List<FastaSequences> fastaList = new ArrayList<FastaSequences>();
			FastaSequences fsRead = new FastaSequences(null, "");
			
			for(String line = fastafilereader.readLine(); line != null; line = fastafilereader.readLine()) 
			{
				
				if(line.charAt(0) == '>')
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
	public static void writeUnique(String input, String output) throws Exception
	{
		List<FastaSequences>fastaList = FastaObjects.readFastaFile(input);
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(output)));
		List<String> seq = new ArrayList<String>();
		HashMap<String,Integer> uniqueMap = new LinkedHashMap<String,Integer>();
		for( FastaSequences fs : fastaList)
		{
			seq.add(fs.getSequence().toUpperCase());
		}
		Set<String> unique = new HashSet<String>(seq);		
		for( String useq : unique)
		{
			int count = Collections.frequency(seq, useq);
			uniqueMap.put(useq, count);
			
		} 
		Writer writer = new FileWriter(output);
		for( Map.Entry<String,Integer> entry : uniqueMap.entrySet())
		{
			writer.write(">" + entry.getValue() + "\n" + entry.getKey() + "\n");
		}
			out.flush();
			out.close();
		
		
	}
	public static void main(String[] args) throws Exception
	{
		List<FastaSequences>fastaList = FastaObjects.readFastaFile("/Users/clairechristelmenard1/primateslab4.fa");
		for( FastaSequences fs : fastaList)
		{
			System.out.println(fs.getHeader());
			System.out.println(fs.getSequence());
			System.out.println(fs.getGCRatio());
		}
		FastaObjects.writeUnique("/Users/clairechristelmenard1/primateslab4.fa","/Users/clairechristelmenard1/primateslab4.fa.output");
	}
}
