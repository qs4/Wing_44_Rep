package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Aus_s__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

			int is_aus_s_flag = cpoint.getIs_Aus_s_flag();

			if (is_aus_s_flag == 1) {
				setValues_Aus_seite(cpoint);
			}

		}
	}

	private void setValues_Aus_seite(Controlpoint_gen cpoint) {

		PolyLine_Aus_Seite pl = (PolyLine_Aus_Seite) cpoint.getPL_Aus_s();

		int is_right_flag = pl.getIs_right_flag();

		Profilebene profilebene = pl.getWire_gen().getProfilebene_gen();

		NumberQuantity x_start = pl.getX_start();

		NumberQuantity ypos = profilebene.getYpos();
		NumberQuantity zpos = profilebene.getZpos();

		NumberQuantity z_s = pl.getZ_start();
		NumberQuantity z_e = pl.getZ_end();

		NumberQuantity offset_8tel = z_s.subtract(z_e).divide(8);

		NumberQuantity z_start = z_s.subtract(offset_8tel);
		NumberQuantity z_end = z_e.add(offset_8tel);

		NumberQuantity offset_mitte = z_start.subtract(z_end).divide(2);

		NumberQuantity x_mitte = null;
		NumberQuantity offset = null;

		if (is_right_flag == 1) {
			x_mitte = x_start.subtract(offset_mitte);
			offset = x_start.subtract(x_mitte).multiply(Math.sin(45.0));

		} else if (is_right_flag == 0) {
			x_mitte = x_start.add(offset_mitte);
			offset = x_mitte.subtract(x_start).multiply(Math.sin(45.0));

		}

		NumberQuantity z_m = z_end.add(offset);

		int anzpoints = pl.getAnzpoints();
		Wire_gen w = pl.getWire_gen();

		int Punktnummer = 0;

		int index_is_19 = cpoint.getIs_polyline_nr_X().lastIndexOf(19);
		int index_is_20 = cpoint.getIs_polyline_nr_X().lastIndexOf(20);

		if (index_is_19 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_19);
			Punktnummer = Punktnummer;
		} else if (index_is_20 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_20);
			Punktnummer = anzpoints - Punktnummer + 1;
		}

		NumberQuantity x = null;
		NumberQuantity y;
		NumberQuantity z = null;

		// X
//		NumberQuantity x_1 = x_start;
//		NumberQuantity x_2 = x_1.add(offset);
//		NumberQuantity x_3 = x_mitte;
//		NumberQuantity x_4 = x_2;
//		NumberQuantity x_5 = x_start;

		NumberQuantity x_1 = x_start;
		NumberQuantity x_2 = x_mitte;
		NumberQuantity x_3 = x_start;

		// Y
		NumberQuantity y_0 = ypos;

		// Z
//		NumberQuantity z_1 = z_start;
//		NumberQuantity z_2 = z_1.subtract(offset);
//		NumberQuantity z_3 = z_m;
//		NumberQuantity z_4 = z_end.add(offset);
//		NumberQuantity z_5 = z_end;
//		
		NumberQuantity z_1 = z_start;
		NumberQuantity z_2 = z_m;
		NumberQuantity z_3 = z_end;

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
//		} else if (Punktnummer == 4) {
//			x = x_4;
//			z = z_4;
//		} else if (Punktnummer == 5) {
//			x = x_5;
//			z = z_5;
		}

		cpoint.setX(x);
		cpoint.setY(y);
		cpoint.setZ(z);

	}
}