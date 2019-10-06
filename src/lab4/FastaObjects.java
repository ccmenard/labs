
package lab4;

import java.io.*;
import java.util.*;

public class FastaObjects 
{
	private static class SequenceParser
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
		public float getGCRatio()
		{
			//return gc_ratio;
			String current_sequence = this.sequence.toUpperCase();
			Map<Character, Integer> GCMap = new HashMap<Character, Integer>();
			float length = current_sequence.length();
			
			for(int i =0; i < length; i++)
			{
				Integer count = GCMap.get(current_sequence.charAt(i));
				if (count == null)
					count = 0;
				
				count++;
				GCMap.put(current_sequence.charAt(i), count);
							
			}
			float count_C = GCMap.get('C');
			float count_G = GCMap.get('G');
			float gc_ratio = ((count_G + count_C) / length) * 100;
			return gc_ratio;
			//used lecture 9
					
		}
		public static List<SequenceParser>readFastaFile(String user_filepath) throws Exception
		{
			BufferedReader fastafile = new BufferedReader(new FileReader(new File(user_filepath)));
			List<SequenceParser> readFastaFile = new ArrayList<SequenceParser>();
			String line = fastafile.readLine();
			String header2 = "";
			String sequence2 = "";
			HashMap<String,String> sequenceMap = new HashMap<String,String>();
			
			for(line = fastafile.readLine(); line != null; line = fastafile.readLine()) 
			{
				if(line.charAt(0) == '>')
					{
						header2 = line;
						sequence2 = "";
						line = fastafile.readLine();
					}
				else
				{
					sequence2 = sequence2 + line;
					sequenceMap.put(header2, sequence2);
					line = fastafile.readLine();
				}
			}
			fastafile.close();
			for(Map.Entry<String,String> entry : sequenceMap.entrySet())
			{
				SequenceParser fastasequenceobjects = new SequenceParser(entry.getKey(), entry.getValue());
				readFastaFile.add(fastasequenceobjects);
			}
			return readFastaFile;
		}
	}
	public static void writeUnique(File infile, File outfile) throws Exception
	{
		BufferedReader fastafile = new BufferedReader(new FileReader(infile));
		String line = fastafile.readLine();
		String sequence2 = "";
		Map<String,Integer> uniqueMap = new HashMap<String,Integer>();
		for(line = fastafile.readLine(); line != null; line = fastafile.readLine()) 
		{
			if((line.charAt(0) == '>') && sequence2 != "")
				{
					Integer count_uniq = uniqueMap.get(sequence2);
					if(count_uniq == null)
					{
						count_uniq = 0;
					}
					count_uniq++;
					
					sequence2 = "";
					line = fastafile.readLine();
					uniqueMap.put(sequence2, count_uniq);
				}
			else
			{
				sequence2 = sequence2 + line;
				line = fastafile.readLine();
			}
		}
		fastafile.close();
		Writer writer = new FileWriter(outfile);
		for(Map.Entry<String,Integer> entry2 : uniqueMap.entrySet())
		{
			writer.write(">" + entry2.getValue() + "\n" + entry2.getKey() + "\n");
		}
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("/n---Welcome!---/nThis is a fasta sequence parser which requires an input of a file path to your .fa file: ");
		String filepath_user_input = System.console().readLine();
		
		List<SequenceParser> fastaList = SequenceParser.readFastaFile("c:\\pointsToSome\\FastaFile.txt");
		File infile = new File(filepath_user_input);
		File outfile = new File(filepath_user_input + ".parsed.txt");
		writeUnique(infile, outfile);
		for( SequenceParser fastasequenceobjects : fastaList)
		{
			System.out.println(fastasequenceobjects.getHeader());
			System.out.println(fastasequenceobjects.getSequence());
			System.out.println(fastasequenceobjects.getGCRatio());
		}
	}
}

