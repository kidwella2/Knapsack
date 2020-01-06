/*
 * Name: Austin Kidwell
 * Course Number: CSC 364-001
 * Description: This program creates class Project used to store values for the
 * knapsack problem
 */
public class Project {
	//initialize variables
	protected String name;
	protected int weeks;
	protected int profit;

	//Create constructor for class
	public Project(String name, int weeks, int profit){
		this.name = name;
		this.weeks = weeks;
		this.profit = profit;
	}

	//Find the number of weeks per project
	public int getWeeks(){
		return this.weeks;
	}

	//Find the profit per project
	public int getProfit(){
		return this.profit;
	}

	@Override
	//Overrides the toString() method print results clearly
	public String toString(){
		return this.name + " " + this.weeks + " " + this.profit;
	}
}
