package pro.ctao.consumer8001;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Consumer8001Application {

	public static void main(String[] args) {
		SpringApplication.run(Consumer8001Application.class, args);
	}

	@Component
	@FeignClient(value = "provider8000")
	public interface ClientApi {
		@GetMapping(value = "/echo/{string}")
		ResponseEntity<String> echo(@PathVariable String string);
	}

	@RestController
	public class ConsumerController {

		@Resource
		private ClientApi clientApi;

		@GetMapping("/consumer/{string}")
		public String get(@PathVariable String string) {
			// 从提供者中获取数据
			ResponseEntity<String> stringResponseEntity = clientApi.echo(string);
			return stringResponseEntity.getBody();
		}
	}

}
