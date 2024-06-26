package com.pst.lookup.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty("code") int code, @JsonProperty("errorMessage") String errorMessage) {}
