package pl.sda.JavaFXWeather_TM.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.JavaFXWeather_TM.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WeatherController {
    private String url;
    private String apiKey;

    public WeatherController(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    public Weather getCityWeather(String city) {
        Weather weather = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL apiURL = new URL(url + "?key=" + apiKey + "&q=" + city);
            weather = objectMapper.readValue(apiURL, Weather.class);
        } catch (IOException e) {
            return null;
        }
        return weather;
    }

    public boolean saveWeatherInFile(String city) {
        Weather weather = getCityWeather(city);
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(city + ".json");
        try {
            objectMapper.writeValue(file, weather);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
