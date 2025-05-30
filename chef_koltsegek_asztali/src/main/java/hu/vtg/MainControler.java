package hu.vtg;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainControler {

    @FXML
    private TableView<Model> Table;

    @FXML
    private TableColumn<Model, String> ChefCol;

    @FXML
    private TableColumn<Model, String> CommentCol;

    @FXML
    private TableColumn<Model, Integer> CurrencyCol;

    @FXML
    private TableColumn<Model, LocalDate> DateCol;

    @FXML
    private TableColumn<Model, String> TypeCol;

    @FXML
    private TableColumn<Model, Integer> idCol;

        @FXML
    void AddBtn(ActionEvent event) {
        try{
            ReaderandWriter readerandWriter = new ReaderandWriter();
            ArrayList<Model> list = readerandWriter.readFile();
            Model models = new Model();
            String selectedCategory = categoryfield.getValue();
            int plusid = list.size() + 1;
            models.setId(plusid);
            models.setChefname(chefield.getText());
            models.setDatum(datefield.getValue());
            models.setType(selectedCategory);
            models.setCurrency(Integer.parseInt(pricefield.getText()));
            models.setComment(commentfield.getText());
            list.add(models);
            Table.getItems().add(models);
            readerandWriter.FileWiriter(list);
            
        }
        catch(Exception e) {
            System.err.println(e.getMessage());

        }

    }

    @FXML
    void ExitBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() throws IOException{
        ReaderandWriter readerorwriter = new ReaderandWriter();
        ArrayList<Model> list = readerorwriter.readFile();
        ObservableList<Model> Models = FXCollections.observableArrayList(list);

        this.idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.ChefCol.setCellValueFactory(new PropertyValueFactory<>("chefname"));
        this.DateCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        this.TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.CurrencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));
        this.CommentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        this.Table.getItems().addAll(Models);
        categoryfield.getItems().addAll("Travel", "Ingredients", "Accommodation", "Equipment", "Other");
    }
    @FXML
    private TextField chefield;

    @FXML
    private TextField commentfield;

    @FXML
    private DatePicker datefield;

    @FXML
    private TextField pricefield;
    @FXML
    private ComboBox<String> categoryfield;
        
}
