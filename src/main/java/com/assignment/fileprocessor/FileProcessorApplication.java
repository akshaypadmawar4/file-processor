package com.assignment.fileprocessor;
import com.assignment.fileprocessor.services.FileReadWriteOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FileProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileProcessorApplication.class, args);

		if(args != null && args.length > 0) {
			String dirPath = args[0];
			System.out.println("Provided path is: "+ dirPath);
			FileReadWriteOperation fileOperation = new FileReadWriteOperation();
			List<String> extensionList = new ArrayList<>();
			extensionList.add("txt");
			extensionList.add("json");
			extensionList.add("csv");
			fileOperation.processFiles(dirPath, extensionList);
		}
	}

}
