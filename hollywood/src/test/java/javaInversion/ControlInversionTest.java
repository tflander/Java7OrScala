package javaInversion;

import static org.junit.Assert.assertEquals;

import java.util.List;

import api.ApiStatus;
import api.OperationResult;

import org.junit.Test;

public class ControlInversionTest {

	@Test
	public void itShouldAllowYouToExecuteABlockOfCodeAgainstTheApi() {
		ConcreteApiBlockOverJava apiOperations = new ConcreteApiBlockOverJava();
		ApiWrapperOverJava.callBlock(apiOperations);
		List<OperationResult> results = apiOperations.getLastResults();
		assertEquals(results.size(), 2);
		verifyResult(results.get(0), ApiStatus.OK, "operationOne performed on test");
		verifyResult(results.get(1), ApiStatus.OK, "operationTwo performed on testing 123");
	}

	private void verifyResult(OperationResult result, ApiStatus expectedStatus, String expectedMessage) {
		assertEquals(result.getStatus(), expectedStatus);
		assertEquals(result.getMessage(), expectedMessage);
	}
}
