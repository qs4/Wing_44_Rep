package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Str__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

			int is_Str_flag = cpoint.getIs_Str_fest_flag();
			if (is_Str_flag == 1) {

				StringerElement stringerE = cpoint.getPL_Str().getWire_gen().getProfilebene_gen().getBauteil()
						.getBauteilE().getSE().getStringerE();
				PolyLine_Str_fest pl = (PolyLine_Str_fest) cpoint.getPL_Str();
				int is_up_flag = pl.getIs_up_flag();
				NumberQuantity width = null;
				NumberQuantity height = null;
				if (is_up_flag == 1) {
					height = stringerE.getHeight();
					width = stringerE.getWidth();
				} else if (is_up_flag == 0) {
					height = stringerE.getHeight().multiply(-1);
					width = stringerE.getWidth();
				}

				Profilebene profilebene = cpoint.getPL_Str().getWire_gen().getProfilebene_gen();

				NumberQuantity x_s = pl.getX_start();

				NumberQuantity ypos = profilebene.getYpos();
				NumberQuantity zpos = profilebene.getZpos();

				NumberQuantity z_start = pl.getZ_start();
				NumberQuantity z_end = pl.getZ_end();

				int anzpoints = pl.getAnzpoints();
				Wire_gen w = pl.getWire_gen();

				int Punktnummer = 0;
				for (int i = 0; i < cpoint.getIs_polyline_nr_X().size(); i++) {
					int pl_nr = cpoint.getIs_polyline_nr_X().get(i);
					if (pl_nr == 7) {
						Punktnummer = cpoint.getIs_point_nr_X().get(i);
						if (w instanceof Wire_Typ2_SC_STR) {
							Punktnummer = anzpoints - Punktnummer + 1;
						} else if ((w instanceof Wire_TypX_Rib)^(w instanceof Wire_TypX_Rib_LE)) {
							Punktnummer = Punktnummer;
						}
					} else if (pl_nr == 8) {
						Punktnummer = cpoint.getIs_point_nr_X().get(i);

						if (w instanceof Wire_Typ2_SC_STR) {
							Punktnummer = Punktnummer;
						} else if ((w instanceof Wire_TypX_Rib)^(w instanceof Wire_TypX_Rib_LE)) {
							Punktnummer = anzpoints - Punktnummer + 1;
						
						}
					}
				}

				NumberQuantity x = null;
				NumberQuantity y;
				NumberQuantity z = null;

				// X
				NumberQuantity x_0 = x_s;

				NumberQuantity x_3 = x_0.add(width);

				// Y
				NumberQuantity y_0 = ypos;

				// Z
				NumberQuantity z_0 = z_start;
				NumberQuantity z_1 = z_start.subtract(height);
				NumberQuantity z_3 = z_end;

				y = y_0.multiply(-1);

				if (Punktnummer == 1) {
					x = x_0;
					z = z_0;
				} else if (Punktnummer == 2) {
					x = x_0;
					z = z_1;
				} else if (Punktnummer == 3) {
					x = x_3;
					z = z_1;

				} else if (Punktnummer == 4) {
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