package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Subsegments_Profilpoints_add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Segment subseg : getGraph().allInstances(Segment.class)) {

			int anzprofil = 2;

			Profilpoints profilpoints[] = new Profilpoints[anzprofil];

			// add Segments
			for (int i = 0; i < anzprofil; i++) {

				profilpoints[i] = Profilpoints.create();
				profilpoints[i].setProzentnummer(i);
				profilpoints[i].setSegments(subseg);
				
				if (i==0) {
					profilpoints[i].setIs_links(1);
					subseg.setPp_left(profilpoints[i]);

				}else if (i==1){
					profilpoints[i].setIs_links(0);
					subseg.setPp_right(profilpoints[i]);

				}
			}
		}
	}
}