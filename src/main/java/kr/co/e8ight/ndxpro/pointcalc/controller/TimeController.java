package kr.co.e8ight.ndxpro.pointcalc.controller;

import kr.co.e8ight.ndxpro.pointcalc.domain.DtDto;
import kr.co.e8ight.ndxpro.pointcalc.domain.Item;
import kr.co.e8ight.ndxpro.pointcalc.domain.TimeInfoDto;
import kr.co.e8ight.ndxpro.pointcalc.domain.VissimResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeController {

    @PostMapping("/api/getTrafficTimeInfo.json")
    public TimeInfoDto timeInfo(@RequestBody DtDto dto) {
        System.out.println("getTraffic");
        if ( dto.getDt().equals("20230709") ) {
            Item item1 = new Item(1, "오전 첨두", "08", "09", "success");
            Item item2 = new Item(2, "야간", "21", "23", "success");
            return new TimeInfoDto(0, "success", 2, List.of(item1, item2));
        } else if ( dto.getDt().equals("20230712") ) {
            Item item1 = new Item(1, "오전 첨두", "08", "09", "success");
            Item item2 = new Item(2, "오후 첨두", "17", "18", "success");
            Item item3 = new Item(3, "야간", "21", "22", "success");
            return new TimeInfoDto(0, "success", 3, List.of(item1, item2, item3));
        } else {
            return new TimeInfoDto(0, "success", 0, new ArrayList<>());
        }
    }

    @PostMapping("/api/startVissim.json")
    public VissimResponseDto startVissim() {
        System.out.println("start");
        return new VissimResponseDto(0, "success", null, 0, null);
    }

    @PostMapping("/api/stopVissim.json")
    public VissimResponseDto stopVissim() {
        System.out.println("stop");
        return new VissimResponseDto(0, "success", null, 0, null);
    }
}
