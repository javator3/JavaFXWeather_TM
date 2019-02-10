package pl.sda.JavaFXWeather_TM.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.sda.JavaFXWeather_TM.model.Weather;

import java.net.URL;
import java.util.ResourceBundle;


public class WindowController implements Initializable {

    @FXML
    private TextField textFieldTown;
    @FXML
    private Label tempLabel;
    @FXML
    private Label ftempLabel;
    @FXML
    private ImageView iconView;
    @FXML
    private Label errorLabel;
    @FXML
    private Label label1;
    @FXML
    private Label label2;


    public void btnPush() {
        WeatherController weatherController = new WeatherController(
                "http://api.apixu.com/v1/current.json",
                "62ec45207b3a45858ca90607191002");
        Weather cityWeather = weatherController.getCityWeather(textFieldTown.getText());
        if (cityWeather != null) {
            setErrorLabelVisible(false);
            tempLabel.setText(cityWeather.getCurrent().getTemp_c().toString() + "C");
            ftempLabel.setText(cityWeather.getCurrent().getFeelslike_c().toString() + "C");
            iconView.setImage(new Image("http:" + cityWeather.getCurrent().getCondition().getIcon()));
        } else {
            if (!errorLabel.isVisible()) {
                setErrorLabelVisible(true);
            }

        }
    }

    private void setErrorLabelVisible(boolean isVisible) {
        errorLabel.setVisible(isVisible);
        tempLabel.setVisible(!isVisible);
        ftempLabel.setVisible(!isVisible);
        label1.setVisible(!isVisible);
        label2.setVisible(!isVisible);
        iconView.setVisible(!isVisible);
    }

    public void initialize(URL location, ResourceBundle resources) {
        errorLabel.setVisible(false);
        errorLabel.setAlignment(Pos.BASELINE_CENTER);
        tempLabel.setText("");
        ftempLabel.setText("");
    }
}
