

/**
 * File Name: PowerBall.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: IntUtil.java RandomInt.java PowerBall.java
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import RandomInit.*;
class PowerBallHash {
  /*
   * ALL PRIVATE DATA BELOW
   * CANNOT ADD ANY MORE MEMBER
   */
  private int[] winningNumber ;
  private int[] ticketNumber ;
  private long cash ;
  static private boolean display = true ;
  static final long jackpot = 100000000;
  static private IntUtil u = new IntUtil();
  
  private void printNumbers() {
    if (display) {
      System.out.println("winningNumber ") ;
      u.pLn(winningNumber);
      System.out.println("ticketNumber  ") ;
      u.pLn(ticketNumber);
    }
  }
  
  public long cash() {
    return cash ;
  }
  
  private void check() {
     //WRITE CODE HERE
     //YOU CAN WRITE AS MANY PRIVATE ROUTINES AS YOU NEED
	  HashSet<Integer> hm = new HashSet<Integer>();
	  int match = 0;
	  int w_pwr_ball_num = winningNumber[winningNumber.length-1];
	  int n_pwr_ball_num = winningNumber[ticketNumber.length-1];
	  
	  //add to hash table
	  for(int i = 0; i < ticketNumber.length - 1 ; i++) {
		  hm.add(ticketNumber[i]);
	  }
	  //find the number of matches
	  for(int i = 0; i < winningNumber.length - 1 ; i++) {
		  if(hm.contains(winningNumber[i])) {
			  match++;
		  }
	  }
	  //check how much cash
	  switch(match) {
	  case 1 :
		  if(w_pwr_ball_num == n_pwr_ball_num) {
			  cash = 4;
		  }
		  break;
	  case 2 :
		  if(w_pwr_ball_num == n_pwr_ball_num) {
			  cash = 7;
		  }
		  break;
	  case 3 :
		  if(w_pwr_ball_num == n_pwr_ball_num) {
			  cash = 100;
		  }
		  else
			  cash = 7;
		  break;
	  case 4 :
		  if(w_pwr_ball_num == n_pwr_ball_num) {
			  cash = 50000;
		  }
		  else
			  cash = 100;
		  break;
	  case 5 :
		  if(w_pwr_ball_num == n_pwr_ball_num) {
			  cash = jackpot;
		  }
		  else
			  cash = 100000;
		  break;
	  default :
		  if(w_pwr_ball_num == n_pwr_ball_num) {
			  cash = 4;
		  }
		  break; 
	  } 
	  printNumbers();
	  System.out.println("you won" + cash);
  }
  
  PowerBallHash(int[] w, int [] t) {
    winningNumber = w ;
    ticketNumber = t ;
    cash = 0 ;
    check() ;
  }
  
  private static void test1() {
	//CANNOT CHANGE BELOW
	    int[] w = {4,8,19,27,24,10} ;
	    {
	      int [] n= {4,8,19,27,24,10} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }
	    {
	      int [] n= {24,27,19,8,4,10} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }
	    {
	      int [] n= {24,27,19,8,4,5} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }
	    {
	      int [] n= {124,127,119,18,14,10} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }
	    {
	      int [] n= {124,127,119,18,14,5} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }
	    {
	      int [] n= {124,127,119,18,14} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }
	    {
	      int [] n= {124,124,19,119,18,14} ;
	      PowerBallHash x = new PowerBallHash(w,n);
	    }  
  }
  
  private static void testRandom() {
  //CANNOT CHANGE BELOW
    System.out.println("----------testRandom()  starts-------------") ;
    display = false ;
    int[] w = {4,8,19,27,24,10} ;
    int max = 1000000 ;
    long c = 0 ;
    System.out.println("Buying " + max + " tickets of worth " + max * 2 +"$") ;
    for (int i = 0; i < max; ++i) {    
      int [] n = u.generateRandomNumber(6,true,1,60);
      PowerBallHash x = new PowerBallHash(w,n); 
      if (x.cash() == jackpot) {
        System.out.println("Won Jacckpot") ;
      }
      c = c + x.cash();
    }
    long p = (c -(max*2)) ;
    System.out.println("Out of " + max + " times you win " + c + "$") ;
    if (p > 0) {
      System.out.println("Profit = " + p) ;
    }else {
      System.out.println("Loss = " + p) ;
    }
    System.out.println("----------testRandom()  ends-------------") ;
  }
  
  private static void testBench() {
  //CANNOT CHANGE BELOW
    test1() ;
  //  testRandom() ;
  }
  
  public static void main(String[] args) {
  //CANNOT CHANGE BELOW
    System.out.println("PowerBall.java");
    testBench();
    System.out.println("Done");
  }
}
