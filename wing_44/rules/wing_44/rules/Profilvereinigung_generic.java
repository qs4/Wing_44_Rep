package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Profilvereinigung_generic extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profilgesamt profile_gesamt : getGraph().allInstances(Profilgesamt.class)) {

			int dotcount = profile_gesamt.getProfile_generic().getDotcount().getValue().intValue();
		
			int oben = profile_gesamt.getProfile_generic().getProfilobenunten().get(0).getOben().getValue().intValue();
			
				int obenindex = 0;
				int untenindex = 1;
				if (oben == 1) {
					obenindex = 0;
					untenindex = 1;
				} else if (oben == 0) {
					obenindex = 1;
					untenindex = 0;
				}

				Profilobenunten Profiloben = profile_gesamt.getProfile_generic().getProfilobenunten().get(obenindex);
				Profilobenunten Profilunten = profile_gesamt.getProfile_generic().getProfilobenunten().get(untenindex);

				Point_Profil Point_o[] = new Point_Profil[dotcount];
				Point_Profil Point_u[] = new Point_Profil[dotcount];
				
				double xo[] = new double[dotcount];
				double yo[] = new double[dotcount];
				double xu[] = new double[dotcount];
				double yu[] = new double[dotcount];
			
				double x[] = new double[dotcount + dotcount - 2];
				double y[] = new double[dotcount + dotcount - 2];
				// flag oben unten beibehalten: 
				double flag_oben[] = new double[dotcount + dotcount - 2];

				for (int i = 0; i < dotcount; i++) {
					Point_o[i] = Profiloben.getPoint_Profil().get(i);
					xo[i] = Point_o[i].getX().getValue();
					yo[i] = Point_o[i].getY().getValue();

					Point_u[i] = Profilunten.getPoint_Profil().get(i);
					xu[i] = Point_u[i].getX().getValue();
					yu[i] = Point_u[i].getY().getValue();

				}
				
					for (int k = 0; k < dotcount; k++) {
						x[k] = xo[dotcount - 1 - k];
						y[k] = yo[dotcount - 1 - k];
						// flag oben unten Information beibehalten: 
						flag_oben[k] = 1;

						
					}
					for (int m = 0; m < (dotcount - 2); m++) {
						x[dotcount + m] = xu[m + 1];
						y[dotcount + m] = yu[m + 1];
						flag_oben[dotcount + m] = 0;
					}
									
					
					double npoint = (dotcount + dotcount - 2);

					int intnpoint = (int) npoint;
			
					Point_Profil point_ges[] = new Point_Profil[intnpoint];
					//
					for (int i = 0; i < npoint; i++) {

						point_ges[i] = Point_Profil.create();
						
						point_ges[i].setPunktnummer(i);
						point_ges[i].setIs_generic_flag(1);
						
						if (flag_oben[i]==1) {
							point_ges[i].setIs_oben_flag(1);
						}else if (flag_oben[i]==0){
							point_ges[i].setIs_oben_flag(0);
						}

//						point_ges[i].setX(x[i]);
//						point_ges[i].setY(y[i]);
						
						point_ges[i].setX(x[i]*1000);
						point_ges[i].setY(y[i]*1000);
						
						profile_gesamt.getElement().add(point_ges[i]);
						profile_gesamt.getPoint_Profil().add(point_ges[i]);

					}

					profile_gesamt.setStart(point_ges[0]);

					for (int i = 0; i < (npoint - 1); i++) {
						point_ges[i].getNextPoint().add(point_ges[i + 1]);

					}
					point_ges[intnpoint - 1].getNextPoint().add(point_ges[0]);

		}
	}

}