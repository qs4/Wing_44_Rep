package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_str__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profilebene profilebene : getGraph().allInstances(Profilebene.class)) {
			int subsegmentnr = profilebene.getBauteil().getSubsegmentnummer();

			if (subsegmentnr == 1) {
				int anzstringer = profilebene.getBauteil().getBauteilE().getSE().getAnzstringertyp1();
				if (anzstringer > 0) {

					create_Coordinates_Stringer(profilebene);

					
				}
			} else if (subsegmentnr == 2 ) {
				int anzstringer = profilebene.getBauteil().getBauteilE().getSE().getAnzstringertyp2();
				if (anzstringer > 0) {
					create_Coordinates_Stringer(profilebene);
				}
			}
		}
	}

	private void create_Coordinates_Stringer(Profilebene profilebene) {
		Coordinates_Stringer coord_up = Coordinates_Stringer.create();
		profilebene.setCoordinates_str_up(coord_up);
		coord_up.setProfilebene(profilebene);
		coord_up.setIs_up_flag(1);

		Coordinates_Stringer coord_down = Coordinates_Stringer.create();
		profilebene.setCoordinates_str_down(coord_down);
		coord_down.setProfilebene(profilebene);
		coord_down.setIs_up_flag(0);


	}

}