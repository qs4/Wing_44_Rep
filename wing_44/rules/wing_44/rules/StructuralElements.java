package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Add;

import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class StructuralElements extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (StructuralElement structuralelement_inst : getGraph().allInstances(StructuralElement.class)) {
			
//			Add add = Add.create();
//			add.getLive().add();
//			structuralelement_inst.setShape(add);
			
		}
	}

}