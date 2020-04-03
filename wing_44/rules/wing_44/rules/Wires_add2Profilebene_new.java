package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Wires_add2Profilebene_new extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profilebene P : getGraph().allInstances(Profilebene.class)) {

			Wire_gen wire_gen = create_Wire_bauteil(P);
			connect_Wire_bauteil(P, wire_gen);

			Wire_gen wire_aus = create_Wire_aus(P);
			if (wire_aus != null) {
				connect_Wire_aus(P, wire_aus);
			}
		}
	}

	private Wire_gen create_Wire_bauteil(Profilebene P) {

		int b_art = P.getBauteil().getBauteilart();
		int segmentnummer = P.getBauteil().getBauteilE().getSE().getSegments().getSegmentnummer();

		Wire_gen wire = null;

		if (b_art == 1) {
			wire = Wire_Typ2_SC_STR.create();
		} else if (b_art == 2) {
			if (segmentnummer == 1) {
				wire = Wire_TypX_Rib.create();
			} else if (segmentnummer == 0) {
				wire = Wire_TypX_Rib_LE.create();
			}
		} else if (b_art == 3) {
			wire = Wire_Typ2_SC_STR.create();
		} else if (b_art == 4) {
			wire = Wire_Typ4_Spar.create();
		} else if (b_art == 5) {
			wire = Wire_Typ4_Skin.create();
		}

		return wire;
	}

	private void connect_Wire_bauteil(Profilebene P, Wire_gen wire) {
		P.setWire_gen(wire);
		wire.setProfilebene_gen(P);
	}

	private Wire_gen create_Wire_aus(Profilebene P) {

		int b_art = P.getBauteil().getBauteilart();
		int segmentnummer = P.getBauteil().getBauteilE().getSE().getSegments().getSegmentnummer();

		Wire_gen wire = null;

		if (b_art == 1) {
		} else if (b_art == 2) {
			if (segmentnummer == 1) {
				wire = Wire_Typ4_Aussparung.create();
			} else if (segmentnummer == 0) {
				wire = Wire_Typ3_Aussparung.create();
			}
		} else if (b_art == 3) {
		} else if (b_art == 4) {
		}

		return wire;
	}

	private void connect_Wire_aus(Profilebene P, Wire_gen wire_aus) {
		P.setWire_aus(wire_aus);
		wire_aus.setProfilebene_gen(P);

	}

}