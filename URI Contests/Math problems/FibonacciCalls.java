import java.util.*;

public class FibonacciCalls{
  public static int x0 = 0;
  public static int x1 = 1;
  public static int num_call = 0;
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int testCases = scn.nextInt();
    for(int i = 0; i < testCases; i++){
      int n = scn.nextInt();
      long result = fib(n);
      System.out.printf("fib(%d) = %d calls = %d\n", n, num_call, result);
    }
  }

  public static long fib(int n){
    long prePrevious = x0;
    long previous = x1;
    long result = 0;
    switch (n) {
      case 1:
        result = x0;
        num_call = 1;
        break;
      case 2:
        result = x1;
        num_call = 2;
        break;
      default:
        num_call = 3;
        for(int i = 3; i <= n+1; i++){
          result = previous + prePrevious;
          prePrevious = previous;
          previous = result;
          num_call += 1;
        }
        break;
    }
    return result;
  }
}
