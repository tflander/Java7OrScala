package api;

public class OriginalApi {
	
	boolean initialized = false;
	
	public OperationResult expensiveInit() {
		verifyNotInitialized();
		initialized = true;
		return new OperationResult(ApiStatus.OK, "init successful");
	}

	public OperationResult close() {
		verifyInitialized();
		initialized = false;
		return new OperationResult(ApiStatus.OK, "api closed");
	}

	public OperationResult operationOne(String param) {
		verifyInitialized();
		return new OperationResult(ApiStatus.OK, "operationOne performed on "
				+ param);
	}

	public OperationResult operationTwo(String param1, int param2) {
		verifyInitialized();
		return new OperationResult(ApiStatus.OK, "operationTwo performed on "
				+ param1 + ' ' + param2);
	}
	
	private void verifyNotInitialized() {
		if (initialized) {
			throw new IllegalStateException("api is not initialized");
		}
	}

	private void verifyInitialized() {
		if (!initialized) {
			throw new IllegalStateException("api is already initialized");
		}
	}
}
