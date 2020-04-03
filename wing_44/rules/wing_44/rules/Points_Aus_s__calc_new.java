package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Aus_s__calc_new extends JavaRule {

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

		int index_is_19 = cpoint.getIs_polyline_nr_X().lastIndexOf(19);
		int index_is_20 = cpoint.getIs_polyline_nr_X().lastIndexOf(20);

		int Punktnummer = 0;
		Controlpoint_gen cpoint1 = null;
		Controlpoint_gen cpoint3 = null;
		PolyLine_Aus_Seite pl_s = (PolyLine_Aus_Seite) cpoint.getPL_Aus_s();

		int anzpoints = pl_s.getAnzpoints();
		
		if (index_is_19 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_19);

			if (Punktnummer == 2) {
				cpoint1 = pl_s.getCP_Aus_s().get(0);
				cpoint3 = pl_s.getCP_Aus_s().get(anzpoints-1);
			}

		} else if (index_is_20 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_20);

			if (Punktnummer == 2) {
				cpoint1 = pl_s.getCP_Aus_s().get(anzpoints-1);
				cpoint3 = pl_s.getCP_Aus_s().get(0);
			}
		}

		if (Punktnummer == 2) {
			NumberQuantity x1 = cpoint1.getX();
			NumberQuantity x3 = cpoint3.getX();

			NumberQuantity y1 = cpoint1.getY();

			NumberQuantity z1 = cpoint1.getZ();
			NumberQuantity z3 = cpoint3.getZ();
			NumberQuantity z2 = z3.add((z1.subtract(z3)).divide(2));

			System.out.println("cpoint1:  " + cpoint1);
			System.out.println("cpoint3:  " + cpoint3);
			System.out.println("x1:  " + x1);
			System.out.println("z2:  " + z2);

			cpoint.setX(x1);
			cpoint.setY(y1);
			cpoint.setZ(z2);
		}

	}
}