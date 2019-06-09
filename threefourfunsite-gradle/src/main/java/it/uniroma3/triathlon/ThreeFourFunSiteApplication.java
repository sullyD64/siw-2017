package it.uniroma3.triathlon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ThreeFourFunSiteApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ThreeFourFunSiteApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ThreeFourFunSiteApplication.class, args);
	}
}
