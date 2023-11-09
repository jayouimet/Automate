package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Automate {
    public String startingState;
    public HashMap<String, State> states;
    public Character[] alphabet;

    public Automate (Character[] alphabet) {
        this.states = new HashMap<String, State>();
        this.alphabet = alphabet;
    }

    public void AddTransition (String nameFrom, String nameTo, Character symbol) {
        if (!states.containsKey(nameFrom) || !states.containsKey(nameFrom)) {
            return;
        }

        states.get(nameFrom).addNextState(symbol, states.get(nameTo));
    }

    public void AddState (State state) {
        states.put(state.name, state);
    }

    public boolean EstAcceptant (String mot) {
        return states.get(startingState).accepts(mot);
    }

    public boolean EstDeterministe() {
        for (State state: states.values()) {
            if (!state.deterministe()) {
                return false;
            }
        }
        return true;
    }

    public String toDot() {
        String ret = "";
        
        ret += "digraph G {\n";
        ret += "start -> " + this.startingState + ";\n";
        for (State state: this.states.values()) {

            for (Map.Entry<Character, ArrayList<State>> entry: state.nextStates.entrySet()) {
                for (State subState: entry.getValue()) {
                    ret += state.name + " -> " + subState.name + " [label=" + entry.getKey() + "];\n";
                }
            }
        }
        ret += "}\n";

        return ret;
    }
}
