package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class HeatIndex implements Observer, DisplayElement{
    private float heatIndex;
    private Subject weatherData;
       private float temperature;
    private float humidity;

    public HeatIndex(Subject weatherData) {
        this.weatherData = weatherData;
        heatIndex = 0.0f;


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
        html += String.format("<label>Heat Index: %s</label><br />", heatIndex);


        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        heatIndex = computeHeatIndex(temperature, humidity);
    }
    public float computeHeatIndex(float temperature, float humidity){
return (float) (
                (16.923 + (0.185212 * temperature) + (5.37941 * humidity) - (0.100254 * temperature * humidity)
                        + (0.00941695 * (temperature * temperature)) + (0.00728898 * (humidity * humidity))
                        + (0.000345372 * (temperature * temperature * humidity)) - (0.000814971 * (temperature * humidity * humidity))
                        + (0.0000102102 * (temperature * temperature * humidity * humidity))
                        - (0.000038646 * (temperature * temperature * temperature)) + (0.0000291583 * (humidity * humidity * humidity))
                        + (0.00000142721 * (temperature * temperature * temperature * humidity))
                        + (0.000000197483 * (temperature * humidity * humidity * humidity))
                        - (0.0000000218429 * (temperature * temperature * temperature * humidity * humidity))
                        + 0.000000000843296 * (temperature * temperature * humidity * humidity * humidity))
                        - (0.0000000000481975 * (temperature * temperature * temperature * humidity * humidity * humidity)));


    }

    @Override
    public String name() {
        return "Heat Index";
    }

    @Override
    public String id() {
        return "heat-index";
    }
    public void subscribe() {
        weatherData.registerObserver(this);
    }
    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
