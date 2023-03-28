package com.example.contester.model.generated.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect CounterModel_PostAspect_increaseCount {

    /**
     * <p>Pointcut for all calls on {@link com.example.contester.model.generated.CounterModel#increaseCount()}.</p>
     */
    protected pointcut increaseCountCaller(com.example.contester.model.generated.CounterModel aClass):
    	call(* com.example.contester.model.generated.CounterModel.increaseCount())
    	&& target(aClass);
    
    /**
     * <p>Checks a postcondition for the operation {@link CounterModel#increaseCount()} defined by the constraint
     * <code>context CounterModel::increaseCount() : 
     *       post counterWasIncremented: self.count = self.count@pre + 1</code></p>
     */
    void around(com.example.contester.model.generated.CounterModel aClass): increaseCountCaller(aClass) {
        /* Disable this constraint for subclasses of CounterModel. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.model.generated.CounterModel")) {
    
        Integer atPreValue1;
        
        if ((Object) aClass.count == null) {
            atPreValue1 = null;
        } else {
            atPreValue1 = new Integer(aClass.count);
        }
    
        proceed(aClass);
    
        if (!((Object) aClass.count).equals((atPreValue1 + new Integer(1)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'counterWasIncremented' (post counterWasIncremented: self.count = self.count@pre + 1) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}