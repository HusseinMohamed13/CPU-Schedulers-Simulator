package os3;

import java.util.*;

public class AG {
	public static void printhistory(ArrayList<QProcess> original) {
		  System.out.print("* Quantum ( ");
      for (int j = 0; j < original.size(); j++) {
          System.out.print(original.get(j).quantum);
          if (j != original.size() - 1) System.out.print(", ");
      }
      System.out.print(" ) -> ceil(50%) = ( ");
      for (int j = 0; j < original.size(); j++) {
          int x = original.get(j).quantum;
          x = (int) Math.ceil(x * 1.0 / 2.0);
          System.out.print(x);
          if (j != original.size() - 1) System.out.print(", ");
      }
      System.out.print(")");
	}

	public static void agSimulation() {
		int n;
		int End = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Number of processes: ");
		n = sc.nextInt();
		ArrayList<QProcess> original = new ArrayList<>();
		ArrayList<String> order = new ArrayList<>();
		List<Pair> waiting_time = new ArrayList<>();
		ArrayList<QProcess> readyqueue = new ArrayList<>();
		ArrayList<QProcess> processes = new ArrayList<>();
		Queue<QProcess> usedbefore = new LinkedList<>(); 
		
		for (int i = 0; i < n; ++i) {
			System.out.print("QProcess Name: ");
			String name = sc.next();
			System.out.print("Arrival Time: ");
			int arr = sc.nextInt();
			System.out.print("Burst Time: ");
			int bur = sc.nextInt();
			System.out.print("Priority: ");
			int pri = sc.nextInt();
			System.out.print("Quantum: ");
			int qnt = sc.nextInt();
			QProcess p = new QProcess(name, arr, bur, pri, bur, 0, 0);
			p.quantum = qnt;
			p.agfactor = arr+bur+pri;
			p.waitingTime = 0;
			p.remaining = 0;
			p.notchangedBurst = p.burst;
			p.turnAround = p.notchangedBurst;
			End += bur;
			processes.add(p);
			original.add(p);
		}
		
		int q=0;
		QProcess p = new QProcess();
		QProcess runningprocess = null;
		for (int i = 0; i < End; i++) {
			//history of quantm every time
			
     
            //check for remaining time
			if(runningprocess!=null&&runningprocess.burst==0) {
               if(readyqueue.size()>=1) {
            	   for(int x=0;x<original.size();x++) {
						if(original.get(x).name.equals(runningprocess.name)) {
							original.get(x).quantum = 0;
						}
				   }	
            	   runningprocess = readyqueue.get(0);
            	 //updating waiting time & turnaround
            	   runningprocess.waitingTime += Math.abs(runningprocess.remaining - i);
					runningprocess.turnAround += Math.abs(runningprocess.remaining - i);
            	   q = runningprocess.quantum;  
            	   readyqueue.remove(0);
            	   //printing history
            	   printhistory(original);
            	   System.out.print(runningprocess.name+" running");
                   System.out.println();
               }				
			}
	        //check for quantm first case
			if(q==0&&runningprocess!=null) {
				p = runningprocess;
				runningprocess = readyqueue.get(0);
				//updating waiting time & turnaround
				runningprocess.waitingTime += Math.abs(runningprocess.remaining - i);
				runningprocess.turnAround += Math.abs(runningprocess.remaining - i);
				q = runningprocess.quantum;  
				readyqueue.remove(0);
				for(int x=0;x<original.size();x++) {
					if(original.get(x).name.equals(p.name)) {
						original.get(x).quantum=p.quantum;
					}
				}
				printhistory(original);
				System.out.print(runningprocess.name+" running");
	            System.out.println();
				p.quantum+=1;
				p.remaining = i;
				readyqueue.add(p);
			}
			//load processes at point of time to queue
			for (int x = 0; x < processes.size(); x++) {
				if(processes.get(x).arrival==i) {
					processes.get(x).remaining = i;
		          readyqueue.add(processes.get(x));
		          processes.remove(x);
				}
			}
			
			//for first process enter queue
			if(runningprocess==null) {
			  runningprocess = readyqueue.get(0);
			   //updating waiting time & turnaround
			  runningprocess.waitingTime += Math.abs(runningprocess.remaining - i);
				runningprocess.turnAround += Math.abs(runningprocess.remaining - i);
			  readyqueue.remove(0);
		 	  q = runningprocess.quantum;
				printhistory(original);
				System.out.print(runningprocess.name+" running");
	            System.out.println();
			}
			
			//find smallest agfactor
			if(readyqueue.size()>=1) {
			int min=readyqueue.get(0).agfactor,indx = 0;
			for (int x = 0; x < readyqueue.size(); x++) {
				if(readyqueue.get(x).agfactor<min) {
					min = readyqueue.get(x).agfactor;
					indx = x;
				}
			} 
			p = readyqueue.get(indx);
			//check second case
			if(runningprocess!=null&&q<=Math.ceil(runningprocess.quantum)*0.5) {
				if(runningprocess.agfactor>p.agfactor) {
					runningprocess.quantum+=q;
					for(int x=0;x<original.size();x++) {
						if(original.get(x).name.equals(runningprocess.name)) {
							original.get(x).quantum=runningprocess.quantum;
						}
					}
					
					runningprocess.remaining = i;
					readyqueue.add(runningprocess);
					runningprocess = p;
					printhistory(original);
					System.out.print(runningprocess.name+" running");
		            System.out.println();
					//updating waiting time & turnaround
					runningprocess.waitingTime += Math.abs(runningprocess.remaining - i);
					runningprocess.turnAround += Math.abs(runningprocess.remaining - i);
					readyqueue.remove(indx);
					q = runningprocess.quantum;
				}
			}
			}
			q--;
			runningprocess.burst-=1;
			Pair x = new Pair(runningprocess.name , runningprocess.waitingTime);
			waiting_time.add(x);
			
			
			order.add(runningprocess.name);
		}
		
		
			
				

		double avgWaiting = 0.0, avgTurnaround = 0.0;
		System.out.println("Order of Execution:");
		for (int i = 0; i < order.size(); i++) {
			if(i+1==order.size()) {
				System.out.print(order.get(i));
				break;
				}
			if(order.get(i).equals(order.get(i+1))) {
				continue;
			}
			else{
			System.out.print(order.get(i));
			if (i != order.size() - 1) System.out.print("->");
			}
		}
		System.out.println();
		for(int i =0; i<waiting_time.size();i++)
		{
			if(i+1==waiting_time.size())
			{
				System.out.print(waiting_time.get(i).x); System.out.print("->"); System.out.print(waiting_time.get(i).y);
				System.out.println();
				break;
			}
			if(waiting_time.get(i).x.equals(waiting_time.get(i+1).x))
				continue;
			else
				System.out.print(waiting_time.get(i).x); System.out.print("->cummualtive waiting time_for now "); System.out.print(waiting_time.get(i).y);
				System.out.println();
		}
		System.out.println();
		for (QProcess org : original) {
			System.out.println("Name: " + org.name + " Waiting Time: " + org.waitingTime + " Turn Around Time: " + org.turnAround);
			avgWaiting += org.waitingTime;
			avgTurnaround += org.turnAround;
		}
		avgTurnaround /= n;
		avgWaiting /= n;
		System.out.println("Average Waiting Time : " + avgWaiting);
		System.out.println("Average Turn Around Time : " + avgTurnaround);
	}
}