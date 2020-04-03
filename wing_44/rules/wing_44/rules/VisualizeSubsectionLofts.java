package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Add;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class VisualizeSubsectionLofts extends JavaRule {

	@Override
	public void execute() throws Exception {
	
		
		for (SubsectionVisualize subsectionvisu : getGraph().allInstances(SubsectionVisualize.class)) {
			Loft_Profil loft_profil = subsectionvisu.getLoft_Profil();
			subsectionvisu.setShape(loft_profil);
		}

	}

}