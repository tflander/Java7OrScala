package javaApiScriptOverJava;

import java.util.ArrayList;
import java.util.List;

import javaSupport.api.OriginalApi;

import javaSupport.api.OperationResult;

public class ApiWrapperOverJava {

	public static OperationResult call(ApiOperationOverJava apiOperation) {
		ApiOperationOverJava[] singleOperation = {apiOperation};
		return call(singleOperation).get(0);
	}
	
	public static List<OperationResult> call(ApiOperationOverJava... apiOperations) {
		OriginalApi api = new OriginalApi();
		api.expensiveInit();
		
		List<OperationResult> results = new ArrayList<OperationResult>();
		for (ApiOperationOverJava apiOperation : apiOperations) {
			results.add(apiOperation.execute());
		}

		api.close();
		return results;	}
}
