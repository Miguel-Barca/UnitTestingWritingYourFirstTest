package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicCalendar {

    private final LocalDate today;
    private List<PatientAppointment> appointments;

    public ClinicCalendar(LocalDate today) {
        this.today = today;
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String patientFirstName, String patientLastName, String doctorKey,
                               String dateTime) {
        Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(dateTime, today); //call to our utility class static method
        PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName,
                localDateTime, doc);
        appointments.add(appointment);
    }

    //the following method is working as an utility method. So it was extracted to a new class
    // -> making our ClinicCalendar class quite a bit smaller
//    private LocalDateTime convertToDateTimeFromString(String dateTime) {
//      LocalDateTime localDateTime;
//      try {
//         if (dateTime.toLowerCase().startsWith("today")) {
//            String[] parts = dateTime.split(" ", 2);
//            LocalTime time = LocalTime.parse(parts[1].toUpperCase(),
//               DateTimeFormatter.ofPattern("h:mm a", Locale.US));
//            localDateTime = LocalDateTime.of(today, time);
//         }
//         else {
//            localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
//               DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
//         }
//      } catch (Throwable t) {
//         throw new RuntimeException("Unable to create date time from: [" +
//            dateTime + "], please enter with format [M/d/yyyy h:mm a], " + t.getMessage());
//      }
//      return localDateTime;
//    }

    public List<PatientAppointment> getAppointments() {
        return this.appointments;
    }

    public List<PatientAppointment> getUpcomingAppointments() {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().isAfter(today))
                .collect(Collectors.toList());
    }

    public boolean hasAppointment(LocalDate date) {
        return appointments.stream().anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
    }

    public List<PatientAppointment> getTodayAppointments() {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
                .collect(Collectors.toList());
    }

    public List<PatientAppointment> getTomorrowAppointments() {
        LocalDate tomorrow = today.plusDays(1);
        return getAppointmentsForDate(tomorrow);
    }

    private List<PatientAppointment> getAppointmentsForDate(LocalDate tomorrow) {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(tomorrow))
                .collect(Collectors.toList());
    }
}