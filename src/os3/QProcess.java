package os3;

public class QProcess {
	String name;
	int arrival;
	int burst;
	int priority;
	int quantum;
	int remaining;
	int waitingTime;
	int turnAround;
	int count;
	int agfactor;
	int notchangedBurst;
    
	public QProcess() {
		
	}
	public QProcess(String name, int arrival, int burst,int priority ,int remaining,int waitingTime, int turnAround) {
		this.name = name;
		this.arrival = arrival;
		this.burst = burst;
		this.priority = priority;
		this.remaining = remaining;
		this.waitingTime = waitingTime;
		this.turnAround = turnAround;
		count = 0;
		agfactor = 0;
		notchangedBurst = 0;
	}
}
