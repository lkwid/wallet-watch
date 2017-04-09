package lkwid.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import lkwid.entity.dto.AccountDto;
import lkwid.validators.PasswordMatches.PasswordMatchesValidator;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches { 
    String message() default "Passwords don't match";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
    
    class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    	@Override
    	public void initialize(PasswordMatches constraintAnnotation) {		
    	}

    	@Override
    	public boolean isValid(Object value, ConstraintValidatorContext context) {
    		AccountDto user = (AccountDto) value;
    		return user.getPassword().equals(user.getMatchingPassword());
    	}

    }
}
