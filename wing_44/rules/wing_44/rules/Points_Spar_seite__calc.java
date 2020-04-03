package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Spar_seite__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

			int is_spar_s_flag = cpoint.getIs_Spar_s_flag();

			if (is_spar_s_flag == 1) {
				setValuesSpar_seite(cpoint);
			}

		}
	}

	private void setValuesSpar_seite(Controlpoint_gen cpoint) {

		int index_is_14 = cpoint.getIs_polyline_nr_X().lastIndexOf(14);
		int index_is_16 = cpoint.getIs_polyline_nr_X().lastIndexOf(16);

		int Punktnummer = 0;
		Controlpoint_gen cpoint1 = null;
		Controlpoint_gen cpoint3 = null;
		PolyLine_Spar_Seite pl_s = (PolyLine_Spar_Seite) cpoint.getPL_Spar_s();

		if (index_is_14 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_14);

			if (Punktnummer == 2) {
				cpoint1 = pl_s.getCP_Spar_s().get(0);
				cpoint3 = pl_s.getCP_Spar_s().get(2);
			}

		} else if (index_is_16 >= 0) {
			Punktnummer = cpoint.getIs_point_nr_X().get(index_is_16);

			if (Punktnummer == 2) {
				cpoint1 = pl_s.getCP_Spar_s().get(2);
				cpoint3 = pl_s.getCP_Spar_s().get(0);
			}
		}

		if (Punktnummer == 2) {
			NumberQuantity x1 = cpoint1.getX();
			NumberQuantity y1 = cpoint1.getY();

			NumberQuantity z1 = cpoint1.getZ();
			NumberQuantity z3 = cpoint3.getZ();
			NumberQuantity z2 = z3.add((z1.subtract(z3)).divide(2));

			cpoint.setX(x1);
			cpoint.setY(y1);
			cpoint.setZ(z2);
		}

	}
}