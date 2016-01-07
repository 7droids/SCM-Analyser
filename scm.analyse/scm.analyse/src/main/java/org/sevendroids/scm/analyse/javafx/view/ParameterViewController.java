/**
 *
 */
package org.sevendroids.scm.analyse.javafx.view;

import javafx.fxml.FXML;
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

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initiaize() {

	}

}
