package com.lonsec.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonsec.service.RequestProcessor;

/**
 * @author Aravind
 *
 */
@Service
public class RequestProcessorImpl implements RequestProcessor {
	
	@Autowired
	private CSVProcessor csvProcessor;
	
	@Override
	public void processInputFiles(String path) {
		File[] files = listFilesInFolder(path);
		
		if(files == null) {
			return;
		}
		
		for (File file : files) {
			try {
				csvProcessor.pareCSV(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public File[] listFilesInFolder(String path) {
		File folder = new File(path);
		if (folder.isDirectory()) {
			return folder.listFiles((dir, name) -> name.endsWith(".csv"));
		}
		if (folder.isFile() && folder.getName().endsWith(".csv")) {
			return new File[] { folder };
		}
		return null;
	}

	@Override
	public void generateMonthlyReport() {
		// TODO Auto-generated method stub
		
	}

}
