import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertCracker extends CrackerCommand {
	private String cid,gid,hid,jid,crackerName;
	private double saleprice;
	//public static final int MYSQL_DUPLICATE_PK = 2455;
	@Override
	public void execute() {
		try{
			System.out.print("Enter Cracker ID: ");
			 cid = br.readLine();
	        System.out.print("Enter Cracker Name: ");
	        crackerName = br.readLine();
	        System.out.print("Enter Hat ID: ");
	        hid = br.readLine();
	        System.out.print("Enter Gift ID: ");
	        gid = br.readLine();
	        System.out.print("Enter Joke ID: ");
	        jid = br.readLine();
	        System.out.print("Enter Saleprice: £");
	        saleprice = Double.parseDouble(br.readLine());
	        
	        insertCracker(cid, crackerName, hid, gid, jid);
	        
	        }catch(IOException ex){
	        	System.out.println("Please Enter a valid Cracker ID!");
	        }catch(NumberFormatException ex) {
	        	System.out.println("Please Enter Valid Number for saleprice!");
	        }
		
	}
	public void insertCracker(String id, String name, String hid, String gid, String jid){
		
	        //Insert Data
	        Connection connect = null;
	        PreparedStatement pStmt = null;
	        try {
	            connect = db.getConnection();
	            String insertHat = "INSERT INTO Cracker VALUES(?,?,?,?,?,?,?)";
	            pStmt = connect.prepareStatement(insertHat);
	            pStmt.setString(1, id);
                pStmt.setString(2, name);
                pStmt.setString(3, jid);
                pStmt.setString(4, gid);
                pStmt.setString(5, hid);
                pStmt.setDouble(6, saleprice);
                pStmt.setInt(7, 0);
	            int row = pStmt.executeUpdate();
	            if (row > 0) {
	            	System.out.println("New Cracker Inserted");
	            }
	            
	            pStmt.close();
	            db.CloseConnection(connect);
	}catch(SQLException ex){
		System.out.println(ex.getMessage());
		}catch(IOException ex){
			System.out.println("Invalid Input");
			}
	
	}

}
