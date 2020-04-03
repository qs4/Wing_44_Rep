package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Subsegments_Profilpoints_calc extends JavaRule {

	@Override
	public void execute() throws Exception {

		for (Profilpoints profilpoint : getGraph().allInstances(Profilpoints.class)) {

			profilpoint.getProzentnummer();

			Segment segment2 = profilpoint.getSegments();

			double laenge = 0;
			double x = 0;
			double y = 0;
			double z = 0; 
			if (profilpoint.getIs_links() == 1) {
				// links
				laenge = segment2.getSubsection().getLaenge_left().getValue();
				x = segment2.getSubsection().getX_left().getValue();
				y = segment2.getSubsection().getY_left().getValue();
				z = segment2.getSubsection().getZ_left().getValue();

			} else if (profilpoint.getIs_links() == 0) {
				// rechts
				laenge = segment2.getSubsection().getLaenge_right().getValue();
				x = segment2.getSubsection().getX_right().getValue();
				y = segment2.getSubsection().getY_right().getValue();
				z = segment2.getSubsection().getZ_rigth().getValue();

			}

			double prozent_a_absolut = segment2.getA_absolut_prozent().getValue();
			double prozent_b_absolut = segment2.getB_absolut_prozent().getValue();
			double prozent_c_absolut = segment2.getC_absolut_prozent().getValue();
			double prozent_d_absolut = segment2.getD_absolut_prozent().getValue();
			double prozent_e_absolut = segment2.getE_absolut_prozent().getValue();
			double prozent_f_absolut = segment2.getF_absolut_prozent().getValue();
			double prozent_g_absolut = segment2.getG_absolut_prozent().getValue();

			double x_a = prozent_a_absolut * laenge+x;
			double x_b = prozent_b_absolut * laenge+x; 
			double x_c = prozent_c_absolut * laenge+x;
			double x_d = prozent_d_absolut * laenge+x;
			double x_e = prozent_e_absolut * laenge+x; 
			double x_f = prozent_f_absolut * laenge+x;
			double x_g = prozent_g_absolut * laenge+x;
			y = y; 
			
			profilpoint.setX_a(x_a);
			profilpoint.setX_b(x_b);
			profilpoint.setX_c(x_c);
			profilpoint.setX_d(x_d);
			profilpoint.setX_e(x_e);
			profilpoint.setX_f(x_f);
			profilpoint.setX_g(x_g);
			
			profilpoint.setY(y);
			profilpoint.setZ(z);
			
		}
	}

}