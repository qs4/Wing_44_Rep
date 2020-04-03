package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Profile;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Wires_add2Loft extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Loft_gen L : getGraph().allInstances(Loft_gen.class)) {
			System.out.println("Loft:  "+L);
			connect_Loft_n_Wire(L);
		}
	}

	private void connect_Loft_n_Wire(Loft_gen L) {
		Wire_gen wire_left = null;
		Wire_gen wire_right = null;

		if (!(L instanceof Loft_Aussparung)) {
			wire_left = (Wire_gen) L.getBauteil_gen().getProfil_left_g().getWire_gen();
			wire_right = (Wire_gen) L.getBauteil_gen().getProfil_right_g().getWire_gen();
		} else if (L instanceof Loft_Aussparung) {
			wire_left = (Wire_gen) L.getBauteil_gen().getProfil_left_g().getWire_aus();
			wire_right = (Wire_gen) L.getBauteil_gen().getProfil_right_g().getWire_aus();
		}

		L.getElement().add(wire_left);
		L.getElement().add(wire_right);

		L.setWire_gen_left(wire_left);
		L.setWire_gen_rigth(wire_right);

		wire_left.setIs_right_flag(0);
		wire_right.setIs_right_flag(1);

		wire_left.setLoft_gen(L);
		wire_right.setLoft_gen(L);

		wire_left.getNext().add(wire_right);

		L.setStart(wire_left);

	}
}