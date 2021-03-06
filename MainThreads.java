
import java.util.ArrayList;
import java.util.List;

public class MainThreads {

	public static void main(String args[]) {
		long start, elapsed;
		start = System.currentTimeMillis();
		
		MagicSquareThread qm1 = new MagicSquareThread("t1", 1, 4);
		MagicSquareThread qm2 = new MagicSquareThread("t2",5, 8);
		MagicSquareThread qm3 = new MagicSquareThread("t3", 9, 12);
		MagicSquareThread qm4 = new MagicSquareThread("t4",13, 16);

		qm1.start();
		qm2.start();
		qm3.start();
		qm4.start();
		while(qm1.isAlive() && qm2.isAlive() && qm3.isAlive() && qm4.isAlive());
		List<M> qm = new ArrayList<M>();
		qm.addAll(qm1.getQm());
		qm.addAll(qm2.getQm());
		qm.addAll(qm3.getQm());
		qm.addAll(qm4.getQm());
		
		boolean[] removido = new boolean[qm.size()];

		/*Initializes the vector that will define the matrices to be removed*/
		for (int i = 0; i < removido.length; i++) {
			removido[i] = false;
		}

		boolean rodado1, rodado2, rodado3, espelhoL, espelhoV, espelhoD, espelhoD2;

		/*Mark the matrices that are rotations or reflections, to be removed suitably, leaving only one of each*/
		for (int h = 0; h < qm.size(); h++) {
			for (int l = 0; l < qm.size(); l++) {
				if (h == l || removido[h])
					continue;

				rodado1 = rodado2 = rodado3 = true;
				espelhoL = espelhoV = espelhoD = espelhoD2 = true;

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (qm.get(h).get(i, j) != qm.get(l).get(j, (4 - i) - 1)) {
							rodado1 = false;
						}
						if (qm.get(h).get(i, j) != qm.get(l).get((4 - i) - 1, (4 - j) - 1)) {
							rodado2 = false;
						}
						if (qm.get(h).get(i, j) != qm.get(l).get((4 - i) - 1, i)) {
							rodado3 = false;
						}
						if (qm.get(h).get(i, j) != qm.get(l).get(i, (4 - j) - 1)) {
							espelhoL = false;
						}
						if (qm.get(h).get(i, j) != qm.get(l).get((4 - i - 1), j)) {
							espelhoV = false;
						}
						if (qm.get(h).get(i, j) != qm.get(l).get((4 - j - 1), (4 - i - 1))) {
							espelhoD = false;
						}
						if (qm.get(h).get(i, j) != qm.get(l).get(j, i)) {
							espelhoD2 = false;
						}
					}
				}

				if (rodado1 || rodado2 || rodado3 || espelhoL || espelhoV || espelhoD || espelhoD2) {
					removido[l] = true;
				}
			}
		}

		/*Removes rotations and reflections*/
		for (int i = removido.length - 1; i >= 0; i--) {
			if (removido[i]) {
				qm.remove(i);
			}
		}

		/*Prints all matrices*/
		for (M mi : qm) {
			mi.print();
			System.out.println("---------");
		}

		System.out.println(qm.size());

		elapsed = System.currentTimeMillis() - start;
		System.out.println((elapsed / 1000.0) + " segundos");
	
	}
	
	
	
	
}
