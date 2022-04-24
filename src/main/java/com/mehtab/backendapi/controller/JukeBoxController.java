package com.mehtab.backendapi.controller;

import com.mehtab.backendapi.controller.dto.ResponseDto;
import com.mehtab.backendapi.helpers.JukeBoxHelper;
import com.mehtab.backendapi.proxy.JukeBoxResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collections;
import java.util.List;


/**
 * The type Juke box controller.
 * @author mehtab singh
 */
@RestController
@RequestMapping("/jukeBoxes")
@Slf4j
public class JukeBoxController {

    private final JukeBoxHelper jukeBoxHelper;

    /**
     * Instantiates a new Juke box controller.
     *
     * @param jukeBoxHelper the juke box helper
     */
    @Autowired
    public JukeBoxController(JukeBoxHelper jukeBoxHelper){
        this.jukeBoxHelper = jukeBoxHelper;
    }

    /**
     * Gets supported juke boxes.
     *
     * @param settingId the setting id
     * @param model     the model
     * @param offset    the offset
     * @param limit     the limit
     * @return the supported juke boxes
     */
    @RequestMapping(method = RequestMethod.GET, value = "getSupportedJukeBoxes")
    public ResponseDto<List<JukeBoxResponseData>> getSupportedJukeBoxes(@RequestParam(value = "settingId") String settingId, @RequestParam(value = "model", required = false) String model,
                                                                             @RequestParam(value = "offset", required = false) Long offset, @RequestParam(value = "limit", required = false) Long limit){
        log.info("Request received to fet jukeboxes for setting id :: " + settingId);
        try{
            List<JukeBoxResponseData> jukeBoxResponseData = jukeBoxHelper.getSupportedJukeBoxes(settingId, model, offset, limit);
            log.info("Request successfully completed to fetch jukeboxes for setting id :: " + settingId);
            return new ResponseDto<>(jukeBoxResponseData);
        }
        catch (ResponseStatusException e){
            log.error("Invalid Setting Id :: " + settingId);
            throw e;
        }
        catch (Exception e){
            log.error("Error while fetching eligible jukeboxes for setting id " + settingId + " :: "  + e );
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred, Please contact application owner"));
        }
    }

}