package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class VisualizeStringers extends JavaRule {

	@Override
	public void execute() throws Exception {

		for (Stringer stringer : getGraph().allInstances(Stringer.class)) {
			Loft_Stringer loft_stringer = (Loft_Stringer) stringer.getLoft_gen();
			stringer.setShape(loft_stringer);
		}
	}

}