package com.mehtab.backendapi.helpers;

import com.mehtab.backendapi.proxy.JukeBox;
import com.mehtab.backendapi.proxy.JukeBoxResponseData;
import com.mehtab.backendapi.proxy.JukeBoxSettingsResponseData;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Juke box helper.
 * @author mehtab singh
 */
@Service
@NoArgsConstructor
public class JukeBoxHelper {

    private JukeBox jukeBox;

    /**
     * Instantiates a new Juke box helper.
     *
     * @param jukeBox the juke box
     */
    @Autowired
    public JukeBoxHelper(JukeBox jukeBox){
        this.jukeBox = jukeBox;
    }

    /**
     * Get supported juke boxes list.
     *
     * @param settingId the setting id
     * @param model     the model
     * @param offset    the offset
     * @param limit     the limit
     * @return the list
     */
    public List<JukeBoxResponseData> getSupportedJukeBoxes(String settingId, String model, Long offset, Long limit){

        JukeBoxSettingsResponseData jukeBoxSettingsResponseData = jukeBox.getJukeBoxSettingsData();
        Optional<JukeBoxSettingsResponseData.SettingsData> settingsData = jukeBoxSettingsResponseData.getSettingsData().stream().filter(data -> Objects.equals(data.getId(), settingId)).findFirst();
        List<String> requiredComponents = getRequiredComponents(settingsData);
        ArrayList<JukeBoxResponseData> jukeBoxResponseData = jukeBox.getJukeBoxData();
        return filterSupportedJukeBoxes(requiredComponents, jukeBoxResponseData, model, offset, limit);

    }

    /**
     * Filter supported juke boxes list.
     *
     * @param requiredComponents  the required components
     * @param jukeBoxResponseData the juke box response data
     * @param model               the model
     * @param offset              the offset
     * @param limit               the limit
     * @return the list
     * Order of returned juke boxes is in accordance with the data as fetched from juke box apis.
     */
    public List<JukeBoxResponseData> filterSupportedJukeBoxes(List<String> requiredComponents, ArrayList<JukeBoxResponseData> jukeBoxResponseData, String model, Long offset, Long limit){

        List<JukeBoxResponseData> filteredJukeBoxes = jukeBoxResponseData.stream().filter(data -> data.getComponents().stream().map(JukeBoxResponseData.Component::getName).collect(Collectors.toList()).containsAll(requiredComponents)).collect(Collectors.toList());
        if(model != null){
            filteredJukeBoxes = filteredJukeBoxes.stream().filter(data -> data.getModel().equals(model)).collect(Collectors.toList());
        }
        if(offset != null){
            filteredJukeBoxes = filteredJukeBoxes.stream().skip(offset).collect(Collectors.toList());
        }
        if(limit != null){
            filteredJukeBoxes = filteredJukeBoxes.stream().limit(limit).collect(Collectors.toList());
        }
        return  filteredJukeBoxes;
    }

    /**
     * Get required components list.
     *
     * @param settingsData the settings data
     * @return the list
     */
    public List<String> getRequiredComponents(Optional<JukeBoxSettingsResponseData.SettingsData> settingsData){
        if(!settingsData.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setting Id is not valid");
        }
        else{
            return settingsData.get().getRequires();
        }
    }

}
