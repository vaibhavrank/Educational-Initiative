import java.util.*;
import java.time.LocalTime;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<ScheduleObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) instance = new ScheduleManager();
        return instance;
    }

    public void addObserver(ScheduleObserver observer) {
        observers.add(observer);
    }

    private void notifyConflict(Task newTask, Task existingTask) {
        for (ScheduleObserver obs : observers)
            obs.notifyConflict(newTask, existingTask);
        Logger.getInstance().error("Task conflict: " + newTask.getDescription() + " with " + existingTask.getDescription());
    }

    private void notifyUpdate(String message) {
        for (ScheduleObserver obs : observers)
            obs.notifyUpdate(message);
        Logger.getInstance().info(message);
    }

    public boolean addTask(Task task) {
        for (Task t : tasks) {
            if (tasksOverlap(task, t)) {
                notifyConflict(task, t);
                return false;
            }
        }
        tasks.add(task);
        notifyUpdate("Task added: " + task.getDescription());
        return true;
    }

    public boolean removeTask(String description) {
        Task found = null;
        for (Task t : tasks) if (t.getDescription().equalsIgnoreCase(description)) { found = t; break; }
        if (found == null) {
            notifyUpdate("Error: Task not found.");
            return false;
        }
        tasks.remove(found);
        notifyUpdate("Task removed: " + description);
        return true;
    }

    public boolean editTask(String oldDescription, Task editedTask) {
        for (Task t : tasks) {
            if (!t.getDescription().equalsIgnoreCase(oldDescription) && tasksOverlap(editedTask, t)) {
                notifyConflict(editedTask, t);
                return false;
            }
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().equalsIgnoreCase(oldDescription)) {
                tasks.set(i, editedTask);
                notifyUpdate("Task edited: " + editedTask.getDescription());
                return true;
            }
        }
        notifyUpdate("Error: Task not found.");
        return false;
    }

    public boolean markCompleted(String description) {
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                t.setCompleted(true);
                notifyUpdate("Task marked as completed: " + description);
                return true;
            }
        }
        notifyUpdate("Error: Task not found.");
        return false;
    }

    public List<Task> getTasksSortedByTime() {
        List<Task> sorted = new ArrayList<>(tasks);
        sorted.sort(Comparator.comparing(Task::getStartTime));
        return sorted;
    }

    public List<Task> getTasksByPriority(String priority) {
        List<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) filtered.add(t);
        }
        filtered.sort(Comparator.comparing(Task::getStartTime));
        return filtered;
    }

    public boolean tasksOverlap(Task a, Task b) {
        return !(a.getEndTime().isBefore(b.getStartTime()) || a.getStartTime().isAfter(b.getEndTime()));
    }
}