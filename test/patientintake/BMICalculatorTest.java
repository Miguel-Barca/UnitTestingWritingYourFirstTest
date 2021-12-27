package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BMI calculator test")
class BMICalculatorTest {

    @Test
    @DisplayName("calculate BMI to two places correctly via inches and pounds")
    void calculateCorrectly() {
        assertEquals(19.2, BMICalculator.calculateBmi(69, 130));
        assertEquals(21.52, BMICalculator.calculateBmi(70, 150));
    }

    @Test
    @DisplayName("throw exception if entered incorrect pattern of data")
    void throwExceptionIfInfiniteOrNaNProvided() {
//        LocalDateTime result = DateTimeConverter.convertStringToDateTime("09/01/2018 100 pm",
//                LocalDate.of(2018,9, 1));
        //junit assertion we can  use to verify we get an exception when a certain code is called
        Throwable error = assertThrows(RuntimeException.class, () ->
                BMICalculator.calculateBmi(00,0000));
        assertEquals("Infinite or NaN", error.getMessage());
    }
}