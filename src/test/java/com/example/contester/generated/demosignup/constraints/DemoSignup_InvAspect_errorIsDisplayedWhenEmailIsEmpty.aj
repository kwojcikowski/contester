package com.example.contester.generated.demosignup.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DemoSignup_InvAspect_errorIsDisplayedWhenEmailIsEmpty {

    /**
     * <p>Describes all Constructors of the class {@link com.example.contester.generated.demosignup.DemoSignup}.</p>
     */
    protected pointcut allDemoSignupConstructors(com.example.contester.generated.demosignup.DemoSignup aClass):
        execution(com.example.contester.generated.demosignup.DemoSignup.new(..)) && this(aClass);
    
    /**
     * <p>Describes all public methods of the class {@link com.example.contester.generated.demosignup.DemoSignup}.</p>
     */
    protected pointcut allDemoSignupPublicMethods(com.example.contester.generated.demosignup.DemoSignup aClass):
        execution(public * com.example.contester.generated.demosignup.DemoSignup.*(..)) && this(aClass);
    
    /**
     * <p><code>Checks an invariant on the class DemoSignup defined by the constraint
     * <code>context DemoSignup
     *       inv errorIsDisplayedWhenEmailIsEmpty: self.email = '' implies self.emailErrorMessage = 'Email is a required field'</code>
     * before execution of public methods.</p>
     */
    before(com.example.contester.generated.demosignup.DemoSignup aClass) : allDemoSignupPublicMethods(aClass) {
        /* Disable this constraint for subclasses of DemoSignup. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.demosignup.DemoSignup")) {
        if (!(!aClass.email.equals("") || aClass.emailErrorMessage.equals("Email is a required field"))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'errorIsDisplayedWhenEmailIsEmpty' (inv errorIsDisplayedWhenEmailIsEmpty: self.email = '' implies self.emailErrorMessage = 'Email is a required field') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
    
    /**
     * <p><code>Checks an invariant on the class DemoSignup defined by the constraint
     * <code>context DemoSignup
     *       inv errorIsDisplayedWhenEmailIsEmpty: self.email = '' implies self.emailErrorMessage = 'Email is a required field'</code>
     * after execution of public methods and constructors.</p>
     */
    after(com.example.contester.generated.demosignup.DemoSignup aClass) : allDemoSignupConstructors(aClass) || allDemoSignupPublicMethods(aClass) {
        /* Disable this constraint for subclasses of DemoSignup. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.demosignup.DemoSignup")) {
        if (!(!aClass.email.equals("") || aClass.emailErrorMessage.equals("Email is a required field"))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'errorIsDisplayedWhenEmailIsEmpty' (inv errorIsDisplayedWhenEmailIsEmpty: self.email = '' implies self.emailErrorMessage = 'Email is a required field') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
    
    /**
     * <p><code>Checks an invariant on the class DemoSignup defined by the constraint
     * <code>context DemoSignup
     *       inv errorIsDisplayedWhenEmailIsEmpty: self.email = '' implies self.emailErrorMessage = 'Email is a required field'</code>
     * after execution of public methods throwing any Exception.</p>
     */
    after(com.example.contester.generated.demosignup.DemoSignup aClass) throwing : allDemoSignupPublicMethods(aClass) {
        /* Disable this constraint for subclasses of DemoSignup. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.demosignup.DemoSignup")) {
        if (!(!aClass.email.equals("") || aClass.emailErrorMessage.equals("Email is a required field"))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'errorIsDisplayedWhenEmailIsEmpty' (inv errorIsDisplayedWhenEmailIsEmpty: self.email = '' implies self.emailErrorMessage = 'Email is a required field') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}