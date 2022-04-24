package com.mehtab.backendapi.controller;

import com.mehtab.backendapi.controller.dto.ResponseDto;
import com.mehtab.backendapi.proxy.JukeBoxResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/jukeBoxes")
@Slf4j
public class JukeBoxController {

    private final JukeBoxHelper jukeBoxHelper;

    @Autowired
    public JukeBoxController(JukeBoxHelper jukeBoxHelper){
        this.jukeBoxHelper = jukeBoxHelper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSupportedJukeBoxes")
    public ResponseDto<List<JukeBoxResponseData>> getSupportedJukeBoxes(@RequestParam(value = "settingId") String settingId, @RequestParam(value = "model", required = false) String model,
                                                                             @RequestParam(value = "offset", required = false) Long offset, @RequestParam(value = "limit", required = false) Long limit){
        try{
            List<JukeBoxResponseData> jukeBoxResponseData = jukeBoxHelper.getSupportedJukeBoxes(settingId, model, offset, limit);
            return new ResponseDto<>(jukeBoxResponseData);
        }
        catch (Exception e){
            log.error("Error while fetching eligible jukeboxes for setting id " + settingId + " :: "  + e );
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred, Please contact application owner"));
        }
    }

}