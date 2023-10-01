package com.quinnlas.microbrewery;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable.Deserializable;

@Introspected
@Deserializable
public record PourInfo (String name) {}
