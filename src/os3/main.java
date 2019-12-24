package os3;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		while (true) {
			System.out.println("1: Non preemptive Shortest- Job First (SJF) Scheduling");
			System.out.println("2: SRTF");
			System.out.println("3: Priority Scheduling");
			System.out.println("4: AG Scheduling");
			System.out.println("5: Exit");
			System.out.print("Input: ");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch (input) {
			    case 1:
			    	SJF sjf = new SJF();
			    	int mat[][] = new int[4][6];
			    	sjf.SJFGUI(4,mat);
			    	break;
			    case 2:
			    	ArrayList<Integer> x1 = new ArrayList<Integer>();
			    	SRTF srtf = new SRTF();
			    	srtf.SRTFGUI(x1);
			    	break;
			    case 3:
			    	PriorityN.prioritySimulation();
			    	break;
				case 4:
					AG.agSimulation();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid Input");
					break;
			}
			
		
	}
}
}
