package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Sc__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

			int is_Sc_fest_flag = cpoint.getIs_Sc_fest_flag();
			if (is_Sc_fest_flag == 1) {
				Sparcap sparcap = (Sparcap) cpoint.getPL_Sc().getWire_gen().getLoft_gen().getBauteil_gen();

				NumberQuantity height_1 = sparcap.getHeight_1();
				NumberQuantity height_2 = sparcap.getHeight_2();

				NumberQuantity width_1 = sparcap.getWidth_1();
				NumberQuantity width_2 = sparcap.getWidth_2();

				Profilebene profilebene = cpoint.getPL_Sc().getWire_gen().getProfilebene_gen();
				PolyLine_Sparcap_fest pl = (PolyLine_Sparcap_fest) cpoint.getPL_Sc();

				NumberQuantity x_s = pl.getX_start();

				NumberQuantity ypos = profilebene.getYpos();
				NumberQuantity zpos = profilebene.getZpos();

				NumberQuantity z_start = pl.getZ_start();
				NumberQuantity z_end = pl.getZ_end();
				
				int anzpoints = pl.getAnzpoints();
				
				int Punktnummer = 0;
				for (int i = 0; i < cpoint.getIs_polyline_nr_X().size(); i++) {
					int pl_nr = cpoint.getIs_polyline_nr_X().get(i);
					if (pl_nr == 9) {
						Punktnummer = cpoint.getIs_point_nr_X().get(i);
						Punktnummer = anzpoints-Punktnummer+1;
					} else if (pl_nr ==10) {
						Punktnummer = cpoint.getIs_point_nr_X().get(i);
					}
				}

				NumberQuantity x = null;
				NumberQuantity y;
				NumberQuantity z = null;

				// X
				NumberQuantity x_0 = x_s;

				NumberQuantity x_1 = x_0.add(width_2.subtract(width_1).divide(2));
				NumberQuantity x_2 = x_0.add(width_2.subtract(width_1).divide(2)).add(width_1);
				NumberQuantity x_3 = x_0.add(width_2);

				// Y
				NumberQuantity y_0 = ypos;

				// Z
				NumberQuantity z_0 = z_start;
				NumberQuantity z_1 = z_start.subtract(height_2).add(height_1);
				NumberQuantity z_2 = z_start.subtract(height_2);

				y = y_0.multiply(-1);

				if (Punktnummer == 1) {
					x = x_0;
					z = z_0;
				} else if (Punktnummer == 2) {
					x = x_0;
					z = z_1;
				} else if (Punktnummer == 3) {
					x = x_1;
					z = z_1;

				} else if (Punktnummer == 4) {
					x = x_1;
					z = z_2;

				} else if (Punktnummer == 5) {
					x = x_2;
					z = z_2;
				} else if (Punktnummer == 6) {
					x = x_2;
					z = z_1;
				} else if (Punktnummer == 7) {
					x = x_3;
					z = z_1;
				} else if (Punktnummer == 8) {
					x = x_3;
					z = z_end;

				}
				cpoint.setX(x);
				cpoint.setY(y);
				cpoint.setZ(z);

			}
		}
	}

}