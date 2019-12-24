package os3;

import java.util.*;

import java.util.Scanner;

public class SRTF {

	public void swap(int a, int b) 
	{ 
	    int temp = a; 
	    a = b; 
	    b = temp; 
	} 
	
	public void arrangeArrival(int num , int mat[][]) {
		java.util.Arrays.sort(mat, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Double.compare(a[1], b[1]);
		    }
		});
		
	}
	public void contextswitching(ArrayList<Integer>x,int mat[][] , int num) {
		int v=1;
		int []f=new int[num]; 
		for(int i=0 ; i<num ; i++) {
			f[i]=0;
		}
		for(int i=0 ; i<x.size()-1 ; i++) {
			if(x.get(i)!=x.get(i+1)) {
				//v++;
				f[x.get(i)]=v;
				v++;
			}
			if(i==x.size()-2) {
				f[x.get(i)]=v;
			}
		}
		for(int i=0 ; i<num ; i++) {
			mat[i][3]+=f[i];
		}
	}
	
	public void CalculateFinishTime(int num , int mat[][],ArrayList<Integer> x)
	{
	    int []rt=new int[num]; 
	    int []wt=new int[num];
	    
	    // Copy the burst time into rt[] 
	    for (int i = 0; i < num; i++) 
	        rt[i] = mat[i][2]; 
	  
	    int complete = 0, t = 0, minm = 1000; 
	    int shortest = 0, finish_time; 
	    boolean check = false; 
	  
	    // Process until all processes gets 
	    // completed 
	    
	    while (complete != num) { 
	  
	        // Find process with minimum 
	        // remaining time among the 
	        // processes that arrives till the 
	        // current time` 
	        for (int j = 0; j < num; j++) { 
	        	
	            if ((mat[j][1] <= t) && 
	            (rt[j] < minm) && rt[j] > 0) { 
	                minm = rt[j]; 
	                shortest = j; 
	               // x.add(j);
	                check = true;
	               
	            } 
	        } 
	        x.add(shortest);
	        if (check == false) { 
	            t++; 
	            continue; 
	        } 
	  
	        // Reduce remaining time by one 
	        rt[shortest]--; 
	  
	        // Update minimum 
	        minm = rt[shortest]; 
	        if (minm == 0) 
	            minm = 1000; 
	  
	        // If a process gets completely 
	        // executed 
	        if (rt[shortest] == 0) { 
	  
	            // Increment complete 
	            complete++; 
	            check = false; 
	  
	            // Find finish time of current 
	            // process 
	            finish_time = t + 1; 
	  
	            // Calculate waiting time 
	            wt[shortest] = finish_time ; 
	                       
	        } 
	        // Increment time 
	        t++; 
	    } 
	  
	   
	    for (int r = 0; r < num; r++)
	        mat[r][3] = wt[r]; 
	   
	 //   contextswitching(x,mat, num);
	    
	    
	}
	
	public void CalculateTurnAroundTime(int num , int mat[][])
	{

	    for(int i=0; i<num; i++)
	    {
	       mat[i][4]=mat[i][3]-mat[i][1];
	    }
	}
	
	public void CalculateWaitingTime(int num , int mat[][])
	{

	    for(int i=0; i<num; i++)
	    {
	       mat[i][5]=mat[i][4]-mat[i][2];
	    }
	}
	
	public void completionTime(int num , int mat[][],ArrayList<Integer> x)
	{
	   CalculateFinishTime(num,mat,x );
	   CalculateTurnAroundTime(num,mat);
	   CalculateWaitingTime(num,mat);
	  
	}
	public void SRTFGUI(ArrayList<Integer> x ) {

		System.out.println("Enter Number of Processes: ");
		Scanner ac = new Scanner(System.in);
		int num  = ac.nextInt();
		int[][] mat = new int[num][6];
	    for(int i=0; i<num; i++)
	    {
	    	System.out.println("Enter Process Id: ");
	    	Scanner a=new Scanner(System.in);
	    	int a1=a.nextInt();
	    	mat[i][0]=a1;
	    	
	    	System.out.println("Enter Arrival Time: ");
	    	Scanner b=new Scanner(System.in);
	    	int b1=b.nextInt();
	    	mat[i][1]=b1;
	    	
	    	System.out.println("Enter Burst Time: ");
	    	Scanner c=new Scanner(System.in);
	    	int c1=c.nextInt();
	    	mat[i][2]=c1;
	    }
	    arrangeArrival(num,mat);
	    completionTime(num,mat, x);
	    int avgWaiting = 0;
        int avgTurnaround = 0;
	    for (int i=0; i<num; i++) {	    	
			System.out.println("Name: " + mat[i][0] + " Waiting Time: " + mat[i][5] + " Turn Around Time: " + mat[i][4]);
			avgWaiting += mat[i][5];
			avgTurnaround += mat[i][4];
		}
	    System.out.println("Avarage WatingTime: "+avgWaiting/num);
	    System.out.println("Avarage TurnArroundTime: "+avgTurnaround/num);
	    System.out.println("Order of Execution :");
	    
	    for(int i=0 ; i<x.size()-1 ; i++) {
	    	if(x.get(i)!=x.get(i+1)) {
	    		int y=x.get(i)+1;
	    	System.out.print("p"+y+"->");
	    }
	    
	} 
	    int y=x.get(x.size()-1)+1;
	    System.out.print("p"+y);
    
	
	}
}
