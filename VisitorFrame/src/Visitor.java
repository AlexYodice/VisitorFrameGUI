import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 * The Visitor class represents a visitor to the office.
 * It includes information such as their name, the company they belong to, the staff they are visiting,
 * and the time they checked in and out.
 */
public class Visitor {
    // Information about the visitor
    private String firstName;
    private String lastName;
    private String company;
    private String visitorsID;
    private String officeNo;
    private String staffVisiting;
    private Timestamp timeIn;
    private Timestamp timeOut;
    private String photo;
    /**
     * Constructor for a Visitor with only an ID. Other information is not known at the time of creation.
     */

    public Visitor(String visitorsID, String firstName, String lastName, String company,
                   String officeNo, String staffVisiting, Timestamp timeIn, Timestamp timeOut, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.visitorsID = visitorsID;
        this.officeNo = officeNo;
        this.staffVisiting = staffVisiting;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.photo = photo;
    }
    /**
     * Constructor for a Visitor with full information provided at the time of creation.

     * visitorsID The ID assigned to the visitor
     * firstName The visitor's first name
     * lastName The visitor's last name
     * company The company name from the visitor belongs to
     * officeNo The office number the visitor is visiting
     * staffVisiting The staff member the visitor is visiting
     * timeIn The check-in time of the visitor
     * timeOut The check-out time of the visitor
     * photo is the photo url of the visitors photo
     */

    // Setters for the Visitor's information
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setVisitorsID(String visitorsID) {
        this.visitorsID = visitorsID;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public void setStaffVisiting(String staffVisiting) {
        this.staffVisiting = staffVisiting;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // Getters for the Visitor's information
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String  getVisitorsID() {
        return visitorsID;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public String getStaffVisiting() {
        return staffVisiting;
    }

    public String getTimeIn() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return dateFormat.format(this.timeIn);
    }

    public String getTimeOut() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return dateFormat.format(this.timeOut);
    }

    public String getPhoto() {
        return photo;
    }

    /**
     * Returns a string representation of the Visitor object, which includes all the visitor's information.
     *
     * @return A string representation of the Visitor object
     */

    @Override
    public String toString() {
        return "firstName='" + firstName + '\n' +
                ", lastName='" + lastName + '\n' +
                ", company='" + company + '\n' +
                ", visitorsID='" + visitorsID + '\n' +
                ", officeNo='" + officeNo + '\n' +
                ", staffVisiting='" + staffVisiting + '\n' +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut + '\n' +
                ", photo='" + photo +
                '}'+ "\n\n" ;
    }
    //Implementation
}
