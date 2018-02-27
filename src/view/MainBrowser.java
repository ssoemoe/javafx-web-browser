package view;

/**
 * JavaFX simple web browser
 * @author Shane Khant Soe Moe (Yahu)
 * Feb 26, 2018
 * */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainBrowser extends Application {

	private final WebView BROWSER = new WebView();
	private final WebEngine ENGINE = BROWSER.getEngine();
	private TextField urlField = new TextField();
	private final ScrollPane SCROLL_PANE = new ScrollPane();
	private Button refreshButton;
	private String urlString;

	/*main method*/
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*Main Vertical View*/
		VBox mainView = new VBox(20);
		
		//Scene for window
		Scene scene = new Scene(mainView);
		
		//url field setting
		urlField.setPromptText("Enter URL here");

		//web view set up
		SCROLL_PANE.setContent(BROWSER);
		SCROLL_PANE.setFitToHeight(true);
		SCROLL_PANE.setFitToWidth(true);

		/*Action on going to URL*/
		urlField.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {

				boolean httpIncluded = true;
				char[] urlChars = urlField.getText().toCharArray();
				String urlHttp = urlChars[0] + urlChars[1] + urlChars[2] + urlChars[3] + "";

				if(!urlHttp.equalsIgnoreCase("http")) {
					urlString = "http://" + urlField.getText(); 
					ENGINE.load(urlString);
				}

				else {
					ENGINE.load(urlString);
				}
			}
		});

		/*setting for url location if the url is visted successfully*/
		ENGINE.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {
				if(newState == Worker.State.SUCCEEDED){
					urlField.setText(ENGINE.getLocation());
				}
			}	
		});

		/*Refresh the web page*/
		refreshButton = new Button("Refresh");
		refreshButton.setOnAction(e -> {
			ENGINE.load(urlString);
		});
		
		/*Top Panel*/
		HBox topPanel = new HBox(10);
		topPanel.getChildren().addAll(refreshButton, urlField);
		topPanel.setHgrow(urlField, Priority.ALWAYS);
		
		/*Set the main vertical view*/
		mainView.getChildren().addAll(topPanel, SCROLL_PANE);
		for(Node child : mainView.getChildren()) {
			mainView.setMargin(child, new Insets(10, 10, 10, 10));
		}

		primaryStage.setScene(scene);
		primaryStage.setTitle("Shane's Browser");
		primaryStage.show();
	}

}
