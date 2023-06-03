package com.example.contester.generated.counter.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Counter_PostAspect_decreaseCount {

    /**
     * <p>Pointcut for all calls on {@link com.example.contester.generated.counter.Counter#decreaseCount()}.</p>
     */
    protected pointcut decreaseCountCaller(com.example.contester.generated.counter.Counter aClass):
    	call(* com.example.contester.generated.counter.Counter.decreaseCount())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link Counter#decreaseCount()} defined by the constraint
     * <code>context Counter::decreaseCount() : 
     *       post counterWasDecremented: self.count.toInteger() = self.count@pre.toInteger() - 1</code></p>
     */
    void around(com.example.contester.generated.counter.Counter aClass): decreaseCountCaller(aClass) {
        /* Disable this constraint for subclasses of Counter. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.counter.Counter")) {
    
        String atPreValue1;
        
        if ((Object) aClass.count == null) {
            atPreValue1 = null;
        } else {
            atPreValue1 = new String(aClass.count);
        }
    
        proceed(aClass);
    
        if (!((Object) Integer.parseInt(aClass.count)).equals((Integer.parseInt(atPreValue1) - new Integer(1)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'counterWasDecremented' (post counterWasDecremented: self.count.toInteger() = self.count@pre.toInteger() - 1) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}