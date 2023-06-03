package com.example.contester.generated.counter.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Counter_PostAspect_resetState {

    /**
     * <p>Pointcut for all calls on {@link com.example.contester.generated.counter.Counter#resetState()}.</p>
     */
    protected pointcut resetStateCaller(com.example.contester.generated.counter.Counter aClass):
    	call(* com.example.contester.generated.counter.Counter.resetState())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link Counter#resetState()} defined by the constraint
     * <code>context Counter::resetState() : 
     *       post counterWasReset: self.count.toInteger() = 0</code></p>
     */
    void around(com.example.contester.generated.counter.Counter aClass): resetStateCaller(aClass) {
        /* Disable this constraint for subclasses of Counter. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.counter.Counter")) {
    
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