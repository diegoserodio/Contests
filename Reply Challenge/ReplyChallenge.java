import java.io.*;
import java.util.*;

public class ReplyChallenge{
  public static HashMap<Character, Integer> tileCost = new HashMap<Character, Integer>();
  public static int[][] lowResPath, connectedNodes;
  public static char[][] map;
  public static double[][] lowResMap;
  public static int[] officeLowResCoordinates = new int[2];
  public static int[] costumeLowResCoordinates = new int[2];
  public static Costumer costumer[];
  public static Office office;

  public static void main(String[] args) {
        String fileName = "3_budapest.txt"; //Defines which input file to use
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Create map HashMap
            char tiles[] = {'#', '~', '*', '+', 'X', '_', 'H', 'T'};
            int costs[] = {1000, 800, 200, 150, 120, 100, 70, 50};
            populateHashMap(tiles, costs);

            //Read first line of the txt file
            line = bufferedReader.readLine();

            //Get information
            int[] infos = parseToIntArray(line, 4);
            int width = infos[0];
            int height = infos[1];
            int numberOfCostumers = infos[2];
            int numberOfOffices = infos[3];

            //Create costumer objects
            costumer = new Costumer[numberOfCostumers];
            for(int i = 0; i < numberOfCostumers; i++){
              String getParameters = bufferedReader.readLine();
              int[] parameters = parseToIntArray(getParameters, 3);
              costumer[i] = new Costumer(parameters[0], parameters[1], parameters[2]);
            }

            //Generate char matrix
            map = new char [height][width];
            for(int i = 0; i < height; i ++){
                String mapLine = bufferedReader.readLine();
                char tileArray[] = parseToCharArray(mapLine, width);
                for(int j = 0; j < width; j++){
                  map[i][j] = tileArray[j];
                }
            }

            //Create center of mass office
            office = new Office(numberOfCostumers);
            for(int i = 0; i < numberOfCostumers; i++){
              office.calculateCenterOfMass(costumer[i]);
            }
            office.parseIntCenterOfMass();
            while(true){
              if(map[office.centerOfMass_y][office.centerOfMass_x] != '#'){
                break;
              }else{
                office.shiftCenterOfMass();
              }
            }

            //Get the side of the square that the map is going to be divided into
            int squareSide = ldc(width, height, 8);

            //Generate lower resolution map
            generateLowerResMap(height, width, squareSide);

            //Find closest costumer
            double[][] officeToCostumer = findClosestCostumer(numberOfOffices);

            //Find low resolution squares which office and costumer are located in
            for(int i = 0; i < 2; i++){
              officeLowResCoordinates[i] = (int) officeToCostumer[0][i]/squareSide;
            }
            for(int i = 0; i < 2; i++){
              costumeLowResCoordinates[i] = (int) officeToCostumer[1][i]/squareSide;
            }

            //Apply A* to the low resolution map
            lowResPath = aStar(officeLowResCoordinates, costumeLowResCoordinates);

            //printFocusedMap(height, width, squareSide, officeLowResCoordinates, costumeLowResCoordinates);

            //Close the file
            bufferedReader.close();
        }

