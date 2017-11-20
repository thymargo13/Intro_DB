import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class CrackerCommand{
	public InputStreamReader is = new InputStreamReader(System.in);
	public BufferedReader br = new BufferedReader(is);
    public abstract void execute();
    
}
