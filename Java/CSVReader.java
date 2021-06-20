/*********************************************************************************************
*  Author: George Aziz
*  Date Created: 14/04/2021
*  Date Last Modified: 14/01/2021
*  Purpose: Program to read and retrieve data from CSVs that have a specific format
**********************************************************************************************/

import java.util.*;
import java.io.*;

public class CSVReader
{
    public static void main (String [] args)
    {
        if (args.length == 2) 
		{
			String fileName = args[0];
            int colNum = Integer.parseInt(args[1]);
            
			Queue queue = new Queue();
			
            readFile(fileName,colNum, queue);
			saveToFile(queue);
		}
    }

	/* Method to read file */
	public static int readFile(String fileName, int colNum, Queue queue)
	{	
			FileInputStream fileStrm = null;
			InputStreamReader inRdr;
			BufferedReader bufRdr = null;
			String line;

			int count = 0;

		try
		{
			fileStrm = new FileInputStream(fileName);
			inRdr = new InputStreamReader(fileStrm);
			bufRdr = new BufferedReader(inRdr);

			//Use while loop to make sure the file isn't empty
			line = bufRdr.readLine();
			while (line != null)
			{
				try
				{
					processLine(fileName, line, colNum, queue); //The line will be processed
					count++; //The count only increases if a line has been processed properly
				}
				catch (IllegalArgumentException e)
				{
					System.out.println(e.getMessage() + " " + line); //Any error will be shown to the user
				}
				line = bufRdr.readLine(); //read next line and repeat the process
			}

			fileStrm.close(); //Once all lines are read, the file must be closed
		}
		catch (IOException e) 
		{
			try
			{
				if (fileStrm != null)
				{
					fileStrm.close();
				}
			}
			catch (IOException ex)
			{
				//File won't close, nothing that can be done
			}
		}

		return count; //Count is the amount of lines processed properly
	}
	
	
	/* Processs a line provided and write to */
	public static void processLine(String fileName, String line, int colNum, Queue queue)
	{
		String [] lineArray = line.split(","); //After every section a ',' will be used to seperate them
		
        int idx = 0;
        for(String data : lineArray)
        {
            if(data.equals("Total"))
            {
                System.out.println(lineArray[idx + colNum]);
				queue.enqueue(fileName + " " + lineArray[idx + colNum]); 
            }
            idx++;
        }

	}

	/* Saves content retrieved from file and saves to a new file */
    public static void saveToFile(Queue queue)
	{
		String message;

		try 
		{	
			FileWriter fw = new FileWriter("Results.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			Iterator iter = queue.iterator();
			while (iter.hasNext())
            {
                pw.print(iter.next());
            }

			pw.close(); //Writer must be closed
		}
		catch (IOException e)
		{
			message = "Error in writing to file:" + e.getMessage(); //If an error occurs whilst saving to File, a message will appear to the user
			System.out.println(message);
		}
		
	}
}