/*
Given a number n, print n-th Fibonacci Number. 

The Fibonacci numbers are the numbers in the following integer sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ……..
*/
class fibonacci{
	public static void main(String[] args) {
        int n = 10; 
        
        System.out.println("Fibonacci sequence up to " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
    }
    
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
}