package com.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.AppTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class AppTests {

	@Test
	public void contextLoads() {
	}

}
