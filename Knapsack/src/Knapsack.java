/*
 * Name: Austin Kidwell
 * Course Number: CSC 364-001
 * Description: This program reads an input file then uses the information to print
 * the number of projects/weeks available, projects chosen, total profit, and list
 * the best projects in an output file.
 */
import java.util.*;
import java.io.*;

public class Knapsack {
  public static void main(String[] args) throws FileNotFoundException{
	// ...
	// Here is code for opening a text file and reading from it,
	// and storing the information in an ArrayList of Project.
	// You would need to write the Project class yourself,
	// and also declare and initialize inputFileName (String).

	//Input the available weeks
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number of available employee work weeks: ");
	int weeks = input.nextInt();

	//Name the input file
	System.out.print("Enter the name of the input file: ");
	String inName = input.next();

	//Name the output file
	System.out.print("Enter the name of the output file: ");
	String outName = input.next();

	//Initialize inputFileName and read the file with a scanner
	String inputFileName = inName;
	Scanner dataFile = new Scanner(new File(inputFileName));
	//ArrayList used to store all projects
	ArrayList<Project> projects = new ArrayList<>();
	//ArrayList used to store most profitable projects
	ArrayList<Project> best = new ArrayList<>();
	//Reads file till no lines left
	while (dataFile.hasNextLine())
	{
		String line = dataFile.nextLine();
		//Splits the string up where spaces exist
		String[] split = line.split(" ");
		//Separates the split string into type Project
		//and adds values to ArrayList projects
		projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
	}

	//Value of total projects to chose from
	int n = projects.size();
	//Matrix to set profit to
	int profit[][] = new int[weeks + 1][n + 1];
	//j stands for amount of projects available
	//w stands for weeks available
	for(int j = 1; j <= n; j++){
		for(int w = 1; w <= weeks; w++){
			//When profit will stay the same
			if(projects.get(j - 1).getWeeks() > w){
				profit[w][j] = profit[w][j - 1];
			}
			//When profit increased and max profit has to be recalculated
			else{
				int w1 = w - projects.get(j - 1).getWeeks();
				profit[w][j] = Math.max(profit[w1][j - 1] + projects.get(j - 1).getProfit(),
						                profit[w][j - 1]);
			}
		}
	}

	//Place holder for weeks
	int sub = weeks;
	//Loop that finds the most profitable projects available then
	//adds them to ArrayList best
	for(int i = n; i > 0; i--){
		if(profit[sub][i] > profit[sub][i - 1]){
			sub -= projects.get(i - 1).getWeeks();
			best.add(projects.get(i - 1));
		}
	}

	//Close input
	input.close();
	//Close input file used
	dataFile.close();

	// Here is code for writing to a text file
	// You will need to declare and initialize outputFileName (String).
	String outputFileName = outName;
	PrintWriter outputFile = new PrintWriter(outputFileName);
	//Adds number of available projects to output file
	outputFile.println("Number of projects available: " + n);
	//Adds weeks available to output file
	outputFile.println("Available employee work weeks: " + weeks);
	//Adds number of projects chosen to output file
	outputFile.println("Number of projects chosen: " + best.size());
	//Adds highest profit possible to output file
	outputFile.println("Total profit: " + profit[weeks][n]);
	//Loop used to add the chosen projects
	for(int i = best.size() - 1; i >= 0; i--)
		outputFile.println(best.get(i).toString());
	//Close output file
	outputFile.close();
  }}
