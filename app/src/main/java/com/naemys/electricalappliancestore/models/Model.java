package com.naemys.electricalappliancestore.models;

import java.util.Map;

public abstract class Model<T> {
    abstract public T fromMap(Map<String, String> m);
    abstract public String getTableName();
}
