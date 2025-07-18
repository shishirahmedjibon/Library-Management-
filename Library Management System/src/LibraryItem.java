

public interface LibraryItem {
    String getTitle();
    String getUniqueId();
    boolean isAvailable();
    void setAvailable(boolean available);
}