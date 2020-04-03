package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Lofts__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Bauteil_gen B : getGraph().allInstances(Bauteil_gen.class)) {

			Loft_gen loft_gen = create_Loft_gen(B);
			if (loft_gen != null) {
				connect_Loft_bauteil(B, loft_gen);
			}
			Loft_gen loft_ellipse = create_Loft_ellipse(B);
			if (loft_ellipse != null) {
				connect_Loft_ellipse(B, loft_ellipse);
			}

		}
	}

	private Loft_gen create_Loft_gen(Bauteil_gen B) {

		int b_art = B.getBauteilart();

		Loft_gen loft = null;

		if (b_art == 1) {
			loft = Loft_Sparcap.create();
		} else if (b_art == 2) {
			loft = Loft_Rib.create();
		} else if (b_art == 3) {
			loft = Loft_Stringer.create();
		} else if (b_art == 4) {
			loft = Loft_Spar.create();
		} else if (b_art == 5) {
			loft = Loft_Skin.create();
		}
		return loft;

	}

	private void connect_Loft_bauteil(Bauteil_gen B, Loft_gen loft) {

		B.setLoft_gen(loft);
		loft.setBauteil_gen(B);

	}

	private Loft_gen create_Loft_ellipse(Bauteil_gen B) {
		int b_art = B.getBauteilart();

		Loft_gen loft = null;

		if (b_art == 1) {

		} else if (b_art == 2) {
			loft = Loft_Aussparung.create();
		}
		return loft;
	}

	private void connect_Loft_ellipse(Bauteil_gen B, Loft_gen loft) {

		B.setLoft_elli(loft);
		loft.setBauteil_gen(B);

	}
}
