package com.naemys.electricalappliancestore.models;

import java.io.Serializable;
import java.util.Map;

public abstract class Model<T> implements Serializable {
    abstract public T fromMap(Map<String, String> m);
    abstract public Map<String, String> toMap(Boolean withId);
    abstract public String getTableName();
    abstract public void setId(String id);
}
