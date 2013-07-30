package javaOverScala;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import scalaSupport.api.ApiStatus;
import scalaSupport.api.OperationResult;
import scalaSupport.api.OriginalApi;

public class JavaOverScalaTest {

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
		assertEquals(ApiStatus.OK(), result.status());
		assertEquals("operationOne performed on test", result.message());
	}
}
