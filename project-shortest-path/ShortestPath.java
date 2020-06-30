/**
 * ShortestPath
 * 
 *   An algorithm for finding the shortest Path between two nodes 
 *   using an adjacency list.
 *   
 *   @author Ian McCamey
 *   @version 20191115
 */

import java.util.*;
import java.io.*;

public class ShortestPath {
	
	/**
	 * Creates a Map linking a starting node to a list of Path objects, a data structure
	 *  containing adjacent nodes and their respective distancess.
	 * 
	 * @param fname
	 * @return
	 */
	
	public static Map< String, List<Path> > readPaths(String fname){
		File file = new File(fname);
		Map< String, List<Path> > paths = new HashMap< String, List<Path> >();
		
		try {
			Scanner readFile = new Scanner(file);
			
			while (readFile.hasNextLine()) {
				String[] tokens = readFile.nextLine().split(",");
				Double distance = Double.parseDouble(tokens[tokens.length - 1]);
				Path forwards = new Path(tokens[1], distance);
				Path backwards = new Path(tokens[0], distance);
				List<Path> destinations;
				
				if (!paths.containsKey(tokens[0]))
					destinations = new LinkedList<Path>();	
				else
					destinations = paths.get(tokens[0]);
				destinations.add(forwards);
				paths.put(tokens[0], destinations);

				if (!paths.containsKey(tokens[1]))
					destinations = new LinkedList<Path>();	
				else
					destinations = paths.get(tokens[1]);
				destinations.add(backwards);
				paths.put(tokens[1], destinations);
			}
			readFile.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return paths;
	}
	
	/**
	 * Displays the Map of starting nodes and their respective potential Paths.
	 * 
	 * @param map
	 */
	
	public static void displayAdjacencyList(Map< String,List<Path> > map) {
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		String s = "Start City";
		
		System.out.println();
		System.out.printf("%-15s", s);
		System.out.println("Paths");
		System.out.println("-------------- ------------------------------");
		
		while (iter.hasNext()) {
			s = iter.next();
			System.out.printf("%-14s", s);
			
			for (int i = 0; i < map.get(s).size(); i++) {
				Path myPath = map.get(s).get(i);
				if (i == 0) {
					System.out.print(" (" + myPath.getEndpoint() + ":");
					System.out.print(myPath.getCost() + ")");
				} else {
					System.out.print(", (" + myPath.getEndpoint() + ":");
					System.out.print(myPath.getCost() + ")");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Returns a Map containing the shortest distance to all destinations given a starting point.
	 * 
	 * @param start
	 * @param adj_list
	 * @return shortestDistances
	 */
	
	public static Map<String, Double> findDistances(String start, Map<String, List<Path>> adj_list){
		Map<String, Double> shortestDistances = new HashMap<String, Double>();
		PriorityQueue<Path> paths = new PriorityQueue<Path>();
		paths.add(new Path(start, 0.0));
		
		while (!paths.isEmpty()) {
			Path current = paths.remove();
			
			if(!shortestDistances.containsKey(current.getEndpoint())) {
				String dest = current.getEndpoint();
				double d = current.getCost();
				shortestDistances.put(dest, d);
				for(Path n : adj_list.get(dest)) {
					paths.add(new Path(n.getEndpoint(), n.getCost() + d));
				}
			}
		}
		return shortestDistances;
	}
	
	/**
	 * Displays the Map containing the shortest distance to all destinations given a starting point.
	 * 
	 * @param start
	 * @param shortest
	 */
	
	public static void displayShortest(String start, Map<String, Double> shortest) {
		Set<String> keys = shortest.keySet();
		Iterator<String> iter = keys.iterator();
		String s = "Dest. City";
		
		System.out.println();
		System.out.println("Distances from " + start + " to each city:");
		System.out.printf("%-15s", s);
		System.out.println("Distance");
		System.out.println("-------------- --------");
		
		while(iter.hasNext()) {
			s = iter.next();
			System.out.printf("%-15s", s);
			System.out.printf("%8.2f\n", shortest.get(s));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);		
		System.out.print("Enter a file name with paths: ");
		String s = keyboard.nextLine();
		Map< String, List<Path> > adjList = readPaths(s);
		displayAdjacencyList(adjList);
		System.out.print("Enter a starting city (empty line to quit): ");
		s = keyboard.nextLine();
		
		while(!s.isEmpty()) {
			Map<String, Double> shortest = findDistances(s, adjList);
			displayShortest(s, shortest);
			System.out.print("Enter a starting city (empty line to quit): ");
			s = keyboard.nextLine();
		}
		System.out.print("Goodbye!");
		keyboard.close();
	}
}
