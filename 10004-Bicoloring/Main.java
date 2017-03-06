import java.io.*;
import java.util.*;

/**
 * Created by Connor on 06/03/2017.
 * Accepted with time: 
 */
class Main{
    
    public static boolean bicolourBfs(Node root){
        root.setVisited(true);
        root.setColour(0); //Set 1st to white
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node currentNode = queue.remove();
            int invertColour = (currentNode.getColour() + 1) % 2;

            for(Node node : currentNode.getConnected()){
                if(node.getColour() == currentNode.getColour()){
                    return false;
                }
                
                if(!node.isVisited()){
                    node.setColour(invertColour);
                    node.setVisited(true);
                    queue.add(node);
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) throws IOException{
        StringBuffer output = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
        
        while(!((line = in.readLine()).equals("0"))){
            int nodes = Integer.parseInt(line);
            Node[] nodeArray = new Node[nodes];
            
            for(int i = 0; i < nodes; i++){
                nodeArray[i] = new Node();
            }
            
            int edges = Integer.parseInt(in.readLine());
            
            for(int i = 0; i < edges; i++){
                line = in.readLine();
                String[] split = line.split(" ");
                int node1 = Integer.parseInt(split[0]);
                int node2 = Integer.parseInt(split[1]);
                
                nodeArray[node1].connectNode(nodeArray[node2]);
                nodeArray[node2].connectNode(nodeArray[node1]);
            }
            
            if(bicolourBfs(nodeArray[0])){
                output.append("BICOLORABLE.\n");
            }else{
                output.append("NOT BICOLORABLE.\n");
            }
        }
        
        System.out.printf(output.toString());
    }
}

class Node{
    private List<Node> connected = new ArrayList<>();
    private boolean visited = false;
    private int colour = -1; // 0 == white | 1 == black
    
    public Node(){}

    public boolean isVisited(){
        return this.visited;
    }
    
    public void setVisited(boolean visited){
        this.visited = visited;
    }
    
    public int getColour(){
        return this.colour;
    }
    
    public void setColour(int colour){
        this.colour = colour;
    }
    
    public void connectNode(Node node){
        this.connected.add(node);
    }
    
    public List<Node> getConnected(){
        return this.connected;
    }
}