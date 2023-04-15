package si.csp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class AC3 {

	public Map<String, LinkedList<Integer>> varDom;
	public LinkedList<Constraint> conList;

	public AC3(Map<String, LinkedList<Integer>> v1, LinkedList<Constraint> v2) {
		varDom = v1;
		conList = v2;
	}

	public void ac3() {
		LinkedList<Arc> arcList = new LinkedList<>();
		for (Iterator<Constraint> ite1 = conList.iterator(); ite1.hasNext();) {
			Constraint constraint = ite1.next();
			Arc a1 = new Arc(constraint.X, constraint.Y);

			if (!arcList.contains(a1)) {
				arcList.add(a1);
			}
		}

		while (!arcList.isEmpty()) {
			Arc a2 = arcList.pop();
			if (arcReduce(a2)) {
				if (varDom.get(a2.X) == null) {
					System.out.println("Error");
				}
				else {
					arcList.add(a2);
				}
			}
		}

	}

	private Boolean arcReduce(Arc a) {

		Boolean change = false;
		LinkedList<Integer> modListX = new LinkedList<>();
		LinkedList<Integer> modListY = new LinkedList<>();

		String keyX = a.X;
		LinkedList<Integer> listX = varDom.get(keyX);
		String keyY = a.Y;
		LinkedList<Integer> listY = varDom.get(keyY);

		Iterator<Integer> ite1 = listX.iterator();
		while (ite1.hasNext()) {
			Integer x = ite1.next();
			Iterator<Integer> ite2 = listY.iterator();
			while (ite2.hasNext()) {
				Integer y = ite2.next();
				if (testCons(a, x, y)) {
					if (!modListX.contains(x)) {
						modListX.add(x);
					}
					if (!modListY.contains(y)) {

						modListY.add(y);
					}
				}
			}
		}
		varDom.put(keyX, modListX);
		varDom.put(keyY, modListY);
		String s1 = listX.toString();
		String s2 = modListX.toString();
		if (!s1.equals(s2)) {
			change = true;
		}
		s1 = listY.toString();
		s2 = modListY.toString();
		if (!s1.equals(s2)) {
			change = true;
		}
		return change;
	}

	private Boolean testCons(Arc a, Integer x, Integer y) {

		Boolean flag = false;
		Iterator<Constraint> ite1 = conList.iterator();
		while (ite1.hasNext()) {
			Constraint constraint = ite1.next();
			if (a.X == constraint.X && a.Y == constraint.Y) {
				flag = constraint.test(x, y);
			}
		}
		return flag;
	}

}
