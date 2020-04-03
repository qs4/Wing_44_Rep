package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.List;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_basis_X__calc_new extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Coordinates_Basis coord_b : getGraph().allInstances(Coordinates_Basis.class)) {
//			System.out.println("START -------:  ");

			Bauteil_gen bauteil = coord_b.getProfilebene().getBauteil();

			int bauteiltypNr = bauteil.getBauteiltypnummer();
			BauteilElement bauteilE = bauteil.getBauteilE();
			Segment seg = bauteil.getBauteilE().getSE().getSegments();
			Subsection subsec = bauteil.getBauteilE().getSE().getSegments().getSubsection();

			NumberQuantity ypos = coord_b.getProfilebene().getYpos();

			calc_x_s(coord_b, bauteil, subsec, seg, ypos);
			calc_x_s2(coord_b, bauteil, subsec, seg);
//			System.out.println("bauteil:  "+ bauteil);
			// dafür nicht implementiert!: machen!
			if (bauteiltypNr != 3) {
				calc_x_m_s(coord_b, bauteil, subsec, seg, ypos);

				calc_x_m_e(coord_b, bauteil, subsec, seg, ypos);
				calc_x_e(coord_b, bauteil, subsec, seg, ypos);
			}

		}
	}

	private void calc_x_s(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,
			NumberQuantity ypos) {

		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();
		int segmentnummer = seg.getSegmentnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		Profilpoints pp = null;
		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();
		NumberQuantity x_s = null;

		if (sectionnummer == 1) {
			if (segmentnummer == 0) {
				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_a();
					x2 = pp_right.getX_a();
					int Nr1 = 199;
					int Nr2 = 201;
					coord_b.getNr1().add(Nr1);
					coord_b.getNr2().add(Nr2);
				}
			} else if (segmentnummer == 1) {
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
			}

		} else if (sectionnummer == 2) {
			if (segmentnummer == 0) {
				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_a();
					x2 = pp_right.getX_a();
					int Nr1 = 199;
					int Nr2 = 201;
					coord_b.getNr1().add(Nr1);
					coord_b.getNr2().add(Nr2);
				}

			} else if (segmentnummer == 1) {
				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_a();
					x2 = pp_right.getX_a();
				} else if (bauteiltypnummer == 3) {
					x1 = pp_left.getX_g();
					x2 = pp_right.getX_g();
				}
			}
		}
		x_s = interpolatex(ypos, y1, y2, x1, x2);

		coord_b.getX().add(x_s.getValue());
	}

	private void calc_x_s2(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg) {
		int segmentnummer = seg.getSegmentnummer();
		double x_s2 = 0;

		if (segmentnummer == 1) {

			Sparcap sparcap_up = (Sparcap) seg.getStructuralElement().getSCE().getBauteil_top().get(0);

			double sparwidth = sparcap_up.getWidth_2().getValue();
			double x_s = coord_b.getX().get(0).doubleValue();
			x_s2 = x_s + sparwidth;
		} else if (segmentnummer == 0) {
			double x_s = coord_b.getX().get(0).doubleValue();
			x_s2 = x_s;
			int Nr1 = 0;
			int Nr2 = 0;
			coord_b.getNr1().add(Nr1);
			coord_b.getNr2().add(Nr2);
		}
		coord_b.getX().add(x_s2);
	}

	private void calc_x_m_s(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,
			NumberQuantity ypos) {

		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();
		int segmentnummer = seg.getSegmentnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();

		NumberQuantity x_m_s = null;

		if (sectionnummer == 1) {
//			if (segmentnummer == 1) {

			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_b();
				x2 = pp_right.getX_b();

			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_e();
				x2 = pp_right.getX_e();
			}
//			} else if (segmentnummer == 0) {
//				if (bauteiltypnummer == 1) {
//					x1 = pp_left.getX_b();
//					x2 = pp_right.getX_b();
//				}
//			}
		} else if (sectionnummer == 2) {
//			if (segmentnummer == 1) {

			if (bauteiltypnummer == 1) {
				x1 = pp_left.getX_b();
				x2 = pp_right.getX_b();
			} else if (bauteiltypnummer == 2) {
				x1 = pp_left.getX_e();
				x2 = pp_right.getX_e();
			}
//			} else if (segmentnummer == 0) {
//				if (bauteiltypnummer == 1) {
//					x1 = pp_left.getX_b();
//					x2 = pp_right.getX_b();
//				}
//			}
		}
		x_m_s = interpolatex(ypos, y1, y2, x1, x2);

		coord_b.getX().add(x_m_s.getValue());

	}

	private void calc_x_m_e(Coordinates_Basis coord_b, Bauteil_gen bauteil, Subsection subsec, Segment seg,
			NumberQuantity ypos) {

		int bauteiltypnummer = bauteil.getBauteiltypnummer();
		int sectionnummer = subsec.getSectionnummer();
		int segmentnummer = seg.getSegmentnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		Profilpoints pp = null;
		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();
		NumberQuantity x_m_e = null;

		if (sectionnummer == 1) {
			if (segmentnummer == 1) {

				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_c();
					x2 = pp_right.getX_c();

				} else if (bauteiltypnummer == 2) {
					x1 = pp_left.getX_f();
					x2 = pp_right.getX_f();
				}
			} else if (segmentnummer == 0) {
				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_f();
					x2 = pp_right.getX_f();

				}
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
		int segmentnummer = seg.getSegmentnummer();

		Profilpoints pp_right = seg.getPp_right();
		Profilpoints pp_left = seg.getPp_left();

		NumberQuantity x1 = null;
		NumberQuantity x2 = null;
		NumberQuantity y1 = pp_left.getY();
		NumberQuantity y2 = pp_right.getY();
		NumberQuantity x_e = null;

		if (sectionnummer == 1) {
			if (segmentnummer == 1) {

				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_d();
					x2 = pp_right.getX_d();

				} else if (bauteiltypnummer == 2) {
					x1 = pp_left.getX_g();
					x2 = pp_right.getX_g();
				}
			} else if (segmentnummer == 0) {
				if (bauteiltypnummer == 1) {
					x1 = pp_left.getX_g();
					x2 = pp_right.getX_g();
				}
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