        //DEBUGGING: ERROS WHILE OPENING TXT FILE
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
        }
  }

  public static int[][] aStar(int[] start, int[] target){
    double miniumHeuristic = Math.pow(10, 6);
    int index = 0;
    int closestNode = 0;
    int[][] path = new int[(lowResMap.length)*(lowResMap[0].length)][2];
    for(int i = 0; i < 2; i++){
      path[0][i] = officeLowResCoordinates[i];
    }
    while(true){
      connectedNodes = getConnectedNodes(path[index]);
      for(int i = 0; i < 4; i++){
        double heuristic = getHeuristicDistance(connectedNodes[i], target);
        if(heuristic < miniumHeuristic){
          miniumHeuristic = heuristic;
          closestNode = i;
        }
      }
      index++;
      path[index][0] = connectedNodes[closestNode][0];
      path[index][1] = connectedNodes[closestNode][1];
      if(path[index][0] == target[0] && path[index][1] == target[1]){
        break;
      }
    }
    System.out.printf("Starting node -> %d, %d\nNext node -> %d, %d\nTarget node -> %d, %d\n", start[0], start[1], connectedNodes[closestNode][0], connectedNodes[closestNode][1], target[0], target[1]);
    return path;
  }

  public static double getHeuristicDistance(int[] nextNode, int[] target){
    double heuristicCost = lowResMap[nextNode[1]][nextNode[0]];
    double distanceToTarget = Math.sqrt(Math.pow(nextNode[0]-target[0], 2)+Math.pow(nextNode[1]-target[1], 2));
    return distanceToTarget+heuristicCost;
  }

  public static int[][] getConnectedNodes(int[] node){
    int[][] result = new int[4][2];
    result[0][0] = node[0];
    result[0][1] = node[1]-1;
    result[1][0] = node[0]-1;
    result[1][1] = node[1];
    result[2][0] = node[0]+1;
    result[2][1] = node[1];
    result[3][0] = node[0];
    result[3][1] = node[1]+1;
    return result;
  }

  public static double[][] findClosestCostumer(int numberOfOffices){
    double[][] coordinates = new double[2][2];
    coordinates[0][0] = office.centerOfMass_x; coordinates[0][1] = office.centerOfMass_y;
    double minDistance = Math.pow(10, 6), distance;
    for(int i = 0; i < numberOfOffices; i++){
      distance = Math.sqrt(Math.pow(office.centerOfMass_x-costumer[i].x, 2) + Math.pow(office.centerOfMass_y-costumer[i].y, 2));
      if(distance < minDistance){
        minDistance = distance;
        coordinates[1][0] = costumer[i].x; coordinates[1][1] = costumer[i].y;
      }
    }
    return coordinates;
  }

  public static void generateLowerResMap(int height, int width, int squareSide){
    lowResMap = new double[height/squareSide][width/squareSide];
    double average = 0;
    for(int i = 0; i < height/squareSide; i++){
      for(int j = 0; j < width/squareSide; j++){
        for(int y = 0; y < squareSide; y++){
          for(int x = 0; x < squareSide; x++){
            average = average + tileCost.get(map[y+(squareSide*i)][x+squareSide*j]);
          }
        }
        average = Math.floor(average/Math.pow(squareSide, 2));
        lowResMap[i][j] = average;
      }
    }
  }

  public static void populateHashMap(char tiles[], int costs[]){
    for(int i = 0; i < tiles.length; i++){
      tileCost.put(tiles[i], costs[i]);
    }
  }

  public static int ldc(int a, int b, int minDivisor){
    int max = a, min = b, div = 0;
    if(b > a){
      max = b;
      min = a;
    }
    for(int i = minDivisor; i < max; i++){
      if(a%i == 0 && b%i == 0){
        div = i;
        break;
      }
    }
    return div;
  }

  public static void printFocusedMap(int height, int width, int squareSide, int[] officeCoords, int[] costumerCoords){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        if(j >= officeCoords[0]*squareSide && j <= officeCoords[0]*squareSide+9 && i >= officeCoords[1]*squareSide && i <= officeCoords[1]*squareSide+9
        || j >= costumerCoords[0]*squareSide && j <= costumerCoords[0]*squareSide+9 && i >= costumerCoords[1]*squareSide && i <= costumerCoords[1]*squareSide+9){
          System.out.print(map[i][j]);
        }
        else{
          System.out.print(" ");
        }
      }
      System.out.print("\n");
    }
  }

  public static void printMap(int height, int width, int numberOfCostumers, Office office){
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        String value = " ";
        for(int k = 0; k < numberOfCostumers; k++){
          if(j == costumer[k].x && i == costumer[k].y){
            value = "C";
            break;
          }
          else if(j == office.centerOfMass_x && i == office.centerOfMass_y){
            value = "O";
            break;
          }
        }
        System.out.print(value);
      }
      System.out.print("\n");
    }
    System.out.printf("Coordenadas do centro de massa: %d, %d\n", office.centerOfMass_x, office.centerOfMass_y);
  }

  public static char[] parseToCharArray(String line, int size){
    char[] result = new char[size];
    for(int i = 0; i < size; i++){
      result[i] = line.charAt(i);
    }
    return result;
  }

  public static int[] parseToIntArray(String info, int qtn){
    String[] getInfo = info.split(" ");
    int[] result = new int[qtn];
    for(int i = 0; i < qtn; i++){
      result[i] = Integer.parseInt(getInfo[i]);
    }
    return result;
  }
}
