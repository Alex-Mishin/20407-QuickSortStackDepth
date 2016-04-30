
/**
 * A tester method for QuickSortTrace class 
 * 
 * @author Nir & Alex
 * @version 0.1
 */
public class Tester
{
    public static void main()
    {
        for(int n=100; n <= 400; n=n*2)
        {
            for(int d=1; d <= 8; d=d*2)
            {
                QuickSortTrace test = new QuickSortTrace(n,d);
                System.out.println("for an array with length n="+n+" and a divisor equal to d="+ d +" the maximum recursion stack is "+test.quickSortStackTrace()); 
            }
        }
    }
}
