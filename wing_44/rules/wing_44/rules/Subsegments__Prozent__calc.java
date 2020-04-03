package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Subsegments__Prozent__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Segment subseg : getGraph().allInstances(Segment.class)) {

			int segnummer = subseg.getSegmentnummer();

			Subsection subsec = subseg.getSubsection();
			double prozent_vorne = 0;
			double prozent_hinten = 0;

			if (segnummer == 0) {
				prozent_vorne = 0;
				prozent_hinten = subsec.getM_prozent().getValue();
			} else if (segnummer == 1) {
				prozent_vorne = subsec.getM_prozent().getValue();
				prozent_hinten = subsec.getN_prozent().getValue();
			} else if (segnummer == 2) {
				prozent_vorne = subsec.getN_prozent().getValue();
				prozent_hinten = 1;
			}
			
			double segment_width_prozent = Math.abs(prozent_vorne-prozent_hinten);

			subseg.setSegment_width_prozent(segment_width_prozent);
			
			subseg.setProzent_vorne(prozent_vorne);
			subseg.setProzent_hinten(prozent_hinten);
			
			
			
		}
	}
}
