package mx.secure.nci.web.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {		
		"classpath:/spring/integration-core-config.xml"
		})
public class ExampleActionTest {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExampleActionTest.class);
		
	@Test
	public void exampleServiceTest(){
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("this is only an example test to show how to load ARQ components for the test.");
			}
	}	
}
