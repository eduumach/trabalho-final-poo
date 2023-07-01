package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/janelas/telaLogin.fxml"));

			scene = new Scene(fxmlLoader.load());
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void alteraScena(String fxml, double altura, double largura) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
		scene.getWindow().setHeight(altura);
		scene.getWindow().setWidth(largura);
		scene.setRoot(fxmlLoader.load());
	}

	public static Scene getScene() {
		return scene;
	}


	public static void main(String[] args) {
		launch(args);
	}
}
