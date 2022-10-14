package pro.ctao.config8002;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Config8002Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Config8002Application.class, args);
		String userName = applicationContext.getEnvironment().getProperty("spring.application.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("user name :"+userName+"; age: "+userAge);
	}

	@RestController
	@RequestMapping("/config")
	@RefreshScope
	public class ConfigController {

		@Value("${useLocalCache:false}")
		private boolean useLocalCache;

		@RequestMapping("/get")
		public boolean get() {
			return useLocalCache;
		}
	}
}
