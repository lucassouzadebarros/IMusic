package challenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import challenge.repository.UserRepository;

public class EmailNotRegisteredValidator implements ConstraintValidator<EmailNotRegistered, String> {

	@Autowired
	private UserRepository repository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repository.existsByEmail(value);
	}
}