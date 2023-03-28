package com.example.contester.model.generated.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect CounterModel_PostAspect_resetState {

    /**
     * <p>Pointcut for all calls on {@link com.example.contester.model.generated.CounterModel#resetState()}.</p>
     */
    protected pointcut resetStateCaller(com.example.contester.model.generated.CounterModel aClass):
    	call(* com.example.contester.model.generated.CounterModel.resetState())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link CounterModel#resetState()} defined by the constraint
     * <code>context CounterModel::resetState() : 
     *       post counterWasReset: self.count = 0</code></p>
     */
    void around(com.example.contester.model.generated.CounterModel aClass): resetStateCaller(aClass) {
        /* Disable this constraint for subclasses of CounterModel. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.model.generated.CounterModel")) {
    
        proceed(aClass);
    
        if (!((Object) aClass.count).equals(new Integer(0))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'counterWasReset' (post counterWasReset: self.count = 0) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}