package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;
import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class VisualizeSparcapsnew extends JavaRule {

	@Override
	public void execute() throws Exception {

		// hier am Ende für Subsection alle Bauteile auf einmal visualisieren:

//		for (Subsections subsection : getGraph().allInstances(Subsections.class)) {

		for (Sparcap sparcap : getGraph().allInstances(Sparcap.class)) {
			int is_up_flag = sparcap.getIs_up_flag();
			int bauteilnummer = sparcap.getBauteilnummer();
			if (!(is_up_flag == 1 & bauteilnummer==3)) {
				Loft_Sparcap loft_sparcap = (Loft_Sparcap) sparcap.getLoft_gen();
				sparcap.setShape(loft_sparcap);
			}
		}

	}

}