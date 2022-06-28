package com.ryuProject.tripleProject.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.stream.Stream;

public enum ActionType {
    ADD("ADD"),
    MOD("MOD"),
    DELETE("DELETE");

    private final String code;

    ActionType(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ActionType findByCode(String code) {
        return Stream.of(ActionType.values())
                .filter(c -> Objects.equals(c.code, code))
                .findFirst()
                .orElse(null);
    }
}
