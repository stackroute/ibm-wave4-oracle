package com.stackroute.manualservice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ManualAnswerServiceApplicationTests {

	@Test
	public void contextLoads() {
		Assert.assertTrue(true);
	}

}
