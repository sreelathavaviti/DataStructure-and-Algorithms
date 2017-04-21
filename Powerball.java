
/**
 * File Name: PowerBall.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: IntUtil.java RandomInt.java PowerBall.java
 */
import java.util.Random;
import RandomInit.*;
class PowerBall {
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
	  int[] cashAmount = {4,4,7,7,100,50000,1000000};
	  int count = 0;
	  for (int i = 0; i < ticketNumber.length-1;i++) {
		  for (int j= 0; j < winningNumber.length-1; j++) {
			  if(winningNumber[j] == ticketNumber[i]) {
				  count++;
				  if(winningNumber[winningNumber.length-1] == ticketNumber[ticketNumber.length-1]) {
					  if(count == 1) {
						  cash = cashAmount[count];
					  }
					  else {
						  cash = cashAmount[count+1];
					  }
				  }
				  else if(count != 1 && count != 2) {
					  cash = cashAmount[count];
				  }
				  break;
			  }
		  }
	  } 
	  if (count == winningNumber.length-1) {
		  cash = cashAmount[count+1];
	  }
	  if((winningNumber[winningNumber.length-1] == ticketNumber[ticketNumber.length-1])) {
		  if(count == 0) {
			  cash = cashAmount[count];
		  }
		  count++;
	  }
	  if(count == winningNumber.length) {
		  cash = jackpot;
	  }  
	  printNumbers();
	  if(cash == jackpot) {
		  System.out.println("You won jackpot");
	  }
	  else {		
		  System.out.println("You won : " + cash);
	  }
  }
  
  PowerBall(int[] w, int [] t) {
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
	      PowerBall x = new PowerBall(w,n);
	    }
	    {
	      int [] n= {24,27,19,8,4,10} ;
	      PowerBall x = new PowerBall(w,n);
	    }
	    {
	      int [] n= {24,27,19,8,4,5} ;
	      PowerBall x = new PowerBall(w,n);
	    }
	    {
	      int [] n= {124,127,119,18,14,10} ;
	      PowerBall x = new PowerBall(w,n);
	    }
	    {
	      int [] n= {124,127,119,18,14,5} ;
	      PowerBall x = new PowerBall(w,n);
	    }
	    {
	      int [] n= {124,127,119,18,14} ;
	      PowerBall x = new PowerBall(w,n);
	    }
	    {
	      int [] n= {124,124,19,119,18,14} ;
	      PowerBall x = new PowerBall(w,n);
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
      PowerBall x = new PowerBall(w,n); 
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
    testRandom() ;
  }
  
  public static void main(String[] args) {
  //CANNOT CHANGE BELOW
    System.out.println("PowerBall.java");
    testBench();
    System.out.println("Done");
  }
}
