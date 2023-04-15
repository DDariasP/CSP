package si.csp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("P1:\n");

		String v1 = "X1";
		String v2 = "X2";
		String v3 = "X3";

		LinkedList<String> varList = new LinkedList<>();
		varList.add(v1);
		varList.add(v2);
		varList.add(v3);

		Map<String, LinkedList<Integer>> varDomAC3 = new HashMap<String, LinkedList<Integer>>();
		Map<String, LinkedList<Integer>> varDomCSP = new HashMap<String, LinkedList<Integer>>();


		LinkedList<Integer> dom1 = new LinkedList<>();
		for (int i = 0; i <= 10; i++) {
			dom1.add(i);
		}
		varDomAC3.put(v1, dom1);
		varDomCSP.put(v1, dom1);

		LinkedList<Integer> dom2 = new LinkedList<>();
		for (int i = 5; i <= 15; i++) {
			dom2.add(i);
		}
		varDomAC3.put(v2, dom2);
		varDomCSP.put(v2, dom2);

		LinkedList<Integer> dom3 = new LinkedList<>();
		for (int i = 8; i <= 15; i++) {
			dom3.add(i);
		}
		varDomAC3.put(v3, dom3);
		varDomCSP.put(v3, dom3);

		Constraint c1 = new Constraint("X1", "X2", ">");
		Constraint c2 = new Constraint("X2", "X3", ">");

		LinkedList<Constraint> conList = new LinkedList<>();
		conList.add(c1);
		conList.add(c2);

		AC3 p1 = new AC3(varDomAC3, conList);

		p1.ac3();

		System.out.println("AC3 results:");
		for (Map.Entry<String, LinkedList<Integer>> set : varDomAC3.entrySet()) {
			String v = set.getKey();
			LinkedList<Integer> list = set.getValue();
			System.out.println(v + "=" + list);
		}

		State start = new State(varList, varDomCSP, conList);
		State goal = CSP.PSR_Rec(start);
		System.out.println("\nCSP_FC results:");
		if (goal != null) {
			System.out.println(goal.varValues.toString());
		} else {
			System.out.println("Error");
		}

		System.out.println("\n\nEND\n\n");
	}

}
