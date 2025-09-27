// Interface for playable media
public interface Playable {
    void play();
    void pause();
    void stop();
    int getDuration();
}

// Abstract base class for all media
public abstract class Media implements Playable {
    protected String title;
    protected String creator;
    protected int duration; // in seconds
    protected String genre;
    
    public Media(String title, String creator, int duration, String genre) {
        this.title = title;
        this.creator = creator;
        this.duration = duration;
        this.genre = genre;
    }
    
    // Abstract method for calculating storage requirements
    public abstract double getStorageSize();
    
    // Concrete methods
    public String getTitle() { return title; }
    public String getCreator() { return creator; }
    public int getDuration() { return duration; }
    public String getGenre() { return genre; }
    
    public void displayInfo() {
        System.out.println(title + " by " + creator + " (" + duration + "s)");
    }
}

// Concrete implementations
public class Song extends Media {
    private String album;
    private int trackNumber;
    
    public Song(String title, String artist, int duration, String genre, 
                String album, int trackNumber) {
        super(title, artist, duration, genre);
        this.album = album;
        this.trackNumber = trackNumber;
    }
    
    @Override
    public void play() {
        System.out.println("Playing song: " + title);
    }
    
    @Override
    public void pause() {
        System.out.println("Pausing song: " + title);
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping song: " + title);
    }
    
    @Override
    public double getStorageSize() {
        return duration * 0.128; // MB per second for typical quality
    }
}

public class Podcast extends Media {
    private String host;
    private int episodeNumber;
    private String description;
    
    public Podcast(String title, String creator, int duration, String genre,
                   String host, int episodeNumber, String description) {
        super(title, creator, duration, genre);
        this.host = host;
        this.episodeNumber = episodeNumber;
        this.description = description;
    }
    
    @Override
    public void play() {
        System.out.println("Playing podcast: " + title + " Episode " + episodeNumber);
    }
    
    @Override
    public void pause() {
        System.out.println("Pausing podcast");
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping podcast");
    }
    
    @Override
    public double getStorageSize() {
        return duration * 0.064; // Lower quality than music
    }
}

public class AudioBook extends Media {
    private String narrator;
    private String author;
    private int chapterCount;
    
    public AudioBook(String title, String author, int duration, String genre,
                     String narrator, int chapterCount) {
        super(title, author, duration, genre);
        this.narrator = narrator;
        this.author = author;
        this.chapterCount = chapterCount;
    }
    
    @Override
    public void play() {
        System.out.println("Playing audiobook: " + title + " narrated by " + narrator);
    }
    
    @Override
    public void pause() {
        System.out.println("Pausing audiobook");
    }
    
    @Override
    public void stop() {
        System.out.println("Stopping audiobook");
    }
    
    @Override
    public double getStorageSize() {
        return duration * 0.096; // Medium quality
    }
}