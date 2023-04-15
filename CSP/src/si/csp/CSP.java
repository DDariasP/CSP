package si.csp;

import java.util.Iterator;
import java.util.LinkedList;

public class CSP {

	public static State PSR(State start) {
		return PSR_Rec(start);
	}

	public static State PSR_Rec(State current) {
		LinkedList<String> aux = current.varList;
		String var = aux.pop();
		aux.addLast(var);
		LinkedList<Integer> dom = current.varDom.get(var);
		for (Iterator<Integer> iterator = dom.iterator(); iterator.hasNext();) {
			int n = iterator.next();
			State next = new State(current, aux, var, n);
			if (next.testCons(var, n)) {
				if (next.testGoal()) {
					return next;
				} else {
					State goal = PSR_Rec(next);
					return goal;
				}
			}
		}
		return null;
	}

}
