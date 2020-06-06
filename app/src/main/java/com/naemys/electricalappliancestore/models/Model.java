package com.naemys.electricalappliancestore.models;

import java.util.Map;

public abstract class Model<T> {
    abstract public T fromMap(Map<String, String> m);
    abstract public Map<String, String> toMap(Boolean withId);
    abstract public String getTableName();
}
