package com.quinnlas.microbrewery.ordering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Beer(String name, String taste, int quantity){ }
