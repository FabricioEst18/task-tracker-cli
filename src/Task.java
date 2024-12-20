import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int lastId = 0;  // Static variable to keep track of the last ID assigned
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // DateTimeFormatter for serializing/deserializing dates
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public String toJson() {
        return "{\"id\":\"" + id + "\", \"description\":\"" + description.strip() + "\", \"status\":\"" + status.toString() +
                "\", \"createdAt\":\"" + createdAt.format(formatter) + "\", \"updatedAt\":\"" + updatedAt.format(formatter) + "\"}";
    }

    public static Task fromJson(String json) {
        String[] json1 = json.replace("{", "").replace("}", "")
                .replace("\"", "").replace(":", ",").split(",");
        String id = json1[1].strip();
        String description = json1[3];

        String s = json1[5].strip();
        Status status = Status.valueOf(s.toUpperCase().replace(" ", "_"));  // Handle different cases/formats

        if (s.equals("Todo")) {
            status = Status.TODO;
        } else if (s.equals("In progress")) {
            status = Status.IN_PROGRESS;
        } else {
            status = Status.DONE;
        }
        String createdAtStr = json1[7];
        String updatedAtStr = json1[9];

        Task task = new Task(description);
        task.id = Integer.parseInt(id);
        task.status = status;
        task.createdAt = LocalDateTime.parse(createdAtStr, formatter);
        task.updatedAt = LocalDateTime.parse(updatedAtStr, formatter);

        if (Integer.parseInt(id) > lastId) {
            lastId = Integer.parseInt(id);
        }
        return task;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "id: " + id + ", description: " + description.strip() + ", status: " + status.toString() +
                ", createdAt: " + createdAt.format(formatter) + ", updatedAt: " + updatedAt.format(formatter);
    }
}