package org.example.leetcode.trie;

import java.util.*;

/**
 *
 * LeetCode #588. Design In-Memory File System
 * leetcode.com/problems/design-in-memory-file-system/
 *
 * TC: O(N + L + LlogL) - N is the number of files and L is the length of the input string
 *
 */

public class FileSystem {
    private File root;

    public FileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        File curr = root;
        List<String> lsFiles = new ArrayList<>();
        if(!path.equals("/")) {
            String[] dirs = path.split("/");
            for(int i = 1; i < dirs.length; i++) {
                curr = curr.files.get(dirs[i]);
            }
            if(curr.isFile) {
                // if t is file, just add the last element
                lsFiles.add(dirs[dirs.length - 1]);
                return lsFiles;
            }
        }

        // if tail is not file
        List<String> directories = new ArrayList<>(curr.files.keySet());
        Collections.sort(directories);
        return directories;
    }

    public void mkdir(String path) {
        File curr = root;
        String[] dirs = path.split("/");
        for(int i = 1; i < dirs.length; i++) {
            if(!curr.files.containsKey(dirs[i])) {
                curr.files.put(dirs[i], new File());
            }
            curr = curr.files.get(dirs[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        File curr = root;
        String[] dirs = filePath.split("/");
        for(int i = 1; i < dirs.length - 1; i++) {
            curr = curr.files.get(dirs[i]);
        }

        if(!curr.files.containsKey(dirs[dirs.length - 1])) {
            curr.files.put(dirs[dirs.length - 1], new File());
        }
        curr = curr.files.get(dirs[dirs.length - 1]);
        curr.isFile = true;
        curr.content = curr.content + content;
    }

    public String readContentFromFile(String filePath) {
        File curr = root;
        String[] dirs = filePath.split("/");

        for(int i = 1; i < dirs.length; i++) {
            curr = curr.files.get(dirs[i]);
        }

        return curr.content;
    }


    private class File {
        boolean isFile = false;
        Map<String, File> files = new HashMap<>();
        String content = "";
    }

}
