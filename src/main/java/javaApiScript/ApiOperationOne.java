package javaApiScript;

import scalaSupport.api.ApiStatus;
import scalaSupport.api.OperationResult;

public class ApiOperationOne implements ApiOperationOverScala {

	private String param;

	public ApiOperationOne(String param) {
		this.param = param;
	}

	@Override
	public OperationResult execute() {
		return new OperationResult(ApiStatus.OK(), "operationOne performed on " + param);
	}

}
