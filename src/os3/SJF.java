package os3;

import java.util.*;
import java.util.Scanner;

public class SJF {
	
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
	
	public void arrangeBurst(int num , int mat[][]) {
		java.util.Arrays.sort(mat, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Double.compare(a[2], b[2]);
		    }
		});
	}
	
	public void CalculateFinishTime(int num , int mat[][])
	{
	  
	    int m[][]=new int [num-1][6];
		arrangeArrival(num,mat);
		
		for(int i=0 ; i<num-1 ; i++) {
			for(int j=0; j<6 ; j++) {
				m[i][j]=mat[i+1][j];
			}
		}
		arrangeBurst(num-1,m);

		for(int i=0 ; i<num-1 ; i++) {
			for(int j=0; j<6 ; j++) {
				mat[i+1][j]=m[i][j];
			}
		}
		mat[0][3]=mat[0][1]+mat[0][2];
	    for(int i=1; i<num; i++)
	    {
	       mat[i][3]=mat[i-1][3]+mat[i][2];
	    }
		
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
	
	public void completionTime(int num , int mat[][])
	{
	   CalculateFinishTime(num,mat);
	   CalculateTurnAroundTime(num,mat);
	   CalculateWaitingTime(num,mat);
	  
	}
	public void SJFGUI() {
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
	    completionTime(num,mat);
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
	    for(int i=0; i<num; i++)
	    {
	    	if(i==num-1) {
	    		break;
	    		}
	    	System.out.print("p" +mat[i][0]+ "->");
	    	
	    }
	    System.out.print("p" +mat[num-1][0]);
	}

}