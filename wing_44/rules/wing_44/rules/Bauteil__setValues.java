package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Bauteil__setValues extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Bauteil_gen bauteil : getGraph().allInstances(Bauteil_gen.class)) {
			setBauteilNummer(bauteil);
			setValues(bauteil);
		}
	}

	private void setValues(Bauteil_gen bauteil) {

		if (bauteil instanceof Sparcap) {

			Sparcap sparcap = ((Sparcap) bauteil);
			int is_up_flag = sparcap.getIs_up_flag();

			if (is_up_flag == 1) {
				sparcap.setHeight_1(0.1);
				sparcap.setHeight_2(0.15);
				sparcap.setWidth_1(0.04);
				sparcap.setWidth_2(0.2);
			} else if (is_up_flag == 0) {
				sparcap.setHeight_1(-0.1);
				sparcap.setHeight_2(-0.15);
				sparcap.setWidth_1(0.04);
				sparcap.setWidth_2(0.2);
			}

		} else if (bauteil instanceof Rib) {

			((Rib) bauteil).setThickness(0.02);

		} else if (bauteil instanceof Stringer) {

			Stringer stringer = ((Stringer) bauteil);

			int is_up_flag = stringer.getIs_up_flag();

			StringerElement stringerE = (StringerElement) stringer.getBauteilE();
			double width = stringerE.getWidth().getValue();
			double heigth = stringerE.getWidth().getValue();

			if (is_up_flag == 1) {
				stringer.setHeight(heigth);
				stringer.setWidth(width);
			} else if (is_up_flag == 0) {
				stringer.setHeight(-heigth);
				stringer.setWidth(width);
			}

		} else if (bauteil instanceof Spar) {
			Spar spar = ((Spar) bauteil);

			spar.setWidth(0.04);

		} else if (bauteil instanceof Skin) {
			Skin skin = ((Skin) bauteil);
			int is_up_flag = skin.getIs_up_flag();

			SkinElement skinE = (SkinElement) skin.getBauteilE();
			double thickness = skinE.getThickness().getValue();
			
			if (is_up_flag == 1) {
				skin.setThickness(thickness);
			} else if (is_up_flag == 0) {
				skin.setThickness(-thickness);
			}
		}
	}

	private void setBauteilNummer(Bauteil_gen bauteil) {
		if (bauteil instanceof Sparcap) {
			bauteil.setBauteilart(1);
		} else if (bauteil instanceof Rib) {
			bauteil.setBauteilart(2);
		} else if (bauteil instanceof Stringer) {
			bauteil.setBauteilart(3);
		} else if (bauteil instanceof Spar) {
			bauteil.setBauteilart(4);
		} else if (bauteil instanceof Skin) {
			bauteil.setBauteilart(5);
		}
	}

}