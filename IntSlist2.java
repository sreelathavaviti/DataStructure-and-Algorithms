

/**
 * File Name: IntSlist2.java Slist of int
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java
 */
import RandomInit.IntUtil;
public class IntSlist2 {
  /*
   * protected data members
   */
  protected node first;
  //Cannot add last
  //Cannot add size
  //Cannot add anything here
  protected static final IntUtil u = new IntUtil();

   protected class node {
    //Cannot change anything in this class
    protected final int d; //You cannot change d or t
    protected final int t ;//Used for testing stable sort
    protected node next; //You should manipulate only next

    protected node(int x,int y) {
      d = x;
      t = y ;
      next = null;
    }
  }

  public IntSlist2() {
    first = null;
  }

  /*
   * Returns the number of elements in this list.
   */
  public int size() {
    //WRITE CODE
	 node n = first;
	 int size = 0;
	 while( n != null) {
		 n = n.next;
		 size++;
	 }
	 return size;
  }

  /*
   * Appends int x to the end of this list.
   */
  public node add(int x,int y, node last) {

    node n = new node(x, y);

    if (first != null) {
    	last.next = n;
    } else {
    	first = n;
    }
    last = n;
	return n;
  }

  private void pLn(String string) {
	// TODO Auto-generated method stub
	System.out.println(string);
	pLn();
  }
  
  public void reverse() {
		// TODO Auto-generated method stub
	    node prev   = null;
	    node current = first;
	    node next;
	    while (current != null)
	    {
	        next  = current.next;  
	        current.next = prev;   
	        prev = current;
	        current = next;
	    }
	    first = prev;
  }

  public void pLn() {
		// TODO Auto-generated method stub
	node n = first;
	while(n != null) {
		System.out.print(n.d);
		if(n.next == null) {
			System.out.print("->NIL");
		}else {
			System.out.print("->");
		}
		n = n.next;
	}
	System.out.println("");
  }

  /*
   * 0 1 2 is ok 1 0 2 is not ok
   * CANNOT CHANGE ROUTINE BELOW
   */
  public void assertSlistInAscending() {
    node t = first;
    if (t != null) {
      node prev = t;
      node next = t.next;
      while (next != null) {
        if (next.d < prev.d) {
          u.myassert(false);
        }
        if (prev.d == next.d) {
          //assures stable sort
    //      u.myassert(prev.t < next.t);    
        }
        prev = next;
        next = next.next;
      }
    }
  }

  /*
   * 2 1 0 is ok 1 0 2 is not ok
   * CANNOT CHANGE ROUTINE BELOW
   */
  public void assertSlistInDescending() {
    node t = first;
    if (t != null) {
      node prev = t;
      node next = t.next;
      while (next != null) {
        if (next.d > prev.d) {
          u.myassert(false);
        }
        if (prev.d == next.d) {
          //assures stable sort
          //Because we are not allowed to change data
          u.myassert(prev.t > next.t);    
        }
        prev = next;
        next = next.next;
      }
    }
  }
  
  /* Factory method. Build an slist from an array */
  //CANNOT CHANGE ROUTINE BELOW
  public static IntSlist2 buildSlist(int [] a) {
    IntSlist2 l = new IntSlist2();
    int n = a.length ;
    node last = null ;
    for (int i = 0; i < n; ++i) {
      int x = a[i] ;
      last = l.add(x,i,last);
    }
    return l;
  }

  protected static void test1() {
    IntSlist2 l = new IntSlist2();
    node last = null ;
    for (int i = 0; i < 8; ++i) {
      last = l.add(i,i,last);
    }
    l.pLn("After adding 8 elements: ");
    int [] a = {9, 6, 7, 10};
    IntSlist2 l2 = IntSlist2.buildSlist(a) ;
    l2.pLn("l2: ");
  }

  protected static void testbed() {
    test1();
    System.out.println("---- test1 passed ------------");
  }

  public static void main(String[] args) {
    System.out.println("IntSlist.java");
    testbed();
    System.out.println("Done");
  }
}
