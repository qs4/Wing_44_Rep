package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Profilekoordinaten_gesamt extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profiles_44 profile_ges_koord : getGraph().allInstances(Profiles_44.class)) {

			int links = profile_ges_koord.getLinks().intValue();

			
			double laenge_profil = 10; // Länge_Profil -> l_rechts / l_links -> li / lk / la
			double verschiebung_x = 0; // Verschiebung_x -> a / k -> berechnen aus pfeilung spannweite etc
			double verschiebung_y = 0; // Verschiebung_y -> // y_mac -> berechnen aus spannweite etc
			double verschiebung_z = 0; // Verschiebung_z -> hoehe
			
			//links
			if (links == 1) {
				laenge_profil = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getLaenge_left().getValue();
				verschiebung_x = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getX_left().getValue(); 
				verschiebung_y = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getY_left().getValue();  
				verschiebung_z = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getZ_rigth().getValue(); 
		   //rechts
			} else  {
				laenge_profil = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getLaenge_right().getValue();
				verschiebung_x = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getX_right().getValue(); 
				verschiebung_y = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getY_right().getValue();  
				verschiebung_z = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getZ_rigth().getValue(); 
			}
			
			
			int dotcount = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getSec().getWing_44().getProfile_generic().getDotcount().getValue().intValue();
			
			int npoint = (dotcount + dotcount - 2);
			int intnpoint = (int) npoint;
			

			// get generic profiles:
			int sectionnummer = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getSectionnummer();


			// get gesamtes generic Profil + generic Profilpunkte
			Profilgesamt profile_ges_generic = profile_ges_koord.getLoft_Profil().getSubsectionVisualize().getSubsections().getSec().getWing_44()
					.getProfile_generic().getProfilgesamt();
			

			
			Point_Profil point_ges_generic[] = new Point_Profil[npoint];
			double x_generic[] = new double[npoint];
			double y_generic[] = new double[npoint];
			int is_oben_flag[] = new int[npoint];



			for (int i = 0; i < npoint; i++) {
				point_ges_generic[i] = (Point_Profil) profile_ges_generic.getPoint_Profil().get(i);
				x_generic[i] = point_ges_generic[i].getX().getValue();
				y_generic[i] = point_ges_generic[i].getY().getValue();
				is_oben_flag[i] = point_ges_generic[i].getIs_oben_flag().intValue();
			}


			// Generic in Koordinaten Umwandeln -> Skalieren(laenge) + Verschieben(verschiebung)
			double x_koord[] = new double[intnpoint];
			double y_koord[] = new double[intnpoint];
			double z_koord[] = new double[intnpoint];

			
//			for (int i = 0; i < intnpoint; i++) {
//				x_koord[i] = (x_generic[i] * laenge_profil) +verschiebung_x;
//				y_koord[i] = -verschiebung_y;
//				z_koord[i] = (y_generic[i] * laenge_profil) + verschiebung_z;
//			}
			
			for (int i = 0; i < intnpoint; i++) {
				x_koord[i] = ((x_generic[i] * laenge_profil) +verschiebung_x*1000);
				y_koord[i] = -verschiebung_y*1000;
				z_koord[i] = ((y_generic[i] * laenge_profil) + verschiebung_z*1000);
			}

			Point_Profil point_koord[] = new Point_Profil[intnpoint];
			
			for (int i = 0; i < npoint; i++) {

				point_koord[i] = Point_Profil.create();

				point_koord[i].setPunktnummer(i);
				point_koord[i].setIs_profil44_flag(1);
				point_koord[i].setIs_oben_flag(is_oben_flag[i]);
			
//					double value = 1.23456789;
//				x_koord[i] = Math.round(1000.0 * x_koord[i]) / 1000.0; 
//				y_koord[i] = Math.round(1000.0 * y_koord[i]) / 1000.0; 
//				z_koord[i] = Math.round(1000.0 * z_koord[i]) / 1000.0; 

				
				point_koord[i].setX(x_koord[i]);
				point_koord[i].setY(y_koord[i]);
				point_koord[i].setZ(z_koord[i]);

				profile_ges_koord.getElement().add(point_koord[i]);
				profile_ges_koord.getPoint_Profil().add(point_koord[i]);

			}

			profile_ges_koord.setStart(point_koord[0]);

			for (int i = 0; i < (npoint - 1); i++) {
				point_koord[i].getNextPoint().add(point_koord[i + 1]);

			}
			point_koord[intnpoint - 1].getNextPoint().add(point_koord[0]);

		}
	}

}
