package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class VisualizeSpars extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Spar spar : getGraph().allInstances(Spar.class)) {
			Loft_Spar loft_spar = (Loft_Spar) spar.getLoft_gen();
			spar.setShape(loft_spar);
		}
	}

}