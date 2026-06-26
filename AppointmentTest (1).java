import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private Date getFutureDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    private Date getPastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    @Test
    void testAppointmentCreatedSuccessfully() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("A12345", futureDate, "Dental appointment");
        assertEquals("A12345", appointment.getAppointmentId());
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals("Dental appointment", appointment.getDescription());
    }

    @Test
    void testAppointmentAcceptsBoundaryValues() {
        Appointment appointment = new Appointment("1234567890", getFutureDate(), "12345678901234567890123456789012345678901234567890");
        assertEquals("1234567890", appointment.getAppointmentId());
        assertEquals("12345678901234567890123456789012345678901234567890", appointment.getDescription());
    }

    @Test
    void testAppointmentIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, getFutureDate(), "Dental appointment"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("A1234567890", getFutureDate(), "Dental appointment"));
    }

    @Test
    void testAppointmentIdIsNotUpdatable() {
        Appointment appointment = new Appointment("A12345", getFutureDate(), "Dental appointment");
        assertEquals("A12345", appointment.getAppointmentId());
    }

    @Test
    void testAppointmentDateCannotBeNullOrInPast() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("A12345", null, "Dental appointment"));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("A12345", getPastDate(), "Dental appointment"));
    }

    @Test
    void testDescriptionCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("A12345", getFutureDate(), null));
        assertThrows(IllegalArgumentException.class, () -> new Appointment("A12345", getFutureDate(), "123456789012345678901234567890123456789012345678901"));
    }

    @Test
    void testAppointmentDateAndDescriptionCanBeUpdated() {
        Appointment appointment = new Appointment("A12345", getFutureDate(), "Dental appointment");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date newFutureDate = calendar.getTime();
        appointment.setAppointmentDate(newFutureDate);
        appointment.setDescription("Updated description");
        assertEquals(newFutureDate, appointment.getAppointmentDate());
        assertEquals("Updated description", appointment.getDescription());
    }

    @Test
    void testSettersRejectInvalidValues() {
        Appointment appointment = new Appointment("A12345", getFutureDate(), "Dental appointment");
        assertThrows(IllegalArgumentException.class, () -> appointment.setAppointmentDate(null));
        assertThrows(IllegalArgumentException.class, () -> appointment.setAppointmentDate(getPastDate()));
        assertThrows(IllegalArgumentException.class, () -> appointment.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> appointment.setDescription("123456789012345678901234567890123456789012345678901"));
    }

    @Test
    void testAppointmentDateUsesDefensiveCopy() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("A12345", futureDate, "Dental appointment");
        Date retrievedDate = appointment.getAppointmentDate();
        retrievedDate.setTime(0L);
        assertNotEquals(0L, appointment.getAppointmentDate().getTime());
    }
}
