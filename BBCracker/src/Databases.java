


import java.io.IOException;
import java.sql.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author thymmm
 */
public class Databases {

    private String username = "hyt735";
    private String password = "364752Twn";
    private String database = "hyt735";
    private String url = "jdbc:postgresql://mod-intro-databases.cs.bham.ac.uk/" + database;

    private ArrayList<String> giftDes = new ArrayList<String>();
    private ArrayList<String> hatDes = new ArrayList<String>();
    private ArrayList<String> joke = new ArrayList<String>();
    private ArrayList<String> name = new ArrayList<String>();

    // Constructor
    public Databases(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Databases() {
        ReadJSON();
    }

    // Constructor
    public Connection getConnection() throws SQLException, IOException {
        try {
            //Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            System.exit(1);
        }
        Connection conn = null;
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Ooops, couldn't get a connection");
            System.out.println("Check that <username> & <password> are right");
            System.exit(1);
            return conn;
        }
    }

    public void CloseConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void ReadJSON() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/RawData.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray giftJson = (JSONArray) jsonObject.get("gift");
            JSONArray hatJson = (JSONArray) jsonObject.get("hat");
            JSONArray jokeJson = (JSONArray) jsonObject.get("joke");
            JSONArray nameJson = (JSONArray) jsonObject.get("name");

            if (nameJson != null) {
                for (int i = 0; i < nameJson.size(); i++) {
                    name.add(nameJson.get(i).toString());
                }
            }

            if (giftJson != null) {
                for (int i = 0; i < giftJson.size(); i++) {
                    giftDes.add(giftJson.get(i).toString());
                }
            }

            if (hatJson != null) {
                for (int i = 0; i < hatJson.size(); i++) {
                    hatDes.add(hatJson.get(i).toString());
                }
            }
            if (jokeJson != null) {
                for (int i = 0; i < jokeJson.size(); i++) {
                    joke.add(jokeJson.get(i).toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public double generatePrice() {
        Random r = new Random();
        int ret = r.nextInt(500 + 1);
        return (ret / 100.0);
    }

    public int random(int max, int min) {
        Random r = new Random();
        int random = r.nextInt(max) + min;
        return random;
    }

    public void insertData() throws IOException {
        //Insert Data
        Connection connect = null;
        PreparedStatement pStmt = null;
        try {
            connect = getConnection();

            for (int i = 1; i <= 100; i++) {
                String insertHat = "INSERT INTO Hat VALUES(?,?,?)";
                pStmt = connect.prepareStatement(insertHat);
                pStmt.setString(1, "H" + i);
                pStmt.setString(2, hatDes.get(random(10, 0)));
                pStmt.setDouble(3, generatePrice());
                int row = pStmt.executeUpdate();
                if (row == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }

            for (int j = 1; j <= 100; j++) {
                String insertGift = "INSERT INTO Gift VALUES(?,?,?)";
                pStmt = connect.prepareStatement(insertGift);
                pStmt.setString(1, "G" + j);
                pStmt.setString(2, giftDes.get(random(10, 0)));
                pStmt.setDouble(3, generatePrice());
                int row = pStmt.executeUpdate();
                if (row == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
            for (int k = 1; k <= 100; k++) {
                String insertJoke = "INSERT INTO Joke VALUES(?,?,?)";
                pStmt = connect.prepareStatement(insertJoke);
                pStmt.setString(1, "J" + k);
                pStmt.setString(2, joke.get(random(10, 0)));
                pStmt.setDouble(3, generatePrice());
                int row = pStmt.executeUpdate();
                if (row == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
            for (int k = 1; k <= 1000; k++) {
                String insertCracker = "INSERT INTO Cracker VALUES(?,?,?,?,?,?,?)";
                pStmt = connect.prepareStatement(insertCracker);
                pStmt.setString(1, "C" + k);
                pStmt.setString(2, name.get(random(10, 0)));
                pStmt.setString(3, "J" + random(100, 1));
                pStmt.setString(4, "G" + random(100, 1));
                pStmt.setString(5, "H" + random(100, 1));
                pStmt.setDouble(6, generateSalePrice());
                pStmt.setInt(7, random(1000, 1));
                int row = pStmt.executeUpdate();
                if (row == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
            pStmt.close();
            CloseConnection(connect);
        } catch (SQLException e) {
            System.out.println("Data Already Inserted.");;
        }

    }

    public double generateSalePrice() {
        Random r = new Random();
        int ret = r.nextInt(200) + 100;
        return ret / 10.0;

    }
}
