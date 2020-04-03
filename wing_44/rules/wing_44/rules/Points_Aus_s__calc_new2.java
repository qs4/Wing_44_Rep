package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Aus_s__calc_new2 extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

			int is_aus_s_flag = cpoint.getIs_Aus_s_flag();
			if (is_aus_s_flag == 1) {

				
				Profilebene profilebene = cpoint.getPL_Aus_s().getWire_gen().getProfilebene_gen();
				PolyLine_Aus_Seite pl = (PolyLine_Aus_Seite) cpoint.getPL_Aus_s();

				NumberQuantity x_s = pl.getX_start();

				NumberQuantity ypos = profilebene.getYpos();
				NumberQuantity zpos = profilebene.getZpos();

				NumberQuantity z_start = pl.getZ_start();
				NumberQuantity z_end = pl.getZ_end();
				
				int anzpoints = pl.getAnzpoints();
				
				int Punktnummer = 0;
				for (int i = 0; i < cpoint.getIs_polyline_nr_X().size(); i++) {
					int pl_nr = cpoint.getIs_polyline_nr_X().get(i);
					if (pl_nr == 19) {
						Punktnummer = cpoint.getIs_point_nr_X().get(i);
					} else if (pl_nr ==20) {
						Punktnummer = cpoint.getIs_point_nr_X().get(i);
						Punktnummer = anzpoints-Punktnummer+1;
					}
				}

				NumberQuantity x = null;
				NumberQuantity y;
				NumberQuantity z = null;
				
				PL_onPro_Aus_offset pl_up = (PL_onPro_Aus_offset) pl.getWire_gen().getPolyLine_gen1().get(0);
				PL_onPro_Aus_offset pl_down = (PL_onPro_Aus_offset) pl.getWire_gen().getPolyLine_gen1().get(2);

				NumberQuantity offset_up = pl_up.getOffset();
				NumberQuantity offset_down = pl_down.getOffset();

				// X
				NumberQuantity x_1 = x_s;
				NumberQuantity x_2 = null;
				NumberQuantity x_3 = x_s;

				// Y
				NumberQuantity y_0 = ypos;

				// Z
				NumberQuantity z_1 = z_start.add(offset_up);
				NumberQuantity z_3 = z_end.add(offset_down);
				NumberQuantity offset_mitte = z_1.subtract(z_3).divide(2);
				
				NumberQuantity offset = null;
				int is_right_flag = pl.getIs_right_flag();

				if (is_right_flag == 1) {
					x_2 = x_1.subtract(offset_mitte);
					offset = x_1.subtract(x_2).multiply(Math.sin(45.0));

				} else if (is_right_flag == 0) {
					x_2 = x_1.add(offset_mitte);
					offset = x_2.subtract(x_1).multiply(Math.sin(45.0));

				}

				NumberQuantity z_2 = z_3.add(offset_mitte);


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
	}

}