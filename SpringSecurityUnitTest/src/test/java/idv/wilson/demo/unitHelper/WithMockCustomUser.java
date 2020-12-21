package idv.wilson.demo.unitHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

	String username() default "user";

	String userid() default "1";

	String email() default "test@paradm.com";

	String[] role() default "USER";
}