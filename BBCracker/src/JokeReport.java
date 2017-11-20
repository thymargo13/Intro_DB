import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JokeReport extends CrackerCommand{
	private String jokeID,joke;
	private double royalty;
	private int times;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try{
	        System.out.print("Enter Joke ID: ");
	        jokeID = br.readLine();
	        
	        if(SelectJoke(jokeID)){
	        System.out.println();
	        System.out.println("---Joke Report---");
	        System.out.println("Joke ID: " + jokeID);
	        System.out.println("Joke: " + joke);
	        System.out.println("Royalty: £" + royalty);
	        System.out.println("Total Used: " + times);
	        System.out.println("Total Royalty Payment: £" + String.format( "%.2f",(times*royalty)));
	        System.out.println("---Joke Report End---");
	        System.out.println();
	        }else{
	        	System.out.println("The Joke ID is Incorrect!");
	        }
	        }catch(IOException ex){
	        	System.out.println("Please Enter a valid Cracker ID!");
	        }
	        
	}
	
	
	public boolean SelectJoke(String jid){
		boolean valid= false;
		 try {
	            connect = db.getConnection();
	            
	            //Create Table
	            String preQueryStatement = "SELECT * FROM Joke WHERE jid = ?";
	            pStmt = connect.prepareStatement(preQueryStatement);
	            pStmt.setString(1, jid);
	            
	            
	            ResultSet rs = pStmt.executeQuery();
	            
	            while(rs.next())
	            	{   
	            	valid = true;
	            	joke = rs.getString("joke");
	            	royalty = rs.getDouble("royalty");
	            	counting(jid);
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
		 return valid;
		 
	}
	public void counting(String jid){
		try {
            connect = db.getConnection();
            
            //Create Table
            String preQueryStatement = "SELECT COUNT(*) AS \"total\" FROM Cracker WHERE jid = ?";
            pStmt = connect.prepareStatement(preQueryStatement);
            pStmt.setString(1, jid);
            
            
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next())
            	{   
            	times = rs.getInt("total");
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
	
}
