package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeConverter should")
class DateTimeConverterTest {

    //inner class that has the desired test methods
    @Nested
    @DisplayName("convert string with 'today' keyword ")
    class TodayTests {
        @Test
        @DisplayName("correctly")
        void convertStringCorrectly() {
            LocalDate today = LocalDate.of(2018, 9, 1);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                    today);
            //adding a customized failure message
            assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0),
                    () -> "Failure to convert 'today' string to expected date time today passed was " + today);
        }

        @Test
        @DisplayName("regardless of letter case")
        void convertStringCorrectlyCaseInsensitive() {
            LocalDate today = LocalDate.of(2018, 9, 1);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("Today 1:00 pm",
                    today);
            //adding a customized failure message
            assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0),
                    () -> "Failure to convert 'today' string to expected date time today passed was " + today);
        }
    }
//moved this 2 methods to an inner class
//    @Test
//    @DisplayName("convert string with 'today' keyword correctly")
//    void convertStringCorrectly() {
//        LocalDate today = LocalDate.of(2018, 9, 1);
//        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
//                today);
//        //adding a customized failure message
//        assertEquals(result, LocalDateTime.of(2018,9,1,13,0),
//                () ->"Failure to convert 'today' string to expected date time today passed was " + today);
//    }
//
//    @Test
//    @DisplayName("convert string with 'today' keyword correctly regardless of letter case")
//    void convertStringCorrectlyCaseInsensitive() {
//        LocalDate today = LocalDate.of(2018, 9, 1);
//        LocalDateTime result = DateTimeConverter.convertStringToDateTime("Today 1:00 pm",
//                today);
//        //adding a customized failure message
//        assertEquals(result, LocalDateTime.of(2018,9,1,13,0),
//                () -> "Failure to convert 'today' string to expected date time today passed was " + today);
//    }

    //Not testing without using "today" keyword
    @Test
    @DisplayName("convert expected date time patter in string correctly")
    void convertCorrectPatternToDateTime() {
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("09/01/2018 1:00 pm",
                LocalDate.of(2018, 9, 1));
        assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0));
    }

    @Test
    @DisplayName("throw exception if entered pattern of string incorrect")
    void throwExceptionIfIncorrectPatternProvided() {
//        LocalDateTime result = DateTimeConverter.convertStringToDateTime("09/01/2018 100 pm",
//                LocalDate.of(2018,9, 1));
        //junit assertion we can  use to verify we get an exception when a certain code is called
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("09/01/2018 100 pm",
                        LocalDate.of(2018, 9, 1)));
        assertEquals("Unable to create date time from: [09/01/2018 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a], " +
                "Text '09/01/2018 100 PM' could not be parsed at index 14", error.getMessage());
    }
}