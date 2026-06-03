import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<MoodEntry> moodLog = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Daily Mood Tracker!");

        while(true){
            System.out.println("""
                    Select an option by typing it's number:
                    1. Log a mood
                    2. View past moods
                    0. Exit
                    """);
            String userInput = scanner.nextLine();

            switch (userInput){
                //exit
                case "0":
                    System.exit(0);
                //log a mood
                case "1":
                    try{
                        moodLog.add(createMoodEntry());
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Emotion not found. Please try again.");
                    }
                    catch (DateTimeParseException e){
                        System.out.println("Date formating incorrect. Please try again");
                    }
                    break;
                //view past moods
                case "2":
                    viewPreviousMoods();
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }

    //handles the submenu for creating an emotion.
    // args: None Return: MoodEntry Throws: IllegalArgumentException, DateTimeParseException
    public static MoodEntry createMoodEntry() throws IllegalArgumentException, DateTimeParseException {
        LocalDate dateToAdd;
        Emotions emotionToAdd;
        String noteToAdd;

        System.out.println("Press enter to use today's date. Otherwise, Enter the date in the format YYYY-MM-DD:");
        String dateInput = scanner.nextLine();
        if (dateInput.isEmpty()){
            dateToAdd = LocalDate.now();
        }
        else {
            dateToAdd = LocalDate.parse(dateInput);
        }

        System.out.println("What emotion are you feeling?");
        for (Emotions emotion: Emotions.values()) {
            System.out.println(emotion);
        }
        emotionToAdd = Emotions.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter any notes you would like to add and press enter:");
        noteToAdd = scanner.nextLine();

        return new MoodEntry(dateToAdd, emotionToAdd, noteToAdd);
    }


    //handles the view previous moods submenu.
    //args: None Return: None
    public static void viewPreviousMoods(){
        System.out.println("""
                View moods by:
                1. All
                2. Date
                3. Emotion
                4. Most common emotion""");
        String userInput = scanner.nextLine();
        switch (userInput){
            //view all
            case "1":
                if (moodLog.isEmpty()){
                    System.out.println("No moods recorded. Add a mood and try again.");
                    break;
                }

                for (MoodEntry mood: moodLog){
                    System.out.println();
                    System.out.println("Date: " + mood.getDate().toString());
                    System.out.println("Mood: " + mood.getMood().toString());
                    System.out.println("Note: " + mood.getNote());
                    System.out.println();
                }
                break;

            //view by date
            case "2":
                if (moodLog.isEmpty()){
                    System.out.println("No moods recorded. Add a mood and try again.");
                    break;
                }

                System.out.println("Enter Date YYYY-MM-DD:");
                String inputDate = scanner.nextLine();
                for (MoodEntry mood: moodLog){
                    if (mood.getDate().equals(LocalDate.parse(inputDate))) {
                        System.out.println();
                        System.out.println("Date: " + mood.getDate().toString());
                        System.out.println("Mood: " + mood.getMood().toString());
                        System.out.println("Note: " + mood.getNote());
                        System.out.println();
                    }
                }
                break;

            //view by emotion
            case "3":
                if (moodLog.isEmpty()){
                    System.out.println("No moods recorded. Add a mood and try again.");
                    break;
                }

                System.out.println("Enter Emotion:");
                String inputEmotion = scanner.nextLine().toUpperCase();
                try {
                    for (MoodEntry mood : moodLog) {
                        if (mood.getMood().equals(Emotions.valueOf(inputEmotion))) {
                            System.out.println();
                            System.out.println("Date: " + mood.getDate().toString());
                            System.out.println("Mood: " + mood.getMood().toString());
                            System.out.println("Note: " + mood.getNote());
                            System.out.println();
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Emotion not found. Please try again.");
                }
                break;
            //view most common emotion
            case "4":
                if (moodLog.isEmpty()){
                    System.out.println("No moods recorded. Add a mood and try again.");
                    break;
                }

                HashMap<Emotions, Integer> frequencyMap = new HashMap<>();
                for (MoodEntry moodEntry: moodLog){
                    frequencyMap.put(moodEntry.getMood(), frequencyMap.getOrDefault(moodEntry.getMood(), 0) + 1);
                }

                Emotions mostCommonEmotion = frequencyMap.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null);


                System.out.println("Your most commonly logged emotion is " + mostCommonEmotion.toString().toLowerCase());
                System.out.println();
                break;
            default:
                System.out.println("unknown option. Returning to main menu.");
        }

    }
}