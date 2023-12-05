package com.example.BankMang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Todo Добавить Front
//Todo добавить Хранение сущностей в БД(Postgres, Mysql, SqLite, H2(По выбору, предпочтительней Postgres))
//Todo Вынести типовые круды в родительские дженериковые классы
//Todo Разобраться с RestController
@SpringBootApplication
public class BankMangApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankMangApplication.class, args);
	}

}
