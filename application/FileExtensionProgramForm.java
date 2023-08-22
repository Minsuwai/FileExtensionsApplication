package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class FileExtensionProgramForm  extends Application {
	static HashMap<String, String> hmap = new HashMap<String, String>();
	TextArea textAreaAllFiles = new TextArea();	
	TextField textFieldExtension = new TextField();	
	TextField textFieldProgram = new TextField();
	TextField textFieldFindExtension = new TextField();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		// Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		// double width = screenSize.getWidth() / 3;
		// double height = screenSize.getHeight() / 2;
		textAreaAllFiles.setStyle("-fx-background-color:rgba(255,0,0,0);");
		textFieldExtension.requestFocus();
		BorderPane borderPane = new BorderPane();
		//pane.setStyle("-fx-background-color: lightgrey;"
		//		+ "-fx-text-fill:red;");
		borderPane.setId("root");
		borderPane.setTop(createTitlePane());
		borderPane.setLeft(createLeftPane());
		borderPane.setRight(createRightPane());

		// Scene scene = new Scene(pane, width, height);
		Scene scene = new Scene(borderPane, 850, 600);
		
		scene.getStylesheets().add
		 (FileExtensionProgramForm.class.getResource("style.css").toExternalForm());
		
		
		primaryStage.setScene(scene);

		primaryStage.setTitle("File Extension Program Form");

		primaryStage.show();
	}
		
	public Pane createTitlePane() {
		GridPane gpane = new GridPane();
		gpane.setPadding(new Insets(10, 10, 10, 10));
		gpane.setVgap(10);
		gpane.setHgap(10);

		Label lblTitle = new Label("File Extension Default Program");
		gpane.add(lblTitle, 0, 0);
		return gpane;
	}

	// Left Start
	public Pane createLeftPane() {
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10, 10, 10, 20));

		textAreaAllFiles.setPrefWidth(250);
		textAreaAllFiles.setPrefHeight(350);
		// txtArea.setScrollLeft(50);
		vBox.getChildren().addAll(textAreaAllFiles, createLeftBottom());

		return vBox;
	}

	public Pane createLeftBottom() {
		GridPane gPane = new GridPane();
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(25, 25, 25, 25));

		Label lblExten = new Label("Enter the file extension and then click \n on find default program.");
		// lblExten.translateXProperty();
		textFieldFindExtension = new TextField();
		Button btnfind = new Button("Find Default Program");
		btnfind.setPrefWidth(150);
		btnfind.setPrefHeight(50);

		btnfind.setOnAction(new EventHandler<ActionEvent>() {
			String key = "";
			String value = "";

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (textFieldFindExtension.getText().equals("")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Error Message");
					// alert.setHeaderText("");
					alert.setContentText("Enter extension data to find!");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				} else {
					key = textFieldFindExtension.getText();
					if (FileExtensionProgramForm.hmap.containsKey(key)) {
						value = FileExtensionProgramForm.hmap.getOrDefault(key, value);
						textAreaAllFiles.clear();
						textAreaAllFiles.appendText("File extension: ." + key + "\t opens with " + value + "\n");
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message");
						// alert.setHeaderText("");
						alert.setContentText("No Entry with this key!");
						alert.showAndWait().ifPresent(rs -> {
							if (rs == ButtonType.OK) {
								System.out.println("Pressed OK.");
							}
						});
						textFieldFindExtension.clear();
						textAreaAllFiles.clear();
					}
				}
			}
		});

		Button btnlist = new Button("List All");
		btnlist.setPrefWidth(150);
		btnlist.setPrefHeight(50);
		btnlist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				readDataFile();
				textAreaAllFiles.clear();
				textFieldExtension.clear();
				textFieldProgram.clear();
				textFieldFindExtension.clear();
				showData();
			}
		});

		gPane.add(lblExten, 0, 0);
		gPane.add(textFieldFindExtension, 0, 1);
		gPane.add(btnfind, 1, 1);
		gPane.add(btnlist, 0, 2, 2, 1);
		// GridPane.setColumnSpan(btnlist,1);
		GridPane.setHalignment(btnlist, HPos.CENTER);

		return gPane;
	}

	// Left End
	// Right Start
	public Pane createRightPane() {
		StackPane bgPane = fieldSet("Edit File Extension", 350);

		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(30, 10, 10, 10));

		Pane upPane = fieldSet("Add Entry", 200);
		upPane.getChildren().add(getUpForm());

		Pane downPane = fieldSet("Delete Entry", 200);
		downPane.getChildren().add(getDownForm());

		Button btnclear = new Button("Clear All Entries");
		btnclear.setPrefWidth(150);
		btnclear.setPrefHeight(50);
		btnclear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				// alert.setHeaderText("");
				alert.setContentText("Are you sure to delete all entries!");
				alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
						System.out.println("Pressed OK.");
						FileExtensionProgramForm.hmap.clear();
						updateDataFile();
						textAreaAllFiles.clear();
						textFieldExtension.clear();
						textFieldProgram.clear();
						textFieldFindExtension.clear();
					}
				});

			}
		});
		vBox.setAlignment(Pos.BOTTOM_CENTER);

		vBox.getChildren().addAll(upPane, downPane, btnclear);

		bgPane.getChildren().add(vBox);

		return bgPane;
	}

	public StackPane fieldSet(String titleString, double value) {
		StackPane pane = new StackPane();

		GridPane grid = new GridPane();

		Label title = new Label(titleString);
		title.setStyle("-fx-translate-y: -10;\n" + "-fx-background-color: lightgrey;");
		title.setPadding(new Insets(0, 20, 0, 20));
		// title.setPrefHeight(30);

		StackPane.setAlignment(title, Pos.TOP_LEFT);

		grid.setStyle("-fx-border-insets: 20 15 15 15");
		// grid.setStyle("-fx-background-color: white");
		grid.setStyle("-fx-border-color: black");

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setPrefWidth(value);

		pane.setPadding(new Insets(25, 25, 25, 25));
		pane.getChildren().addAll(grid, title);
		pane.setMargin(title, new Insets(0, 0, 0, 15));
		return pane;
	}

	public GridPane getUpForm() {
		GridPane gPane = new GridPane();
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(25, 25, 25, 25));

		Label lblExten = new Label("Extension");
		textFieldExtension = new TextField();
		Label lblProg = new Label("Program");
		textFieldProgram = new TextField();
		Button btnadd = new Button("Add New Entry");
		btnadd.setPrefWidth(150);
		btnadd.setPrefHeight(50);

		btnadd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				readDataFile();
				if (!textFieldExtension.getText().equals("") && !textFieldProgram.getText().equals("")) {					
					if (!FileExtensionProgramForm.hmap.containsKey(textFieldExtension.getText())) {
						FileExtensionProgramForm.hmap.put(textFieldExtension.getText(), textFieldProgram.getText());
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Confirm Message");
						// alert.setHeaderText("");
						alert.setContentText("Successfully New Entry added!");
						alert.showAndWait().ifPresent(rs -> {
							if (rs == ButtonType.OK) {
								System.out.println("Pressed OK.");
							}
						});
						textAreaAllFiles.appendText(
								"File extension: ." + textFieldExtension.getText() + "\t opens with " + textFieldProgram.getText() + "\n");
						updateDataFile();
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR Message");
						// alert.setHeaderText("");
						String s = "An entry with Key = " + textFieldExtension.getText() + " already exists.!";
						alert.setContentText(s);
						alert.showAndWait().ifPresent(rs -> {
							if (rs == ButtonType.OK) {
								System.out.println("Pressed OK.");
							}
						});
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					// alert.setHeaderText("");
					alert.setContentText("Enter all required data!");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				}
				// showData();
				textFieldExtension.clear();
				textFieldProgram.clear();
				textFieldFindExtension.clear();
			}
		});

		gPane.add(lblExten, 0, 0);
		gPane.add(textFieldExtension, 1, 0);
		gPane.add(lblProg, 0, 1);
		gPane.add(textFieldProgram, 1, 1);
		gPane.add(btnadd, 1, 2);

		return gPane;
	}

	public GridPane getDownForm() {
		GridPane gPane = new GridPane();
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(10, 25, 25, 50));

		Label lblExten = new Label("Use the find button and then click \n on delete current entry. ");
		Button btnDelete = new Button("Delete Current Entry");
		btnDelete.setPrefWidth(150);
		btnDelete.setPrefHeight(50);

		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (textFieldFindExtension.getText().equals("")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					// alert.setHeaderText("");
					alert.setContentText("Firstly Need to find extension key!");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				} else {
					if (!FileExtensionProgramForm.hmap.remove(textFieldFindExtension.getText()).equals(null)) {
						updateDataFile();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Confirm Message");
						// alert.setHeaderText("");
						alert.setContentText("Successfully Entry deleted!");
						alert.showAndWait().ifPresent(rs -> {
							if (rs == ButtonType.OK) {
								System.out.println("Pressed OK.");
							}
						});
						textFieldFindExtension.clear();
						textAreaAllFiles.clear();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message");
						// alert.setHeaderText("");
						alert.setContentText("No Entry with this key!");
						alert.showAndWait().ifPresent(rs -> {
							if (rs == ButtonType.OK) {
								System.out.println("Pressed OK.");
							}
						});
					}
				}

			}
		});

		gPane.add(lblExten, 0, 0);
		gPane.add(btnDelete, 0, 1);
		gPane.setAlignment(Pos.CENTER);
		return gPane;
	}
	// Right Pane End

	// Show all file in textarea

	@SuppressWarnings("rawtypes")
	public void showData() {
		/* Display content using Iterator */
		Set set = FileExtensionProgramForm.hmap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry) iterator.next();
			System.out.print("key is: " + mentry.getKey() + " & Value is: ");
			System.out.println(mentry.getValue());
			textAreaAllFiles
					.appendText("File extension: . " + mentry.getKey() + " \t opens with " + mentry.getValue() + "\n");
		}
	}

	@SuppressWarnings("resource")
	public void updateDataFile() {
		try {

			FileOutputStream outputFile = new FileOutputStream("d:\\test.txt");
			ObjectOutputStream output = new ObjectOutputStream(outputFile);
			output.writeObject(FileExtensionProgramForm.hmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "resource" })
	public void readDataFile() {
		try {

			FileInputStream inputFile = new FileInputStream("d:\\test.txt");			
			ObjectInputStream input = new ObjectInputStream(inputFile);
			FileExtensionProgramForm.hmap = (HashMap<String, String>) input.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
