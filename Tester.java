/**
 * A tester method for QuickSortTrace class 
 * 
 * @author Nir & Alex
 * @version 0.9
 */

public class Tester
{
    public static void main()
    {
        int[] n = {100,200,300};
        int k, depth;
        
        System.out.println("The QuickSort Stack Depth Tester:");
        for(int i=0; i<n.length; i++){
            //Test 1: k=1
            k = 1;
            QuickSortStackDepth test1 = new QuickSortStackDepth(n[i],1);
            depth = test1.quickSortStackTrace();
            System.out.println("Array size (n): "+n[i]+" k: 1   depth: "+depth);

            //Test 2: k=m/8
            k = 8;
            QuickSortStackDepth test2 = new QuickSortStackDepth(n[i],8);
            depth = test2.quickSortStackTrace();
            System.out.println("Array size (n): "+n[i]+" k: m/8 depth: "+depth);

            //Test 3: k=m/4
            k = 4;
            QuickSortStackDepth test3 = new QuickSortStackDepth(n[i],4);
            depth = test3.quickSortStackTrace();
            System.out.println("Array size (n): "+n[i]+" k: m/4 depth: "+depth);

            //Test 4: k=m/4
            k = 2;
            QuickSortStackDepth test4 = new QuickSortStackDepth(n[i],2);
            depth = test4.quickSortStackTrace();
            System.out.println("Array size (n): "+n[i]+" k: m/2 depth: "+depth);            

    }
        System.out.println("Done.");
    }
}