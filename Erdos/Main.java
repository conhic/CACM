import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main{
	static Map<String, Node> authors = new HashMap<>();
	static List<String> pendingResultAuthors = new ArrayList<>();
	static final String ERDOS = "Erdos, P.";
	
	static void mapRelations(String[] authorsArray){
		for(int i = 0; i < authorsArray.length; i += 2){
			String name = authorsArray[i].trim() + ", " + authorsArray[i+1].trim();
			if(authors.get(name) == null){
				authors.put(name, new Node(name));
			}
		}
		
		for(int i = 0; i < authorsArray.length; i+=2){
			for(int j = 0; j < i; j+=2){
				Node nodeI = authors.get(authorsArray[i].trim() + ", " +authorsArray[i+1].trim());
				Node nodeJ = authors.get(authorsArray[j].trim() + ", " + authorsArray[j+1].trim());
				if(!nodeI.nodes.contains(nodeJ)){
					nodeI.nodes.add(nodeJ);
					nodeJ.nodes.add(nodeI);
				}
			}
		}
	}
	
	static void bfs(Node root){
		if(root != null){		
			root.erdos = 0;
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			
			while(!queue.isEmpty()){
				Node currentNode = queue.remove();
				Node childNode = null;
				int distance = authors.get(currentNode.name).erdos;
				
				while((childNode = currentNode.getUnvisitedNode()) != null){
					childNode.visited = true;
					childNode.erdos = distance + 1;
					queue.add(childNode);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		int numScenarios = Integer.parseInt(in.readLine().trim());
		for(int i = 0; i < numScenarios; i++){
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			int numArticles = Integer.parseInt(st.nextToken().trim());
			int numAuthors = Integer.parseInt(st.nextToken().trim());
			
			for(int j = 0; j < numArticles; j++){
				String articleLine = in.readLine().trim();
				//Remove name of article
				String[] authorsArray = articleLine.split(":");
				//Split authors into array
				authorsArray = authorsArray[0].split("\\,");
				mapRelations(authorsArray);
			}
			
			for(int j = 0; j < numAuthors; j++){
				String authorName = in.readLine().trim();
				pendingResultAuthors.add(authorName);
			}
			
			System.out.printf("Scenario %d\n",i+1);
			Node root = authors.get(ERDOS);
			bfs(root);

			for(String pendingAuthor : pendingResultAuthors){
				Node result = authors.get(pendingAuthor);
				
				if(result != null){
					int erdos = result.erdos;
					if(erdos != -1){
						System.out.printf("%s %d\n",pendingAuthor,erdos);
					}else{
						System.out.printf("%s infinity\n",pendingAuthor);
					}
				}else{
					System.out.printf("%s infinity\n",pendingAuthor);
				}
			}
			
			authors.clear();
			pendingResultAuthors.clear();
		}
	}
}

class Node{
	List<Node> nodes = new ArrayList<>();
	String name;
	boolean visited;
	int erdos = -1;
	
	
	public Node(String name){
		this.name = name;
		this.visited = false;
	}
	
	public Node getUnvisitedNode(){		
		if(!nodes.isEmpty()){
			for(Node node : nodes){
				if(node.visited == false) return node;
			}
		}
		return null;
	}
	
	public boolean equals(Object o){
		if(o instanceof Node){
			Node object = (Node)o;
			if((object.equals(this.name))) return true;
		}
		
		return false;
	}
}