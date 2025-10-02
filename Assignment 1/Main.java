import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConsoleObserver());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Astronaut Schedule Organizer Menu");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");
            System.out.print("Enter option: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Description: "); String desc = sc.nextLine();
                        System.out.print("Start Time (HH:mm): "); String start = sc.nextLine();
                        System.out.print("End Time (HH:mm): "); String end = sc.nextLine();
                        System.out.print("Priority (High/Medium/Low): "); String prio = sc.nextLine();
                        Task task = TaskFactory.createTask(desc, start, end, prio);
                        if (manager.addTask(task)) System.out.println("Task added successfully. No conflicts.");
                        break;
                    case "2":
                        System.out.print("Description to remove: "); String rdesc = sc.nextLine();
                        if (manager.removeTask(rdesc)) System.out.println("Task removed successfully.");
                        break;
                    case "3":
                        List<Task> all = manager.getTasksSortedByTime();
                        if (all.isEmpty()) System.out.println("No tasks scheduled for the day.");
                        else for (Task t : all) System.out.println(t);
                        break;
                    case "4":
                        System.out.print("Description of task to edit: "); String edesc = sc.nextLine();
                        System.out.print("New Description: "); String newDesc = sc.nextLine();
                        System.out.print("New Start Time (HH:mm): "); String newStart = sc.nextLine();
                        System.out.print("New End Time (HH:mm): "); String newEnd = sc.nextLine();
                        System.out.print("New Priority (High/Medium/Low): "); String newPrio = sc.nextLine();
                        Task edited = TaskFactory.createTask(newDesc, newStart, newEnd, newPrio);
                        if (manager.editTask(edesc, edited)) System.out.println("Task edited successfully.");
                        break;
                    case "5":
                        System.out.print("Description to mark as completed: "); String cdesc = sc.nextLine();
                        if (manager.markCompleted(cdesc)) System.out.println("Task marked as completed.");
                        break;
                    case "6":
                        System.out.print("Priority to view (High/Medium/Low): "); String vprio = sc.nextLine();
                        List<Task> prioList = manager.getTasksByPriority(vprio);
                        if (prioList.isEmpty()) System.out.println("No tasks found for specified priority.");
                        else for (Task pt : prioList) System.out.println(pt);
                        break;
                    case "7":
                        System.out.println("Exiting. Goodbye.");
                        Logger.getInstance().info("Application exited.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                Logger.getInstance().error(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }
}