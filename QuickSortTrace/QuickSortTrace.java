import java.util.Random;

/**
 * 
 * 
 * @author Nir & Alex 
 * @version 0.2
 */
public class QuickSortTrace
{

    // instance variables
    private int[] _A;
    private int _n;
    private int _divisor = 2;

    /**
     * A constructor that build an array with n+1 unique int values,
     * from index 1 to n+1 (A[0] not used).
     * 
     * @param n int, length of array to construct
     */
    public QuickSortTrace(int n)
    {
        _A = new int[n+1];
        _n = n;
        for (int i = 1; i <= _n; i++) //fill array with numbers from 1 to n
            _A[i] = i;
        Random rand = new Random();
        for (int i = 1; i <= _n; i++) //shuffle array
            arraySwap(_A, _A[i], rand.nextInt(_n)+1);
    }
    
    /**
     * Allows choosing a custom pivot selection method for the quick sort algorithem,
     * where paramater d is the divisor.
     * for example if d = 2, then the algorithem will use the median of each sub-array as a pivot. 
     * 
     * @param n int, length of array to construct
     * @param d int, divisor for pivot selection
     */
    public QuickSortTrace(int n, int d)
    {
        _divisor = d;
        _A = new int[n+1];
        _n = n;
        for (int i = 1; i <= _n; i++) //fill array with numbers from 1 to n
            _A[i] = i;
        Random rand = new Random();
        for (int i = 1; i <= _n; i++) //shuffle array
            arraySwap(_A, _A[i], rand.nextInt(_n)+1);
    }

    /**
     * swap two cells by given positions, within an array.
     *
     * @param i The index of the first cell.
     * @param j The index of the second cell.
     */
    private void arraySwap(int[] a,int i ,int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < _A.length; i++)
            s += "["+i+"]: "+_A[i]+"\n";
        return s;
    }

    /**
     * Finds the index of the k-th element in array A,
     * for example, k=1 will return the index of the minimal element.
     * 
     * @param k     k-th element to return from smallest to largest
     * @return      the index of the k-th element
     */
    public int randomizedSelect(int k)
    {
        int[] a = new int[_n+1];
        for (int i = 1; i <= _n; i++)
            a[i] = _A[i];
        int selectedValue = randomizedSelect(a, 1, _n, k);
        System.out.println(selectedValue);
        for (int i = 1; i <= _n; i++)
            if (_A[i]==selectedValue)
                return i;
        return 0;
    }

    /**
     * Randomized Select Algorithemm.
     * The implementation is based on the pseudo code from the course book, p.154
     *
     * @param a An array which contains the subarray to be sorted.
     * @param p The subarray beginning index.
     * @param r The subarray end index.
     * @param i Size order of desired value.
     * @return the index of the i-th element
     */
    private int randomizedSelect(int[] a, int p, int r, int i)
    {
        if (p == r){
            // reached max recursion depth
            return a[p];
        }

        int q = randomizedPartitioner(a, p, r); // get the pivot using a randomized partitioner
        int k = q - p + 1;  // number of elements that are <= to the pivot value

        if (i == k){
            // the pivot is the answer
            return a[q];
        }
        else if (i < k){
            // the i-th smallest element is in the lower part of array a
            return randomizedSelect(a, p, q-1, i);
        }
        else{
            // the i-th smallest element is in the upper part of array a
            return randomizedSelect(a, q+1, r, i-k);
        }
    }

    /**
     * Partitions a sub-array using a randomly chosen pivot.
     * The implementation is based on the pseudo code from the course book, p.129
     *
     * @param a An array which contains the subarray to be partitioned.
     * @param p The subarray beginning index.
     * @param r The subarray end index.
     */
    private int randomizedPartitioner(int[] a, int p, int r)
    {
        // Randomly select an index between p and r (inclusive)
        Random rand = new Random();
        int i = rand.nextInt(r - p + 1) + p;
        //swap between the two so partitioner() will use the randomly chosen element as pivot
        arraySwap(a, r, i);
        return partition(a, p, r);
    }

    /**
     * Partitions a sub-array using the last cell as a pivot.
     * The implementation is based on the pseudo code from the course book, p.122
     *
     * @param a An array which contains the subarray to be partitioned.
     * @param p The subarray beginning index.
     * @param r The subarray end index.
     * @return An index
     */
    private int partition(int[] a, int p, int r)
    {
        int x = a[r]; //pivot
        int i = p - 1;
        //iterate from beginning of the array to the last cell excluding the pivot itself.
        for (int j = p; j < r-1; j++) {
            if (a[j] <= x) {
                i++;
                arraySwap(a, i, j);
            }
        }

        // Place the pivot value in its correct location in the array.
        arraySwap(a, i+1, r);
        return i + 1;
    }

    /**
     * Sorts the array A using the QuickSort algorithem, 
     * whilst selecting the pivot element according to the predefined divisor.
     * 
     * @return      maximum recursion depth   
     */
    public int quickSortStackTrace()
    {
        return quickSortStackTrace(1, _n, (int)Math.ceil((double)(_n-1)/_divisor)+1);
    }

    //Based on Quick Sort algorithem from book p.122, but whilst using the k-th element as pivot
    private int quickSortStackTrace(int p, int r, int k)
    {
        System.out.println("p="+p+" r="+r+" k="+k);
        if (p<r)
        {
            System.out.print(this);
            int pivot = randomizedSelect(k);    //select the k-th element
            System.out.println("the "+k+"-th element is at index: "+pivot);
            arraySwap(_A, pivot, r);            //move k-th element to the last cell, and partition
            int q = partition(_A, p, r);  
            //recursive call modified to find the new reletive k-th pivot according to divisor
            return max( quickSortStackTrace(p, q-1, (int)Math.ceil((double)(q-1)/_divisor)) + 1, 
                        quickSortStackTrace(q+1, r, q + (int)Math.ceil((double)(r-q+1)/_divisor)) + 1);     
        }
        //when condition is not met, maximum recursion depth has been reached (including lest level when no action was taken)
        System.out.println("p="+p+" r="+r+" k="+k+" finished!");
        return 1; 
    }
    
    //select the maximum between two integers
    private int max(int i, int j)
    {
        return i>j?i:j;
    }
}