// Abstract User class
public abstract class User {
    protected String username;
    protected String email;
    protected ArrayList<Playlist> playlists;
    
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.playlists = new ArrayList<>();
    }
    
    public abstract boolean canSkipAds();
    public abstract int getMaxPlaylistCount();
    public abstract boolean canDownload();
    
    public void createPlaylist(String name) {
        if (playlists.size() < getMaxPlaylistCount()) {
            playlists.add(new Playlist(name, this));
        } else {
            System.out.println("Maximum playlist limit reached");
        }
    }
}

public class FreeUser extends User {
    private int adsListened;
    
    public FreeUser(String username, String email) {
        super(username, email);
        this.adsListened = 0;
    }
    
    @Override
    public boolean canSkipAds() {
        return false;
    }
    
    @Override
    public int getMaxPlaylistCount() {
        return 5;
    }
    
    @Override
    public boolean canDownload() {
        return false;
    }
    
    public void listenToAd() {
        adsListened++;
    }
}

public class PremiumUser extends User {
    private boolean isOfflineMode;
    
    public PremiumUser(String username, String email) {
        super(username, email);
        this.isOfflineMode = false;
    }
    
    @Override
    public boolean canSkipAds() {
        return true;
    }
    
    @Override
    public int getMaxPlaylistCount() {
        return Integer.MAX_VALUE; // Unlimited
    }
    
    @Override
    public boolean canDownload() {
        return true;
    }
    
    public void enableOfflineMode() {
        this.isOfflineMode = true;
    }
}

// Playlist class demonstrating composition
public class Playlist {
    private String name;
    private ArrayList<Media> mediaList;
    private User owner;
    private boolean isPublic;
    
    public Playlist(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.mediaList = new ArrayList<>();
        this.isPublic = false;
    }
    
    public void addMedia(Media media) {
        mediaList.add(media);
    }
    
    public void removeMedia(Media media) {
        mediaList.remove(media);
    }
    
    public void playAll() {
        for (Media media : mediaList) {
            media.play();
        }
    }
    
    public int getTotalDuration() {
        return mediaList.stream().mapToInt(Media::getDuration).sum();
    }
    
    public double getTotalStorageSize() {
        return mediaList.stream().mapToDouble(Media::getStorageSize).sum();
    }
}