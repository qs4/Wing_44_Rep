package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class StructuralElements_calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (StructuralElement SE : getGraph().allInstances(StructuralElement.class)) {
			// set anzribs
			int anzribtyp1 = SE.getAnzribtyp1();
			int anzribtyp2 = SE.getAnzribtyp2();

			if (anzribtyp1 > 0 | anzribtyp2 > 0) {
				SE.setIs_ribs(1);
			}

			// set anzspars
			int anzspars = SE.getAnzspars();
			if (anzspars > 0) {
				SE.setIs_spars(1);
				SE.setIs_sparcaps(1);
			}

			// set anzstringers
			int anzstringertyp1 = SE.getAnzstringertyp1();
			int anzstringertyp2 = SE.getAnzstringertyp2();

			if (anzstringertyp1 > 0 | anzstringertyp2 > 0) {
				SE.setIs_stringers(1);
			}

			// set anzstringers
			int anzskintyp1 = SE.getAnzskintyp1();
			int anzskintyp2 = SE.getAnzskintyp2();

			if (anzskintyp1 > 0 | anzskintyp2 > 0) {
				SE.setIs_skin(1);
			}

		}
	}

}