import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class CrackerCommand{
	public InputStreamReader is = new InputStreamReader(System.in);
	public BufferedReader br = new BufferedReader(is);
	public Databases db = new Databases();
	public Connection connect =null;
	public PreparedStatement pStmt =null;
    public abstract void execute();
    
}
