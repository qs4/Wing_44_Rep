package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Polylines_inside_setOffset extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (PolyLine_gen pl : getGraph().allInstances(PolyLine_gen.class)) {
			setoffset(pl);

		}
	}

	private void setoffset(PolyLine_gen pl) {
		if (pl instanceof PL_onPro_Aus_offset) {
			double offset = 0;
			int is_up_flag = pl.getIs_up_flag();
			if (is_up_flag == 1) {
				offset = -120;
			} else if (is_up_flag == 0) {
				offset = 120;
			}

			((PL_onPro_Aus_offset) pl).setOffset(offset);
		} else if (pl instanceof PL_onPro_Skin_offset) {
			Skin skin = (Skin) pl.getWire_gen().getProfilebene_gen().getBauteil();
			int is_up_flag_skin = skin.getIs_up_flag();
			double offset = 0;

			int is_up_flag = pl.getIs_up_flag();
			if (is_up_flag_skin == 1) {
				if (is_up_flag == 1) {
					offset = 20;
				} else if (is_up_flag == 0) {
					offset = 0;
				}
			}else if (is_up_flag_skin==0) {
				if (is_up_flag == 1) {
					offset = 0;
				} else if (is_up_flag == 0) {
					offset = -20;
				}
			}

			((PL_onPro_Skin_offset) pl).setOffset(offset);
		}

	}
}