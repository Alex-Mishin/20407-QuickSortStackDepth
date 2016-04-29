import java.util.Random;

/**
 * 
 * 
 * @author Nir & Alex 
 * @version 0.1
 */
public class QuickSortTrace
{
    
    
    // instance variables
    private int[] _A;
    private int _n;
    

    /**
     * A constructor that build an array with n+1 unique int values,
     * from index 1 to n+1 (A[0] not used)
     */
    public QuickSortTrace(int n)
    {
        _A = new int[n+1];
        _n = n;
        for (int i = 1; i <= _n; i++) //fill array with numbers from 1 to n
            _A[i] = i;
        Random rand = new Random();
        for (int i = 1; i <= _n; i++) //shuffle array
            arraySwap(_A[i], rand.nextInt(_n)+1);
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
    
    /**
     * Find the index of the k-th element in array A,
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
        return randomizedSelect(a, 0, _n , k);
    }
    
    //implement method from book, p.154
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
	    return partitioner(a, p, r);
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
     * whilst selecting the pivot element to be the k-th element of the array
     * 
     * @param k     k-th element of the array to use as pivot for QuickSort
     * @return      maximum recursion depth   
     */
    public int quickSortStackTrace(int k)
    {
        return 0;
    }
}
