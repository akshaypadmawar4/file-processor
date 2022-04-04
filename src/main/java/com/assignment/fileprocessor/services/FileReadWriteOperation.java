package com.assignment.fileprocessor.services;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The type File read write operation.
 */
public class FileReadWriteOperation{
    /**
     * Process files.
     *
     * @param dirPath       the dir path
     * @param extensionList the extension list
     */
    public void processFiles(String dirPath , List<String> extensionList){
        FileWriter fileWriter = new FileWriter();
        FileReader fileReader = new FileReader();
         for(String extension: extensionList){
            System.out.println("Thread started for file format " + extension);
            new Thread(new Runnable() {
                 @Override
                 public void run() {
                     System.out.println("Started execution for file format: "+ extension);
                     Instant start = Instant.now();
                     fileWriter.writeFileToDestinationDir(fileReader.getFileNames(dirPath,null,extension),extension);
                     System.out.println("End execution for file format: "+ extension);
                     Instant end = Instant.now();
                     long timeElapsed = Duration.between(start, end).toMillis();
                     System.out.println(String.format("Total time taken for execution for file format %s  :  %s  millisecond",extension,timeElapsed));
                 }
             }).start();
         }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        FileReadWriteOperation fileOperation =new FileReadWriteOperation();
        List<String> extensionList = new ArrayList<>();
        extensionList.add("txt");
        extensionList.add("json");
        extensionList.add("csv");
        String localDir = System.getProperty("user.dir");
        fileOperation.processFiles("D:\\Users\\Assignment\\demo\\src\\main\\resources\\InputFiles",extensionList);
    }
}
