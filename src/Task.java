public class Task {
    private String id;
    private String description;
    private Status status;

    public Task(String id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
    }

    public void markDone() {
        this.status = Status.DONE;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}
