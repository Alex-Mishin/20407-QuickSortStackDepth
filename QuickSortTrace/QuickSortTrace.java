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
        for (int i = 0; i <= _n; i++) //print array
            System.out.println(i + ": "+ _A[i]);
    
    }

    private void arraySwap(int i ,int j)
    {
        int temp = _A[i];
        _A[i] = _A[j];
        _A[j] = temp;
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
        //inside note: be careful not to change the original array A
        return 0;
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
