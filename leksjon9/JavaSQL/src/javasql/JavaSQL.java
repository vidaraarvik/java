/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author speedy laptop
 */
public class JavaSQL extends Application {
    
    private final static String HOST = "itfag.usn.no";
    private final static String USERNAME = "h17u404";
    private final static String PASSWORD = "pw404";
    private final static String DATABASE = "h17db404";
    
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String PORT = "3306";
    
    private final static String URL
            = "jdbc:mysql://"
            + HOST
            + ":"
            + PORT
            + "/"
            + DATABASE
            + "?user=" + USERNAME
            + "&password=" + PASSWORD;
    // jdbc:mysql://itfag.usn.no:3306/h17db404?user=h17u404&password=pw404

    TextArea utData = new TextArea();
    
    @Override
    public void start(Stage stage) {
        Button btnHent = new Button();
        btnHent.setText("Hent");
        btnHent.setOnAction(e -> hentAnsattData());
        
        Button btnEndre = new Button();
        btnEndre.setText("Endre");
        btnEndre.setOnAction(e -> endreAnsattData());
        
        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnHent, btnEndre);
        
        BorderPane root = new BorderPane();
        root.setCenter(utData);
        root.setBottom(hBox);
        
        Scene scene = new Scene(root, 300, 250);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void hentAnsattData() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(URL);
            
            Statement s = conn.createStatement();
            String sql = "SELECT * FROM Ansatt";
            
            ResultSet svar = s.executeQuery(sql);
            
            while (svar.next()) {
                int nr = svar.getInt("Nr");
                String navn = svar.getString("Navn");
                utData.appendText(nr + " " + navn + "\n");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Fant ikke JDBC-driver");
        } catch (SQLException ex) {
            System.out.println("Noe gikk galt");
        }
    }
    
    public void endreAnsattData() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(URL);
            
            Statement s = conn.createStatement();
            String sql = "UPDATE Ansatt Set Navn ='Per'";
            
            int antallRader = s.executeUpdate(sql);
            
            utData.setText(antallRader + "rader endret");
        } catch (ClassNotFoundException ex) {
            System.out.println("Fant ikke JDBC-driver");
        } catch (SQLException ex) {
            System.out.println("Noe gikk galt");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
