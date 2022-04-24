package com.mehtab.backendapi.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Juke box settings response data.
 * @author mehtab singh
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JukeBoxSettingsResponseData implements Serializable {

    @JsonProperty("settings")
    private ArrayList<SettingsData> settingsData;


    /**
     * The type Settings data.
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class SettingsData implements Serializable {

        @JsonProperty("id")
        private String id;

        @JsonProperty("requires")
        private ArrayList<String> requires;

    }

}
