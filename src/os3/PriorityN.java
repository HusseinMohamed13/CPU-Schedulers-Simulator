package os3;

import java.util.ArrayList;
import java.util.Scanner;

public class PriorityN {
	public static void prioritySimulation() {
		int n;
		int End = 0;
		System.out.print("Number of processes: ");
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ArrayList<QProcess> processes = new ArrayList<>();
		ArrayList<QProcess> Finished = new ArrayList<>();
		ArrayList<String> order = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			System.out.print("QProcess Name: ");
			String name = sc.next();
			System.out.print("Burst Time: ");
			int bur = sc.nextInt();
			System.out.print("Priority: ");
			int pr = sc.nextInt();
			QProcess p = new QProcess(name, 0, bur, pr, 0, 0, 0);
			p.priority = pr;
			End += bur;
			processes.add(p);
		}
		int last = -1;
		for (int i = 0; i < End; ) {
			processes.sort((o1, o2) -> {
				if (o1.priority < o2.priority) return -1;
				else return 1;
			});
			QProcess p = new QProcess();
			p=processes.get(0);
			p.priority = processes.get(0).priority;
			p.waitingTime = i;
			i += p.burst;
			int bur=p.burst;
			order.add(p.name);
			processes.remove(0);
			
			for (int j = 0; j < processes.size(); j++) {
				QProcess curProcess = processes.get(j);
				curProcess.count+=bur;
				while(curProcess.count>=5) {
					if(curProcess.count>=1) {
					curProcess.priority-=1;
					}
					curProcess.count-=5;
				}
			}
			
			p.turnAround = i - p.arrival;
			Finished.add(p);

		}
		double avgWaiting = 0.0, avgTurnaround = 0.0;
		System.out.println("Order of Execution:");
		for (int i = 0; i < order.size(); i++) {
			System.out.print(order.get(i));
			if (i != order.size() - 1) System.out.print("->");
		}
		System.out.println();
		for (QProcess finish : Finished) {
			System.out.println("Name: " + finish.name + " Waiting Time: " + finish.waitingTime + " Turn Around Time: " + finish.turnAround);
			avgWaiting += finish.waitingTime;
			avgTurnaround += finish.turnAround;
		}
		avgTurnaround /= n;
		avgWaiting /= n;
		System.out.println("Average Waiting Time : " + avgWaiting);
		System.out.println("Average Turn Around Time : " + avgTurnaround);
	}
}