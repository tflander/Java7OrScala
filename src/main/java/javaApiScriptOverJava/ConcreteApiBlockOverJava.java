package javaApiScriptOverJava;

import static javaSupport.api.ApiStatus.OK;

import java.util.ArrayList;
import java.util.List;
import javaSupport.api.OperationResult;
import javaSupport.api.OriginalApi;

public class ConcreteApiBlockOverJava implements ApiBlockOverJava {

	private List<OperationResult> lastResults;

	@Override
	public void execute(OriginalApi api) {
		lastResults = new ArrayList<OperationResult>();
        OperationResult resultOne = api.operationOne("test");
        lastResults.add(resultOne);
        if (resultOne.getStatus() == OK) {
        	lastResults.add(api.operationTwo("testing", 123));
        }
	}

	public List<OperationResult> getLastResults() {
		return lastResults;
	}

}
