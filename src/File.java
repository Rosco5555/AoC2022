public class File {
    String name;
    long size;

    public File(String name, long size){
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("- %s (file, size=%s)", name, String.valueOf(size));
    }
}
