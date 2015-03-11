import java.io.BufferedReader;
import java.io.InputStreamReader;


public class TP2a {
  private static BufferedReader br;
	private static String tabWords[];
  private static String auxString[];
  private static BinaryTreeNode startNode;



	public static void main(String[] args){

		br = new BufferedReader(new InputStreamReader(System.in));
    int howManyTimes = 0;
    int action = 0;
    int canI = 0;
    auxString = new String[2];

		while((tabWords = receiveWords()) != null){
			for(String t : tabWords){
          if(t.equals("PASS")){
            action = 1;
            howManyTimes = 2;
          }
          else if(t.equals("STATUS")){
            howManyTimes = 1;
            action = 2;
          }
          else if(t.equals("UNFLAG")){
            howManyTimes = 1;
            action = 3;
          }
          else if( action != 0 && howManyTimes != 0){
              auxString[howManyTimes-1] = t;
              howManyTimes--;
          }


          if(action == 1 && howManyTimes == 0){
            passAction();
            action = 0;
          }
          else if(action == 2 && howManyTimes == 0){
            statusAction();
            action = 0;
          }
          else if(action == 3 && howManyTimes == 0){
            unflagAction();
            action = 0;
          }
			}
		}
	}

	public static String[] receiveWords(){
		try {
			String s = br.readLine();
			if(!s.equals("")) return s.split(" ");
			else return null;
		} catch (Exception e) {}
		return null;
	}

  public static void passAction() {
    BinaryTreeNode dataNode = new BinaryTreeNode();
    dataNode.licensePlate = auxString[1];
    dataNode.state = auxString[0];
    dataNode.times = 1;

    if(startNode == null){
      startNode = dataNode;
    }
    else{
      searchTreeNode(startNode, dataNode,1);
    }

  }

  public static void statusAction() {
    BinaryTreeNode dataNode = new BinaryTreeNode();
    dataNode.licensePlate = auxString[0];
    if(startNode == null){
      System.out.println(dataNode.licensePlate + " " + "NO RECORD");
    }
    else{
      searchTreeNode(startNode, dataNode,2);
    }
  }


  public static void unflagAction() {
    BinaryTreeNode dataNode = new BinaryTreeNode();
    dataNode.licensePlate = auxString[0];
    if(startNode == null){

    }
    else{
      searchTreeNode(startNode, dataNode,3);
    }

  }

  static void searchTreeNode(BinaryTreeNode startNode,BinaryTreeNode dataNode,int action){

    if(startNode.licensePlate.compareTo(dataNode.licensePlate) == 0){
      if(action == 1){
        startNode.times++;
        startNode.state = dataNode.state;
        return;
      }
      else if(action == 2){
        System.out.println(startNode.licensePlate + " " + startNode.times + " " + startNode.state);
      }
      else if(action == 3){
        startNode.state = "R";

      }
    }
    else if (startNode.licensePlate.compareTo(dataNode.licensePlate) > 0){
      if(startNode.rightNode == null){
        if(action == 1){
        startNode.rightNode = dataNode;
        return;
        }
        else if(action == 2){
          System.out.println(dataNode.licensePlate + " NO RECORD");
          return;
        }
      }
      else{
        searchTreeNode(startNode.rightNode,dataNode,action);
      }
      return;
    }
    else if (startNode.licensePlate.compareTo(dataNode.licensePlate) < 0){
      if(startNode.leftNode == null){
        if(action == 1){
          startNode.leftNode = dataNode;
          return;
        }
        else if(action == 2){
          System.out.println(dataNode.licensePlate + " NO RECORD");
          return;
        }
      }
      else{
        searchTreeNode(startNode.leftNode,dataNode,action);
      }
      return;
    }
  }

}

class BinaryTreeNode{
  String state;
  String licensePlate;
  int times;
  BinaryTreeNode leftNode;
  BinaryTreeNode rightNode;
  BinaryTreeNode parent;
}
