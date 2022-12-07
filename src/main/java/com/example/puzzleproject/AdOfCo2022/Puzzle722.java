package com.example.puzzleproject.AdOfCo2022;

import com.example.puzzleproject.StartApplication;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Puzzle722 {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);



    public void puzzle722() {

        List<Directory> allDirectories = createAllDirectories();
            long sumOfAll = 0;
         for(Directory directory: allDirectories){
             if(directory.getTotalSize() <= 100000){
                 sumOfAll += directory.getTotalSize();
                 log.info("directory: " + directory.getName() + " with size: " + directory.getTotalSize() );
                 log.info("total size now: " + sumOfAll);
             }
         }

        log.info("total number is: " + sumOfAll);
    }

    public void puzzle722B() {

        List<Directory> allDirectories = createAllDirectories();
        long totalSize = allDirectories.get(0).getTotalSize();
        long freeSpace = 70000000L - totalSize;
        long spaceNeeded = 30000000 - freeSpace;
        List<Long> directorySizes = new ArrayList<>();
        for(Directory directory: allDirectories){

            if(directory.getTotalSize() >= spaceNeeded){
                directorySizes.add(directory.getTotalSize());
            }
        }
        Collections.sort(directorySizes);
        log.info("lowest number: " + directorySizes.get(0));
    }

    public List<Directory> createAllDirectories(){
        List<String> inputFileByline =  FileReader("AdOfCo2022/puzzle7.txt");

        String nameStartDirectory = "/";
        List<Directory> startDirectories = new ArrayList<>();
        List<Long> startFileSizes = new ArrayList<>();
        boolean startMapped = true;
        Directory startDirectory = new Directory(nameStartDirectory,"",startDirectories,startFileSizes, startMapped);
        Directory workingDirectory = startDirectory;
        List<Directory> allDirectories = new ArrayList<>();

        allDirectories.add(startDirectory);


        for(int i= 0; i < inputFileByline.size(); i++) {
            String input = inputFileByline.get(i);
            if(!input.contains("$")){
                if(input.contains("dir")){
                    String directoryName = input.split(" ")[1];
                    String parentDirectory = workingDirectory.getName();
                    List<Directory> directories = new ArrayList<>();
                    List<Long> fileSizes = new ArrayList<>();
                    Directory directory = new Directory(directoryName,parentDirectory,directories,fileSizes, false);
                    workingDirectory.directories.add(directory);
                    allDirectories.add(directory);
                } else {
                    long fileSize = Long.parseLong(input.split(" ")[0]);
                    workingDirectory.fileSizes.add(fileSize);
                    workingDirectory.mapped = true;
                }
            } else {
                if(input.contains("$ cd /")){
                    workingDirectory = startDirectory;
                } else if(input.contains("$ cd ..")){
                    String parent = workingDirectory.getParentDirectory();
                    // go through all directories somehow to get the correct one
                    for(Directory directory: allDirectories){
                        if(directory.getName().equals(parent)){
                            //make sure this is the child
                            if(directory.getDirectories().contains(workingDirectory)){
                                workingDirectory = directory;
                                break;
                            };

                        }
                    }
                } else if(input.contains("$ cd")){
                    String directoryToGoTo = input.split(" ")[2];
                    for(int x = 0; x < workingDirectory.getDirectories().size(); x++){
                        if(workingDirectory.getDirectories().get(x).getName().equals(directoryToGoTo)){
                            workingDirectory = workingDirectory.getDirectories().get(x);
                        }
                    }

                }
            }

        }
        log.info("creating directories finished");
        return  allDirectories;
    }

    @Data
    private class Directory{

        String name;
        String parentDirectory;
        List<Directory> directories;
        List<Long> fileSizes;

        boolean mapped;

        public Directory(String name, String parentDirectory, List<Directory> directories, List<Long> fileSizes, boolean mapped) {
            this.name = name;
            this.parentDirectory = parentDirectory;
            this.directories = directories;
            this.fileSizes = fileSizes;
            this.mapped = mapped;
        }

        public long getTotalSize(){

            long totalDirectorySize = 0;
            for(int x = 0; x < directories.size(); x++){
                totalDirectorySize += directories.get(x).getTotalSize();
            }

            long totalFileSize = 0;
            for(int i = 0; i < fileSizes.size(); i++){
                totalFileSize += fileSizes.get(i);
            }
            return totalFileSize + totalDirectorySize;
        }
    }


    private static List<String> FileReader(String source){

        Path path = Paths.get("src/main/resources/" + source );
        List<String> list = new ArrayList<>();

        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = br.readLine();

            int i =0;
            while (line != null){
                list.add(i, line);
                i++;
                line = br.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
