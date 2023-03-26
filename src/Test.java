import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> str = new ArrayList<String>();
		ArrayList<String> str2 = new ArrayList<String>();
		int size =0;
		try {
			File file = new File("karate_club_network.txt");
			Scanner d = new Scanner(file);
			while (d.hasNext()) {
				str.add(d.next()) ;
				str.add(d.next());
				size++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		Graph graph = new Graph(size);		
		for (int i = 0; i < str.size(); i+=2) {
			graph.addEdge(str.get(i), str.get(i+1));
		} 		
		System.out.println("Zachary Karate Club Network – The Highest Node for Betweennes and the value: ");
		graph.Betweenness();
		System.out.println("Zachary Karate Club Network – The Highest Node for Closeness and the value: ");
		graph.closennes();
		
		
		try {
			File file2 = new File("facebook_social_network.txt");
			Scanner s = new Scanner(file2);
			while (s.hasNext()) {
				str2.add(s.next()) ;
				str2.add(s.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		Graph graph2 = new Graph(2000);		
		for (int i = 0; i < str2.size(); i+=2) {
			graph2.addEdge(str2.get(i), str2.get(i+1));
		} 	
		System.out.println("Facebook Social Network - The Highest Node for Betweennes and the value ");
		graph2.Betweenness();
		System.out.println("Facebook Social Network - The Highest Node for Closeness and the value ");
		graph2.closennes();
	}
}
