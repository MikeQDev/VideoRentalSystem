package com.ecsu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vrs.VideoRentalSystemApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VideoRentalSystemApplication.class)
@WebAppConfiguration
public class VideoRentalSystemApplicationTests {

	@Test
	public void contextLoads() {
	}

}
