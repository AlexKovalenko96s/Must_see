package ua.kas.list_and_scrollpane;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ListViewController implements Initializable {

	@FXML 
	ImageView imv_pic;
	@FXML 
	ImageView imv_map;
	@FXML
	Label l_name;
	@FXML
	Label l_web;
	@FXML
	Label l_address;
	@FXML
	Label l_number;
	@FXML
	Label l_rating;
	@FXML
	private ListView<String> listView;
	@FXML
	Button photo;
	static String message = "";

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	
		
		
		try {
			Image imageDecline = new Image(getClass().getResourceAsStream("photo.png"));
			photo.setGraphic(new ImageView(imageDecline));
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemove", "root", "root");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from must_see");
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			while (myRs.next()) {
				listView.getItems().addAll(myRs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void work() throws SQLException, IOException {

		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemove", "root", "root");
		ResultSet myRs = null;
		ObservableList<String> movies;
		movies = listView.getSelectionModel().getSelectedItems();

		for (String m : movies) {
			message = m;
			System.out.println(message);

			java.sql.PreparedStatement myStmt;
			
			myStmt = myConn.prepareStatement("select * from must_see where name =?");	
			myStmt.setString(1, message);
			myRs = myStmt.executeQuery();
			
			Blob img_pic;
			Blob img_map;
			byte[] imgData_pic = null;
			byte[] imgData_map = null;
			
			while (myRs.next()) {
				l_name.setText(myRs.getString("name"));
				l_web.setText(myRs.getString("web"));
				l_address.setText(myRs.getString("address"));
				l_number.setText(myRs.getString("number"));
				l_rating.setText(myRs.getString("rating"));
				
				img_pic = myRs.getBlob("pic");
				img_map = myRs.getBlob("map");
				imgData_pic = img_pic.getBytes(1, (int) img_pic.length());
				imgData_map = img_map.getBytes(1, (int) img_map.length());
				BufferedImage imag_pic =ImageIO.read(new ByteArrayInputStream(imgData_pic));
				BufferedImage imag_map =ImageIO.read(new ByteArrayInputStream(imgData_map));
				imv_pic.setImage(SwingFXUtils.toFXImage(imag_pic, null));
				imv_map.setImage(SwingFXUtils.toFXImage(imag_map, null));
				message = "";
			}
		}
	}
	
	public void photo(ActionEvent e) throws IOException{
		Scene photo_scene = new Scene(FXMLLoader.load(getClass().getResource("Photo.fxml")));
		photo_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.setScene(photo_scene);
		app_stage.show();
	}
}