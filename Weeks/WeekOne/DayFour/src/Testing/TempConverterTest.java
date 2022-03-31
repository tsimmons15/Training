package Testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TempConverterTest {

    private double[] celsius = new double[] {
            0, 30, -30, 100, -100, -280
    };

    private double[] fahrenheit = new double[] {
            0, 35, 100, -100, -35
    };

    private double[] kelvin = new double[] {

    };

    TempConverter converter;

    @Test
    void celsiusToFahrenheit() {
        converter = new TemperatureConverter();
        double result = converter.convertCelsiusToFahr(celsius[0]);
        Assertions.assertEquals(32, result, .1, celsius[0] + " failed check.");
        result = converter.convertCelsiusToFahr(celsius[1]);
        Assertions.assertEquals(86, result, .1, celsius[1] + " failed check.");
        result = converter.convertCelsiusToFahr(celsius[2]);
        Assertions.assertEquals(-22, result, .1, celsius[2] + " failed check.");
        result = converter.convertCelsiusToFahr(celsius[3]);
        Assertions.assertEquals(212, result, .1, celsius[3] + " failed check.");
        result = converter.convertCelsiusToFahr(celsius[4]);
        Assertions.assertEquals(-148, result, .1, celsius[2] + " failed check.");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            converter.convertCelsiusToFahr(celsius[5]);
        }, celsius[5] + " failed to throw exception.");
    }
}
