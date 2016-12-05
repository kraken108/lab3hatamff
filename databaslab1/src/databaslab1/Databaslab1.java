/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;


import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;
import database.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    

    private DatabaseCommunicator dbComm;
    private TextField txt;
    private ChoiceBox<String> choiceBox;
    private ArrayList<Media> listItems;
    private rateWindow rw;
    private TableView table = new TableView();    
    ConfirmBox c = new ConfirmBox();
    Media m1 = new Media();

    
    @Override
    public void start(Stage primaryStage){
        
        listItems = new ArrayList<>();
        rw = new rateWindow();
        Boolean exit = false;
        Button btn = new Button();
        btn.setText("Search");
        Button rate = new Button();
        rate.setText("Rate");
        Button addNewArtist = new Button();
        addNewArtist.setText("Add new Artist");
        Button addCD = new Button();
        addCD.setText("Add CD");
        
        //Button rateButton = new Button(");                                                                                                                                                       ");
        //rateButton.setVisible(false);
        
        dbComm = null;
        try{
            dbComm = new DatabaseCommunicator();
        }catch(SQLException e){
            AlertBox.display("Error!", "Failed to connect to database: " + e);
            exit = true;
        }catch(Exception ex){
            AlertBox.display("Error!", "Failed to connect to database: " + ex);
            exit = true;
        }
        
        txt = new TextField("Enter search word");
        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Artist", "Title", "Genre", "Rating");
        choiceBox.setTooltip(new Tooltip("Search by"));
        choiceBox.getSelectionModel().selectFirst();
        
        
        TableColumn firstCol = new TableColumn("Title");
        TableColumn secondCol = new TableColumn("Artist");
        TableColumn thirdCol = new TableColumn("Genre");
        TableColumn fourthCol = new TableColumn("Rating");
        TableColumn fifthCol = new TableColumn("Date");
        
        table.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol, fifthCol);
        table.setEditable(true);
        
        
        rate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Media m = getSelectedAlbum();
                if(m!=null){
                    rw.rateAlbum(dbComm,m);
                }
            }
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>() {  
            @Override
            public void handle(ActionEvent event) {
                sendSearch();
            }
        });
            

        
        
        /*add.setText("Add CD");
        add.setOnAction(e -> {
            m1=c.display(dbComm);   
        });

        System.out.println(m1.getGenre());
        System.out.println(m1.getTitle());
        System.out.println(m1.getPublishDate());
        System.out.println(m1.getRating());
        */
        BorderPane borderPane = new BorderPane();
        
        
        HBox statusbar = new HBox();
        borderPane.setTop(statusbar);
        statusbar.setSpacing(10);
        statusbar.setPadding(new Insets(10, 0, 10, 10));
        
        HBox statusbar2 = new HBox();  
        statusbar2.setSpacing(10);
        borderPane.setBottom(statusbar2);
        statusbar2.setPadding(new Insets(10, 0, 10, 10));
        
        
        addNewArtist.setOnAction(e -> addNewArtist());
        addCD.setOnAction(e -> addCD());
        //NYTT
        
        
        
        borderPane.setCenter(table);
        statusbar2.getChildren().addAll(addCD, addNewArtist, rate);
        statusbar.getChildren().addAll(btn,txt,choiceBox);
        
        Scene scene = new Scene(borderPane, 768, 512);

        primaryStage.setOnCloseRequest(event->{
            dbComm.closeConnection();
        });
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        if(exit){
            primaryStage.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void sendSearch(){
        //table.getItems().clear();
        listItems.clear();
        
        Thread thread = new Thread(){
            public void run(){
                ArrayList<Media> tempMusicAlbum = 
                        dbComm.searchRequest(txt.getText(),(String)
                                choiceBox.getSelectionModel().getSelectedItem());
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(tempMusicAlbum!=null){
                                    for(Media ma : tempMusicAlbum){
                                        if(ma!=null){
                                            table.getItems().add(ma.toString());
                                            listItems.add(ma);
                                        }
                                    }
                                }
                                else AlertBox.display("Error!", "Search failed.");   
                            }
                        }
                );
            }
        };
        thread.start();
    }
    
    private Media getSelectedAlbum(){
        int n = table.getSelectionModel().getSelectedIndex();
        if(n == -1){
            return null;
        }
        return listItems.get(n);
    }

    public void addNewArtist(){
        
        Stage stage = new Stage();
        stage.setTitle("Add Artist");

        Button closeButton = new Button("Close");
        Button submitButton = new Button("Submit");       
        
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(3);
        grid.setHgap(5);
        
        Label infoLabel = new Label("Enter values: ");        
        Label nameLabel = new Label("Name: ");
        Label ageLabel = new Label("Age: ");
        Label nationalityLabel = new Label("Nationality: ");
        
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField nationalityField = new TextField();
        nameField.setPromptText("Name");
        ageField.setPromptText("Age");
        nationalityField.setPromptText("Nationality");
            
        GridPane.setConstraints(infoLabel, 4, 0);
        GridPane.setConstraints(nameLabel, 2, 3);
        GridPane.setConstraints(ageLabel, 2, 5);
        GridPane.setConstraints(nationalityLabel, 2, 7);
        GridPane.setConstraints(nameField, 4, 3);
        GridPane.setConstraints(ageField, 4, 5);
        GridPane.setConstraints(nationalityField, 4, 7);
        GridPane.setConstraints(closeButton, 6, 8);
        GridPane.setConstraints(submitButton, 4, 8);
        
        
                
        closeButton.setOnAction(e -> stage.close());
        submitButton.setOnAction(e -> {
        if(!(nameField.getText().length() >2 || ageField.getText().length() > 0 || nationalityField.getText().length() > 2)){
            AlertBox.display("Error", "You must enter correct data!");
        }
        else{
            int tempAge = Integer.parseInt(ageField.getText());
            Person p = new Person(nameField.getText(), tempAge, nationalityField.getText());
        }
        });
        
        grid.getChildren().addAll(nameLabel, ageLabel, nationalityLabel, nameField, ageField, nationalityField, closeButton, submitButton, infoLabel);
        Scene scene = new Scene(grid,350,200);
        stage.setScene(scene);
        stage.show();   
    }
    
    public void addCD(){
        
        Media albumToReturn = new Media();
        Stage stage = new Stage();
        stage.setTitle("Add CD");
        
        Button addButton = new Button("Add another Person");
        Button dateButton = new Button("Pick a Date");
        Button doneButton = new Button("Done, next album");
        Button closeButton = new Button("Close");
        
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setShowWeekNumbers(true); 
        datePicker.setEditable(false);
        
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(3);
        grid.setHgap(5);
        grid.add(datePicker, 1, 4);
        
        Label artistLabel = new Label("Person name: ");
        Label titleLabel = new Label("Title: ");
        Label dateLabel = new Label("Publish date: ");
        Label genreLabel = new Label("Genre: ");
        
        TextField personField = new TextField();
        TextField titleField = new TextField();
        TextField genreField = new TextField();
        
        personField.setPromptText("Person");
        titleField.setPromptText("Title");
        genreField.setPromptText("Genre");
        
        GridPane.setConstraints(artistLabel, 0, 0);
        GridPane.setConstraints(personField, 1, 0);
        GridPane.setConstraints(addButton, 1, 1);
        GridPane.setConstraints(titleLabel, 0, 2);
        GridPane.setConstraints(titleField, 1, 2);
        GridPane.setConstraints(dateButton, 1, 4);
        GridPane.setConstraints(dateLabel, 0, 4);
        GridPane.setConstraints(genreField, 1, 3);
        GridPane.setConstraints(genreLabel, 0, 3);
        GridPane.setConstraints(doneButton, 1, 5);
        GridPane.setConstraints(closeButton, 2, 5);      
        
        closeButton.setOnAction(e ->{
            stage.close();
        });
        
        addButton.setOnAction(e -> {
            if(!(personField.getText().length()>2)){
                AlertBox.display("Error", "Name must be longer than 2 characters");
            }
            else{
                Person p1 = new Person(personField.getText());
                albumToReturn.addPerson(p1);
                personField.clear();
            }
        });
        
        doneButton.setOnAction(e -> {
            if(!(titleField.getText().length() > 2 || genreField.getText().length() > 2)){
                AlertBox.display("Error", "Enter correct values");
            }
            else{                                      
                albumToReturn.setTitle(titleField.getText());
                albumToReturn.setGenre(genreField.getText());
                titleField.clear();
                genreField.clear();                               
            }
            
            if(!(titleField.getText().length() > 2 || genreField.getText().length() > 2)){
                AlertBox.display("Error", "Enter correct values");
            }
            else{
                if(personField.getText().length() > 2)
                    albumToReturn.addPerson(new Person(personField.getText()));
                    
                albumToReturn.setTitle(titleField.getText());
                albumToReturn.setGenre(genreField.getText());
                titleField.clear();
                genreField.clear(); 
            }
            
        });     
        
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        albumToReturn.setPublishDate(date); 
        
        
        grid.getChildren().addAll(artistLabel, personField, titleLabel, titleField, doneButton, dateLabel, genreLabel, genreField, addButton, closeButton);

        Scene scene = new Scene(grid, 350, 200);
        stage.setScene(scene);
        stage.show();  
        
    }
    
}



