package si.csp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Memory {

	public LinkedList<String> varList;
	public Map<String, LinkedList<Integer>> tmpDom;
	public Map<String, Integer> varValues;
	public Map<String, Boolean> varDomEmpty;

	public Memory(State_FC state) {
		varList = new LinkedList<>();
		for (Iterator<String> ite1 = state.getVarList().iterator(); ite1.hasNext();) {
			String string = ite1.next();
			varList.add(string);
		}
		tmpDom = new HashMap<String, LinkedList<Integer>>();
		for (Map.Entry<String, LinkedList<Integer>> set : state.getD().entrySet()) {
			String v = set.getKey();
			LinkedList<Integer> list = set.getValue();
			tmpDom.put(v, list);
		}
		varValues = new HashMap<String, Integer>();
		varDomEmpty = new HashMap<String, Boolean>();
		for (Iterator<String> ite2 = varList.iterator(); ite2.hasNext();) {
			String key = ite2.next();
			Boolean value = false;
			varDomEmpty.put(key, value);
		}

	}

	public Boolean testDEmpty() {
		Boolean empty = false;
		Iterator<String> ite1 = varList.iterator();
		while (ite1.hasNext() && !empty) {
			String vres = ite1.next();
			empty = varDomEmpty.get(vres);
		}
		return empty;
	}

}
