package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Profilebene_y__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profilebene profileebene : getGraph().allInstances(Profilebene.class)) {

			Bauteil_gen bauteil = profileebene.getBauteil();
//			System.out.println("START ---- bauteil:   " + bauteil);

			BauteilElement bauteilE = bauteil.getBauteilE();
			Segment seg = bauteil.getBauteilE().getSE().getSegments();
			Subsection subsec = bauteil.getBauteilE().getSE().getSegments().getSubsection();

			NumberQuantity ypos = calc_ypos(profileebene, bauteil, bauteilE, subsec);
			NumberQuantity xpos = calc_xpos(profileebene, subsec, ypos);
			NumberQuantity laenge = calc_laenge(profileebene, subsec, ypos);

		}
	}



	private NumberQuantity calc_ypos(Profilebene profileebene, Bauteil_gen bauteil, BauteilElement bauteilE,
			Subsection subsec) {

		NumberQuantity ypos = null;

		if (bauteil instanceof Rib) {
			ypos = calc_ypos_rib(profileebene, bauteil, bauteilE, subsec);
		} else {
			ypos = calc_ypos_else(profileebene, subsec);
		}

		profileebene.setYpos(ypos);

		return ypos;

	}

	private NumberQuantity calc_ypos_rib(Profilebene profileebene, Bauteil_gen bauteil, BauteilElement bauteilE,
			Subsection subsec) {
		int is_right_flag = profileebene.getIs_right_flag();
		Rib rib = (Rib) bauteil;
		Integer ribtypnummer = rib.getBauteiltypnummer();

		RibElement ribE = (RibElement) bauteil.getBauteilE();

		NumberQuantity verschiebung_y = subsec.getY_left();
		Integer ribnummer = rib.getBauteilnummer()-1;
		NumberQuantity ypos = null;
		NumberQuantity thickness = null;
		if (ribtypnummer == 1) {
			thickness = ribE.getThickness();
			NumberQuantity rippenabstand1 = ribE.getRippenabstand1();
			ypos = verschiebung_y.add(rippenabstand1.divide(2))
					.add((rippenabstand1.add(thickness)).multiply(ribnummer));

		} else if (ribtypnummer == 2) {
			thickness = ribE.getThickness();
			NumberQuantity rippenabstand2 = ribE.getRippenabstand2();
			ypos = verschiebung_y.add(rippenabstand2.divide(2))
					.add((rippenabstand2.add(thickness)).multiply(ribnummer));

		}
		if (is_right_flag == 1) {
			ypos = ypos.add(thickness);
		}
		return ypos;
	}

	private NumberQuantity calc_ypos_else(Profilebene profileebene, Subsection subsec) {
		int is_right_flag = profileebene.getIs_right_flag();

		NumberQuantity verschiebung_y_left = subsec.getY_left();
		NumberQuantity verschiebung_y_right = subsec.getY_right();

		NumberQuantity ypos = verschiebung_y_left;

		if (is_right_flag == 1) {
			ypos = verschiebung_y_right;

		}
		return ypos;
	}

	private NumberQuantity calc_xpos(Profilebene profile, Subsection subsec, NumberQuantity ypos) {

		NumberQuantity y_left = subsec.getY_left();
		NumberQuantity y_right = subsec.getY_right();
		NumberQuantity x_left = subsec.getX_left();
		NumberQuantity x_right = subsec.getX_right();

		NumberQuantity xpos = interpolatex(ypos, y_left, y_right, x_left, x_right);

		profile.setXpos(xpos);

		return xpos;

	}

	private NumberQuantity calc_laenge(Profilebene profile, Subsection subsec, NumberQuantity ypos) {

		NumberQuantity l_left = subsec.getLaenge_left();
		NumberQuantity l_right = subsec.getLaenge_right();
		NumberQuantity y_left = subsec.getY_left();
		NumberQuantity y_right = subsec.getY_right();

		NumberQuantity laenge = interpolatex(ypos, y_left, y_right, l_left, l_right);

		profile.setLaenge(laenge);

		return laenge;
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