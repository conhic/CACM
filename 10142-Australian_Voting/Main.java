import java.util.*;
import java.io.*;

public class Main{
	public static List<Vote> votes;
	public static String[] candidates;
	public static final String DQ = "DQ";
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numCases = Integer.parseInt(in.readLine());
		in.readLine();
		
		for(int cases = 0; cases < numCases; cases++){
			//Read and map candidates to columns
			int numCandidates = Integer.parseInt(in.readLine());
			candidates = new String[numCandidates];
				
			for(int i = 0; i < numCandidates; i++){
				candidates[i] = in.readLine().trim();
			}
			
			//Initialize votes
			int numVotes = 0;
			votes = new LinkedList<>();

			String line;
			while ((line = in.readLine()) != null && !line.equals("")){
				List<Integer> priority = new LinkedList<>();
				StringTokenizer st = new StringTokenizer(line);
				
				for(int i = 0; i < numCandidates; i++){
					int count = Integer.parseInt(st.nextToken().trim());
					priority.add(count);
				}

				Vote vote = new Vote(priority);
				votes.add(vote);
				numVotes++;
			}
			
			//find the winners
			while(!calculateWinner(numCandidates, numVotes, cases, numCases));
		}
	}
	
	private static boolean calculateWinner(int numCandidates, int numVotes, int cases, int numCases){
		List<Integer> maxList = new LinkedList<>();
		List<Integer> minList = new LinkedList<>();
		int[] count = new int[numCandidates];

		//Count votes
		for(Vote vote : votes){
			//Candidate index starts at 1
			count[vote.getCandidate() - 1]++;
		}
		
		int max = count[0];
		int min = count[0];
		maxList.add(0);
		
		//Find min and max
		for(int i = 1; i < count.length; i++){
			//Replace max if winner
			if(count[i] >= max) {
				if(count[i] > max){
					max = count[i];
					maxList.clear();
				}
				maxList.add(i);
			}else if(count[i] <= min && !candidates[i].equals(DQ)){
				//Replace min if loser
				if(count[i] < min){
					min = count[i];
					minList.clear();
				}
				
				minList.add(i);
			}
		}
			
		//check if we have a winner
		if(max == min || ((double)max/numVotes > 0.5)){
			printWinners(maxList, cases, numCases);
			return true;
		}else{
			removeLosers(minList);
			return false;
		}
	}
	
	private static void printWinners(List<Integer> winners, int cases, int numCases){
		for(int i = 0; i < winners.size(); i++){
			System.out.printf(candidates[i]);
			if(i < winners.size() - 1){
				System.out.printf("\n");
			}
		}
		
		//Separate cases
		if(cases < numCases - 1){
			System.out.printf("\n");
		}
	}
	
	private static void removeLosers(List<Integer> remove){
		//Remove invalid Candidates from Votes
		for(Vote vote : votes){
			for(Integer index : remove){
				vote.removeCandidate(index);
			}
		}
	}
}

class Vote{
	private List<Integer> priority;

	public Vote(List<Integer> priority){
		this.priority = priority;
	}
	
	public int getCandidate(){
		return this.priority.get(0);
	}
	
	public void removeCandidate(Integer i){
		this.priority.remove(i);
	}
}