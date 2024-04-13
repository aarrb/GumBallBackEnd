package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;
    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }
    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "can not do that";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = " inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "plz wait";
        }
        return new TransitionResult(succeeded, message, state, count);
    }
    @Override
    public TransitionResult ejectQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "ejecting";
            succeeded = true;
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "no quarter to eject!";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "ejecting";
            succeeded = true;
        }
        return new TransitionResult(succeeded, message, state, count);
    }
    @Override
    public TransitionResult turnCrank() {
        boolean succeeded = false;
        String message = "";
        String stateAfterAttempt = null;
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "turning";
            state = SOLD;
            return dispense();
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "No";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "The machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "nope nothing";
        }
        stateAfterAttempt = state;
        return new TransitionResult(succeeded, message, state, count);
    }
    @Override
    public TransitionResult dispense() {
            boolean succeeded = false;
            String message = "";
            String stateAfterAttempt = null;
            if(state.equalsIgnoreCase(SOLD)){
                message = "ball comes rolling ";
                count = count - 1;
                if (count == 0){
                    message = "out of gumballs!";
                    state = SOLD_OUT;
                } else {
                    state = NO_QUARTER;
                }
                succeeded = true;
            } else if (state.equalsIgnoreCase(NO_QUARTER)){
                message = "Pay 1st !";
            } else if (state.equalsIgnoreCase(SOLD_OUT)){
                message = "no dispensed";
            } else if (state.equalsIgnoreCase(HAS_QUARTER)){
                message = "no dispensed";
            }
            stateAfterAttempt = state;
            return new TransitionResult(succeeded, message, state, count);
        }
        @Override
        public void changeTheStateTo(GumballMachineState name) {
        }
        @Override
        public Integer getCount() {
            return count;
        }
        @Override
        public String getTheStateName() {
            return null;
        }
        @Override
        public void releaseBall() {
        }
    }