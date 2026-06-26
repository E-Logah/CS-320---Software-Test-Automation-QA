public class Task {
    private final String taskId;
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException("Invalid task ID");
        }
        validateName(name);
        validateDescription(description);

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public String getTaskId() { return taskId; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Invalid task name");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid task description");
        }
    }
}
