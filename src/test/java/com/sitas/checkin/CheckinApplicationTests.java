package com.sitas.checkin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CheckinApplicationTests {

	@Test
	void Example (){
		assertEquals (2, 1+1);
	}
	@Test
	void FailureExample (){

		//uncomment next line to force a test fail
		//assertEquals (3, 1+1);
	}
}
