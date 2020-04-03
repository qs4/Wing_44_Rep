package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Profil__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profilebene profilebene : getGraph().allInstances(Profilebene.class)) {

			Profil profil = Profil.create();
			profilebene.setProfil(profil);
			profil.setProfilebene(profilebene);
		}
	}
}
