package eryingzhang.net;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "eryingzhang.net.mapper")
public class ShiroMavenApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShiroMavenApplication.class, args);
	}
}
