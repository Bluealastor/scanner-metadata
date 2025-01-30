package it.digitouch.metadati;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
* PER AUTO CONFIGURAZIONE DEL DB (da provare con mysql)
* AGGIUNGERE
* exclude = {DataSourceAutoConfiguration.class }
*/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MetadatiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetadatiApplication.class, args);
	}

}
