package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Subsections__coordinates_calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Subsection subsections : getGraph().allInstances(Subsection.class)) {

			// Hilfsvariablen getten:

			double li = subsections.getSec().getWing_44().getLi().getValue();
			double mac = subsections.getSec().getWing_44().getMAC().getValue();
			double lk = mac;
			double la = subsections.getSec().getWing_44().getLa().getValue();

			double ymac = subsections.getSec().getWing_44().getYMAC().getValue();
			double spannweite = subsections.getSec().getWing_44().getSpannweite().getValue();
			double pfeilung = subsections.getSec().getWing_44().getPfeilung().getValue();

			// Laenge und Verschiebungen berechnen:
			int sectionnummer = subsections.getSectionnummer();

			double laenge_i = li;
			double laenge_k = lk;
			double laenge_a = la;

			double verschiebung_x_i = 0;
			double verschiebung_x_k = Math.sin(Math.toRadians(pfeilung)) * ymac + (li / 4) - (lk / 4) - 5;// -3
			double verschiebung_x_a = Math.sin(Math.toRadians(pfeilung)) * (spannweite / 2) + (li / 4) - (la / 4) - 10;

			double verschiebung_y_i = 0;
			double verschiebung_y_k = ymac;
			double verschiebung_y_a = spannweite / 2;

			double verschiebung_z = 0;

			if (sectionnummer == 1) {

				// links
				subsections.setLaenge_left(laenge_i);
				subsections.setY_left(verschiebung_y_i);
				subsections.setX_left(verschiebung_x_i);

				// rechts
				subsections.setLaenge_right(laenge_k);
				subsections.setY_right(verschiebung_y_k);
				subsections.setX_right(verschiebung_x_k);

				subsections.setZ_rigth(verschiebung_z);

			} else if (sectionnummer == 2) {

				// links
				subsections.setLaenge_left(laenge_k);
				subsections.setY_left(verschiebung_y_k);
				subsections.setX_left(verschiebung_x_k);

				// rechts
				subsections.setLaenge_right(laenge_a);
				subsections.setY_right(verschiebung_y_a);
				subsections.setX_right(verschiebung_x_a);

				subsections.setZ_rigth(verschiebung_z);

			}

		}
	}

}