package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class calc_genericprofiles_oben extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profile_generic profile : getGraph().allInstances(Profile_generic.class)) {

			int oben = profile.getProfilobenunten().get(0).getOben().getValue().intValue();

			int obenindex = 0;
			
			if (oben == 1) {
				obenindex = 0;
			} else if (oben == 0) {
				obenindex = 1;
			}
			
			double M = profile.getNACA_M().getValue();
			double P = profile.getNACA_P().getValue();
			double XX = profile.getNACA_XX().getValue();

			int dotcount = profile.getDotcount().getValue().intValue();

			// NACA4 Profil
			double m = M / 100;
			double p = P / 10;
			double t = XX / 100;

			double panelcount = dotcount - 1;
			double panellength = 1 / panelcount;

			double npoint = (dotcount + dotcount - 2);
			int intnpoint = (int) npoint;

			// xu / yu -> Obere Profilseite
			Point_Profil Point[] = new Point_Profil[dotcount];
			double xu[] = new double[dotcount];
			double yu[] = new double[dotcount];

			// Profildicke
			double yt[] = new double[dotcount];

			// Skelettlinie
			double xc[] = new double[dotcount];
			double yc[] = new double[dotcount];

			// dy_c/dx_c
			double gc[] = new double[dotcount];

			// thetha
			double sc[] = new double[dotcount];

			int i = 1;
			xc[0] = 0;

			while (i < dotcount) {
				xc[i] = i * panellength;
				i = i + 1;
			}

			if (m != 0) {
				for (i = 0; i < dotcount; i++) {
					if (xc[i] <= p) {
						yc[i] = (m / (p * p)) * ((2 * p * xc[i]) - (xc[i] * xc[i]));
					}
					if (xc[i] > p) {
						yc[i] = (m / ((1 - p) * (1 - p))) * (1 - (2 * p) + (2 * p * xc[i]) - (xc[i] * xc[i]));
					}

					gc[i] = (m / (p * p)) * (2 * p + 2 * xc[i]);

				}
			}

			for (i = 0; i < dotcount; i++) {
				sc[i] = Math.atan(gc[i]);
			}

			for (i = 0; i < dotcount; i++) {
				yt[i] = 5 * t * ((0.29690 * Math.pow(xc[i], 0.5)) - (0.12600 * xc[i]) - (0.35160 * Math.pow(xc[i], 2))
						+ (0.28430 * Math.pow(xc[i], 3)) - (0.10150 * Math.pow(xc[i], 4)));
			}

			for (i = 0; i < dotcount; i++) {
				xu[i] = xc[i] - yt[i] * Math.sin(sc[i]);
				yu[i] = yc[i] + yt[i] * Math.cos(sc[i]);

			}
			xu[0] = 0;
			xu[dotcount - 1] = 1;
			
			Profilobenunten Profil = profile.getProfilobenunten().get(obenindex);

			for (i = 0; i < dotcount; i++) {
				Point[i] = Point_Profil.create();
				Point[i].setX(xu[i]);
				Point[i].setY(yu[i]);
				Point[i].setPunktnummer(i);
				Point[i].setIs_generic_flag(1);
				

				Profil.getElement().add(Point[i]);
				Profil.getPoint_Profil().add(Point[i]);

			}
			for (i = 0; i < (dotcount - 1); i++) {

				Point[i].getNextPoint().add(Point[i + 1]);

			}

		}

	}

}