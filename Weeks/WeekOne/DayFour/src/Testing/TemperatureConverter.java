package Testing;

public class TemperatureConverter implements TempConverter{

    @Override
    public double convertFahrToCelsius(double fahrenheit) {
        return (fahrenheit - 32)*(5.0/9);
    }

    @Override
    public double convertFahrToKelvin(double fahrenheit) {
        return 0;
    }

    @Override
    public double convertCelsiusToFahr(double celsius) {
        if (celsius <  -273.15) {
            throw new IllegalArgumentException("Absolute zero is -273.15");
        }
        return (celsius * 9 / 5) + 32;
    }

    @Override
    public double convertCelsiusToKelvin(double celsius) {
        return 0;
    }

    @Override
    public double convertKelvinToCelsius(double kelvin) {
        return 0;
    }

    @Override
    public double convertKelvinToFahrenheit(double kelvin) {
        return 0;
    }
}
