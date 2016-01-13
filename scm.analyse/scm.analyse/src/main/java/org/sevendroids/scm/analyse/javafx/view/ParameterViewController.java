/**
 *
 */
package org.sevendroids.scm.analyse.javafx.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;

import org.sevendroids.scm.analyse.javafx.SCMAnalyserFX;
import org.sevendroids.scm.analyse.util.NoEmptyProperties;
import org.sevendroids.scm.analyse.util.PropertyUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author 7driods
 *
 */
public class ParameterViewController {

	@FXML
	private TextField urlField;

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private DatePicker toDateField;

	@FXML
	private DatePicker fromDateField;

	@FXML
	private TextField fileNameField;

	@FXML
	private TextField authorField;

	@FXML
	private TextField commentField;

	@FXML
	private TextField minNoOfCommentField;

	// Reference to the main application.
	private SCMAnalyserFX mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initiaize() {

	}

	@FXML
	private void handleExitAction() {
		Alert choiceDialog = new Alert(AlertType.CONFIRMATION);
		// choiceDialog.initOwner(mainApp.getPrimaryStage());
		choiceDialog.setTitle("Close Application?");
		choiceDialog.setContentText("Do you realy want to close the application?");
		Optional<ButtonType> answer = choiceDialog.showAndWait();
		if (answer.get() == ButtonType.OK)
			System.exit(0);
	}

	@FXML
	private void handleStartAction() {
		String checkResult = PropertyUtil.checkProperties(readUserInput());
		if (checkResult != null) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText(checkResult);
			error.showAndWait();
		}
	}

	/**
	 * Convert user input into properties
	 *
	 * @return
	 */
	private Properties readUserInput() {
		Properties props = new NoEmptyProperties();
		props.setProperty(PropertyUtil.URL_PROPERTY, urlField.getText());
		props.setProperty(PropertyUtil.USERNAME_PROPERTY, usernameField.getText());
		props.setProperty(PropertyUtil.PASSWORD_PROPERTY, passwordField.getText());
		props.setProperty(PropertyUtil.AUTHOR_PROPERTY, authorField.getText());
		props.setProperty(PropertyUtil.COMMENT_PROPERTY, commentField.getText());
		props.setProperty(PropertyUtil.FILENAME_PROPERTY, fileNameField.getText());
		LocalDate fromDate = fromDateField.getValue();
		if (fromDate != null)
			props.setProperty(PropertyUtil.FROM_DATE_PROPERTY,
					DateTimeFormatter.ofPattern(PropertyUtil.DATE_FORMAT).format(fromDate));
		props.setProperty(PropertyUtil.MIN_NO_OF_COMMENTS_PROPERTY, minNoOfCommentField.getText());
		LocalDate toDate = toDateField.getValue();
		if (toDate != null)
			props.setProperty(PropertyUtil.TO_DATE_PROPERTY,
					DateTimeFormatter.ofPattern(PropertyUtil.DATE_FORMAT).format(toDate));
		return props;
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(SCMAnalyserFX mainApp) {
		this.mainApp = mainApp;
	}

}
