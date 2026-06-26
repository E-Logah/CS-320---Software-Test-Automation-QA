import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testTaskCreatedSuccessfully() {
        Task task = new Task("T12345", "Task Name", "Task description");
        assertEquals("T12345", task.getTaskId());
        assertEquals("Task Name", task.getName());
        assertEquals("Task description", task.getDescription());
    }

    @Test
    void testTaskAcceptsBoundaryValues() {
        Task task = new Task("1234567890", "12345678901234567890", "12345678901234567890123456789012345678901234567890");
        assertEquals("1234567890", task.getTaskId());
        assertEquals("12345678901234567890", task.getName());
        assertEquals("12345678901234567890123456789012345678901234567890", task.getDescription());
    }

    @Test
    void testTaskIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "Task Name", "Task description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("T1234567890", "Task Name", "Task description"));
    }

    @Test
    void testTaskIdIsNotUpdatable() {
        Task task = new Task("T12345", "Task Name", "Task description");
        assertEquals("T12345", task.getTaskId());
    }

    @Test
    void testNameCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Task("T12345", null, "Task description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("T12345", "123456789012345678901", "Task description"));
    }

    @Test
    void testDescriptionCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Task("T12345", "Task Name", null));
        assertThrows(IllegalArgumentException.class, () -> new Task("T12345", "Task Name", "123456789012345678901234567890123456789012345678901"));
    }

    @Test
    void testNameAndDescriptionCanBeUpdated() {
        Task task = new Task("T12345", "Task Name", "Task description");
        task.setName("Updated Task");
        task.setDescription("Updated description");
        assertEquals("Updated Task", task.getName());
        assertEquals("Updated description", task.getDescription());
    }

    @Test
    void testSettersRejectInvalidValues() {
        Task task = new Task("T12345", "Task Name", "Task description");
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
        assertThrows(IllegalArgumentException.class, () -> task.setName("123456789012345678901"));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("123456789012345678901234567890123456789012345678901"));
    }
}
