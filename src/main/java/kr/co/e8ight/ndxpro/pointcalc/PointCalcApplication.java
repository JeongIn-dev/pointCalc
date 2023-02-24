package kr.co.e8ight.ndxpro.pointcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// docker stop testsource
// docker rm testsource
// docker rmi 172.16.28.217:12000/data-ingest/pointcalc:latest
// docker run -dp 51001:9000 --name testsource -v /home/jeongin/visim:/visim 172.16.28.217:12000/data-ingest/pointcalc
@SpringBootApplication
public class PointCalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointCalcApplication.class, args);
	}

}
