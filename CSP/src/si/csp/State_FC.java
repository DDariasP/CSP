package si.csp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class State_FC {

	public Memory m;
	private final LinkedList<String> varList;
	private final Map<String, LinkedList<Integer>> varDom;
	private final LinkedList<Constraint> conList;

	public State_FC(LinkedList<String> v1, Map<String, LinkedList<Integer>> v2, LinkedList<Constraint> v3) {
		varList = v1;
		varDom = v2;
		conList = v3;
	}

	public State_FC(State_FC s, String var, int n, Memory memory) {
		varList = s.getVarList();
		varDom = s.getD();
		conList = s.conList;
		memory.varList.addLast(var);
		memory.varValues.put(var, n);
	}

	public Boolean testGoal(Memory memory) {

		Iterator<Constraint> ite1 = conList.iterator();
		Boolean flag = true;

		while (ite1.hasNext() && flag) {
			Constraint c = ite1.next();
			Integer a = memory.varValues.get(c.X);
			Integer b = memory.varValues.get(c.Y);
			if (a == null || b == null) {
				flag = false;
			} else {
				flag = c.test(a, b);
			}
		}
		return flag;
	}

	public boolean forwardCheck(String var, int n, Memory memory) {

		Boolean empty = false;
		Iterator<Constraint> ite1 = conList.iterator();

		while (ite1.hasNext()) {
			Constraint constraint = ite1.next();
			if (constraint.X == var) {
				LinkedList<Integer> modListY = new LinkedList<>();
				String keyY = constraint.Y;
				LinkedList<Integer> listY = memory.tmpDom.get(keyY);
				System.out.println("checking " + var + ":" + n);
				System.out.println("dom" + keyY + "=" + listY.toString());
				Iterator<Integer> ite2 = listY.iterator();

				while (ite2.hasNext()) {
					Integer y = ite2.next();
					System.out.println("testing " + keyY + ":" + y);
					System.out.println(constraint.X + ":" + n + " " + constraint.condition + " " + keyY + ":" + y
							+ " = " + constraint.test(n, y));
					if (constraint.test(n, y)) {
						System.out.println("valid");
						if (!modListY.contains(y)) {
							System.out.println("add");
							modListY.add(y);
						}
					} else {
						System.out.println("invalid");
						System.out.println("remove");
					}
				}

				empty = modListY.isEmpty();
				System.out.println(keyY + "empty:" + empty);
				System.out.println("modlist" + keyY + "=" + modListY.toString());
				System.out.println("dom" + keyY + "=" + modListY.toString());

			}
			for (Iterator<String> ite3 = memory.varList.iterator(); ite3.hasNext();) {

				String restoreAll = ite3.next();
				if (!var.equals(restoreAll)) {
					memory.tmpDom.put(restoreAll, getD().get(restoreAll));
				}
			}
		}
		return empty;
	}

	public LinkedList<String> getVarList() {
		return varList;
	}

	public Map<String, LinkedList<Integer>> getD() {
		return varDom;
	}

}
