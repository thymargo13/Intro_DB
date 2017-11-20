
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;

public class BBCracker   {
	
	private static Databases db = new Databases();
	
	public static void main(String[] args) throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		CrackerCommand cmd;
		String [] commdClass ={"CrackerReport","JokeReport","InsertCracker"};
		
		//Process();
		
		while(true){
			
			System.out.println("---Birmingham Bespoke Cracker Company---");
	        System.out.println("[1]--- View Craker Report");
	        System.out.println("[2]--- View Joke Report");
	        System.out.println("[3]--- Insert New Craker");
	        System.out.println("[0]--- Exit System");
	        System.out.println();
	        
	        System.out.println("enter an integer");
	        int option = keyboard.nextInt();
	        

			cmd = (CrackerCommand)Class.forName(commdClass[option-1]).newInstance();
			cmd.execute();

		}
        
	}
	public static void Process() throws SQLException, IOException {
		
		
		db.CreateGift();
		db.CreateHat();
		db.CreateJoke();
		db.CreateCracker();
		db.insertData();
	}
	

}
