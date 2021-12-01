import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;
import patientintake.Doctor;
import patientintake.PatientAppointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {

    @Test
    public void allowEntryOfAnAppointment() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now()); // setup code
        calendar.addAppointment("Jim", "Weaver","avery",
                "09/01/2018 2:00 pm"); // code that calls the system under test
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments); //if it were null the assertion would fail and abort execution with a failure message
        assertEquals(1, appointments.size()); /*Junit most commonly used assertion | the second value is
        the actual value we got back from the system*/

        //setup -> execute -> verify with assertions || a common pattern with any type of automated test
        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Jim", enteredAppt.getPatientFirstName());
        assertEquals("Weaver", enteredAppt.getPatientLastName());
        assertEquals(Doctor.avery, enteredAppt.getDoctor());
        assertSame(Doctor.avery, enteredAppt.getDoctor()); // assertSame will assert if the two variables
        // being compared point to the same object in memory
        assertEquals("9/1/2018 02:00 PM",
                enteredAppt.getAppointmentDateTime()
                        .format(DateTimeFormatter.ofPattern("M/d/yyy hh:mm a", Locale.ENGLISH)));
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Jim", "Weaver","avery",
                "09/01/2018 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2018,9,1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        assertFalse(calendar.hasAppointment(LocalDate.of(2018,9,1)));
    }
}