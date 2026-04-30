package com.itsjbges.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
@ActiveProfiles("test")
class BlogApplicationTests {

	static {
        // This ensures the variables are set before the Spring Context even starts
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

	@Test
	void contextLoads() {
	}

}
