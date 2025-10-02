import java.time.LocalTime;


public class TaskFactory {
    private static String normalizeTime(String input){
        input = input.trim();

        String[] parts = input.split(":");
        String hour, minute;
        try{
            if(parts.length == 1) {
                hour = parts[0];
                minute =  "00";
            } else if(parts.length == 2) {
                hour = parts[0];
                minute = parts[1];      
            }else{
                throw new Exception("Error: Invalid Time Formate HH:mm");
            }
        }catch(Exception e){
            throw new RuntimeException("Error: Invalid Time Formate HH:mm");
        }
        int h, m;
        try{
            h = Integer.parseInt(hour);
            m = Integer.parseInt(minute);
            if(h<0 || h>23 || m<0 || m>59){
                throw new Exception("Error: Invalid Time Formate HH:mm");
            }
        }catch(Exception e){
            throw new NumberFormatException("Error: Invalid Time Formate HH:mm");
        }
        
        return hour + ":" + minute;
    }
    public static Task createTask(String description, String start, String end, String priority) throws Exception {
        LocalTime startTime, endTime;
 
        try {
            if(start.length()!=2){
                start = "0"+start;
            }
            if(end.length()!=2){
                end = "0"+end;
            }
            startTime = LocalTime.parse(normalizeTime(start));
            endTime = LocalTime.parse(normalizeTime(end));
        } catch (Exception e) {
            throw new Exception("Error: Invalid time format. Use HH:mm");
        }
        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            throw new Exception("Error: End time must be after start time.");
        }
        return new Task(description, startTime, endTime, priority);
    }
}