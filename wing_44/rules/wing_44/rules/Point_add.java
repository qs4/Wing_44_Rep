package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Point_add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Coordinates coord : getGraph().allInstances(Coordinates.class)) {

			for (int i = 0; i < coord.getX().size(); i++) {
				Punkt_zNr punkt = Punkt_zNr.create();
				coord.getPunkt_zNr().add(punkt);
				punkt.setZKoordinaten_neu(coord);

				punkt.setIs_up_flag(coord.getIs_up_flag());
				punkt.setX(coord.getX().get(i));
				punkt.setNr1(coord.getNr1().get(i));
				punkt.setNr2(coord.getNr2().get(i));
				punkt.setZ(coord.getZ().get(i));

			}

		}
	}

}