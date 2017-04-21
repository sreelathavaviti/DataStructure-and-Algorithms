/**
 * File Name: SlistMergeSort.java 
 * Sort int slist using Merge Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistMergeSort.java
 */

class SlistMergeSort extends SlistSort{
 
  @Override
  protected void sort(boolean ascend) {
    //WRITE CODE HERE
	int size = a.size();
	IntSlist2 t = new IntSlist2();
	mergeSortR(0, size, t);
  }
  
  private void mergeSortR(int start, int end, IntSlist2 t) {
	int mid = (end - start)/2 + start;
	if(end - start > 1){
		numRecursion++;
		mergeSortR(start,mid,t);
		numRecursion++;
		mergeSortR(mid,end,t);
		merge(start,mid,end,t);
	}
  }

  private void merge(int start, int mid, int end, IntSlist2 t) {
	int i = start;
	int j = mid;
	int k = 0;
	while (i < mid && j < end) {
		numCompare++;
		numSwap++;
		if(getElement(i) < getElement(j)) {
			addElement(t,i,k);
			i++;
			k++;
		}else {
			addElement(t,j,k);
			j++;
			k++;
		}
		while( i < mid) {
			numSwap++;
			addElement(t,i,k);
			i++;
			k++;
		}
		while( j < end) {
			addElement(t,j,k);
			j++;
			k++;
		}
		a.first = t.first;
	}
  }

  private void addElement(IntSlist2 t, int i, int position) {
	    int data = getElement(i);
	  	node newNode = null;
	    if (t.first == null) {
	    	t.add(data,position, newNode);
	    }else if (position == 0) {
	    		newNode = new node(data,position);
		        newNode.next = t.first;
		        t.first = newNode;
		}
		else {
			newNode = new node(data,position);
		    node prev = null;
		    node current = t.first;
		    int j = 0;
		    while (current != null && j < position) {
		        prev = current;
		        current = current.next;
		        j++;
		    }
		    prev.next = newNode;
		}
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
    System.out.println("SlistMergeSort.java");
    SlistMergeSort u = new SlistMergeSort() ;
    u.testBench();
  }
}

