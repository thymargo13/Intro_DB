import DataBase.*;
import java.io.IOException;
import java.sql.SQLException;
import Command.*;

public class BBCracker   {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Databases db = new Databases();
		Command cmd;
		String [] commdClass ={"CrackerReport","JokeReport","InsertCracker"};
		db.CreateGift();
		db.CreateHat();
		db.CreateJoke();
		db.CreateCracker();
		db.insertData();
		
        System.out.println("---Birmingham Bespoke Cracker Company---");
        System.out.println("[1]--- View Craker Report");
        System.out.println("[2]--- View Joke Report");
        System.out.println("[3]--- Insert New Craker");
        System.out.println("[0]--- Exit System");
	}
	

}
