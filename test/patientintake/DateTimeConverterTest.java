package patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterTest {

    @Test
    void convertStringCorrectly() {
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                LocalDate.of(2018,9, 1));
        assertEquals(result, LocalDateTime.of(2018,9,1,13,0));
    }

    //Not testing without using "today" keyword
    @Test
    void convertCorrectPatternToDateTime() {
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("09/01/2018 1:00 pm",
                LocalDate.of(2018,9, 1));
        assertEquals(result, LocalDateTime.of(2018,9,1,13,0));
    }

    @Test
    void throwExceptionIfIncorrectPatternProvided() {
//        LocalDateTime result = DateTimeConverter.convertStringToDateTime("09/01/2018 100 pm",
//                LocalDate.of(2018,9, 1));
        //junit assertion we can  use to verify we get an exception when a certain code is called
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("09/01/2018 100 pm",
                LocalDate.of(2018,9, 1)));
        assertEquals("Unable to create date time from: [09/01/2018 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a], " +
                "Text '09/01/2018 100 PM' could not be parsed at index 14", error.getMessage());
    }
}