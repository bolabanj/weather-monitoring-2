package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Observer, DisplayElement{
    private float minTemp;
    private float maxTemp;
    private float avgTemp;
    private Subject weatherData;
    public StatisticsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        this.minTemp = 0;
        this.maxTemp = 0;
        this.avgTemp = 0;




    }

    @Override
    public String display() {

        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Avg Temperature: %s</label><br />", avgTemp);
        html += String.format("<label>Min Temeperature: %s</label><br />", minTemp);
        html += String.format("<label>Max Temp: %s</label>", maxTemp);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        if(temperature < minTemp){
            minTemp = (int) temperature;
        }
        if(temperature > maxTemp){
            maxTemp = (int) temperature;
        }
        avgTemp = (minTemp + maxTemp) / 2;
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics-display";
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }
    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}