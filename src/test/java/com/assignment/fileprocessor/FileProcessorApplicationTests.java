package com.assignment.fileprocessor;

import com.assignment.fileprocessor.services.FileReadWriteOperation;

import java.util.ArrayList;
import java.util.List;

class FileProcessorApplicationTests {

	//@Test
	void givenSourceDirAndExtensionList_whenProcessFiles_thenCopyToDestinationDirectory() throws InterruptedException {
		FileReadWriteOperation fileOperation =new FileReadWriteOperation();
		List<String> extensionList = new ArrayList<>();
		extensionList.add("txt");
		extensionList.add("json");
		extensionList.add("csv");
		String localDir = System.getProperty("user.dir");
		String dirPath = localDir +  "\\src\\test\\resources\\InputFiles";
		fileOperation.processFiles(dirPath,extensionList);
		Thread.sleep(5000);
	}
}
