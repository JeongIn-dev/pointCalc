package kr.co.e8ight.ndxpro.pointcalc.controller;

import kr.co.e8ight.ndxpro.pointcalc.domain.DtDto;
import kr.co.e8ight.ndxpro.pointcalc.domain.Item;
import kr.co.e8ight.ndxpro.pointcalc.domain.TimeInfoDto;
import kr.co.e8ight.ndxpro.pointcalc.domain.VissimResponseDto;
import kr.co.e8ight.ndxpro.pointcalc.service.CSVInjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TimeController {
    @Autowired
    private CSVInjectionService service;

    @PostMapping("/api/getTrafficTimeInfo.json")
    public TimeInfoDto timeInfo(@RequestBody DtDto dto) {
        System.out.println("getTraffic");
        if ( Integer.parseInt(dto.getDt()) >= 20240101 ) {
            Item item1 = new Item(1, "07~09", "07", "09", "success");
            Item item2 = new Item(2, "11~13", "11", "13", "success");
            Item item3 = new Item(3, "17~19", "17", "19", "success");
            Item item4 = new Item(4, "20~22", "20", "22", "success");
            return new TimeInfoDto(0, "success", 2, List.of(item1, item2, item3, item4));
        } else {
            return new TimeInfoDto(0, "success", 0, new ArrayList<>());
        }
    }

    @PostMapping("/api/startVissim.json")
    public VissimResponseDto startVissim(@RequestBody Map<String, String> body) {
        String from = body.get("fromDtm").substring(8, 10);
        String to = body.get("toDtm").substring(8, 10);
        service.setTimeGroup(from + "~" + to);
        service.start();
        System.out.println("start " + from + "~" + to);
        return new VissimResponseDto(0, "success", null, 0, null);
    }

    @PostMapping("/api/stopVissim.json")
    public VissimResponseDto stopVissim() {
        System.out.println("stop");
        service.stop();
        return new VissimResponseDto(0, "success", null, 0, null);
    }
}
