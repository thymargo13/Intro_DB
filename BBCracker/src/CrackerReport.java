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
//	private Databases db = new Databases();
//	
//	private Connection connect =null;
//	private PreparedStatement pStmt =null;
	
	private String cid, gid,hid,jokeid, crackerName;
	private double saleprice, royalty, giftprice, hatprice;
	private int quantity;
	private String joke, giftDesption, hatDesption;
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.print("Enter Cracker ID: ");
        try{
        customCid = br.readLine();
        ReportPrinting(customCid);
        
       
        }catch(IOException ex){
        	System.out.println("Please Enter a valid Cracker ID!");
        }
        
		
	}
	public boolean SelectCracker(String cid){
		boolean exist = false;
		 try {
	            connect = db.getConnection();
	            
	            //Create Table
	            String preQueryStatement = "SELECT * FROM Cracker WHERE cid = ?";
	            pStmt = connect.prepareStatement(preQueryStatement);
	            pStmt.setString(1, customCid);
	            
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	            while(rs.next())
	            	{
	            		exist = true;
		        	    gid = rs.getString("gid");
		        	    hid = rs.getString("hid");
		        	    jokeid = rs.getString("jid");
		        	    crackerName = rs.getString("name");
		        		saleprice = rs.getDouble("saleprice");
		        		quantity = rs.getInt("quantity");
		        		SelectGift(gid);
		        		SelectHat(hid);
		        		SelectJoke(jokeid);
		        	//	System.out.println(jokeid);
	            	}
		        
	            	
	            if(exist==false){System.out.println("Cracker ID does NOT exist!");}
	            	
	            
	        	
	        	
	            pStmt.close();
	            db.CloseConnection(connect);
	        } catch(SQLException ex) {
	            while (ex != null) {
	                ex.printStackTrace();
	                ex = ex.getNextException();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		 return exist;
	}
	public void SelectGift(String gid){
		
		 try {
	            connect = db.getConnection();
	            
	            //Create Table
	            String preQueryStatement = "SELECT * FROM Gift WHERE gid = ?";
	            pStmt = connect.prepareStatement(preQueryStatement);
	            pStmt.setString(1, gid);
	            
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	            while(rs.next())
	            	{   
	            	giftDesption = rs.getString("description");
	            	giftprice = rs.getDouble("price");   
	            	}
		       // System.out.println(giftDesption+giftprice);
		        pStmt.close();
	            db.CloseConnection(connect);
	        } catch(SQLException ex) {
	            while (ex != null) {
	                ex.printStackTrace();
	                ex = ex.getNextException();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		 
	}
	public void SelectHat(String hid){
		
		 try {
	            connect = db.getConnection();
	            
	            //Create Table
	            String preQueryStatement = "SELECT * FROM Hat WHERE hid = ?";
	            pStmt = connect.prepareStatement(preQueryStatement);
	            pStmt.setString(1, hid);
	            
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	            while(rs.next())
	            	{   
	            	hatDesption = rs.getString("description");
	            	hatprice = rs.getDouble("price");   
	            	}
		        //System.out.println(giftDesption+giftprice);
		        pStmt.close();
	            db.CloseConnection(connect);
	        } catch(SQLException ex) {
	            while (ex != null) {
	                ex.printStackTrace();
	                ex = ex.getNextException();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		 
	}
	public void SelectJoke(String jid){
		
		 try {
	            connect = db.getConnection();
	            
	            //Create Table
	            String preQueryStatement = "SELECT * FROM Joke WHERE jid = ?";
	            pStmt = connect.prepareStatement(preQueryStatement);
	            pStmt.setString(1, jid);
	            
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	            while(rs.next())
	            	{   
	            	joke = rs.getString("joke");
	            	royalty = rs.getDouble("royalty");   
	            	}
	            
		        pStmt.close();
	            db.CloseConnection(connect);
	        } catch(SQLException ex) {
	            while (ex != null) {
	                ex.printStackTrace();
	                ex = ex.getNextException();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		 
	}
	public void ReportPrinting(String cid){
		if(SelectCracker(cid)){
	        System.out.println();
	        System.out.println("---Cracker Report---");
	        System.out.println("Cracker ID: " + customCid);
	        System.out.println("Cracker Name: " + crackerName);
	        System.out.println("Gift Description: " + giftDesption);
	        System.out.println("Joke: " + joke);
	        System.out.println("Hat Description: " + hatDesption);
	        System.out.println("Sale Price: £" + saleprice);
	        System.out.println("Quantity Sold: " + quantity);
	        System.out.println("Net Profit: £" + String.format( "%.2f",(saleprice-royalty-giftprice-hatprice)));
	        System.out.println("---Cracker Report End---");
	        System.out.println();
		}
		
	}
	

}
