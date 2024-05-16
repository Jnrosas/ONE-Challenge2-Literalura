package com.ray.literalura;

import com.ray.literalura.main.Main;
import com.ray.literalura.repository.CBooksRepository;
import com.ray.literalura.repository.CPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private CBooksRepository cBooksRepository;
	@Autowired
	private CPersonRepository cPersonRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(cBooksRepository, cPersonRepository);
		main.menu();
	}
}
