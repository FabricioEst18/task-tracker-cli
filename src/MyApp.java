public class MyApp {
    public static void main(String[] args) {
        TaskManager taskTracker = new TaskManager();
        taskTracker.listTasks("Done");
        taskTracker.quit();
    }
}
