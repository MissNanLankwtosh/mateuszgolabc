package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import stablepoint.StablePoint;

/*
 * DatabaseManager class 
 * It is using LinkedList for simulation.
 */
public class DatabaseManager {

	private Connection c;  				//it is not necessary if we only simulating database
	private String pathToServer, userName, userPassword;
	private int numberOfLastBackups;
	private LinkedList<StablePoint> stablePoints = new LinkedList<StablePoint>(); 
	
	/*
	 * Constructor
	 * Usage:
	 * path = "jdbc://localhost/..."
	 * userName = "admin"
	 * userPassword = "admin"
	 * DatabaseManager(path, userName, userPassword);
	 */
	public DatabaseManager(String pathToS, String userN, String userP) {
		
		stablePoints = new LinkedList<StablePoint>(); 
		this.pathToServer = pathToS;  	//it is not necessary if we only simulating database
		this.userName = userN;			//it is not necessary if we only simulating database
		this.userPassword = userP;		//it is not necessary if we only simulating database
		openConnection();				//it is not necessary if we only simulating database
	}
	
	
	public void insert(StablePoint sp) {
		stablePoints.addLast(sp);
		
		if (stablePoints.size() > numberOfLastBackups) { stablePoints.removeFirst(); }
	}
	
	public void deleteLastStablePoint() {
		
		stablePoints.removeLast(); 	// catch NoSuchElementException 
	}
	
	public void getLastStablePoint() {
		
		stablePoints.getLast(); 	// catch NoSuchElementException 
	}
	
	public int getNumberOfLastBackups() {
		
		return stablePoints.size();
	}
	
	public void update() {
		//??
	}
	
	/*
	 * =============================================================================
	 * THE FOLLOWING PARTS ARE THE PART-IMPLEMENTATIONS OF REAL DATABASE ENVIRONMENT
	 * Are they necessary? I dont think so cause it's just a mock, LinkedList-type implementation 
	 * is enough for simulating the database environment
	 */
	public void openConnection() {
		try {
			c = DriverManager.getConnection(pathToServer, userName, userPassword);
		} catch ( SQLException err ) {
			System.out.println( err.getMessage( ) );
		}
	}
	
	public void disConnect() {
		try {
			c.close();
		} catch ( SQLException err ) {
			System.out.println( err.getMessage( ) );
		}
	}
	
	public void insertToDatabase(int preModuleID, int postModuleID, String link1, String link2) {
		
		if (c != null)	{
			PreparedStatement stnt = null;
			ResultSet rs = null;
			
			try {
				stnt = c.prepareStatement(
					"INSERT INTO stablepoints VALUES (?,?,?,?)"
				);
				
				stnt.setInt(0, preModuleID);
				stnt.setInt(1, postModuleID);
				stnt.setString(2, link1);
				stnt.setString(3, link2);
	
				stnt.executeQuery();
			}
			catch (SQLException ex) {}
	
			finally
			{
				if (stnt != null)
					try
					{
						if (rs != null)
						try
						{
							rs.close();
						}
						catch (SQLException ex) {}
						stnt.close();
					}
					catch (SQLException ex) {}
			}
		}
	}
}
