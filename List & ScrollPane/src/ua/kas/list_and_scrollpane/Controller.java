package ua.kas.list_and_scrollpane;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML ImageView i1;
	@FXML ImageView i2;
	@FXML ImageView i3;
	@FXML ImageView i4;
	@FXML ImageView i5;
	@FXML ImageView i6;
	@FXML ImageView i7;
	@FXML ImageView i8;
	@FXML ImageView i9;
	ListViewController lc = new ListViewController();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/freemove", "root", "root");
			java.sql.PreparedStatement ps = myConn.prepareStatement("select * from must_see where name=?");
			String n = ListViewController.message;
			ps.setString(1, n);
			ResultSet myRs = ps.executeQuery();
			Blob i11;
			Blob i22;
			Blob i33;
			Blob i44;
			Blob i55;
			Blob i66;
			Blob i77;
			Blob i88;
			Blob i99;
			byte[] i1_Data = null;
			byte[] i2_Data = null;
			byte[] i3_Data = null;
			byte[] i4_Data = null;
			byte[] i5_Data = null;
			byte[] i6_Data = null;
			byte[] i7_Data = null;
			byte[] i8_Data = null;
			byte[] i9_Data = null;

			while (myRs.next()) {
				
				i11 = myRs.getBlob("i1");
				i22 = myRs.getBlob("i2");
				i33 = myRs.getBlob("i3");
				i44 = myRs.getBlob("i4");
				i55 = myRs.getBlob("i5");
				i66 = myRs.getBlob("i6");
				i77 = myRs.getBlob("i7");
				i88 = myRs.getBlob("i8");
				i99 = myRs.getBlob("i9");
				
				try{
					i1_Data = i11.getBytes(1, (int) i11.length());
					BufferedImage imag_1 =ImageIO.read(new ByteArrayInputStream(i1_Data));
					i1.setImage(SwingFXUtils.toFXImage(imag_1, null));
				}catch(Exception ex){}
				
				try{
					i2_Data = i22.getBytes(1, (int) i22.length());
					BufferedImage imag_2 =ImageIO.read(new ByteArrayInputStream(i2_Data));
					i2.setImage(SwingFXUtils.toFXImage(imag_2, null));
				}catch(Exception ex){}
				
				try{
					i3_Data = i33.getBytes(1, (int) i33.length());
					BufferedImage imag_3 =ImageIO.read(new ByteArrayInputStream(i3_Data));
					i3.setImage(SwingFXUtils.toFXImage(imag_3, null));
				}catch(Exception ex){}
				
				try{
					i4_Data = i44.getBytes(1, (int) i44.length());
					BufferedImage imag_4 =ImageIO.read(new ByteArrayInputStream(i4_Data));
					i4.setImage(SwingFXUtils.toFXImage(imag_4, null));
				}catch(Exception ex){}
				
				try{
					i5_Data = i55.getBytes(1, (int) i55.length());
					BufferedImage imag_5 =ImageIO.read(new ByteArrayInputStream(i5_Data));
					i5.setImage(SwingFXUtils.toFXImage(imag_5, null));
				}catch(Exception ex){}
				
				try{
					i6_Data = i66.getBytes(1, (int) i66.length());
					BufferedImage imag_6 =ImageIO.read(new ByteArrayInputStream(i6_Data));
					i6.setImage(SwingFXUtils.toFXImage(imag_6, null));
				}catch(Exception ex){}
				
				try{
					i7_Data = i77.getBytes(1, (int) i77.length());
					BufferedImage imag_7 =ImageIO.read(new ByteArrayInputStream(i7_Data));
					i7.setImage(SwingFXUtils.toFXImage(imag_7, null));
				}catch(Exception ex){}
				
				try{
					i8_Data = i88.getBytes(1, (int) i88.length());
					BufferedImage imag_8 =ImageIO.read(new ByteArrayInputStream(i8_Data));
					i8.setImage(SwingFXUtils.toFXImage(imag_8, null));
				}catch(Exception ex){}
				
				try{
					i9_Data = i99.getBytes(1, (int) i99.length());
					BufferedImage imag_9 =ImageIO.read(new ByteArrayInputStream(i9_Data));
					i9.setImage(SwingFXUtils.toFXImage(imag_9, null));
				}catch(Exception ex){}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void back(ActionEvent e) throws IOException{
		Scene must_see_scene = new Scene(FXMLLoader.load(getClass().getResource("list.fxml")));
		must_see_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.setScene(must_see_scene);
		app_stage.show();
	}
}
