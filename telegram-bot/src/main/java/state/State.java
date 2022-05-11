package state;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum State {
    /**
     * Define the status and realize the flow of consent and rejection
     */
    STATE_NOTSTARTED("NOTSTARTED") {
        @Override
        State getNextState() {
            return STATE_STARTED;
        }
    },
    STATE_STARTED("STARTED") {
        @Override
        State getNextState() {
            return STATE_COMPLETED;
        }
    },
    STATE_COMPLETED("COMPLETED") {
        @Override
        State getNextState() {
            throw new IllegalStateException("Current end state");
        }
    };

    private final String value;

    String getValue () {
        return this.value;
    }

    State(String value) {
        this.value = value;
    }

    private static final Map<String, State> CACHE;

    static {
        CACHE = Arrays.stream(State.values()).collect(Collectors.toMap(State::getValue, Function.identity()));
    }

    public static State getByValue(String value) {
        return CACHE.get(value);
    }

    /**
     * Status after approval
     */
    abstract State getNextState();

}
