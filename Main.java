import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
                case "0": //exit
                    System.exit(0);
                case "1": //log a mood
                    try{
                        moodLog.add(createMoodEntry());
                    }
                    catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case "2": //view past moods
                    viewPreviousMoods();
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }

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

    public static void viewPreviousMoods(){
        for (MoodEntry mood: moodLog){
            System.out.println(mood.getDate().toString() + mood.getMood() + mood.getNote());
        }
    }
}