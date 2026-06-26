import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Invalid appointment ID");
        }
        validateAppointmentDate(appointmentDate);
        validateDescription(description);

        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    public String getAppointmentId() { return appointmentId; }
    public Date getAppointmentDate() { return new Date(appointmentDate.getTime()); }
    public String getDescription() { return description; }

    public void setAppointmentDate(Date appointmentDate) {
        validateAppointmentDate(appointmentDate);
        this.appointmentDate = new Date(appointmentDate.getTime());
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Invalid appointment date");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid description");
        }
    }
}
