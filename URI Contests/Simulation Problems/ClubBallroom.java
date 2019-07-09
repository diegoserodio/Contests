import java.util.*;
import java.lang.*;
import java.io.*;

public class ClubBallroom{
  public  static Scanner scn = new Scanner(System.in);

  public static double width, height;

  public static void main(String[] args) {
    getParameters();
  }

  public static void getParameters(){
    width = 100*scn.nextDouble();
    height = 100*scn.nextDouble();
  }
}
