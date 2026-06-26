import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    private Date getFutureDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    private Appointment createAppointment(String id) {
        return new Appointment(id, getFutureDate(), "Dental appointment");
    }

    @Test
    void testAddAppointmentSuccessfully() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = createAppointment("A12345");
        service.addAppointment(appointment);
        assertEquals(appointment, service.getAppointment("A12345"));
        assertEquals(1, service.getAppointmentCount());
    }

    @Test
    void testCannotAddNullAppointment() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }

    @Test
    void testCannotAddDuplicateAppointmentId() {
        AppointmentService service = new AppointmentService();
        service.addAppointment(createAppointment("A12345"));
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(createAppointment("A12345")));
    }

    @Test
    void testDeleteAppointmentSuccessfully() {
        AppointmentService service = new AppointmentService();
        service.addAppointment(createAppointment("A12345"));
        service.deleteAppointment("A12345");
        assertNull(service.getAppointment("A12345"));
        assertEquals(0, service.getAppointmentCount());
    }

    @Test
    void testCannotDeleteAppointmentThatDoesNotExist() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("A99999"));
    }

    @Test
    void testMultipleAppointmentsCanBeAddedWithUniqueIds() {
        AppointmentService service = new AppointmentService();
        Appointment appointmentOne = createAppointment("A12345");
        Appointment appointmentTwo = createAppointment("B12345");
        service.addAppointment(appointmentOne);
        service.addAppointment(appointmentTwo);
        assertEquals(2, service.getAppointmentCount());
        assertEquals(appointmentOne, service.getAppointment("A12345"));
        assertEquals(appointmentTwo, service.getAppointment("B12345"));
    }
}
