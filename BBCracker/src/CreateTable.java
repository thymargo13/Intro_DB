import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable extends CrackerCommand {
	@Override
	public void execute() {
		CreateGift();
		CreateHat();
		CreateJoke();
		CreateCracker();
	}

	public void CreateHat() {
		Connection connect = null;
		PreparedStatement pStmt = null;

		try {
			connect = db.getConnection();
			// Drop Table
			String dropTable = "DROP TABLE IF EXISTS Hat CASCADE";
			pStmt = connect.prepareStatement(dropTable);
			pStmt.executeUpdate();
			// Create Table
			String preQueryStatement = "CREATE TABLE Hat(" + "hid VARCHAR(10) NOT NULL,"
					+ "description VARCHAR(100) NOT NULL," + "price DECIMAL(4,2) NOT NULL,"
					+ "CONSTRAINT hid_pk PRIMARY KEY(hid));";
			pStmt = connect.prepareStatement(preQueryStatement);
			pStmt.executeUpdate();

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

	public void CreateGift() {
		Connection connect = null;
		PreparedStatement pStmt = null;
		try {
			connect = db.getConnection();
			String dropTable = "DROP TABLE IF EXISTS Gift CASCADE";
			pStmt = connect.prepareStatement(dropTable);
			pStmt.executeUpdate();
			String preQueryStatement = "CREATE TABLE Gift(" + "gid VARCHAR(10) NOT NULL,"
					+ "description VARCHAR(100) NOT NULL," + "price DECIMAL(4,2) NOT NULL,"
					+ "CONSTRAINT gid_pk PRIMARY KEY(gid));";
			pStmt = connect.prepareStatement(preQueryStatement);
			pStmt.executeUpdate();
			pStmt.close();
			db.CloseConnection(connect);
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void CreateJoke() {
		Connection connect = null;
		PreparedStatement pStmt = null;
		try {
			connect = db.getConnection();
			String dropTable = "DROP TABLE IF EXISTS Joke CASCADE";
			pStmt = connect.prepareStatement(dropTable);
			pStmt.executeUpdate();
			String preQueryStatement = "CREATE TABLE Joke(" + "jid VARCHAR(10) NOT NULL,"
					+ "joke VARCHAR(100) NOT NULL," + "royalty DECIMAL(4,2) NOT NULL,"
					+ "CONSTRAINT jid_pk PRIMARY KEY(jid));";
			pStmt = connect.prepareStatement(preQueryStatement);
			pStmt.executeUpdate();
			pStmt.close();
			db.CloseConnection(connect);
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void CreateCracker() {
		Connection connect = null;
		PreparedStatement pStmt = null;
		try {
			connect = db.getConnection();
			String dropTable = "DROP TABLE IF EXISTS Cracker CASCADE";
			pStmt = connect.prepareStatement(dropTable);
			pStmt.executeUpdate();

			String preQueryStatement = "CREATE TABLE Cracker(" + "cid VARCHAR(10) NOT NULL,"
					+ "name VARCHAR(100) NOT NULL," + "jid VARCHAR(10) NULL," + "gid VARCHAR(10) NULL,"
					+ "hid VARCHAR(10) NULL," + "saleprice DECIMAL(4,2) NOT NULL," + "quantity INTEGER NOT NULL,"
					+ "CONSTRAINT cracker_joke_fk FOREIGN KEY(jid) REFERENCES Joke(jid),"
					+ "CONSTRAINT cracker_gift_fk FOREIGN KEY(gid) REFERENCES Gift(gid),"
					+ "CONSTRAINT cracker_hat_fk FOREIGN KEY(hid) REFERENCES Hat(hid),"
					+ "CONSTRAINT cracker_pk PRIMARY KEY(cid));";
			pStmt = connect.prepareStatement(preQueryStatement);
			pStmt.executeUpdate();

			pStmt.close();
			db.CloseConnection(connect);
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
