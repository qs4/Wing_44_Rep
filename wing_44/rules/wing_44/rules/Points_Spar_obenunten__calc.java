package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Spar_obenunten__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

			int is_spar_ou_flag = cpoint.getIs_Spar_ou_flag();
			
			if (is_spar_ou_flag == 1) {
				setValuesSpar_obenunten(cpoint);
			}
							
		}
	}


	private void setValuesSpar_obenunten(Controlpoint_gen cpoint) {
		Spar spar = (Spar) cpoint.getPL_Spar_ou().getWire_gen().getProfilebene_gen().getBauteil();

		PolyLine_Spar_obenunten pl = (PolyLine_Spar_obenunten) cpoint.getPL_Spar_ou();

		int is_up_flag = pl.getIs_up_flag();

		Sparcap sparcap = null;
		if (is_up_flag == 1) {
			sparcap = (Sparcap) pl.getWire_gen().getProfilebene_gen().getBauteil().getBauteilE().getSE().getSCE()
					.getBauteil_top().get(0);
		} else if (is_up_flag == 0) {
			sparcap = (Sparcap) pl.getWire_gen().getProfilebene_gen().getBauteil().getBauteilE().getSE().getSCE()
					.getBauteil_down().get(0);
		}

		NumberQuantity w2 = sparcap.getWidth_2();
		NumberQuantity w1 = sparcap.getWidth_1();
		NumberQuantity h1 = sparcap.getHeight_1();
		NumberQuantity h2 = sparcap.getHeight_2();

		NumberQuantity width_spar = spar.getWidth();

		Profilebene profilebene = cpoint.getPL_Spar_ou().getWire_gen().getProfilebene_gen();

		NumberQuantity x_s = pl.getX_start();
		NumberQuantity x_start = x_s.add((w1.add(w2).divide(2)));

		NumberQuantity ypos = profilebene.getYpos();
		NumberQuantity zpos = profilebene.getZpos();

		NumberQuantity z_start = pl.getZ_start();
		z_start = z_start.subtract((h2.subtract(h1)));

		NumberQuantity z_end = pl.getZ_end();

		int anzpoints = pl.getAnzpoints();
		Wire_gen w = pl.getWire_gen();

		int Punktnummer = 0;

		int index_is_13 = cpoint.getIs_polyline_nr_X().lastIndexOf(13);
		int index_is_15 = cpoint.getIs_polyline_nr_X().lastIndexOf(15);

		if (index_is_15 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_15);
			Punktnummer = Punktnummer;
		} else if (index_is_13 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_13);
			Punktnummer = anzpoints - Punktnummer + 1;
		}

		NumberQuantity x = null;
		NumberQuantity y;
		NumberQuantity z = null;

		// X
		NumberQuantity x_1 = x_start;
		NumberQuantity x_2 = x_start.add(width_spar.divide(2));
		NumberQuantity x_3 = x_1.add(width_spar);

		// Y
		NumberQuantity y_0 = ypos;

		// Z
		NumberQuantity z_1 = z_start;
		NumberQuantity z_2 = z_start;
		NumberQuantity z_3 = z_start;

		y = y_0.multiply(-1);

		if (Punktnummer == 1) {
			x = x_1;
			z = z_1;
		} else if (Punktnummer == 2) {
			x = x_2;
			z = z_2;
		} else if (Punktnummer == 3) {
			x = x_3;
			z = z_3;
		}
		cpoint.setX(x);
		cpoint.setY(y);
		cpoint.setZ(z);

	}

}