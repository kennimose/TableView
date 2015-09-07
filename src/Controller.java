import java.util.ArrayList;

/**
 * Created by kennimose on 04/09/15.
 */
public class Controller {

    DBHandler dbh = new DBHandler();

    public ArrayList<Person> getPersonFromDBHandler() {
        return dbh.getPerson();
    }

    public void WriteToDBHandler(String textFName, String textLName, String textEmail, Double rValue) {
        dbh.writeToDB(textFName, textLName, textEmail, rValue);
    }

    public void deleteFromDBHandler(int id) {
        dbh.deleteFromDB(id);
    }

    public void updateTroughDBHandler(Person person) {
        dbh.updateDBHandler(person);
    }
}