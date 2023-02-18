import java.util.*;

public class Directory {
    String name;
    ArrayList<File> files;
    Directory parent;
    ArrayList<Directory> children;

    public Directory(String name, ArrayList<File> files, Directory parent, ArrayList<Directory> children){
        this.name = name;
        this.files = files;
        this.parent = parent;
        this.children = children;
    }

    public Directory getSubDirectory(String target){
        for (Directory d: children){
            if (d.name.equals(target)){
                return d;
            }
        }
        return null;
    }

    public long sumOfDirectories(Stack<Directory> S,  Set<Directory> visited, long sz){
        S.push(this);
        long dirSize = this.size(new Stack<>(), new HashSet<>(), 0);
        if (dirSize <= 100000){
            sz += dirSize;
        }

        while (!S.isEmpty()){
            Directory dir = S.pop();
            if (!visited.contains(dir)){
                for (Directory child: dir.children){
                    S.push(child);
                    long t = child.size(new Stack<>(), new HashSet<>(), 0);
                    if (t <=  100000){
                        sz += t;
                    }
                }
            }
        }
        return sz;
    }
    public long size(Stack<Directory> S,  Set<Directory> visited, long sz){
        S.push(this);
        for (File f: files){
            sz += f.size;
        }

        while (!S.isEmpty()){
            Directory dir = S.pop();
            if (!visited.contains(dir)) {
                visited.add(dir);
                for (Directory child : dir.children) {
                    S.push(child);
                    sz = child.size(S, visited, sz);
                }
            }
        }
        return sz;
    }
    public void printStructure(Stack<Directory> S,  Set<Directory> visited, String ident){
        System.out.printf("%s- %s (dir)\n", ident, name);
        S.add(this);
        ident += "\t";

        while (!S.isEmpty()){
            Directory dir = S.pop();
            if (!visited.contains(dir)){
                visited.add(dir);
                for (Directory child: dir.children){
                    S.push(child);
                    child.printStructure(S, visited, ident);
                }
            }
        }

        for (File f: files){
            System.out.println(ident + f);
        }
    }

}
