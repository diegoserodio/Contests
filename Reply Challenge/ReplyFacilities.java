//Costumer Object
class Costumer{
  public int x, y, reward;

  Costumer(int xCoord, int yCoord, int rewardValue){
    x = xCoord;
    y = yCoord;
    reward = rewardValue;
  }
}

//Office object
class Office{
  public int x, y;
  public int centerOfMass_x, centerOfMass_y;
  double xCoords, yCoords, numberOfCostumers;

  Office(int costumersQtn){
    xCoords = 0;
    yCoords = 0;
    numberOfCostumers = costumersQtn;
  }

  public void calculateCenterOfMass(Costumer costumer){
    xCoords = xCoords+costumer.x/numberOfCostumers;
    yCoords = yCoords+costumer.y/numberOfCostumers;
  }

  public void parseIntCenterOfMass(){
    centerOfMass_x = (int) xCoords;
    centerOfMass_y = (int) yCoords;
  }

  public void shiftCenterOfMass(){
    centerOfMass_y--;
  }
}
