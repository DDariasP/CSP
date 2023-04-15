package si.csp;

import java.util.Iterator;
import java.util.LinkedList;

public class CSP_FC {

	public static State_FC PSR(State_FC start) {
		Memory memory = new Memory(start);
		return PSR_Rec(start, memory);
	}

	public static State_FC PSR_Rec(State_FC current, Memory memory) {

		String var = memory.varList.pop();
		LinkedList<Integer> domX = memory.tmpDom.get(var);
		System.out.println("dom" + var + "=" + domX.toString());

		for (Iterator<Integer> iterator = domX.iterator(); iterator.hasNext();) {

			int n = iterator.next();
			System.out.println("iterator=" + n);
			State_FC next = new State_FC(current, var, n, memory);

			System.out.println("pre-fc");

			if (!next.forwardCheck(var, n, memory)) {
				System.out.println("fc=true");
				Boolean fc = true;

				if (next.testGoal(memory)) {
					System.out.println("goalnext");
					if (next != null) {
						next.m = memory;
					}
					return next;
				}

				if (fc) {
					System.out.println("continue with " + var + ":" + n);
					memory.varList.remove(var);
					memory.varList.addLast(var);
					memory.varValues.put(var, n);
					memory.varDomEmpty.put(var, false);
					if (next != null) {
						next.m = memory;
					}
					return PSR_Rec(next, memory);
				}
			}

			else {
				System.out.println("fc=false");
				System.out.println("restore");
				System.out.println("next without " + var + ":" + n);
				domX = memory.tmpDom.get(var);
				domX.removeFirstOccurrence(n);
				memory.tmpDom.put(var, domX);
				System.out.println("next dom" + var + "=" + domX.toString());
				State_FC goal = PSR_Restore(current, var, n, memory);
				if (goal != null) {
					goal.m = memory;
				}
				return goal;
			}
		}
		
		return null;
	}

	private static State_FC PSR_Restore(State_FC next, String var, int n, Memory memory) {
		memory.varList.removeFirstOccurrence(var);
		memory.varList.addFirst(var);
		memory.varValues.remove(var);
		State_FC goal = PSR_Rec(next, memory);
		if (goal != null) {
			goal.m = memory;
		}
		return goal;
	}

}
