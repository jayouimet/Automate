package com.company;

import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
	    State q0 = new State("q0", false);
        State q1 = new State("q1", false);
        State q2 = new State("q2", true);

        Character[] alphabet = {'a', 'b'};
        Automate automate = new Automate(alphabet);

        automate.AddState(q0);
        automate.AddState(q1);
        automate.AddState(q2);

        automate.AddTransition("q0", "q1", 'a');
        automate.AddTransition("q0", "q0", 'b');
        automate.AddTransition("q1", "q2", 'a');
        automate.AddTransition("q1", "q0", 'b');
        automate.AddTransition("q2", "q2", 'a');
        automate.AddTransition("q2", "q2", 'b');

        automate.startingState = "q0";

        String[] tests = {"ababa", "bbbb", "", "aa", "aaba", "abaab"};

        System.out.println("Is deterministic : " + automate.EstDeterministe());

        for (String test: tests) {
            System.out.println(test + " : " + automate.EstAcceptant(test));
        }
        try{
            FileWriter myWriter = new FileWriter("automate.dot");
            myWriter.write(automate.toDot());
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
