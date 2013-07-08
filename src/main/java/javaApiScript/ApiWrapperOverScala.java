package javaApiScript;

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
			results.add(execOperation(apiOperation, api));
		}

		api.close();
		return results;
	}

	private static OperationResult execOperation(ApiOperationOverScala apiOperation, OriginalApi api) {
		switch(apiOperation.getOperation()) {
		case OPERATION_ONE:
			StringOperationParam operationParam = (StringOperationParam) apiOperation.getOperationParams()[0];
			return api.operationOne(operationParam.getValue());
		case OPERATION_TWO:
			StringOperationParam operationParam1 = (StringOperationParam) apiOperation.getOperationParams()[0];
			IntegerOperationParam operationParam2 = (IntegerOperationParam) apiOperation.getOperationParams()[1];
			return api.operationTwo(operationParam1.getValue(), operationParam2.getValue());
		}
		return null;
	}

}
