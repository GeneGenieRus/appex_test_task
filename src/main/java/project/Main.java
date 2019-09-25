package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import project.service.Service;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class Main extends Application {
    public static ConfigurableApplicationContext appCtx;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
        loader.setControllerFactory(appCtx::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

        Service service = appCtx.getBean(Service.class);
        System.out.println(Arrays.toString(service.getAll().toArray()));

        launch(args);
    }
}
