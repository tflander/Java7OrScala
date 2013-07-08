package javaApiScriptOverScala;

import java.util.ArrayList;
import java.util.List;

import scalaSupport.api.OperationResult;
import scalaSupport.api.OriginalApi;

public class ApiWrapperOverScala {

	public static OperationResult call(
			ApiOperationOverScala apiOperation) {
		ApiOperationOverScala[] singleOperation = {apiOperation};
		return call(singleOperation).get(0);
	}

	public static List<OperationResult> call(
			ApiOperationOverScala... apiOperations) {
		OriginalApi api = new OriginalApi();
		api.expensiveInit();
		
		List<OperationResult> results = new ArrayList<OperationResult>();
		for (ApiOperationOverScala apiOperation : apiOperations) {
			results.add(apiOperation.execute());
		}

		api.close();
		return results;
	}
}
