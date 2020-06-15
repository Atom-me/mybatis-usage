package com.atom.mybatis.doenums;

import lombok.Getter;

/**
 * @author Atom
 */
@Getter
public enum UserState {

    ACTIVE(1, "A"),
    INACTIVE(2, "I");

    private final int state;
    private final String descp;

    UserState(int state, String descp) {
        this.state = state;
        this.descp = descp;

    }

}
