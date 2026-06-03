import java.time.LocalDate;

public class MoodEntry {
    private LocalDate date;
    private Emotions mood;
    private String note;

    public MoodEntry(LocalDate date, Emotions mood, String note){
        this.date = date;
        this.mood = mood;
        this.note = note;
    }

    public Emotions getMood() {
        return mood;
    }

    public void setMood(Emotions mood) {
        this.mood = mood;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
