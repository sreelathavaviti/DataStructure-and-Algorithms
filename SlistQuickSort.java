
/**
 * File Name: SlistQuickSort 
 * Sort int slist using Quick Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistQuickSort.java
 */

class SlistQuickSort extends SlistSort{
 
  @Override
  protected void sort(boolean ascend) { 
	  int size = a.size();
	  QS_R(0,size-1);
  }

  private void QS_R(int p, int r) {
	  if(p < r){
		  numRecursion++;
		  int j = Lomuto_Partition(p,r);
		  QS_R(p, j-1);
		  QS_R(j+1,r);
	  }	
  }


	private int Lomuto_Partition(int p, int r) {
		// TODO Auto-generated method stub
		int pivotPoint = p;
		int data;
		int pivotValue = getElement(pivotPoint);
		int j = pivotPoint;
		
		for(int i = j + 1; i <= r; ++i) {
			numCompare++;
			data = getElement(i);
			if(data < pivotValue) {
				j++;
				swapElement(i,j);
				numSwap++;
			}
			else if(data == pivotValue) {
				if( j != pivotPoint) {
					j++;
					swapElement(i,j);
					swapElement(j,pivotPoint);
					numSwap++;
				}
			}
		}
		swapElement(j,pivotPoint);
		numSwap++;
		pivotPoint = j;
		return pivotPoint;
	}
	private void swapElement(int x, int y) {
		int countX = 0;
		int countY = 0;
		if (x == y) return;
		 
		   node prevX = null; 
		   node currX = a.first;
		   while ((currX != null) && (countX != x))
		   {
			   countX++;
		       prevX = currX;
		       currX = currX.next;
		   }
		   node prevY = null;
		   node currY = a.first;
		   while ((currY != null) && (countY != y))
		   {
			   countY++;
		       prevY = currY;
		       currY = currY.next;
		   }

		   if (currX == null || currY == null)
		       return;
		 
		   if (prevX != null)
		       prevX.next = currY;
		   else 
		       a.first = currY;  
	
		   if (prevY != null)
		       prevY.next = currX;
		   else  
		       a.first = currX;
		   
		   node temp = currY.next;
		   currY.next = currX.next;
		   currX.next  = temp;
	}

	private int getElement(int index) {
		node current = a.first;
	    int count = 0; 
	    
	    while (current != null)
	    {
	        if (count == index)
	            return current.d;
	        count++;
	        current = current.next;
	    }
		return 0;
	}
public static void main(String[] args) {
    System.out.println("SlistQuickSort.java");
    SlistQuickSort u = new SlistQuickSort() ;
    u.testBench();
  }

}
