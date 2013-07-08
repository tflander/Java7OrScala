package javaApiScript;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import scalaSupport.api.ApiStatus;
import scalaSupport.api.OperationResult;

public class ApiScriptOverScalaTest {

	@Test
	public void itShouldAllowYouToScriptASingleApiCall() {
		ApiOperationOverScala apiOperation = new ApiOperationOne("test");
		OperationResult result = ApiWrapperOverScala.call(apiOperation);
		verifyResult(result, ApiStatus.OK(), "operationOne performed on test");
	}

	@Test
	public void itShouldAllowYouToPassMultipleOperationsToTheApi() {
		ApiOperationOverScala apiOperation1 = new ApiOperationOne("test");
		ApiOperationOverScala apiOperation2 = new ApiOperationTwo("testing", 123);
		List<OperationResult> results = ApiWrapperOverScala.call(apiOperation1, apiOperation2);
		assertEquals(results.size(), 2);
		verifyResult(results.get(0), ApiStatus.OK(), "operationOne performed on test");
		verifyResult(results.get(1), ApiStatus.OK(), "operationTwo performed on testing 123");
	}

	private void verifyResult(OperationResult result, ApiStatus.Val expectedStatus, String expectedMessage) {
		assertEquals(result.status(), expectedStatus);
		assertEquals(result.message(), expectedMessage);
	}
}
