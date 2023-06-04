package com.example.contester.generated.democounter.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DemoCounter_PostAspect_resetState {

    /**
     * <p>Pointcut for all calls on {@link com.example.contester.generated.democounter.DemoCounter#resetState()}.</p>
     */
    protected pointcut resetStateCaller(com.example.contester.generated.democounter.DemoCounter aClass):
    	call(* com.example.contester.generated.democounter.DemoCounter.resetState())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link DemoCounter#resetState()} defined by the constraint
     * <code>context DemoCounter::resetState() : 
     *       post counterWasReset: self.count.toInteger() = 0</code></p>
     */
    void around(com.example.contester.generated.democounter.DemoCounter aClass): resetStateCaller(aClass) {
        /* Disable this constraint for subclasses of DemoCounter. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.democounter.DemoCounter")) {
    
        proceed(aClass);
    
        if (!((Object) Integer.parseInt(aClass.count)).equals(new Integer(0))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'counterWasReset' (post counterWasReset: self.count.toInteger() = 0) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}