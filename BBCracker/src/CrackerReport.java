import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CrackerReport extends CrackerCommand{
	
	private String customCid;
	private Databases db = new Databases();
	
	private Connection connect =null;
	private PreparedStatement pStmt =null;
	
	private String cid, gid,hid,jid, crackerName;
	private double saleprice, royalty, giftprice, hatprice;
	private int quantity;
	private String joke, giftDesption, hatDesption;
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
        try{
        System.out.print("Enter Cracker ID: ");
        customCid = br.readLine();
        
        SelectCracker(customCid);
        
        System.out.println("Cracker ID: " + cid);
        System.out.println("Cracker Name: " + crackerName);
        System.out.println("Gift Description: " + giftDesption);
        System.out.println("Joke: " + joke);
        System.out.println("Hat Description: " + hatDesption);
        System.out.println("Sale Price: £" + saleprice);
        System.out.println("Quantity Sold: " + quantity);
        System.out.println("Net Profit: £" + (saleprice-royalty-giftprice-hatprice));
        }catch(IOException ex){
        	System.out.println("Please Enter a valid Cracker ID!");
        }

		
	}
	public void SelectCracker(String cid){
		 try {
	            connect = db.getConnection();
	            
	            //Create Table
	            String preQueryStatement = "SELECT * FROM Cracker WHERE cid = ?";
	            pStmt = connect.prepareStatement(preQueryStatement);
	            pStmt.setString(1, customCid);
	            
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	        	
	        	while (rs.next()) {
	        	    cid = rs.getString("cid");
	        	    gid = rs.getString("gid");
	        	    hid = rs.getString("hid");
	        	    jid = rs.getString("jid");
	        	    crackerName = rs.getString("name");
	        		saleprice = rs.getDouble("saleprice");
	        		quantity = rs.getInt("quantity");
	        	}
	            
	            pStmt.close();
	            db.CloseConnection(connect);
	        } catch (SQLException ex) {
	            while (ex != null) {
	                ex.printStackTrace();
	                ex = ex.getNextException();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	

}
