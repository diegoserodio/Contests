import java.util.*;
import java.lang.*;
import java.io.*;

public class ExchangingCards{

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int repetitions = scn.nextInt();
    ArrayList<Integer> results = new ArrayList<Integer>();
    for (int i = 0; i < repetitions; i++) {
      int a = scn.nextInt();
      int b = scn.nextInt();
      int minValue = minorValue(a, b);
      int maxValue = maximumValue(a, b);
      results.add(gdc(maxValue, minValue));
    }
    for(int i : results){
      System.out.println(i);
    }
  }

  public static int minorValue(int a, int b){
    if(a <= b) return a;
    else return b;
  }

  public static int maximumValue(int a, int b){
    if(a >= b) return a;
    else return b;
  }

  public static int gdc(int a, int b){
    int div = a;
    int temp = b;
    while(true){
      int rest = div%temp;
      if(rest == 0){
        break;
      }else{
        div = temp;
        temp = rest;
      }
    }
    return temp;
  }

}
