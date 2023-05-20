import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a database interface for operations related to the Visitor objects.
 * It includes methods for adding, updating, and fetching Visitor data from the database.
 */
public class VisitorQueries {
    private static final String URL = "jdbc:mysql://localhost:3306/company";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MyN3wP4ssw0rd";

    private Connection connection = null;
    private PreparedStatement selectAllVisitors = null;
    private PreparedStatement selectVisitorByID = null;
    private PreparedStatement insertNewVisitor = null;
    private PreparedStatement updateVisitor = null;
    private PreparedStatement selectVisitorByDate = null;

    /**
     * Default constructor that sets up the database connection and prepares SQL statements.
     */
    public VisitorQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            selectAllVisitors = connection.prepareStatement("SELECT * FROM company.visitors");
            selectVisitorByID = connection.prepareStatement("SELECT * FROM company.visitors WHERE id = ?");
            insertNewVisitor = connection.prepareStatement("INSERT INTO company.visitors (FirstName, LastName, Company_from, Office_visiting, " +
                    "Staff_visiting, date_begin, date_end, Visitor_ID, Photo) VALUES(?,?,?,?,?,?,?,?,?)");
            updateVisitor = connection.prepareStatement("UPDATE company.visitors SET FirstName = ?, " +
                    "LastName = ?, Company_from = ?, Office_visiting = ?, Staff_visiting = ?, date_begin = " +
                    "?, date_end = ?, Visitor_ID = ?, Photo = ? WHERE id = ?");
            selectVisitorByDate = connection.prepareStatement("SELECT * FROM Visitors where date(date_begin) = date(CURRENT_TIMESTAMP)");

        } catch (SQLException ex) {
            System.err.println("Connection Error with Database" + ex.getMessage());
            System.exit(1);
            close();
        }
    }
    
    /**
     * Fetches all visitors from the database.
     * A list of all visitors from the database.
     */
    public StringBuilder getAllVisitors() {
        StringBuilder text = new StringBuilder(" ");
        List<Visitor> results;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllVisitors.executeQuery();
            results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(new Visitor(resultSet.getString("id"), resultSet.getString("FirstName"),
                        resultSet.getString("LastName"), resultSet.getString("company_from"),
                        resultSet.getString("Office_visiting"), resultSet.getString("Staff_Visiting"),
                        resultSet.getTimestamp("date_begin"), resultSet.getTimestamp("date_end"),
                        resultSet.getString("Photo")));
            }
            text.append(results.toString());
        } catch (SQLException ex) {
            System.err.println("Error in DB: " + ex.getMessage());
            close();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return text;
    }
    /**
     * Fetches a visitor by ID from the database.
     *
     *  id The ID of the visitor to fetch.
     *  A list containing the visitor with the provided ID, or an empty list if no visitor was found.
     */
    public List<Visitor> getVisitorByID(int id) {
        List<Visitor> results = null;
        ResultSet resultSet = null;

        try {
            selectVisitorByID.setInt(1, id);
            resultSet = selectVisitorByID.executeQuery();
            results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(new Visitor(resultSet.getString("Visitor_ID"), resultSet.getString("FirstName"),
                        resultSet.getString("LastName"), resultSet.getString("Company_from"),
                        resultSet.getString("Office_Visiting"), resultSet.getString("Staff_visiting"),
                        resultSet.getTimestamp("date_begin"), resultSet.getTimestamp("date_end"),
                        resultSet.getString("Photo")));
            }
        } catch (SQLException ex) {
            System.err.println("Error in DB: " + ex.getMessage());
            close();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return results;
    }

    /**
     * Adds a new visitor to the database.
     *
     *  fName The first name of the new visitor.
     *  lName The last name of the new visitor.
     *  company The company of the new visitor.
     *  oNum The office number of the new visitor.
     *  Visit The staff visiting of the new visitor.
     *  tIn The time in of the new visitor.
     *  tOut The time out of the new visitor.
     *  photo The URL from the photo taken.
     *  The number of rows affected in the database, or 0 if the operation failed.
     */
    public int addVisitor(String fName, String lName, String company,
                          String oNum, String sVisit, Timestamp tIn,
                          Timestamp tOut, String ID, String photo) {
        int result = 0;

        try {
            insertNewVisitor.setString(1, fName);
            insertNewVisitor.setString(2, lName);
            insertNewVisitor.setString(3, company);
            insertNewVisitor.setString(4, oNum);
            insertNewVisitor.setString(5, sVisit);
            insertNewVisitor.setTimestamp(6, tIn);
            insertNewVisitor.setTimestamp(7, tOut);
            insertNewVisitor.setString(8, ID);
            insertNewVisitor.setString(9, photo);


            result = insertNewVisitor.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error in DB: " + ex.getMessage());
            close();
        }
        return result;
    }

    /**
     * This method updates an existing visitor in the database. The arguments include:
     * ID - The ID of the visitor to update.
     * fName - The new first name of the visitor.
     * lName - The new last name of the visitor.
     * company - The new company of the visitor.
     * oNum - The new office number of the visitor.
     * sVisit - The new staff the visitor is visiting.
     * tIn - The new timestamp representing the time the visitor checked in.
     * tOut - The new timestamp representing the time the visitor checked out.
     * photo - The new URL from the photo taken.
     * The method returns the number of rows affected in the database, or 0 if the operation failed.
     */
    public int updateVisitor(String fName, String lName, String company,
                             String oNum, String sVisit, Timestamp tIn,
                             Timestamp tOut, String vID, String photo, int id) {
        int result = 0;

        try {
            updateVisitor.setString(1, fName);
            updateVisitor.setString(2, lName);
            updateVisitor.setString(3, company);
            updateVisitor.setString(4, oNum);
            updateVisitor.setString(5, sVisit);
            updateVisitor.setTimestamp(6, tIn);
            updateVisitor.setTimestamp(7, tOut);
            updateVisitor.setString(8, vID);
            updateVisitor.setString(9,photo );
            updateVisitor.setInt(10, id);


            result = updateVisitor.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error in DB: " + ex.getMessage());
            close();
        }
        return result;

    }
    public String getVisitorByDate() {
        StringBuilder text = new StringBuilder(" ");
        List<Visitor> results;
        ResultSet resultSet = null;

        try {
            resultSet = selectVisitorByDate.executeQuery();
            results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(new Visitor(resultSet.getString("id"), resultSet.getString("FirstName"),
                        resultSet.getString("LastName"), resultSet.getString("company_from"),
                        resultSet.getString("Office_visiting"), resultSet.getString("Staff_Visiting"),
                        resultSet.getTimestamp("date_begin"), resultSet.getTimestamp("date_end"),
                        resultSet.getString("Photo")));
                text.append(results.toString());
            }
        } catch (SQLException ex) {
            System.err.println("Error in DB: " + ex.getMessage());
            close();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return text.toString();
    }
    /**
     * This method is used to close the database connection.
     */
    public void close(){
        try {
            connection.close();
        } catch (SQLException ex){
            System.err.println("Error in DB: " + ex.getMessage());
        }
    }
}