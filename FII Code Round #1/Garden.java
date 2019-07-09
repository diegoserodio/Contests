import java.util.*;
import java.lang.*;
import java.io.*;

class Garden {
  public static ArrayList<Integer> xCoord = new ArrayList<Integer>();
  public static ArrayList<Integer> yCoord = new ArrayList<Integer>();
  public static int x1 = 0, x2 = 0, x3 = 0, x4 = 0, y1 = 0, y2 = 0, y3 = 0, y4 = 0;

	public static void main (String[] args) throws java.lang.Exception {
	  Scanner in = new Scanner(System.in);

		int pointsQtn = in.nextInt();
		int pointsWanted = in.nextInt();

		for(int i = 0; i < pointsQtn; i++){
      xCoord.add(in.nextInt());
      yCoord.add(in.nextInt());
		}

    findCorners(xCoord, yCoord);
    showCornes();
	}

	public static void findCorners(ArrayList<Integer> xValues, ArrayList<Integer> yValues){
	    double minDistMain = (Math.pow(200000, 2))*2, maxDistMain = 0, minDistSec = (Math.pow(200000, 2))*2, maxDistSec = 0;

	    for(int i = 0; i < xValues.size(); i++){
          int xMainCoord = xValues.get(i);
          int yMainCoord = yValues.get(i);
          int xSecCoord = 200000 - xValues.get(i);
          int ySecCoord = yValues.get(i);
          double tempMain = Math.pow(xMainCoord, 2) + Math.pow(yMainCoord, 2);
          double tempSec = Math.pow(xSecCoord, 2) + Math.pow(ySecCoord, 2);

          if(tempMain <= minDistMain){
            minDistMain = tempMain;
            x1 = xValues.get(i);
            y1 = yValues.get(i);
          }
          else if(tempMain >= maxDistMain){
            minDistMain = tempMain;
            x3 = xValues.get(i);
            y3 = yValues.get(i);
          }

          if(tempSec <= minDistSec){
            minDistSec = tempSec;
            x2 = xValues.get(i);
            y2 = yValues.get(i);
          }
          else if(tempSec >= maxDistSec){
            maxDistSec = tempSec;
            x4 = xValues.get(i);
            y4 = yValues.get(i);
          }
      }
	}

  public static void showCornes(){
    System.out.println("");
    System.out.println("////////");
    System.out.print(x1);
    System.out.print("  ");
    System.out.println(y1);
    System.out.println("///");
    System.out.print(x2);
    System.out.print("  ");
    System.out.println(y2);
    System.out.println("///");
    System.out.print(x3);
    System.out.print("  ");
    System.out.println(y3);
    System.out.println("///");
    System.out.print(x4);
    System.out.print("  ");
    System.out.println(y4);
    System.out.println("////////");
    System.out.println("");
  }
}
