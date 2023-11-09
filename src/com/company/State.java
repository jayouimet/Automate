package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class State {
    public boolean isAccepting;
    public String name;
    public HashMap<Character, ArrayList<State>> nextStates;

    public State (String name, boolean isAccepting) {
        this.isAccepting = isAccepting;
        this.nextStates = new HashMap<Character, ArrayList<State>>();
        this.name = name;
    }

    public void addNextState(Character c, State nextState) {
        ArrayList<State> states;
        if (nextStates.containsKey(c)) {
            states = nextStates.get(c);
        } else {
            states = new ArrayList<State>();
        }
        states.add(nextState);
        nextStates.put(c, states);
    }

    public boolean accepts(String word) {
        if (word.length() == 0) {
            return isAccepting;
        }
        char nextChar = word.charAt(0);
        if (nextStates.containsKey(nextChar)){
            ArrayList<State> states = nextStates.get(nextChar);

            for (State s : states) {
                if (s.accepts(word.substring(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deterministe() {
        for (Map.Entry<Character, ArrayList<State>> entry: nextStates.entrySet()) {
            if (entry.getValue().size() > 1) {
                return false;
            }
            if (entry.getKey() == '-') {
                return false;
            }
        }
        return true;
    }
}
