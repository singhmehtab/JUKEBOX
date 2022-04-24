package com.mehtab.backendapi.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;


@FeignClient(name = "jukeboxService", url = "${jukeboxService.host}")
public interface JukeBox {

    @RequestMapping(method = RequestMethod.GET, value = Constants.jukeBoxDataEndPoint)
    ArrayList<JukeBoxResponseData> getJukeBoxData();

    @RequestMapping(method = RequestMethod.GET, value = Constants.settingsDataEndPoint)
    JukeBoxSettingsResponseData getJukeBoxSettingsData();

}
