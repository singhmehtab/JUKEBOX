package com.mehtab.backendapi.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;


/**
 * The interface Juke box.
 * @author mehtab singh
 */
@FeignClient(name = "jukeboxService", url = "${jukeboxService.host}")
public interface JukeBox {

    /**
     * Gets juke box data.
     *
     * @return the juke box data
     */
    @RequestMapping(method = RequestMethod.GET, value = Constants.jukeBoxDataEndPoint)
    ArrayList<JukeBoxResponseData> getJukeBoxData();

    /**
     * Gets juke box settings data.
     *
     * @return the juke box settings data
     */
    @RequestMapping(method = RequestMethod.GET, value = Constants.settingsDataEndPoint)
    JukeBoxSettingsResponseData getJukeBoxSettingsData();

}
