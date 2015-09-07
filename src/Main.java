import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import  java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Controller con = new Controller();
    Rating r = new Rating();

    TableView<Person> table = new TableView<Person>();
    ObservableList<Person> data;
    List list = new ArrayList<>();

    BorderPane border = new BorderPane();
    Group group = new Group();
    GridPane grid = new GridPane();

    VBox v = new VBox(5);

    TableColumn id = new TableColumn("id");
    TableColumn firstName = new TableColumn("Firstname");
    TableColumn lastName = new TableColumn("Lastname");
    TableColumn email = new TableColumn("Email");
    TableColumn rating = new TableColumn("Rating");

    TextField textFName = new TextField("Firstname");
    TextField textLName = new TextField("Lastname");
    TextField textEmail = new TextField("Email - xxx@mail.dk");

    Button add = new Button("add");
    Button delete = new Button("delete");
    Button change = new Button("change");

    Label labelFName = new Label("Firstname");
    TextField tempFName = new TextField();
    Label labelLName = new Label("Lastname");
    TextField tempLName = new TextField();
    Label labelEmail = new Label("Email");
    TextField tempEmail = new TextField();
    Label labelRating = new Label("Rating");
    Label labelRandom = new Label("  ");


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Adressbook");

        //Setup the table

        id.setMinWidth(100);
        firstName.setMinWidth(100);
        lastName.setMinWidth(100);
        email.setMinWidth(150);
        rating.setMinWidth(100);


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        border.setCenter(group);

        data = getinitialisingData();
        table.getColumns().addAll(id, firstName, lastName, email, rating);

        r.setPartialRating(false);

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    Person person = table.getSelectionModel().getSelectedItem();
                    tempFName.setText(person.getFirstName());
                    tempLName.setText(person.getLastName());
                    tempEmail.setText(person.getEmail());
                    r.setRating(person.getRating());
                }}});

        // Setup the right side

        border.setRight(v);

        delete.setMinWidth(175);
        change.setMinWidth(175);
        add.setMinWidth(175);
        add.setMinHeight(40);


        v.setStyle("-fx-padding: 10; -fx-background-color: lightgrey ");
        delete.setOnAction(event -> delete());
        add.setOnAction(event -> addNew());
        change.setOnAction(event -> change());

        v.getChildren().addAll(labelFName,tempFName, labelLName, tempLName, labelEmail,
                            tempEmail, labelRating, r, labelRandom, delete, change, add);

        border.setStyle("-fx-padding: 10;");
        group.getChildren().addAll(table);
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }

    private ObservableList<Person> getinitialisingData() {

        list = con.getPersonFromDBHandler();

        ObservableList data = FXCollections.observableArrayList(list);
        table.setItems(data);

        return data;
    }

    private void addNew() {
        con.WriteToDBHandler(tempFName.getText(), tempLName.getText(), tempEmail.getText(), r.ratingProperty().getValue());
        data = getinitialisingData();

    }

    private void delete() {
       Person person = table.getSelectionModel().getSelectedItem();
        con.deleteFromDBHandler(person.getId());
        data = getinitialisingData();
        tempFName.clear();
        tempLName.clear();
        tempEmail.clear();

    }

    private void change(){
        Person person = table.getSelectionModel().getSelectedItem();

        person.setFirstName(tempFName.getText());
        person.setLastName(tempLName.getText());
        person.setEmail(tempEmail.getText());
        person.setRating(r.ratingProperty().getValue());

        con.updateTroughDBHandler(person);
        getinitialisingData();

    }


}
