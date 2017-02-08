import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Created by Connor on 07/02/2017.
 * Accepted with time: 0.560s
 */

class Main{
    static Map<String, Author> authors = new HashMap<>();
    static List<String> pendingResultAuthors = new ArrayList<>();
    static final String ERDOS = "Erdos, P.";

    static void mapRelations(String[] authorsArray){
        List<Author> temp = new ArrayList<>();

        for(int i = 0; i < authorsArray.length; i += 2){
            String name = authorsArray[i].trim();
            if( i+1 < authorsArray.length ) name += ", " + authorsArray[i+1].trim();

            if(authors.get(name) == null){
                Author author = new Author(name);
                authors.put(name, author);
            }

            temp.add(authors.get(name));
        }

        for(int i = 0; i < temp.size(); i ++){
            for(int j = 0; j < i; j ++){
                Author authorI = temp.get(i);
                Author authorJ = temp.get(j);
                if(!authorI.nodes.contains(authorJ)){
                    authorI.nodes.add(authorJ);
                    authorJ.nodes.add(authorI);
                }
            }
        }
    }

    static void bfs(Author root){
        if(root != null){
            root.erdos = 0;
            root.visited = true;
            Queue<Author> queue = new LinkedList<>();
            queue.add(root);

            while(!queue.isEmpty()){
                Author currentAuthor = queue.remove();
                Author childAuthor = null;
                int distance = authors.get(currentAuthor.name).erdos;

                while((childAuthor = currentAuthor.getUnvisitedNode()) != null){
                    childAuthor.visited = true;
                    childAuthor.erdos = distance + 1;
                    queue.add(childAuthor);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numScenarios = Integer.parseInt(in.readLine().trim());
        for(int i = 0; i < numScenarios; i++){
            String[] numberLine = in.readLine().split(" ");
	    int numArticles = Integer.parseInt(numberLine[0]);
            int numAuthors = Integer.parseInt(numberLine[1]);

            for(int j = 0; j < numArticles; j++){
                String articleLine = in.readLine().trim();
                //Remove name of article
                String[] authorsArray = articleLine.split(":");
                //Split authors into array
                authorsArray = authorsArray[0].split(",");
                mapRelations(authorsArray);
            }

            for(int j = 0; j < numAuthors; j++){
                String authorName = in.readLine().trim();
                pendingResultAuthors.add(authorName);
            }

            System.out.printf("Scenario %d\n",i+1);
            Author root = authors.get(ERDOS);
            bfs(root);

            for(String pendingAuthor : pendingResultAuthors){
                Author result = authors.get(pendingAuthor);

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

class Author{
    List<Author> nodes = new ArrayList<>();
    String name;
    boolean visited;
    int erdos = -1;


    public Author(String name){
        this.name = name;
        this.visited = false;
    }

    public Author getUnvisitedNode(){
        if(!nodes.isEmpty()){
            for(Author node : nodes){
                if(node.visited == false) return node;
            }
        }
        return null;
    }

    public boolean equals(Object o){
        if(o instanceof Author){
            Author object = (Author)o;
            if((object.equals(this.name))) return true;
        }

        return false;
    }
}
