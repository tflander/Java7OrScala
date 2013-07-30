package javaInversion;

import api.OriginalApi;

public class ApiWrapperOverJava {

	public static void callBlock(ApiBlockOverJava apiOperations) {
		OriginalApi api = new OriginalApi();
		api.expensiveInit();
		apiOperations.execute(api);
		api.close();
	}
	
}
