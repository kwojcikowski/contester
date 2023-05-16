package com.example.contester.generated.signup.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Signup_InvAspect_errorIsDisplayedWhenAgeNonPositive {

    /**
     * <p>Describes all Constructors of the class {@link com.example.contester.generated.signup.Signup}.</p>
     */
    protected pointcut allSignupConstructors(com.example.contester.generated.signup.Signup aClass):
        execution(com.example.contester.generated.signup.Signup.new(..)) && this(aClass);
    
    /**
     * <p>Describes all public methods of the class {@link com.example.contester.generated.signup.Signup}.</p>
     */
    protected pointcut allSignupPublicMethods(com.example.contester.generated.signup.Signup aClass):
        execution(public * com.example.contester.generated.signup.Signup.*(..)) && this(aClass);
    
    /**
     * <p><code>Checks an invariant on the class Signup defined by the constraint
     * <code>context Signup
     *       inv errorIsDisplayedWhenAgeNonPositive: self.age <> '' and self.age.toInteger() < 0 implies self.ageErrorMessage = 'Age must be greater than 0'</code>
     * before execution of public methods.</p>
     */
    before(com.example.contester.generated.signup.Signup aClass) : allSignupPublicMethods(aClass) {
        /* Disable this constraint for subclasses of Signup. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.signup.Signup")) {
        if (!(!(!aClass.age.equals("") && (Integer.parseInt(aClass.age) < new Integer(0))) || aClass.ageErrorMessage.equals("Age must be greater than 0"))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'errorIsDisplayedWhenAgeNonPositive' (inv errorIsDisplayedWhenAgeNonPositive: self.age <> '' and self.age.toInteger() < 0 implies self.ageErrorMessage = 'Age must be greater than 0') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
    
    /**
     * <p><code>Checks an invariant on the class Signup defined by the constraint
     * <code>context Signup
     *       inv errorIsDisplayedWhenAgeNonPositive: self.age <> '' and self.age.toInteger() < 0 implies self.ageErrorMessage = 'Age must be greater than 0'</code>
     * after execution of public methods and constructors.</p>
     */
    after(com.example.contester.generated.signup.Signup aClass) : allSignupConstructors(aClass) || allSignupPublicMethods(aClass) {
        /* Disable this constraint for subclasses of Signup. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.signup.Signup")) {
        if (!(!(!aClass.age.equals("") && (Integer.parseInt(aClass.age) < new Integer(0))) || aClass.ageErrorMessage.equals("Age must be greater than 0"))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'errorIsDisplayedWhenAgeNonPositive' (inv errorIsDisplayedWhenAgeNonPositive: self.age <> '' and self.age.toInteger() < 0 implies self.ageErrorMessage = 'Age must be greater than 0') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
    
    /**
     * <p><code>Checks an invariant on the class Signup defined by the constraint
     * <code>context Signup
     *       inv errorIsDisplayedWhenAgeNonPositive: self.age <> '' and self.age.toInteger() < 0 implies self.ageErrorMessage = 'Age must be greater than 0'</code>
     * after execution of public methods throwing any Exception.</p>
     */
    after(com.example.contester.generated.signup.Signup aClass) throwing : allSignupPublicMethods(aClass) {
        /* Disable this constraint for subclasses of Signup. */
        if (aClass.getClass().getCanonicalName().equals("com.example.contester.generated.signup.Signup")) {
        if (!(!(!aClass.age.equals("") && (Integer.parseInt(aClass.age) < new Integer(0))) || aClass.ageErrorMessage.equals("Age must be greater than 0"))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'errorIsDisplayedWhenAgeNonPositive' (inv errorIsDisplayedWhenAgeNonPositive: self.age <> '' and self.age.toInteger() < 0 implies self.ageErrorMessage = 'Age must be greater than 0') was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}