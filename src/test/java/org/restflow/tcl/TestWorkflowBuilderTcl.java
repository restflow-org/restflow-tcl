package org.restflow.tcl;

import org.restflow.WorkflowContext;
import org.restflow.WorkflowContextBuilder;
import org.restflow.actors.Workflow;
import org.restflow.actors.WorkflowBuilder;
import org.restflow.data.ConsumableObjectStore;
import org.restflow.test.RestFlowTestCase;

public class TestWorkflowBuilderTcl extends RestFlowTestCase {
	
	private WorkflowContext _context;
	private ConsumableObjectStore _store;
	
	public void setUp() throws Exception {
		super.setUp();
		_store = new ConsumableObjectStore();
		_context = new WorkflowContextBuilder()
			.store(_store)
			.build();
	}
	
	public void test_WorkflowBuilder_HelloWorld_OneNode_Tcl() throws Exception {

		Workflow workflow = new WorkflowBuilder()
			.context(_context)
			.node(new TclNodeBuilder()
				.step("puts [list Hello world!]"))
			.build();
		
		workflow.configure();
		workflow.initialize();
		
		workflow.run();

		assertEquals(0, _store.size());
	}
}
