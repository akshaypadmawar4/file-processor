package com.assignment.fileprocessor.services;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type File reader.
 */
public class FileReader {

    /**
     * Get file names map.
     *
     * @param dirPath     the dir path
     * @param dirFilesMap the dir files map
     * @param fileExt     the file ext
     * @return the map
     */
    public Map<String,List<File>> getFileNames(String dirPath, Map<String,List<File>> dirFilesMap, final String fileExt){
        List<File> files = new ArrayList<>();
        try {
            Map<String, List<File>> currentFiles = dirFilesMap;
            if (currentFiles == null) {
                currentFiles = new HashMap<>();
            }
            File file = new File(dirPath);

            FileFilter fileFilter = new FileFilter() {
                @Override
                public boolean accept(File file) {
                    boolean acceptFile = false;
                    if (file.isDirectory()) {
                        acceptFile = true;
                    } else if (file.getName().toLowerCase().endsWith(fileExt.toLowerCase())) {
                        acceptFile = true;
                    }
                    return acceptFile;
                }
            };

            for (File dirFile : file.listFiles(fileFilter)) {
                if (dirFile.isFile() &&
                        dirFile.getName().toLowerCase().endsWith(fileExt.toLowerCase())) {
                    files.add(dirFile);
                } else if (dirFile.isDirectory()) {
                    if (!files.isEmpty()) {
                        currentFiles.put(dirPath, files);
                    }

                    getFileNames(dirFile.getAbsolutePath(), currentFiles, fileExt);
                }
            }
            if (!files.isEmpty()) {
                currentFiles.put(dirPath, files);
            }
            return currentFiles;
        }
        catch(Exception e){
            System.out.println(String.format("Error occurred while reading file for format %s. Message: %s", fileExt, e.getMessage()));
            throw e;
        }
    }
}
