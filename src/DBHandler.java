import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHandler {

    Connection myConn;
    private ArrayList<Person> p;


    public DBHandler() {
        try {
            myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:8889/intellijTest", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<Person> getPerson() {
        PersonReadFromDatabase();
        return (p);
    }

    private void PersonReadFromDatabase() {
        p = new ArrayList<Person>();

        try {
            Statement myStatement = (Statement) myConn.createStatement();
            ResultSet myRs = ((java.sql.Statement) myStatement).executeQuery
                    ("SELECT `id`, `firstnameCol`, `lastnameCol`, `emailCol`, `ratingCol` FROM Person_Table;");

            while(myRs.next()) {
                p.add(new Person(myRs.getInt("id"), myRs.getString("firstnameCol"), myRs.getString("lastnameCol"),
                                myRs.getString("emailCol"), myRs.getDouble("ratingCol")));
            }
        } catch (SQLException e){e.printStackTrace();}
    }

    public void writeToDB(String textFName, String textLName, String textEmail, Double rValue) {

        try {
        Statement myStatement = (Statement) myConn.createStatement();

        myStatement.executeUpdate("INSERT INTO Person_Table" + "(firstnameCol, lastnameCol, emailCol, ratingCol)"
                + "VALUES ('" + textFName + "', '" + textLName + "', '" + textEmail +  "', '" + rValue + "')" + ";");

    } catch (SQLException e){e.printStackTrace();}
    }

    public void deleteFromDB(int id) {
        p = new ArrayList<Person>();

        try {
            Statement myStatement = (Statement) myConn.createStatement();

            String query = "DELETE FROM Person_Table WHERE id = " + id;

            myStatement.executeUpdate(query);


        } catch (SQLException e) {e.printStackTrace();}
    }

    public void updateDBHandler(Person p) {
        try {

            Statement myStatement = (Statement) myConn.createStatement();

            String query = "UPDATE Person_Table SET firstnameCol='"+p.getFirstName()+"', lastnameCol = '"+p.getLastName()+
                    "', emailCol = '"+p.getEmail()+"'," + " ratingCol = '"+p.getRating()+ "' " + "WHERE id = "+p.getId();

            myStatement.executeUpdate(query);

        } catch (SQLException e) {e.printStackTrace();}
    }


}
