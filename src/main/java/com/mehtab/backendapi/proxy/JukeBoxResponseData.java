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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Juke box response data.
 * @author mehtab singh
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JukeBoxResponseData implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("model")
    private String model;

    @JsonProperty("components")
    private ArrayList<Component> components;

    @Override
    public boolean equals(Object object){
        if(object == null) return false;
        if(!object.getClass().equals(this.getClass())) return false;
        JukeBoxResponseData otherObject = (JukeBoxResponseData) object;
        return Objects.equals(this.id, otherObject.getId()) && Objects.equals(this.model, otherObject.getModel())
                && otherObject.getComponents().stream().map(Component::getName).collect(Collectors.toList()).equals(this.getComponents().stream().map(Component::getName).collect(Collectors.toList()));
    }

    /**
     * The type Component.
     */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Component implements Serializable{

        @JsonProperty("name")
        private String name;

    }

}
