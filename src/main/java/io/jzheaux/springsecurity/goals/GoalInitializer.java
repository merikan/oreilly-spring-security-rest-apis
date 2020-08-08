package io.jzheaux.springsecurity.goals;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class GoalInitializer implements SmartInitializingSingleton {
	private final GoalRepository goals;
	private final UserRepository users;

	public GoalInitializer(GoalRepository goals, UserRepository users) {
		this.goals = goals;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.goals.save(new Goal("Read War and Peace", "user"));
		this.goals.save(new Goal("Free Solo the Eiffel Tower", "user"));
		this.goals.save(new Goal("Hang Christmas Lights", "user"));

		User user = new User("user", "{bcrypt}$2a$10$3njzOWhsz20aimcpMamJhOnX9Pb4Nk3toq8OO0swIy5EPZnb1YyGe");
		user.grantAuthority("goal:read");
		this.users.save(user);

		User hasRead = new User("hasread", "{bcrypt}$2a$10$3njzOWhsz20aimcpMamJhOnX9Pb4Nk3toq8OO0swIy5EPZnb1YyGe");
		hasRead.grantAuthority("goal:read");
		this.users.save(hasRead);

		User hasWrite = new User("haswrite", "{bcrypt}$2a$10$3njzOWhsz20aimcpMamJhOnX9Pb4Nk3toq8OO0swIy5EPZnb1YyGe");
		hasWrite.grantAuthority("goal:write");
		this.users.save(hasWrite);

		User admin = new User("admin", "{bcrypt}$2a$10$3njzOWhsz20aimcpMamJhOnX9Pb4Nk3toq8OO0swIy5EPZnb1YyGe");
		admin.grantAuthority("goal:read");
		admin.grantAuthority("goal:write");
		admin.grantAuthority("ROLE_ADMIN");
		this.users.save(admin);
	}
}