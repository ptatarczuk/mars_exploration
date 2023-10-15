package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

public interface ConfigurationValidator {
    boolean validate(ConfigurationParameters configurationParameters, Map map);
}
