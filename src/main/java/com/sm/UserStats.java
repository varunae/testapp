package com.sm;

import java.util.Optional;
import java.util.OptionalLong;

public class UserStats {

    public Optional<Long> visitCount;

    public UserStats () {
    }

    public UserStats (Long l) {
        visitCount = Optional.of(l);
    }

}
