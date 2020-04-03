package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_basis__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profilebene profilebene : getGraph().allInstances(Profilebene.class)) {
			create_Coordinates_Basis(profilebene);
		}
	}

	private void create_Coordinates_Basis(Profilebene profilebene) {
		Coordinates_Basis coord_up = Coordinates_Basis.create();
		profilebene.setCoordinates_basis_up(coord_up);
		coord_up.setProfilebene(profilebene);
		coord_up.setIs_up_flag(1);

		Coordinates_Basis coord_down = Coordinates_Basis.create();
		profilebene.setCoordinates_basis_down(coord_down);
		coord_down.setProfilebene(profilebene);
		coord_down.setIs_up_flag(0);

	}
}
