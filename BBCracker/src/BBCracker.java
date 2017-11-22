
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;

public class BBCracker {

	private static Databases db = new Databases();

	public static void main(String[] args)
			throws SQLException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub

		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		CrackerCommand cmd;
		String[] commdClass = { "ExitCommand", "CrackerReport", "JokeReport", "InsertCracker" };

		Process();
		

		while (true) {
			try {
			System.out.println("---Birmingham Bespoke Cracker Company---");
			System.out.println("[1]--- View Craker Report");
			System.out.println("[2]--- View Joke Report");
			System.out.println("[3]--- Insert New Craker");
			System.out.println("[0]--- Exit System");
			System.out.println();
			System.out.print("Select Function: ");
			
			int option = Integer.parseInt(br.readLine());
			//System.out.println(option);
			cmd = (CrackerCommand) Class.forName(commdClass[option]).newInstance();
			cmd.execute();
			
			System.out.println();
			}catch(Exception e) {
				e.getMessage();
				System.out.println("Please Enter 0 - 3 for selecting function");
			}
		}

	}

	public static void Process() throws SQLException, IOException {
		// db.CreateGift();
		// db.CreateHat();
		// db.CreateJoke();
		// db.CreateCracker();
		db.insertData();
	}

}
