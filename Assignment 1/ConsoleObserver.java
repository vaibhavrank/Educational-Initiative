public class ConsoleObserver implements ScheduleObserver {
    @Override
    public void notifyConflict(Task newTask, Task existingTask) {
        System.out.printf("Error: Task conflicts with existing task "+"%s"+ "%s", existingTask.getDescription(), existingTask);
    }
    @Override
    public void notifyUpdate(String message) {
        System.out.println(message);
    }
}