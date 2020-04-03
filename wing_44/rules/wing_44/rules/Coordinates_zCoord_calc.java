package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.List;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_zCoord_calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Coordinates coord : getGraph().allInstances(Coordinates.class)) {

			Profil profil = coord.getProfilebene().getProfil();
			Profilebene profilebene = coord.getProfilebene();

			int anzpoint = profil.getPoint_Profil().size();

			Point_Profil pointprofil[] = new Point_Profil[anzpoint];

			for (int i = 0; i < anzpoint; i++) {
				pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
			}

			calc_z(coord, pointprofil);
		}
	}

	private void calc_z(Coordinates coord, Point_Profil[] pointprofil) {
		for (int i = 0; i < coord.getX().size(); i++) {

			int Nr_1 = coord.getNr1().get(i);
			int Nr_2 = coord.getNr2().get(i);

			double x = coord.getX().get(i).doubleValue() * 1000;
			double z = 0; // z = zpos;
			if (Nr_1 != 0) {
				z = interpolate(x, Nr_1, Nr_2, pointprofil) / 1000;
			}

			coord.getZ().add(z);
		}
	}

	private double interpolate(double x, int Nr_1, int Nr_2, Point_Profil[] pointprofil) {

		double x1 = pointprofil[Nr_1].getX().getValue();

		double z1 = pointprofil[Nr_1].getZ().getValue();

		double x2 = pointprofil[Nr_2].getX().getValue();
		double z2 = pointprofil[Nr_2].getZ().getValue();

//			double z = z1.add(((x.subtract(x1)).divide(x2.subtract(x1)).multiply(z2.subtract(z1)))).getValue();
		double z = z1 + (x - x1) * (z2 - z1) / (x2 - x1);

		return z;
	}

}