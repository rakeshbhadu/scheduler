package com.rakesh.scheduler.enums;

/**
 * Player state
 * READY: player is ready with missiles and will hit upon instruction
 * IN_PLAY: player has been given instruction to be ready with missile
 * PLAYED: player shot the missile and is ready for next instruction
 */
public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);


    private final int value;

    Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
