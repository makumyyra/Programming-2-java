package part03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import part01.Commit;

public class Main {

    public static void main(String[] args) throws IOException {
        Path logFile = Path.of("pizza-commits.txt");
        String fileContents = Files.readString(logFile, StandardCharsets.UTF_8);
        
        String[] contents = fileContents.split("\n\n");
        Map<String, String> original = new HashMap<>();
        List<String> sorted = new LinkedList<>();
        
        for (String commit : contents) {
        	Commit c = new Commit(commit);
        	String parent = c.getParentId();
        	original.put(parent, commit);        	
        }
                
        String first = original.get("None");
        Commit latestCommit = new Commit(first);
        sorted.add(first);
        original.remove("None");
                
        while(original.size()>0) {
        	String currentParent = latestCommit.getCommitId();
        	String currentCommit = original.get(currentParent);
        	sorted.add(currentCommit);
        	latestCommit.setCommitBody(currentCommit);
        	original.remove(currentParent);	
        }
        
        for (String sortedCommit : sorted) {
        	System.out.println(sortedCommit);
        }
    }
}
