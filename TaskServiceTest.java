import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    private Task createTask(String id) {
        return new Task(id, "Task Name", "Task description");
    }

    @Test
    void testAddTaskSuccessfully() {
        TaskService service = new TaskService();
        Task task = createTask("T12345");
        service.addTask(task);
        assertEquals(task, service.getTask("T12345"));
        assertEquals(1, service.getTaskCount());
    }

    @Test
    void testCannotAddNullTask() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
    }

    @Test
    void testCannotAddDuplicateTaskId() {
        TaskService service = new TaskService();
        service.addTask(createTask("T12345"));
        assertThrows(IllegalArgumentException.class, () -> service.addTask(createTask("T12345")));
    }

    @Test
    void testDeleteTaskSuccessfully() {
        TaskService service = new TaskService();
        service.addTask(createTask("T12345"));
        service.deleteTask("T12345");
        assertNull(service.getTask("T12345"));
        assertEquals(0, service.getTaskCount());
    }

    @Test
    void testCannotDeleteTaskThatDoesNotExist() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask("T99999"));
    }

    @Test
    void testUpdateTaskFieldsSuccessfully() {
        TaskService service = new TaskService();
        service.addTask(createTask("T12345"));
        service.updateName("T12345", "Updated Task");
        service.updateDescription("T12345", "Updated description");
        assertEquals("Updated Task", service.getTask("T12345").getName());
        assertEquals("Updated description", service.getTask("T12345").getDescription());
    }

    @Test
    void testCannotUpdateTaskThatDoesNotExist() {
        TaskService service = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> service.updateName("T99999", "Updated Task"));
        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("T99999", "Updated description"));
    }

    @Test
    void testUpdateRejectsInvalidFieldValues() {
        TaskService service = new TaskService();
        service.addTask(createTask("T12345"));
        assertThrows(IllegalArgumentException.class, () -> service.updateName("T12345", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("T12345", null));
    }
}
