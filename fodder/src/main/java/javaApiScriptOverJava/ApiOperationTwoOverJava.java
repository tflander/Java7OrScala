package javaApiScriptOverJava;

import javaSupport.api.ApiStatus;
import javaSupport.api.OperationResult;

public class ApiOperationTwoOverJava implements ApiOperationOverJava {

	private String param1;
	private int param2;

	public ApiOperationTwoOverJava(String param1, int param2) {
		this.param1 = param1;
		this.param2 = param2;
	}

	@Override
	public OperationResult execute() {
		return new OperationResult(ApiStatus.OK, "operationTwo performed on " + param1 + ' ' + param2);
	}

}
