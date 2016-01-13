package org.sevendroids.scm.analyse.javafx;

import java.io.IOException;

import org.sevendroids.scm.analyse.javafx.view.ParameterViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SCMAnalyserFX extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SCM Analyser");

		initRootLayout();

		showParameterView();
	}

	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SCMAnalyserFX.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showParameterView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SCMAnalyserFX.class.getResource("view/ParameterView.fxml"));
			AnchorPane pane = loader.load();

			// Give the controller access to the main app.
			ParameterViewController controller = loader.getController();
			controller.setMainApp(this);

			rootLayout.setCenter(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
