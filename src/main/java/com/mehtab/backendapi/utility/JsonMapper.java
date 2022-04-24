package com.mehtab.backendapi.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * The type Json mapper.
 */
public class JsonMapper {

    /**
     * Build json to bean for nested object t.
     *
     * @param <T>           the type parameter
     * @param filePath      the file path
     * @param typeReference the type reference
     * @return the t
     * @throws IOException the io exception
     */
    public static <T> T buildJsonToBeanForNestedObject(String filePath, TypeReference typeReference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readValue(new File(filePath), typeReference);
    }

}
