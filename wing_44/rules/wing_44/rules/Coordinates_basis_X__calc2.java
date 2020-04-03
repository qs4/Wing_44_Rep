package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_basis_X__calc2 extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Coordinates_Basis coord_b : getGraph().allInstances(Coordinates_Basis.class)) {

			Bauteil_gen bauteil = coord_b.getProfilebene().getBauteil();
			System.out.println("START:---:  "+bauteil);

			int bauteiltypNr = bauteil.getBauteiltypnummer();
			BauteilElement bauteilE = bauteil.getBauteilE();
			Segment seg = bauteil.getBauteilE().getSE().getSegments();
			Subsection subsec = bauteil.getBauteilE().getSE().getSegments().getSubsection();

			NumberQuantity ypos = coord_b.getProfilebene().getYpos();
					
			calc_x_s(coord_b, bauteil, subsec, seg, ypos);
			calc_x_s2(coord_b, bauteil, subsec, seg);
			System.out.println("1:  "+bauteil);

			int subsegNr = bauteil.getSubsegmentnummer();
			int sectionummer = subsec.getSectionnummer();
			// dafür nicht implementiert!: machen!
			System.out.println("2:  "+bauteil);
			if (bauteiltypNr != 3) {
				calc_x_m_s(coord_b, bauteil, subsec, seg, ypos);
				System.out.println("4:  "+bauteil);

				calc_x_m_e(coord_b, bauteil, subsec, seg, ypos);
				calc_x_e(coord_b, bauteil, subsec, seg, ypos);
			}

		}
	}

	


	private void calc_x_s(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,
			NumberQuantity ypos) {

		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		Profilpoints pp = null;
		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();
		NumberQuantity x_s = null;

		if (sectionnummer == 1) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_a();
				x2 = pp_right.getX_a();

			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_d();
				x2 = pp_right.getX_d();
			} else if (bauteiltypnummer == 3) {
				x1 = pp_left.getX_g();
				x2 = pp_right.getX_g();
			}
		} else if (sectionnummer == 2) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_a();
				x2 = pp_right.getX_a();
			} else if (bauteiltypnummer == 3) {
				x1 = pp_left.getX_g();
				x2 = pp_right.getX_g();
			}
		}
		x_s = interpolatex(ypos, y1, y2, x1, x2);

		coord_b.getX().add(x_s.getValue());
	}
	private void calc_x_s2(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg) {

		Sparcap_up sparcap_up = (Sparcap_up) seg.getStructuralElement().getSCE().getBauteil_top().get(0);

		double sparwidth = sparcap_up.getWidth_2().getValue();
		double x_s = coord_b.getX().get(0).doubleValue();
		double x_s2 = x_s + sparwidth;
		
		coord_b.getX().add(x_s2);
	}

	
	private void calc_x_m_s(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,
			NumberQuantity ypos) {

		System.out.println("3:  "+bauteil);

		
		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();

		NumberQuantity x_m_s = null;

		if (sectionnummer == 1) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_b();
				x2 = pp_right.getX_b();

			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_e();
				x2 = pp_right.getX_e();
			}
		} else if (sectionnummer == 2) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_b();
				x2 = pp_right.getX_b();
			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_e();
				x2 = pp_right.getX_e();
			}
		}
		x_m_s = interpolatex(ypos, y1, y2, x1, x2);

		coord_b.getX().add(x_m_s.getValue());

	}
	private void calc_x_m_e(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,
			NumberQuantity ypos) {

		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		Profilpoints pp = null;
		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();
		NumberQuantity x_m_e = null;

		if (sectionnummer == 1) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_c();
				x2 = pp_right.getX_c();

			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_f();
				x2 = pp_right.getX_f();
//			} else if (bauteiltypnummer == 3) {
//				x1 = pp_left.getX_g();
//				x2 = pp_right.getX_g();
			}
		} else if (sectionnummer == 2) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_f();
				x2 = pp_right.getX_f();

			}
		}
		x_m_e = interpolatex(ypos, y1, y2, x1, x2);

		coord_b.getX().add(x_m_e.getValue());
	}
	private void calc_x_e(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,

			NumberQuantity ypos) {

		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();
		NumberQuantity x_e = null;

		if (sectionnummer == 1) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_d();
				x2 = pp_right.getX_d();

			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_g();
				x2 = pp_right.getX_g();
			}
		} else if (sectionnummer == 2) {
			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_g();
				x2 = pp_right.getX_g();
			}
		}

		x_e = interpolatex(ypos, y1, y2, x1, x2);

		coord_b.getX().add(x_e.getValue());
	}

	
	
	private NumberQuantity interpolatex(NumberQuantity y, NumberQuantity y1, NumberQuantity y2, NumberQuantity x1,
			NumberQuantity x2) {


		NumberQuantity y_y1 = y.subtract(y1);
		NumberQuantity y2_y1 = y2.subtract(y1);
		NumberQuantity x2_x1 = x2.subtract(x1);

		NumberQuantity divide = y_y1.divide(y2_y1);

		NumberQuantity multiply = divide.multiply(x2_x1);

		NumberQuantity x = x1.add(multiply);


		return x;
	}
}