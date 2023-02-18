import java.io.*;
import java.util.*;

public class Main {

    static boolean allDiff(String s){
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            charSet.add(s.charAt(i));
        }
        return charSet.size() == s.length();
    }
    static int getMarkerIndex(String s, int n){
        for (int i = 0; i < s.length()-n; i++){
            if (allDiff(s.substring(i, i+n))){
                return i+n;
            }

        }
        return 0;
    }

    static ArrayList<String> readLines(String fileName){
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
    public static void main(String[] args) {
        day7();
    }


    static boolean isChangeDirectory(String s){
        return (s.substring(0,4).equals("$ cd"));
    }

    static boolean isList(String s){
        return (s.substring(2, 4).equals("ls"));
    }

    static boolean isDirectory(String s){
        return (s.substring(0, 3).equals("dir"));
    }

    static boolean isFile(String s){
        return (!isDirectory(s) && !isList(s) && !isChangeDirectory(s));
    }

    static File parseFile(String s){
        String[] size_and_name = s.split(" ");
        long size = Integer.valueOf(size_and_name[0]);
        String name = size_and_name[1];
        return new File(name, size);
    }

    static void day7(){
        ArrayList<String> lines = readLines("./input.txt");
        HashMap<String, Directory> fileSystem = new HashMap<>();
        fileSystem.put("/",new Directory("/", new ArrayList<>(), null, new ArrayList<>()));
        Directory root =  new Directory("/", new ArrayList<>(), null, new ArrayList<>());
        Directory curr_dir = root;
        for (String line: lines){
            line = line.trim();
            if (isChangeDirectory(line)) {
                String target = line.split("cd ")[1];
                switch (target){
                    case "/"  -> { curr_dir = root;} // cd /
                    case ".." -> {curr_dir = curr_dir.parent;} // cd ..
                    default -> {curr_dir = curr_dir.getSubDirectory(target);} // cd x
                }

            }
            if (isDirectory(line)){
                String dirName = line.split(" ")[1];
                Directory new_dir = new Directory(dirName, new ArrayList<>(), curr_dir, new ArrayList<>());
                curr_dir.children.add(new_dir);
            }

            if (isFile(line)){
                curr_dir.files.add(parseFile(line));
            }

        }
        System.out.println(root.sumOfDirectories(new Stack<>(), new HashSet<>(), 0));

    }



    static void day6() {
        ArrayList<String> lines = readLines("./input.txt");
        for (String line: lines){
            System.out.println(getMarkerIndex(line, 14));
        }
    }
}