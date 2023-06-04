package com.example.contester.generated.democounter.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DemoCounter_PostAspect_increaseCount {

    /**
     * <p>Pointcut for all calls on {@link com.example.contester.generated.democounter.DemoCounter#increaseCount()}.</p>
     */
    protected pointcut increaseCountCaller(com.example.contester.generated.democounter.DemoCounter aClass):
    	call(* com.example.contester.generated.democounter.DemoCounter.increaseCount())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link DemoCounter#increaseCount()} defined by the constraint
     * <code>context DemoCounter::increaseCount() : 
     *       post counterWasIncremented: self.count.toInteger() = self.count@pre.toInteger() + 1</code></p>
     */
    void around(com.example.contester.generated.democounter.DemoCounter aClass): increaseCountCaller(aClass) {
        /* Disable this constraint for subclasses of DemoCounter. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.democounter.DemoCounter")) {
    
        String atPreValue1;
        
        if ((Object) aClass.count == null) {
            atPreValue1 = null;
        } else {
            atPreValue1 = new String(aClass.count);
        }
    
        proceed(aClass);
    
        if (!((Object) Integer.parseInt(aClass.count)).equals((Integer.parseInt(atPreValue1) + new Integer(1)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'counterWasIncremented' (post counterWasIncremented: self.count.toInteger() = self.count@pre.toInteger() + 1) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}