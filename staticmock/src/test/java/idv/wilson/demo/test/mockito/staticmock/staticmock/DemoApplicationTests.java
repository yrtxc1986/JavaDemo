package idv.wilson.demo.test.mockito.staticmock.staticmock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SaveFile.class)
public class DemoApplicationTests {

	@InjectMocks
	SaveFile sf;
	

	@Test
	public void contextLoads() throws Exception {
		String fileName = "testsing";
		
		File f = mock(File.class);
		when(f.canWrite()).thenReturn(true);
		
		PowerMockito.whenNew(File.class).withArguments(fileName).thenReturn(f);
		sf.save(fileName);
	}

}
