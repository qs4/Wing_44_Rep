package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_Rb_Seiten_calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {

//			int is_polylinenummer_1 = cpoint.getIs_polyline_nr_1();
//			int is_polylinenummer_3 = cpoint.getIs_polyline_nr_3();

			int is_Rb_S = cpoint.getIs_Rb_S_flag();
			if (is_Rb_S == 1) {

				for (int i = 0; i < cpoint.getIs_polyline_nr_X().size(); i++) {
					int pl_nr = cpoint.getIs_polyline_nr_X().get(i);

					if (pl_nr == 11) {

						// provisorisch --> Bauteilparameter in Sparcaps!:
						Bauteil_gen bauteil = cpoint.getPL_Rb_S().getWire_gen().getProfilebene_gen().getBauteil();
						int segmentnummer = bauteil.getBauteilE().getSE().getSegments().getSegmentnummer();
						
						Sparcap sparcap_up = null;
						if (segmentnummer == 1) {
							sparcap_up = (Sparcap) bauteil.getBauteilE().getSE().getSCE().getBauteil_typ1()
									.get(0);
						} else if (segmentnummer == 0) {
							sparcap_up = (Sparcap) bauteil.getBauteilE().getSE().getSegments().getSubsection()
									.getSegments().get(1).getStructuralElement().getSCE().getBauteil_typ1().get(0);
						}

						Profilebene profilebene = cpoint.getPL_Rb_S().getWire_gen().getProfilebene_gen();

						NumberQuantity height_1 = sparcap_up.getHeight_1();
						NumberQuantity height_2 = sparcap_up.getHeight_2();
						NumberQuantity width_1 = sparcap_up.getWidth_1();
						NumberQuantity width_2 = sparcap_up.getWidth_2();

						PolyLine_Rib_Seite pl = (PolyLine_Rib_Seite) cpoint.getPL_Rb_S();

						NumberQuantity xpos = profilebene.getXpos();
						NumberQuantity ypos = profilebene.getYpos();
						NumberQuantity zpos = profilebene.getZpos();

						NumberQuantity x_start = pl.getX_end();
						NumberQuantity z_end = pl.getZ_end();
						NumberQuantity z_start = pl.getZ_start();

						NumberQuantity x_1 = x_start;
						NumberQuantity x_3 = x_1.add((width_2).subtract(width_1).divide(2));

						// NumberQuantity x_3 =
						// x_up_e.add((width_2).subtract(width_1).divide(2)).substract(witdh_spar); !!!

						NumberQuantity z_1 = z_start;
						NumberQuantity z_2 = z_1.subtract(height_2).add(height_1);
						NumberQuantity z_6 = z_end;
						NumberQuantity z_4 = z_6.add(height_2).subtract(height_1);

						int Punktnummer = 0;
						for (i = 0; i < cpoint.getIs_polyline_nr_X().size(); i++) {
							pl_nr = cpoint.getIs_polyline_nr_X().get(i);
							if (pl_nr == 11) {
								Punktnummer = cpoint.getIs_point_nr_X().get(i);
							}
						}

						NumberQuantity x = null;
						NumberQuantity y = null;
						NumberQuantity z = null;

						if (Punktnummer == 1) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_1;

						} else if (Punktnummer == 2) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_2;
						} else if (Punktnummer == 3) {
							x = x_3;
							y = ypos.multiply(-1);
							z = z_2;
						} else if (Punktnummer == 4) {
							x = x_3;
							y = ypos.multiply(-1);
							z = z_4;
						} else if (Punktnummer == 5) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_4;
						} else if (Punktnummer == 6) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_6;
						}

						cpoint.setX(x);
						cpoint.setY(y);
						cpoint.setZ(z);

					} else if (pl_nr == 12) {
						// provisorisch --> Bauteilparameter in Sparcaps!:

						Bauteil_gen bauteil = cpoint.getPL_Rb_S().getWire_gen().getProfilebene_gen().getBauteil();

						Sparcap sparcap_up = (Sparcap) bauteil.getBauteilE().getSE().getSCE().getBauteil_typ1().get(0);
						Spar spar = (Spar) bauteil.getBauteilE().getSE().getSparE().getBauteil_typ1().get(0);

						PolyLine_Rib_Seite pl = (PolyLine_Rib_Seite) cpoint.getPL_Rb_S();
						Profilebene profilebene = cpoint.getPL_Rb_S().getWire_gen().getProfilebene_gen();

						NumberQuantity height_1 = sparcap_up.getHeight_1();
						NumberQuantity height_2 = sparcap_up.getHeight_2();
						NumberQuantity width_1 = sparcap_up.getWidth_1();
						NumberQuantity width_2 = sparcap_up.getWidth_2();

						NumberQuantity width_spar = spar.getWidth();

						NumberQuantity xpos = profilebene.getXpos();
						NumberQuantity ypos = profilebene.getYpos();
						NumberQuantity zpos = profilebene.getZpos();

						NumberQuantity x_start = pl.getX_end();
						NumberQuantity z_end = pl.getZ_end();
						NumberQuantity z_start = pl.getZ_start();

						NumberQuantity x_1 = x_start;
						NumberQuantity x_3 = x_1.subtract(((width_2).subtract(width_1)).divide(2)).add(width_spar);

						NumberQuantity z_1 = z_start;
						NumberQuantity z_2 = z_1.subtract(height_2).add(height_1);
						NumberQuantity z_6 = z_end;
						NumberQuantity z_4 = z_6.add(height_2).subtract(height_1);

						int anzpoints = pl.getAnzpoints();

						int Punktnummer = 0;
						for (i = 0; i < cpoint.getIs_polyline_nr_X().size(); i++) {
							pl_nr = cpoint.getIs_polyline_nr_X().get(i);
							if (pl_nr == 12) {
								Punktnummer = cpoint.getIs_point_nr_X().get(i);
								Punktnummer = anzpoints - Punktnummer + 1;

							}
						}
						NumberQuantity x = null;
						NumberQuantity y = null;
						NumberQuantity z = null;

						if (Punktnummer == 1) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_1;

						} else if (Punktnummer == 2) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_2;
						} else if (Punktnummer == 3) {
							x = x_3;
							y = ypos.multiply(-1);
							z = z_2;
						} else if (Punktnummer == 4) {
							x = x_3;
							y = ypos.multiply(-1);
							z = z_4;
						} else if (Punktnummer == 5) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_4;
						} else if (Punktnummer == 6) {
							x = x_1;
							y = ypos.multiply(-1);
							z = z_6;
						}

						cpoint.setX(x);
						cpoint.setY(y);
						cpoint.setZ(z);
					}
				}
			}
		}
	}

}