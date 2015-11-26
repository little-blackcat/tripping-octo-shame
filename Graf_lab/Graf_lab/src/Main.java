import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer> theShortestWay = new ArrayList<>(); // droga
	static ArrayList<Integer> Line = new ArrayList<>();
	static ArrayList<Integer> Column = new ArrayList<>();
	static ArrayList<Integer> stackOfEdges = new ArrayList<>();
	
	
	public static void main(String[] args) throws FileNotFoundException {

		int fileType = 1;
		int number = 40;
		int something[][] = null;
		
		
		switch(fileType){
		case 1:
			java.io.FileReader frconfig = new java.io.FileReader("config.txt");
			Scanner config = new Scanner(frconfig);

			number = Integer.parseInt(config.nextLine()); // liczba miast z
																// pliku
			something = new int[number][number];
			
			for (int i = 0; i < number; i++) { // tablica z pliku
				String[] line = (config.nextLine()).split(" ");
				for (int j = 0; j < number; j++) {
					something[i][j] = Integer.parseInt(line[j]);
				}
			}
			config.close();
			break;
		case 2:
			XMLParser parser = new XMLParser();
			something = parser.XMLParsing();
			break;
		}
		
		
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				System.out.print(something[i][j] + " ");
			}
			System.out.println();
		}
		
		// skopiowanie glownej macierzy
		double[][] somethingCopy = new double[number][number];
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				somethingCopy[i][j] = something[i][j];
			}
		}
		for (int i = 1; i <= number; i++) {
			Line.add(i);
			Column.add(i);
		}
		Graf graf;
		graf = new Graf(number, something);
		boolean[] visitedEdges = new boolean [number];
		
		long startTimeAll = System.nanoTime();
		
		//graf.przegladZupelny(number, 0, visitedEdges, stackOfEdges);
		//System.out.println("Najkrotsza droga z przegladu zupelnego:" + Collections.min(graf.drogaPrzeglad));
		
		long estimatedTime = System.nanoTime() - startTimeAll;
		System.out.println("Czas wykonania przegladu zupelnego: " + estimatedTime);
		
		long startTime = System.nanoTime(); 
		
		while (number != 2) {

			graf = new Graf(number, something);
			//graf.printWeights();
			graf.getShortestWay();
			//System.out.println("");
			graf.algorytm();

			theShortestWay.add(Line.get(graf.deleteLine));
			theShortestWay.add(Column.get(graf.deleteColumn));

			if (Line.indexOf(Column.get(graf.deleteColumn)) != -1
					&& Column.indexOf(Line.get(graf.deleteLine)) != -1) {
				something[Line.indexOf(Column.get(graf.deleteColumn))][Column
						.indexOf(Line.get(graf.deleteLine))] = -1;
			}

			// usuniecie lini i kolumny
			Line.remove(Line.indexOf(Line.get(graf.deleteLine)));
			Column.remove(Column.indexOf(Column.get(graf.deleteColumn)));

			// przekopiowanie tablicy do tymczasowej
			int[][] temp = new int[number][number];
			for (int i = 0; i < number; i++) {
				for (int j = 0; j < number; j++) {
					temp[i][j] = something[i][j];
				}
			}
			// stworzenie nowej pomniejszonej tablicy
			number--;
			something = new int[number][number];

			for (int i = 0; i < graf.deleteLine; i++) {
				for (int j = 0; j < graf.deleteColumn; j++) {
					something[i][j] = temp[i][j];
				}
				for (int j = graf.deleteColumn; j < number; j++) {
					something[i][j] = temp[i][j + 1];
				}
			}

			for (int i = graf.deleteLine; i < number; i++) {
				for (int j = 0; j < graf.deleteColumn; j++) {
					something[i][j] = temp[i + 1][j];
				}
				for (int j = graf.deleteColumn; j < number; j++) {
					something[i][j] = temp[i + 1][j + 1];
				}
			}
		}

		// dodanie dwoch ostatnich drog z macierzy 2x2
		theShortestWay.add(Line.get(0));
		theShortestWay.add(Column.get(0));
		theShortestWay.add(Line.get(1));
		theShortestWay.add(Column.get(1));
		//System.out.println(theShortestWay);

		int droga = 0;
		for (int i = 0; i < theShortestWay.size(); i += 2) {
			droga += somethingCopy[theShortestWay.get(i) - 1][theShortestWay
					.get(i + 1) - 1];
		}
		
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Czas wykonania algorytmu: " + estimatedTime);
		
		System.out.println("Obliczona droga wynosi:" + droga);
		
	}
}
