import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graf {
	ArrayList <Integer> drogaPrzeglad = new ArrayList <>();
	int numberOfEdges; // liczba wierzcholkow
	int distance[][] = null; // tablica z odleglosciami
	int deleteLine; // wiersz, kotry usuwamy
	int deleteColumn; // kolumna, ktora usuwamy
	int limit = 0; // dolne ograniczenie

	Graf(int number, int[][] dist) { // konstruktor :D
		this.numberOfEdges = number;
		this.distance = dist;
	}

	public int getNumberOfEdges() { // pobranie liczby wierzcholkow
		return numberOfEdges;
	}

	public void printWeights() { // wyprintowanie tablicy z wierzcholkami
		for (int i = 0; i < this.numberOfEdges; i++) {
			for (int j = 0; j < this.numberOfEdges; j++) {
				System.out.print(this.distance[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int getSampleWay(ArrayList <Integer> droga) { // wyliczenie przykladowej drogi
		int lengthOfWay = 0; // 1-2-3-4-5-1
		for (int i = 0; i < droga.size()-1; i++) {
			lengthOfWay += (this.distance[droga.get(i)][droga.get(i+1)]);
		}
		lengthOfWay += (this.distance[droga.get(droga.size()-1)][droga.get(0)]);
		return lengthOfWay;
	}

	/*
	 * funkcja ktora odejmuje najmniejsze wartosci z wierszy, p�niej z kolumn i
	 * je sumuje ("dolne ograniczenie" w tamtym pliczku)
	 */
	// /////////////////////
	/*
	 * zamienilem if (i!=j) na if (this.distance[i][j]!=-1 )na potrzeby
	 * algorytmu
	 */
	public int getShortestWay() {
		ArrayList<Integer> theSmallest = new ArrayList<>();
		int min;
		for (int i = 0; i < this.numberOfEdges; i++) {
			min = 30;
			for (int j = 0; j < this.numberOfEdges; j++) {
				if ((this.distance[i][j] < min) && this.distance[i][j] != -1) {
					min = this.distance[i][j];
				}
			}
			for (int j = 0; j < this.numberOfEdges; j++) {
				if (this.distance[i][j] != -1) {
					this.distance[i][j] = this.distance[i][j] - min;
				}
			}
			theSmallest.add(min);
		}

		for (int i = 0; i < this.numberOfEdges; i++) {
			min = 10;
			for (int j = 0; j < this.numberOfEdges; j++) {
				if ((this.distance[j][i] < min) && this.distance[j][i] != -1) {
					min = this.distance[j][i];
				}
			}
			for (int j = 0; j < this.numberOfEdges; j++) {
				if (this.distance[j][i] != -1) {
					this.distance[j][i] = this.distance[j][i] - min;
				}
			}
			theSmallest.add(min);
		}
		for (int i = 0; i < theSmallest.size(); i++) {
			limit += theSmallest.get(i);
		}
		return limit;
	}

	static /*
	 * algorytm dzialania zgodnie z pdfem z linku :
	 * http://www.gryf-elektryk.pl/sprawozdania
	 * /Rozwiazenie-problemu-komiwojazera-metoda-podzialu-i-ograniczen.pdf
	 */
	boolean test;
	public void przegladZupelny(int n, int u, boolean[] visitedEdges, ArrayList<Integer> stackOfEdges ) { 
	
		int v = u; //biezacy wierzcholek grafu
		stackOfEdges.add(v); // umieszczamy wierzcholek na liscie
		if (stackOfEdges.size() < n) {
			visitedEdges[v] = true;
			for (int i=0; i<n; i++) {
				if (i!=v) {
					u = i;
					if (visitedEdges[u] == false) przegladZupelny(n, u, visitedEdges, stackOfEdges);
				}
			}
			visitedEdges[v]=false;
			stackOfEdges.remove(stackOfEdges.remove(stackOfEdges.indexOf(v)));
		}
		else {
			test = false;
			for (int i=0; i<n; i++) {
				if (i!=v) {
					u=i;
					if (u==0) {
						test=true;	
						//if (test==true) System.out.print("Hamilton Cycle:");
					}
				}
			}
			//System.out.print(stackOfEdges);
			//System.out.print("Droga: " + getSampleWay(stackOfEdges));
			drogaPrzeglad.add(getSampleWay(stackOfEdges));
			//if (test==true) System.out.println("");
			stackOfEdges.remove(stackOfEdges.remove(stackOfEdges.indexOf(v)));
		}
		
	}
	public int algorytm() {

		ArrayList<Integer> theSmallest2 = new ArrayList<>();
		int min;
		int checkIfLineZero = 0, checkIfColumnZero = 0;
		for (int i = 0; i < this.numberOfEdges; i++) {
			min = 30;
			for (int j = 0; j < this.numberOfEdges; j++) {
				if ((this.distance[i][j] < min) && (this.distance[i][j] != -1)) {
					if (this.distance[i][j] == 0 && checkIfLineZero == 0)
						checkIfLineZero = 1; // gdy wiecej niz jedno zero 2 lini
												// to min=0
					else
						min = this.distance[i][j];
				}
			}
			theSmallest2.add(min);
			checkIfLineZero = 0;
		}
		for (int i = 0; i < this.numberOfEdges; i++) {
			min = 10;
			for (int j = 0; j < this.numberOfEdges; j++) {
				if ((this.distance[j][i] < min) && (this.distance[j][i] != -1)) {
					if (this.distance[j][i] == 0 && checkIfColumnZero == 0)
						checkIfColumnZero = 1; // gdy wiecej niz jedno zero 2
												// kolumnie to min=0
					else
						min = this.distance[j][i];
				}
			}
			theSmallest2.add(min);
			checkIfColumnZero = 0;
		}

		int max = Collections.max(theSmallest2);
		int indexOfMax = theSmallest2.indexOf(max);

		if (indexOfMax < this.numberOfEdges) { // indeks odpowiada lini
			deleteLine = indexOfMax;
			for (int j = 0; j < this.numberOfEdges; j++) {
				if (this.distance[deleteLine][j] == 0)
					deleteColumn = j;
			}
		} else {
			deleteColumn = indexOfMax - this.numberOfEdges; // indeks
															// pomniejszony
															// odpowiada
															// kolumnie
			for (int i = 0; i < this.numberOfEdges; i++) {
				if (this.distance[i][deleteColumn] == 0)
					deleteLine = i;
			}
		}

		limit += max;
		return limit;
	}

}
