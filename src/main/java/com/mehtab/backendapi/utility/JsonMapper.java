package com.mehtab.backendapi.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class JsonMapper {

    public static <T> T buildJsonToBeanForNestedObject(String filePath, TypeReference typeReference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readValue(new File(filePath), typeReference);
    }

}
