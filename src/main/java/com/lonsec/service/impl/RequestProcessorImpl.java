package com.lonsec.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.lonsec.service.RequestProcessor;

/**
 * @author Aravind
 *
 */
@Service
public class RequestProcessorImpl implements RequestProcessor {

	@Override
	public void processInputFiles(String path) {
		// TODO Auto-generated method stub

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
