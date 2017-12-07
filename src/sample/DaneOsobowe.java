package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by pwilkin on 30-Nov-17.
 */
public class DaneOsobowe implements HierarchicalController<MainController> {

    public TextField imie;
    public TextField nazwisko;
    public TextField pesel;
    public TextField indeks;
    public TableView<Student> tabelka;
    private MainController parentController;

    public void dodaj(ActionEvent actionEvent) {
        Student st = new Student();
        st.setName(imie.getText());
        st.setSurname(nazwisko.getText());
        st.setPesel(pesel.getText());
        st.setIdx(indeks.getText());
        tabelka.getItems().add(st);
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        //tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
        tabelka.setItems(parentController.getDataContainer().getStudents());
    }

    public void usunZmiany() {
        tabelka.getItems().clear();
        tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
    }

    public MainController getParentController() {
        return parentController;
    }

    public void initialize() {
        for (TableColumn<Student, ?> studentTableColumn : tabelka.getColumns()) {
            if ("imie".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            } else if ("nazwisko".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            } else if ("pesel".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
            } else if ("indeks".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("idx"));
            }
        }

    }

    public void synchronizuj(ActionEvent actionEvent) {
        parentController.getDataContainer().setStudents(tabelka.getItems());
    }
}
