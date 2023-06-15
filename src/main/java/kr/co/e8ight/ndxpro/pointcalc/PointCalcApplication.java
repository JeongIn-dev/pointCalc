package kr.co.e8ight.ndxpro.pointcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// docker stop limes
// docker rm limes
// docker rmi 172.16.28.217:12000/data-ingest/pointcalc:0.0.2
// docker run -dp 51001:9000 --name limes -v /home/jeongin/visim:/visim 172.16.28.217:12000/data-ingest/pointcalc:0.0.2
@SpringBootApplication
public class PointCalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointCalcApplication.class, args);
	}

}


