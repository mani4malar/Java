package com.pojo;

import java.util.Map;

public class Config {
private Map<String, Environment> Environment;

public Map<String, Environment> getEnvironment() {
    return Environment;
}

public void setEnvironment(Map<String, Environment> environment) {
    this.Environment = environment;
}
}
