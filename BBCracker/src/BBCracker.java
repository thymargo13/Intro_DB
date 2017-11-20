
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;

public class BBCracker   {
	
	private static Databases db = new Databases();
	
	public static void main(String[] args) throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try{
		Scanner keyboard = new Scanner(System.in);
		CrackerCommand cmd;
		String [] commdClass ={"CrackerReport","JokeReport","InsertCracker"};
		
		Process();
		
		while(true){
			
			System.out.println("---Birmingham Bespoke Cracker Company---");
	        System.out.println("[1]--- View Craker Report");
	        System.out.println("[2]--- View Joke Report");
	        System.out.println("[3]--- Insert New Craker");
	        System.out.println("[0]--- Exit System");
	        System.out.println();
	        
	        System.out.print("Select Function: ");
	        int option = keyboard.nextInt();
	        
	        if(option==0){
	        	System.exit(0);
	        }else{
	        	cmd = (CrackerCommand)Class.forName(commdClass[option-1]).newInstance();
				cmd.execute();
	        }
			

		}
		}catch(Exception e){System.out.println("Invalid Command"); System.exit(0);}
	}
	public static void Process() throws SQLException, IOException {
		db.CreateGift();
		db.CreateHat();
		db.CreateJoke();
		db.CreateCracker();
		db.insertData();
	}
	
	

}
