// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Module {
  public static ArrayList<Integer> multiples = new ArrayList<Integer>();
	public static void main (String[] args) throws java.lang.Exception {
	  Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int m = in.nextInt();

    for(int i = a; i <= b; i++){
      if(i%m == 0){
        multiples.add(i);
      }
    }

    int res = b;

    if(multiples.get(multiples.size()) <= b){
        res = multiples.get(multiples.size())-1;
    }
    
    System.out.println(res%m);
	}
}
