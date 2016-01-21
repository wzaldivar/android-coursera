package vandy.mooc.model.aidl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vandy.mooc.model.aidl.WeatherData.Main;
import vandy.mooc.model.aidl.WeatherData.Sys;
import vandy.mooc.model.aidl.WeatherData.Weather;
import vandy.mooc.model.aidl.WeatherData.Wind;

import android.util.JsonReader;
import android.util.JsonToken;

/**
 * Parses the Json weather data returned from the Weather Services API
 * and returns a List of WeatherData objects that contain this data.
 */
public class WeatherDataJsonParser {
    /**
     * Used for logging purposes.
     */
    private final String TAG =
            this.getClass().getCanonicalName();

    /**
     * Parse the @a inputStream and convert it into a List of JsonWeather
     * objects.
     */
    public List<WeatherData> parseJsonStream(InputStream inputStream)
            throws IOException {

        // TODO -- you fill in here.
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        return parseJsonWeatherDataArray(reader);
    }

    /**
     * Parse a Json stream and convert it into a List of WeatherData
     * objects.
     */
    public List<WeatherData> parseJsonWeatherDataArray(JsonReader reader)
            throws IOException {
        // TODO -- you fill in here.
        List<WeatherData> weatherDatas = new ArrayList<WeatherData>();

        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            while (reader.hasNext()) {
                weatherDatas.add(parseJsonWeatherData(reader));
            }
            reader.endArray();

        } else if (reader.peek() == JsonToken.BEGIN_OBJECT) {
            weatherDatas.add(parseJsonWeatherData(reader));

        }

        return weatherDatas;
    }

    /**
     * Parse a Json stream and return a WeatherData object.
     */
    public WeatherData parseJsonWeatherData(JsonReader reader)
            throws IOException {

        // TODO -- you fill in here.
        WeatherData weatherData = new WeatherData();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals(WeatherData.name_JSON)) {
                weatherData.setName(reader.nextString());

            } else if (name.equals(WeatherData.dt_JSON)) {
                weatherData.setDate(reader.nextLong());

            } else if (name.equals(WeatherData.cod_JSON)) {
                weatherData.setCod(reader.nextLong());

            } else if (name.equals(WeatherData.weather_JSON)) {
                weatherData.setWeathers(parseWeathers(reader));

            } else if (name.equals(WeatherData.sys_JSON)) {
                weatherData.setSys(parseSys(reader));

            } else if (name.equals(WeatherData.main_JSON)) {
                weatherData.setMain(parseMain(reader));

            } else if (name.equals(WeatherData.wind_JSON)) {
                weatherData.setWind(parseWind(reader));

            } else if (name.equals(WeatherData.message_JSON)) {
                weatherData.setMessage(reader.nextString());

            } else {
                reader.skipValue();

            }
        }
        reader.endObject();

        return weatherData;
    }

    /**
     * Parse a Json stream and return a List of Weather objects.
     */
    public List<Weather> parseWeathers(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
        List<Weather> weathers = new ArrayList<Weather>();


        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            while (reader.hasNext()) {
                weathers.add(parseWeather(reader));
            }
            reader.endArray();

        } else if (reader.peek() == JsonToken.BEGIN_OBJECT) {
            weathers.add(parseWeather(reader));

        }

        return weathers;
    }

    /**
     * Parse a Json stream and return a Weather object.
     */
    public Weather parseWeather(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
        Weather weather = new Weather();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals(Weather.id_JSON)) {
                weather.setId(reader.nextLong());

            } else if (name.equals(Weather.main_JSON)) {
                weather.setMain(reader.nextString());

            } else if (name.equals(Weather.description_JSON)) {
                weather.setDescription(reader.nextString());

            } else if (name.equals(Weather.icon_JSON)) {
                weather.setIcon(reader.nextString());

            } else {
                reader.skipValue();

            }
        }
        reader.endObject();

        return weather;
    }

    /**
     * Parse a Json stream and return a Main Object.
     */
    public Main parseMain(JsonReader reader)
            throws IOException {
        // TODO -- you fill in here.
        Main main = new Main();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals(Main.temp_JSON)) {
                main.setTemp(reader.nextDouble());

            } else if (name.equals(Main.humidity_JSON)) {
                main.setHumidity(reader.nextLong());

            } else if (name.equals(Main.pressure_JSON)) {
                main.setPressure(reader.nextDouble());

            } else {
                reader.skipValue();

            }
        }
        reader.endObject();

        return main;
    }

    /**
     * Parse a Json stream and return a Wind Object.
     */
    public Wind parseWind(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
        Wind wind = new Wind();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals(Wind.speed_JSON)) {
                wind.setSpeed(reader.nextDouble());

            } else if (name.equals(Wind.deg_JSON)) {
                wind.setDeg(reader.nextDouble());

            } else {
                reader.skipValue();

            }
        }
        reader.endObject();

        return wind;
    }

    /**
     * Parse a Json stream and return a Sys Object.
     */
    public Sys parseSys(JsonReader reader)
            throws IOException {
        // TODO -- you fill in here.
        Sys sys = new Sys();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals(Sys.sunrise_JSON)) {
                sys.setSunrise(reader.nextLong());

            } else if (name.equals(Sys.sunset_JSON)) {
                sys.setSunset(reader.nextLong());

            } else if (name.equals(Sys.country_JSON)) {
                sys.setCountry(reader.nextString());

            } else if (name.equals(Sys.message_JSON)) {
                sys.setMessage(reader.nextDouble());

            } else {
                reader.skipValue();

            }
        }
        reader.endObject();

        return sys;
    }
}
