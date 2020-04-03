package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Subsegments__SubProzent__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Segment subseg : getGraph().allInstances(Segment.class)) {

			double prozent_vorne = subseg.getProzent_vorne().getValue();
			double prozent_hinten = subseg.getProzent_hinten().getValue();

			double laenge_prozent_segment = prozent_hinten - prozent_vorne;

			// Später für jede Section und Segment einzeln setzen wenn nötig!:
			subseg.setA_prozent(0.0);
			subseg.setB_prozent(0.17);
			subseg.setC_prozent(0.39);
			subseg.setD_prozent(0.5);
			subseg.setE_prozent(0.67);
			subseg.setF_prozent(0.89);
			subseg.setG_prozent(1.0);

			double a_prozent_lokal = subseg.getA_prozent().getValue();
			double b_prozent_lokal = subseg.getB_prozent().getValue();
			double c_prozent_lokal = subseg.getC_prozent().getValue();
			double d_prozent_lokal = subseg.getD_prozent().getValue();
			double e_prozent_lokal = subseg.getE_prozent().getValue();
			double f_prozent_lokal = subseg.getF_prozent().getValue();
			double g_prozent_lokal = subseg.getG_prozent().getValue();

			double a_prozent_absolut = laenge_prozent_segment * a_prozent_lokal + prozent_vorne;
			double b_prozent_absolut = laenge_prozent_segment * b_prozent_lokal + prozent_vorne;
			double c_prozent_absolut = laenge_prozent_segment * c_prozent_lokal + prozent_vorne;
			double d_prozent_absolut = laenge_prozent_segment * d_prozent_lokal + prozent_vorne;
			double e_prozent_absolut = laenge_prozent_segment * e_prozent_lokal + prozent_vorne;
			double f_prozent_absolut = laenge_prozent_segment * f_prozent_lokal + prozent_vorne;
			double g_prozent_absolut = laenge_prozent_segment * g_prozent_lokal + prozent_vorne;

			subseg.setA_absolut_prozent(a_prozent_absolut);
			subseg.setB_absolut_prozent(b_prozent_absolut);
			subseg.setC_absolut_prozent(c_prozent_absolut);
			subseg.setD_absolut_prozent(d_prozent_absolut);
			subseg.setE_absolut_prozent(e_prozent_absolut);
			subseg.setF_absolut_prozent(f_prozent_absolut);
			subseg.setG_absolut_prozent(g_prozent_absolut);

			// Test
			calc_prozente(subseg);

		}
	}

	private void calc_prozente(Segment subseg) {
		double prozent_vorne = subseg.getProzent_vorne().getValue();
		double prozent_hinten = subseg.getProzent_hinten().getValue();

		double laenge_prozent_segment = prozent_hinten - prozent_vorne;
		int anzprozent = subseg.getAnzprozent();
		
		double laenge_prozent_subsegment = laenge_prozent_segment/(anzprozent+1);
//		System.out.println("laenge_prozent_subsegment:  "+laenge_prozent_subsegment);
		
		for (int i = 0; i < anzprozent; i++) {
			double x_absolut_prozent = laenge_prozent_subsegment*i+prozent_vorne;
//			System.out.println("prozentnr:  "+i);

//			System.out.println("x_absolut_prozent:  "+x_absolut_prozent);
			subseg.getX_absolut_prozent().add(x_absolut_prozent);
		}
		
	}

}