package si.csp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main_FC {

	public static void main(String[] args) throws Exception {

		System.out.println("P1:\n");

		String v1 = "X1";
		String v2 = "X2";
		String v3 = "X3";

		LinkedList<String> varList = new LinkedList<>();
		varList.add(v1);
		varList.add(v2);
		varList.add(v3);

		Map<String, LinkedList<Integer>> varDomFC = new HashMap<String, LinkedList<Integer>>();

		LinkedList<Integer> e1 = new LinkedList<>();
		for (int i = 0; i <= 10; i++) {
			e1.add(i);
		}
		varDomFC.put(v1, e1);

		LinkedList<Integer> e2 = new LinkedList<>();
		for (int i = 5; i <= 15; i++) {
			e2.add(i);
		}
		varDomFC.put(v2, e2);

		LinkedList<Integer> e3 = new LinkedList<>();
		for (int i = 8; i <= 15; i++) {
			e3.add(i);
		}
		varDomFC.put(v3, e3);

		Constraint c1 = new Constraint("X1", "X2", ">");
		Constraint c2 = new Constraint("X2", "X3", ">");

		LinkedList<Constraint> conList = new LinkedList<>();
		conList.add(c1);
		conList.add(c2);

		System.out.println("\nCSP_FC start:\n");
		State_FC start = new State_FC(varList, varDomFC, conList);
		State_FC goal = CSP_FC.PSR(start);
		System.out.println("\nCSP_FC results:");
		if (goal != null) {
			System.out.println(goal.m.varValues.toString());
		} else {
			System.out.println("Error");
		}

		System.out.println("\n\nEND\n\n");
	}

}
