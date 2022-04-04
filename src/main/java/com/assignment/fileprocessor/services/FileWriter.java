package com.assignment.fileprocessor.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * The type File writer.
 */
public class FileWriter {

    /**
     * Write file to destination dir.
     *
     * @param filemap   the filemap
     * @param extension the extension
     */
    public void writeFileToDestinationDir(Map<String, List<File>> filemap, String extension){
        try {
            for (Map.Entry<String, List<File>> entry : filemap.entrySet()) {

                String dirPath = entry.getKey();
                List<File> fileList = entry.getValue();

                dirPath = dirPath.replace("InputFiles", "OutputFiles\\" + extension);

                for (File sourceFile : fileList) {
                    String destinationPath = getDestinationPath(sourceFile, dirPath);
                    File destinationDir = new File(destinationPath);
                    if (!destinationDir.exists()) {
                        destinationDir.mkdirs();
                    }

                    File destinationFile = new File(destinationPath + "\\" + sourceFile.getName());
                    copyFileUsingStream(sourceFile, destinationFile);
                }
            }
            System.out.println("File write operation is completed for file format " + extension);
        }
        catch(Exception e){
            System.out.println(String.format("Error occurred while writing file format %s . Message : %s", extension,e.getMessage()));
        }
    }

    private String getDestinationPath(File sourceFile,String dirPath){
        String finalPath = dirPath;
        if(isQuarantineFile(sourceFile)){
            finalPath = dirPath + "\\quarantine";
        }
        return finalPath;
    }

    private boolean isQuarantineFile(File source){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
            String line = "";

            while ((line = br.readLine()) != null) {
                if (line.contains("MOBILE_NUMBER") || line.contains("EMAIL")) {
                    return true;
                }
            }
        }
        catch(IOException e){
            System.out.println(String.format("Error occurred while reading file %s . Message : %s", source.getName(),e.getMessage()));
        }
        return false;
    }

    private void copyFileUsingStream(File source, File dest) throws IOException {
        try ( InputStream is =  new FileInputStream(source);
              OutputStream os = new FileOutputStream(dest) ){

            int bytesRead = -1;
            byte[] buffer = new byte[1024];
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception ex) {
            System.out.println(String.format("Unable to copy file: %s Message: %s " + source.getName(), ex.getMessage()));
            throw ex;
        }
    }
}
