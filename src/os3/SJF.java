package os3;

import java.util.*;
import java.util.Scanner;

public class SJF {
	//private int num;
	 //private int mat[][];
   

   
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
	   // mat[0][3]=mat[0][1]+mat[0][2];
	   // for(int i=1; i<num; i++)
	   // {
	   //    mat[i][3]=mat[i-1][3]+mat[i][2];
	  //  }
	    //-----------------------
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
		
	   
	    //arrangeArrival(num,mat);
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
	
	public void CalculateAverageTime(int num , int mat[][])
	{
		
	    int sum=0;
	    int[] arr=new int[5]; 
       int y=0;
	   
	for(int i=1; i<6; i++){
	    
	  for(int j=0; j<num; j++)
	    {
	       sum+=mat[j][i];
	      // System.out.println(mat[j][i]);
	    }
	  arr[y]=sum/num;
	  sum=0;
	  y++;
	

	}
	
	System.out.println("AverageTime" + "\t\t" +arr[0]+"\t\t" +arr[1]+"\t\t" +arr[2]+ "\t\t" +arr[3] +"\t\t" +arr[4]);
    
	
	
	}
	
	public void completionTime(int num , int mat[][])
	{
	   CalculateFinishTime(num,mat);
	   CalculateTurnAroundTime(num,mat);
	   CalculateWaitingTime(num,mat);
	  
	}
	public void SJFGUI(int num,int mat[][]) {

	   

	  
	    System.out.println("...Enter the process ID...\\n");
        
	    for(int i=0; i<num; i++)
	    {
	    	System.out.println("...Process " + (i+1) + "...\\n" );
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
	    System.out.println("Before Arrange...\\n");
	    System.out.println("Process ID\\tArrival Time\\tBurst Time\\n");
	    
	    for(int i=0; i<num; i++)
	    {
	    	 System.out.println(mat[i][0]+"\t\t"+mat[i][1]+"\t\t"+mat[i][2]+"\n");
	    }

	    arrangeArrival(num,mat);
	    completionTime(num,mat);
	    
	    System.out.println("Final Result...\\n");
	    System.out.println("Process ID\\tArrival Time\\tBurst Time\\tFinish Time\\tTurnAround Time\\tWaiting Time\\t");
	   
	    for(int i=0; i<num; i++)
	    {
	    	System.out.println(mat[i][0]+"\t\t"+mat[i][1] +"\t\t"+mat[i][2] +"\t\t"+mat[i][3]+"\t\t"+mat[i][4]+"\t\t"+mat[i][5] );
	    }
	    CalculateAverageTime(num,mat);
	    
	    //arrangeBurst( num ,  mat);
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
