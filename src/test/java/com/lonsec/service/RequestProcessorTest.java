package com.lonsec.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lonsec.AppTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class RequestProcessorTest {
	
	@Autowired
	private RequestProcessor processor;
	
	@Test
	public void listFilesInFolder() {
		
	}

}
