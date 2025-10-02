public interface ScheduleObserver {
    void notifyConflict(Task newTask, Task existingTask);
    void notifyUpdate(String message);
}