import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by kennimose on 31/08/15.
 */
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private double rating;

    public Person(int id, String firstName, String lastName, String email, double rating) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rating = rating;
    }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
