package javaInversion;

import static org.junit.Assert.*;

import java.util.List;

import javaApiScriptOverJava.ApiBlockOverJava;
import javaApiScriptOverJava.ApiOperationOneOverJava;
import javaApiScriptOverJava.ApiOperationOverJava;
import javaApiScriptOverJava.ApiOperationTwoOverJava;
import javaApiScriptOverJava.ApiWrapperOverJava;
import javaApiScriptOverJava.ConcreteApiBlockOverJava;
import javaSupport.api.ApiStatus;
import javaSupport.api.OperationResult;

import org.junit.Before;
import org.junit.Test;

public class ControlInversionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void itShouldAllowYouToScriptASingleApiCall() {
		ApiOperationOverJava apiOperation = new ApiOperationOneOverJava("test");
		OperationResult result = ApiWrapperOverJava.call(apiOperation);
		verifyResult(result, ApiStatus.OK, "operationOne performed on test");
	}

	@Test
	public void itShouldAllowYouToPassMultipleOperationsToTheApi() {
		ApiOperationOverJava apiOperation1 = new ApiOperationOneOverJava("test");
		ApiOperationOverJava apiOperation2 = new ApiOperationTwoOverJava("testing", 123);
		List<OperationResult> results = ApiWrapperOverJava.call(apiOperation1, apiOperation2);
		assertEquals(results.size(), 2);
		verifyResult(results.get(0), ApiStatus.OK, "operationOne performed on test");
		verifyResult(results.get(1), ApiStatus.OK, "operationTwo performed on testing 123");
	}

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
