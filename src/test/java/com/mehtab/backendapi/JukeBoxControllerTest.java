package com.mehtab.backendapi;


import com.fasterxml.jackson.core.type.TypeReference;
import com.mehtab.backendapi.helpers.JukeBoxHelper;
import com.mehtab.backendapi.proxy.JukeBoxResponseData;
import com.mehtab.backendapi.proxy.JukeBoxSettingsResponseData;
import com.mehtab.backendapi.utility.JsonMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class JukeBoxControllerTest {

        private static final String JUKE_BOX_DATA_PATH = "src/test/resources/JukeBoxData/jukeBoxList.json";
        private static final String JUKE_BOX_SETTINGS_DATA_PATH = "src/test/resources/JukeBoxData/jukeBoxSettingsList.json";
        private static final String SUPPORTED_JUKE_BOXES_RESPONSE = "src/test/resources/JukeBoxData/supportedJukeBoxesResponse.json";

        JukeBoxHelper jukeBoxHelper;

        @Before
        public void setUp() {
                jukeBoxHelper = new JukeBoxHelper();
        }

        @Test
        public void getSupportedJukeBoxes() throws Exception{

                String settingId = "2321763c-8e06-4a31-873d-0b5dac2436da";
                String model = null;
                Long offset = null;
                Long limit = null;
                ArrayList<JukeBoxResponseData> jukeBoxResponseData = (JsonMapper.buildJsonToBeanForNestedObject(JUKE_BOX_DATA_PATH, new TypeReference<ArrayList<JukeBoxResponseData>>(){}));
                JukeBoxSettingsResponseData jukeBoxSettingsResponseData =JsonMapper.buildJsonToBeanForNestedObject(JUKE_BOX_SETTINGS_DATA_PATH, new TypeReference<JukeBoxSettingsResponseData>(){});
                Optional<JukeBoxSettingsResponseData.SettingsData> settingsData = jukeBoxSettingsResponseData.getSettingsData().stream().filter(data -> Objects.equals(data.getId(), settingId)).findFirst();
                List<String> requiredComponents = jukeBoxHelper.getRequiredComponents(settingsData);
                List<JukeBoxResponseData> jukeBoxResponseDataList =  jukeBoxHelper.filterSupportedJukeBoxes(requiredComponents, jukeBoxResponseData, model, offset, limit);

                ArrayList<JukeBoxResponseData> supportedJukeBoxResponseData = JsonMapper.buildJsonToBeanForNestedObject(SUPPORTED_JUKE_BOXES_RESPONSE, new TypeReference<ArrayList<JukeBoxResponseData>>(){});
                Assertions.assertThat(supportedJukeBoxResponseData).isEqualTo(jukeBoxResponseDataList);

        }

}
