import java.util.Random;

/**
 * @authors Nir Magnezi & Alex Mishin
 * @version 0.9
 */

public class QuickSortStackDepth
{
    private int[] _A;
    private int _divisor;

    //helper functions
    /**
     * Select the maximum between two integers.
     *
     * @param i The first integer.
     * @param j The second integers.
     */
    private int max(int i, int j)
    {
        return i>j?i:j;
    }

    /**
     * Swap two cells by given positions, within an array.
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
     * Print the array.
     */    
    public String toString()
    {
        String s = "";
        for (int i = 0; i < _A.length; i++)
            s += "["+i+"]: "+_A[i]+"\n";
        return s;
    }
    //end of helper functions

    /**
     * A constructor that build a shuffled array with n+1 unique int values,
     * from index 1 to n+1 (A[0] not used).
     * 
     * The constructor accepts a custom pivot selection for the quick sort algorithem,
     * where paramater d is the divisor.
     * for example if d = 2, then the algorithem will use the median of each sub-array as a pivot. 
     * 
     * @param n int, length of array to construct
     * @param d int, divisor for pivot selection
     */
    public QuickSortStackDepth(int n, int d)
    {
        _divisor = d;
        _A = new int[n+1];
        for (int i = 1; i <= _A.length-1; i++){
            //fill array with numbers from 1 to n
            _A[i] = i;
        }
        Random rand = new Random();
        for (int i = 1; i <= _A.length-1; i++){
            //shuffle the array
            arraySwap(_A, i, rand.nextInt(_A.length-1)+1);
        }
    }

    /**
     * Finds the index of the k-th element in array A,
     * for example, k=1 will return the index of the minimal element.
     * The function will make a copy of the array to refrain from any changes to the original array.
     *
     * @param k     k-th element to return from smallest to largest
     * @return      the index of the k-th element
     */
    public int randomizedSelect(int k)
    {
        int[] a = new int[_A.length];
        for (int i = 1; i <= _A.length-1; i++){
            //make a copy of the original array in order to avoid modifications on it
            a[i] = _A[i];
        }
        int selectedValue = randomizedSelect(a, 1, _A.length-1, k);
        for (int i = 1; i <= _A.length-1; i++){
            //search for the pivot value index in original array
            if (_A[i]==selectedValue)
                return i;
            }
        return 0;
    }

    /**
     * Randomized Select Algorithem.
     * The implementation is based on the pseudo code from the course book, p.154
     *
     * @param a An array which contains the subarray to be sorted.
     * @param p The subarray lower index.
     * @param r The subarray upper index.
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
     * @param p The sub-array lower index.
     * @param r The sub-array upper index.
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
     * @param p The sub-array lower index.
     * @param r he sub-array upper index.
     * @return The pivot index
     */
    private int partition(int[] a, int p, int r)
    {
        int x = a[r]; //pivot
        int i = p - 1;
        //iterate through the array cells, excluding the pivot itself
        for (int j = p; j <= r-1; j++) {
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
     * Sorts the array _A using the QuickSort algorithem, 
     * whilst selecting the pivot element according to the predefined divisor.
     *
     * @return maximum recursion depth
     */
    public int quickSortStackTrace()
    {
        int k = (int)Math.ceil((double)((_A.length-2))/_divisor)+1; //Array upper border round up integer division
        return quickSortStackTrace(1, (_A.length-1), k);
    }

    /**
     * Based on Quick Sort algorithem from book p.122, but whilst using the k-th element as pivot
     * 
     * @param p The sub-array lower index.
     * @param r he sub-array upper index.
     * 
     * @return maximum recursion depth
     */
    private int quickSortStackTrace(int p, int r, int k)
    {
        //System.out.println("p="+p+" r="+r+" k="+k);
        if (p<r)
        {
            int pivot = randomizedSelect(k); //select the k-th element
            arraySwap(_A, pivot, r); //move k-th element to the last cell, and partition
            int q = partition(_A, p, r);
            //recursive call modified to find the new reletive k-th pivot according to divisor
            //in addition, a modification was made to sum the amount of recursive calls
            return max(quickSortStackTrace(p, q-1, (int)Math.ceil((double)(q-1)/_divisor)) + 1, 
                       quickSortStackTrace(q+1, r, q + (int)Math.ceil((double)(r-q+1)/_divisor)) + 1);     
        }
        //when condition is not met, maximum recursion depth has been reached (including lest level when no action was taken)
        return 1; 
    }
}