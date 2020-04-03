package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Aus_s__calc_new9 extends JavaRule {

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

				NumberQuantity z_s = pl.getZ_start();
				NumberQuantity z_e = pl.getZ_end();
				
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
				NumberQuantity x_start = x_s;
				NumberQuantity x_m = null;
				NumberQuantity x_end = x_s;

				// Y
				NumberQuantity y_0 = ypos;

				// Z
				NumberQuantity z_start = z_s.add(offset_up);
				NumberQuantity z_end = z_e.add(offset_down);
				NumberQuantity radius = z_start.subtract(z_end).divide(2);
				
				int is_right_flag = pl.getIs_right_flag();
				
				NumberQuantity offset91 = null;
				NumberQuantity offset92 = null;
				NumberQuantity offset93 = null;

				if (is_right_flag == 1) {
					x_m = x_start.subtract(radius);
					offset91 = radius.multiply(Math.sin(Math.toRadians(22.5))).multiply(-1);
					offset92 = radius.multiply(Math.sin(Math.toRadians(45))).multiply(-1);
					offset93 = radius.multiply(Math.sin(Math.toRadians(67.5))).multiply(-1);

				} else if (is_right_flag == 0) {
					x_m = x_start.add(radius);
					offset91 = radius.multiply(Math.sin(Math.toRadians(22.5)));
					offset92 = radius.multiply(Math.sin(Math.toRadians(45)));
					offset93 = radius.multiply(Math.sin(Math.toRadians(67.5)));

				}
				
				NumberQuantity offset_positiv91 = radius.multiply(1-Math.cos(Math.toRadians(22.5)));
				NumberQuantity offset_positiv92 = radius.multiply(1-Math.cos(Math.toRadians(45)));
				NumberQuantity offset_positiv93 = radius.multiply(1-Math.cos(Math.toRadians(67.5)));

//				System.out.println(Math.sin(Math.toRadians(45)));

				NumberQuantity z_m = z_end.add(radius);


				y = y_0.multiply(-1);

				if (Punktnummer == 1) {
					x = x_start;
					z = z_start;
				} else if (Punktnummer == 2) {
					x = x_start.add(offset91);
					z = z_start.subtract(offset_positiv91);
				} else if (Punktnummer == 3) {
					x = x_start.add(offset92);
					z = z_start.subtract(offset_positiv92);
				} else if (Punktnummer == 4) {
					x = x_start.add(offset93);
					z = z_start.subtract(offset_positiv93);
				} else if (Punktnummer == 5) {
					x = x_m;
					z = z_m;
				} else if (Punktnummer == 6) {
					x = x_end.add(offset93);
					z = z_end.add(offset_positiv93);
				} else if (Punktnummer == 7) {
					x = x_end.add(offset92);
					z = z_end.add(offset_positiv92);
				} else if (Punktnummer == 8) {
					x = x_end.add(offset91);
					z = z_end.add(offset_positiv91);
				} else if (Punktnummer == 9) {
					x = x_end;
					z = z_end;

				}
				cpoint.setX(x);
				cpoint.setY(y);
				cpoint.setZ(z);

			}
		}
	}

}