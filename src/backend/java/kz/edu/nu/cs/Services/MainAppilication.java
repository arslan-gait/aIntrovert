package kz.edu.nu.cs.Services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/mainServices")
public class MainAppilication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public MainAppilication() {
		new ChatServer(10001).start();
		singletons.add(new AuthService());
		singletons.add(new EventService());
		singletons.add(new UserService());
		singletons.add(new LogService());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
