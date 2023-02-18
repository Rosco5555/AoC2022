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
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("./input.txt"))) {
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(getMarkerIndex(line, 14));
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}