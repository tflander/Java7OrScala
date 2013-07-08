package javaApiScript;

public class ApiOperationOverScala {

	private ApiOperationEnum operation;
	private OperationParam[] operationParams;

	public ApiOperationOverScala(ApiOperationEnum operation,
			OperationParam... operationParams) {
		this.operation = operation;
		this.operationParams = operationParams;
	}

	public ApiOperationEnum getOperation() {
		return operation;
	}

	public OperationParam[] getOperationParams() {
		return operationParams;
	}
}
