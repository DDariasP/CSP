package si.csp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class State {

	public LinkedList<String> varList;
	public Map<String, Integer> varValues;
	public Map<String, LinkedList<Integer>> varDom;
	public final LinkedList<Constraint> conList;

	public State(State s, LinkedList<String> aux, String var, int n) {
		varList = aux;
		varValues = s.varValues;
		varValues.put(var, n);
		varDom = s.varDom;
		conList = s.conList;
	}

	public State(LinkedList<String> v1, Map<String, LinkedList<Integer>> v2, LinkedList<Constraint> v3) {
		varList = v1;
		varValues = new HashMap<String, Integer>();
		varDom = v2;
		conList = v3;
	}
	
	public Boolean testGoal() {
		Iterator<Constraint> iterator = conList.iterator();
		Boolean flag = true;
		while (iterator.hasNext() && flag) {
			Constraint c = iterator.next();
			Integer a = varValues.get(c.X);
			Integer b = varValues.get(c.Y);
			if (a == null || b == null) {
				flag = false;
			} else {
				flag = c.test(a, b);
			}
		}
		return flag;
	}

	public Boolean testCons(String var, int n) {	
		Iterator<Constraint> iterator = conList.iterator();
		Boolean flag = true;
		while (iterator.hasNext() && flag) {
			Constraint c = iterator.next();
			if (c.X.equals(var)) {
				String y = c.Y;
				Integer m = varValues.get(y);
				if (m != null) {
					flag = c.test(n, m);
				}
			}
		}
		if (flag) {
			varValues.put(var, n);
		}
		return flag;
	}

}
