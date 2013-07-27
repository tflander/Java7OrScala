package javaInversion;

import static javaSupport.api.ApiStatus.OK;
import static org.junit.Assert.assertEquals;
import javaSupport.api.OperationResult;
import javaSupport.api.OriginalApi;

import org.junit.Before;
import org.junit.Test;

public class NoInversionTest {

	private OriginalApi api;

	@Before
	public void setUp() throws Exception {
		api = new OriginalApi();
	}

	@Test(expected = IllegalStateException.class)
	public void itFailsWithoutInitialization() {
		api.operationOne("test");
	}
	
	@Test
	public void itWorksWithInitialization() {
		api.expensiveInit();
		OperationResult result = api.operationOne("test");
		api.close();
		assertEquals(OK, result.getStatus());
		assertEquals("operationOne performed on test", result.getMessage());
	}
}
