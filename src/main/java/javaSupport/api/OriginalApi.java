package javaSupport.api;

public class OriginalApi {
	public OperationResult expensiveInit() {
		return new OperationResult(ApiStatus.OK, "init successful");
	}

	public OperationResult close() {
		return new OperationResult(ApiStatus.OK, "api closed");
	}

	public OperationResult operationOne(String param) {
		return new OperationResult(ApiStatus.OK, "operationOne performed on "
				+ param);
	}

	public OperationResult operationTwo(String param1, int param2) {
		return new OperationResult(ApiStatus.OK, "operationTwo performed on "
				+ param1 + ' ' + param2);
	}
}
