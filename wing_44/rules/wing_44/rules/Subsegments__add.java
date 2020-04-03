package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Subsegments__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Subsection subsection : getGraph().allInstances(Subsection.class)) {
			int anzseg = subsection.getAnzahlsegmente();

			Segment segment[] = new Segment[anzseg];

			// add Segments
			for (int i = 0; i < anzseg; i++) {

				segment[i] = Segment.create();
				segment[i].setSegmentnummer(i);
				segment[i].setSubsection(subsection);
				subsection.getSub().add(segment[i]);
				subsection.getSegments().add(segment[i]);
			}

		}
	}
}