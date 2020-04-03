package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Cut;
import de.iils.dc43.core.geometry.publication.G;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class VisualizeRibs extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Rib rib : getGraph().allInstances(Rib.class)) {
			Loft_Rib loft_rib = (Loft_Rib) rib.getLoft_gen();
//			rib.setShape(loft_rib);

			Loft_Aussparung loft_aus = (Loft_Aussparung) rib.getLoft_elli();
//			rib.setShape(loft_ellipse);

			Cut cut = Cut.create();
			cut.setLive(loft_rib);
			cut.setDie(loft_aus);
			rib.setShape(cut);
		}
	}

}