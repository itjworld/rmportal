package com.rmportal;

import java.text.ParseException;

import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringBootWebInitApp.class})
@ActiveProfiles("prod")
@SpringBootTest
public class RmportalTest {


//	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	//@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(dsvJobController).build();
	}

	@Test
	public void testGetCompletedJobs() throws ParseException {
		
		System.out.println("finish");
//		String date_s = "03/10/2018";
//		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
//		Date startDate = dt.parse(date_s);
//
//		System.out.println(startDate);
//
//		date_s = "03/22/2018";
//		Date endDate = dt.parse(date_s);

//		System.out.println(endDate);
//		System.out.println(dsvReportDetailDao.getBranchVersionStatus(DSV_BRANCH_VERSION_KEY));
	}
}
