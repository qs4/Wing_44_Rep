package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Wires_setValues_new extends JavaRule {

	@Override
	public void execute() throws Exception {

		// alle gleich --> generisch machen!
		for (Wire_Typ2_SC_STR wire_typ2 : getGraph().allInstances(Wire_Typ2_SC_STR.class)) {
			wire_typ2.setAnzpolyline(2);

			set_Polylinelist_Typ2(wire_typ2);
		}

		for (Wire_TypX_Rib wire_typX : getGraph().allInstances(Wire_TypX_Rib.class)) {

			set_Polylinelist_TypX(wire_typX);
		}

		for (Wire_TypX_Rib_LE wire_typX_LE : getGraph().allInstances(Wire_TypX_Rib_LE.class)) {

			set_Polylinelist_TypX_Rib_LE(wire_typX_LE);
		}

		for (Wire_Typ4_Spar wire_typ4_spar : getGraph().allInstances(Wire_Typ4_Spar.class)) {
			set_Polylinelist_Typ4_Spar(wire_typ4_spar);
		}

		for (Wire_Typ4_Aussparung wire_typ4_aus : getGraph().allInstances(Wire_Typ4_Aussparung.class)) {
			set_Polylinelist_Typ4_Aus(wire_typ4_aus);
		}

		for (Wire_Typ4_Skin wire_typ4_skin : getGraph().allInstances(Wire_Typ4_Skin.class)) {
			set_Polylinelist_Typ4_Skin(wire_typ4_skin);
		}
		
		for (Wire_Typ3_Aussparung wire_typ3_aus : getGraph().allInstances(Wire_Typ3_Aussparung.class)) {
			set_Polylinelist_Typ3_Aus(wire_typ3_aus);
		}

		for (Wire_gen wire_gen : getGraph().allInstances(Wire_gen.class)) {
			setAnzpolyLine(wire_gen);
		}
	}

	
	private void set_Polylinelist_Typ4_Skin(Wire_Typ4_Skin wire_typ4_skin) {
		wire_typ4_skin.getPolylinetypliste().add(21);
		wire_typ4_skin.getPolylinetypliste().add(23);
		wire_typ4_skin.getPolylinetypliste().add(22);
		wire_typ4_skin.getPolylinetypliste().add(24);
	}


	private void set_Polylinelist_Typ3_Aus(Wire_Typ3_Aussparung wire_typ3_aus) {
		wire_typ3_aus.getPolylinetypliste().add(17);
		wire_typ3_aus.getPolylinetypliste().add(25);
		wire_typ3_aus.getPolylinetypliste().add(18);
		wire_typ3_aus.getPolylinetypliste().add(26);
	}

	private void set_Polylinelist_Typ4_Aus(Wire_Typ4_Aussparung wire_typ4_aus) {
		wire_typ4_aus.getPolylinetypliste().add(17);
		wire_typ4_aus.getPolylinetypliste().add(19);
		wire_typ4_aus.getPolylinetypliste().add(18);
		wire_typ4_aus.getPolylinetypliste().add(20);
	}

	private void setAnzpolyLine(Wire_gen wire_gen) {
		int anzpolyline = wire_gen.getPolylinetypliste().size();
		wire_gen.setAnzpolyline(anzpolyline);
	}

	private void set_Polylinelist_Typ4_Spar(Wire_Typ4_Spar wire_typ4_spar) {
		wire_typ4_spar.getPolylinetypliste().add(13);
		wire_typ4_spar.getPolylinetypliste().add(14);
		wire_typ4_spar.getPolylinetypliste().add(15);
		wire_typ4_spar.getPolylinetypliste().add(16);
	}

	private void set_Polylinelist_Typ2(Wire_Typ2_SC_STR wire_typ2) {
		Bauteil_gen bauteil = wire_typ2.getProfilebene_gen().getBauteil();
		int is_up_flag = 0;

		if (bauteil instanceof Stringer) {
			is_up_flag = ((Stringer) bauteil).getIs_up_flag();
		} else if (bauteil instanceof Sparcap) {
			is_up_flag = ((Sparcap) bauteil).getIs_up_flag();
		}

		if (is_up_flag == 1 & (bauteil instanceof Stringer)) {
			wire_typ2.getPolylinetypliste().add(5); // on profil
			wire_typ2.getPolylinetypliste().add(7); // fest
		} else if (is_up_flag == 0 & (bauteil instanceof Stringer)) {
			wire_typ2.getPolylinetypliste().add(6); // on profil
			wire_typ2.getPolylinetypliste().add(8); // fest
		} else if (is_up_flag == 1 & (bauteil instanceof Sparcap)) {
			wire_typ2.getPolylinetypliste().add(3); // on profil
			wire_typ2.getPolylinetypliste().add(9); // fest
		} else if (is_up_flag == 0 & (bauteil instanceof Sparcap)) {
			wire_typ2.getPolylinetypliste().add(4); // on profil
			wire_typ2.getPolylinetypliste().add(10); // fest
		}

	}

	private void set_Polylinelist_TypX(Wire_TypX_Rib wire_typX) {
		int anzstringers = get_anzstringers_wire_typX_rib(wire_typX);
		wire_typX.setAnzpolyline(4 + anzstringers * 2);

		for (int i = 0; i < anzstringers; i++) {
			wire_typX.getPolylinetypliste().add(1); // on profil
			wire_typX.getRib_onPro_Nr().add(i + 1);
			wire_typX.getStr_Nr().add(0);

			wire_typX.getPolylinetypliste().add(7); // on profil
			wire_typX.getRib_onPro_Nr().add(0);
			wire_typX.getStr_Nr().add(i + 1);

		}
		wire_typX.getPolylinetypliste().add(1); // on profil
		wire_typX.getRib_onPro_Nr().add(anzstringers + 1);
		wire_typX.getStr_Nr().add(0);

		wire_typX.getPolylinetypliste().add(11); // fest
		wire_typX.getRib_onPro_Nr().add(0);
		wire_typX.getStr_Nr().add(0);

		for (int i = 0; i < anzstringers; i++) {
			wire_typX.getPolylinetypliste().add(2); // on profil
			wire_typX.getRib_onPro_Nr().add(anzstringers + 1 - i);
			wire_typX.getStr_Nr().add(0);

			wire_typX.getPolylinetypliste().add(8); // on profil
			wire_typX.getRib_onPro_Nr().add(0);
			wire_typX.getStr_Nr().add(anzstringers - i);

		}
		wire_typX.getPolylinetypliste().add(2); // on profil
		wire_typX.getRib_onPro_Nr().add(1);
		wire_typX.getStr_Nr().add(0);

		wire_typX.getPolylinetypliste().add(12); // fest
		wire_typX.getRib_onPro_Nr().add(0);
		wire_typX.getStr_Nr().add(0);

	}
	private void set_Polylinelist_TypX_Rib_LE(Wire_TypX_Rib_LE wire_typX_LE) {
		int anzstringers = get_anzstringers_wire_typX_rib(wire_typX_LE);
		wire_typX_LE.setAnzpolyline(3 + anzstringers * 2);

		for (int i = 0; i < anzstringers; i++) {
			wire_typX_LE.getPolylinetypliste().add(1); // on profil
			wire_typX_LE.getRib_onPro_Nr().add(i + 1);
			wire_typX_LE.getStr_Nr().add(0);

			wire_typX_LE.getPolylinetypliste().add(7); // on profil
			wire_typX_LE.getRib_onPro_Nr().add(0);
			wire_typX_LE.getStr_Nr().add(i + 1);

		}
		wire_typX_LE.getPolylinetypliste().add(1); // on profil
		wire_typX_LE.getRib_onPro_Nr().add(anzstringers + 1);
		wire_typX_LE.getStr_Nr().add(0);

		wire_typX_LE.getPolylinetypliste().add(11); // fest
		wire_typX_LE.getRib_onPro_Nr().add(0);
		wire_typX_LE.getStr_Nr().add(0);

		for (int i = 0; i < anzstringers; i++) {
			wire_typX_LE.getPolylinetypliste().add(2); // on profil
			wire_typX_LE.getRib_onPro_Nr().add(anzstringers + 1 - i);
			wire_typX_LE.getStr_Nr().add(0);

			wire_typX_LE.getPolylinetypliste().add(8); // on profil
			wire_typX_LE.getRib_onPro_Nr().add(0);
			wire_typX_LE.getStr_Nr().add(anzstringers - i);

		}
		wire_typX_LE.getPolylinetypliste().add(2); // on profil
		wire_typX_LE.getRib_onPro_Nr().add(1);
		wire_typX_LE.getStr_Nr().add(0);

//		wire_typX_LE.getPolylinetypliste().add(12); // fest
//		wire_typX_LE.getRib_onPro_Nr().add(0);
//		wire_typX_LE.getStr_Nr().add(0);

	}

	private int get_anzstringers_wire_typX_rib(Wire_Rib wire_rib) {
		Bauteil_gen rib = wire_rib.getProfilebene_gen().getBauteil();
		int bauteiltypnummer = rib.getBauteiltypnummer();
		int anzstringers = 0;

		if (bauteiltypnummer == 1) {
			anzstringers = wire_rib.getProfilebene_gen().getBauteil().getBauteilE().getSE().getAnzstringertyp1();
		} else if (bauteiltypnummer == 2) {
			anzstringers = wire_rib.getProfilebene_gen().getBauteil().getBauteilE().getSE().getAnzstringertyp2();
		}
		wire_rib.setAnzstringers(anzstringers);
		return anzstringers;
	}

//	private int get_anzstringers_wire_typX_rib(Wire_TypX_Rib wire_typX) {
//		Bauteil_gen rib = wire_typX.getProfilebene_gen().getBauteil();
//		int bauteiltypnummer = rib.getBauteiltypnummer();
//		int anzstringers = 0;
//
//		if (bauteiltypnummer == 1) {
//			anzstringers = wire_typX.getProfilebene_gen().getBauteil().getBauteilE().getSE().getAnzstringertyp1();
//		} else if (bauteiltypnummer == 2) {
//			anzstringers = wire_typX.getProfilebene_gen().getBauteil().getBauteilE().getSE().getAnzstringertyp2();
//		}
//		wire_typX.setAnzstringers(anzstringers);
//		return anzstringers;
//	}

}
