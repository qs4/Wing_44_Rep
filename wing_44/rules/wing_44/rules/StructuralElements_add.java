package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class StructuralElements_add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Segment seg : getGraph().allInstances(Segment.class)) {


			StructuralElement structuralelement = StructuralElement.create();
			structuralelement.setSegments(seg);
			seg.setStructuralElement(structuralelement);
			

		}
	}

}