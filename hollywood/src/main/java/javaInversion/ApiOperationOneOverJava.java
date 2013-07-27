package javaInversion;

import api.ApiStatus;
import api.OperationResult;

public class ApiOperationOneOverJava implements ApiOperationOverJava {

	private String param;

	public ApiOperationOneOverJava(String param) {
		this.param = param;
	}

	@Override
	public OperationResult execute() {
		return new OperationResult(ApiStatus.OK, "operationOne performed on " + param);
	}

}
