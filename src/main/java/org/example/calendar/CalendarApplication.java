package org.example.calendar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"org.example.calendar.dao.mapper", "org.example.calendar.dao.manual"})
public class CalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarApplication.class, args);
	}

}
