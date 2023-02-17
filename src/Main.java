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
    static int getMarkerIndex(String s){
        for (int i = 0; i < s.length()-4; i++){
            if (allDiff(s.substring(i, i+4))){
                return i+4;
            }

        }
        return 0;
    }
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
                System.out.println(getMarkerIndex(line));
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}