package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Bauteil__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (BauteilElement bauteilE : getGraph().allInstances(BauteilElement.class)) {

			int anzspars = bauteilE.getSE().getAnzspars();

			ArrayList<Bauteil_gen> bauteils1 = null;
			ArrayList<Bauteil_gen> bauteils2 = null;

			if (bauteilE instanceof SparcapElement | bauteilE instanceof SparElement) {

				bauteils1 = create_spars(bauteilE, anzspars);
				connectbauteil1(bauteilE, bauteils1);

			} else {

				int anzbauteil1 = get_anzbauteil1(bauteilE);

				bauteils1 = create_Bauteil1(bauteilE, anzbauteil1);
				connectbauteil1(bauteilE, bauteils1);

				int anzbauteil2 = get_anzbauteil2(bauteilE);
				bauteils2 = create_Bauteil2(bauteilE, anzbauteil2);
				connectbauteil1(bauteilE, bauteils2);

			}
		}
	}

	private ArrayList<Bauteil_gen> create_spars(BauteilElement bauteilE, int anzspars2) {
		int anzspars = bauteilE.getSE().getAnzspars();
		ArrayList<Bauteil_gen> bauteils = Lists.newArrayList();

		if (bauteilE instanceof SparcapElement) {

			if (anzspars >= 1) {
				Sparcap sparcap_up1 = Sparcap.create();
				Sparcap sparcap_down1 = Sparcap.create();
				sparcap_up1.setIs_up_flag(1);
				sparcap_down1.setIs_up_flag(0);

				sparcap_down1.setBauteilnummer(1);
				sparcap_up1.setBauteilnummer(1);
				sparcap_up1.setBauteiltypnummer(1);
				sparcap_down1.setBauteiltypnummer(1);
				bauteils.add(sparcap_up1);
				bauteils.add(sparcap_down1);

				sparcap_up1.setSubsegmentnummer(1);
				sparcap_down1.setSubsegmentnummer(1);

			}
			if (anzspars >= 2) {
				Sparcap sparcap_up2 = Sparcap.create();
				Sparcap sparcap_down2 = Sparcap.create();
				sparcap_up2.setIs_up_flag(1);
				sparcap_down2.setIs_up_flag(0);

				sparcap_down2.setBauteilnummer(2);
				sparcap_up2.setBauteilnummer(2);
				sparcap_up2.setSubsegmentnummer(2);
				sparcap_down2.setSubsegmentnummer(2);
				sparcap_up2.setBauteiltypnummer(2);
				sparcap_down2.setBauteiltypnummer(2);
				bauteils.add(sparcap_up2);
				bauteils.add(sparcap_down2);
				// provisorisch, bis Subsegmentunterteilung rausnehmen!
				if (anzspars == 2) {
					sparcap_up2.setSubsegmentnummer(1);
					sparcap_down2.setSubsegmentnummer(1);
					sparcap_up2.setBauteiltypnummer(3);
					sparcap_down2.setBauteiltypnummer(3);
				}

			}

			if (anzspars >= 3) {
				Sparcap sparcap_up3 = Sparcap.create();
				Sparcap sparcap_down3 = Sparcap.create();
				sparcap_up3.setIs_up_flag(1);
				sparcap_down3.setIs_up_flag(0);

				sparcap_down3.setBauteilnummer(3);
				sparcap_up3.setBauteilnummer(3);
				sparcap_up3.setSubsegmentnummer(2);
				sparcap_down3.setSubsegmentnummer(2);
				bauteils.add(sparcap_up3);
				bauteils.add(sparcap_down3);

				sparcap_up3.setBauteiltypnummer(3);
				sparcap_down3.setBauteiltypnummer(3);
			}
		} else if (bauteilE instanceof SparElement) {

			if (anzspars >= 1) {

				Spar spar1 = Spar.create();
				spar1.setBauteilnummer(1);
				spar1.setBauteiltypnummer(1);
				bauteils.add(spar1);
				spar1.setSubsegmentnummer(1);

			}
			if (anzspars >= 2) {

				Spar spar2 = Spar.create();
				spar2.setBauteilnummer(2);
				spar2.setSubsegmentnummer(2);
				spar2.setBauteiltypnummer(2);

				// provisorisch, bis Subsegmentunterteilung rausnehmen!
				if (anzspars == 2) {
					spar2.setSubsegmentnummer(1);
					spar2.setBauteiltypnummer(3);

				}

				bauteils.add(spar2);
			}
			if (anzspars >= 3) {

				Spar spar3 = Spar.create();
				spar3.setBauteilnummer(3);
				spar3.setBauteiltypnummer(3);
				bauteils.add(spar3);
				spar3.setSubsegmentnummer(2);

			}
		}

		return bauteils;
	}

	private void connectbauteil2(BauteilElement bauteilE, ArrayList<Bauteil_gen> bauteils) {
		int size = bauteils.size();

		for (int i = 0; i < size; i++) {
			bauteils.get(i).setBauteilE(bauteilE);
			bauteilE.getBauteil_typ2().add(bauteils.get(i));
			bauteilE.getSub().add(bauteils.get(i));
		}
	}

	private void connectbauteil1(BauteilElement bauteilE, ArrayList<Bauteil_gen> bauteils) {
		int size = bauteils.size();

		for (int i = 0; i < size; i++) {
			bauteils.get(i).setBauteilE(bauteilE);
			if (bauteils.get(i).getBauteiltypnummer() == 1) {
				bauteilE.getBauteil_typ1().add(bauteils.get(i));
			} else if (bauteils.get(i).getBauteiltypnummer() == 2) {
				bauteilE.getBauteil_typ2().add(bauteils.get(i));
			} else if (bauteils.get(i).getBauteiltypnummer() == 3) {
				bauteilE.getBauteil_typ3().add(bauteils.get(i));

			}
			bauteilE.getSub().add(bauteils.get(i));
		}
	}

	private ArrayList<Bauteil_gen> create_Bauteil2(BauteilElement bauteilE, int anzbauteil) {
		ArrayList<Bauteil_gen> bauteils = Lists.newArrayList();

		if (bauteilE instanceof RibElement) {
			Bauteil_gen bauteil1[] = new Bauteil_gen[anzbauteil];

			for (int i = 0; i < anzbauteil; i++) {
				bauteil1[i] = Rib.create();
				bauteil1[i].setBauteilnummer(i + 1);
				bauteil1[i].setBauteiltypnummer(2);
				bauteil1[i].setSubsegmentnummer(2);

				bauteils.add(bauteil1[i]);
			}
		} else if (bauteilE instanceof StringerElement) {
			Bauteil_gen bauteil1_up[] = new Bauteil_gen[anzbauteil];

			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_up[i] = Stringer.create();
				bauteil1_up[i].setBauteilnummer(i + 1);
				bauteil1_up[i].setBauteiltypnummer(2);
				bauteil1_up[i].setSubsegmentnummer(2);
				((Stringer) bauteil1_up[i]).setIs_up_flag(1);

				bauteils.add(bauteil1_up[i]);

			}

			Bauteil_gen bauteil1_down[] = new Bauteil_gen[anzbauteil];
			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_down[i] = Stringer.create();
				bauteil1_down[i].setBauteilnummer(i + 1);
				bauteil1_down[i].setBauteiltypnummer(2);
				bauteil1_down[i].setSubsegmentnummer(2);
				((Stringer) bauteil1_down[i]).setIs_up_flag(0);

				bauteils.add(bauteil1_down[i]);
			}
		} else if (bauteilE instanceof SkinElement) {
			Bauteil_gen bauteil1_up[] = new Bauteil_gen[anzbauteil];

			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_up[i] = Skin.create();
				bauteil1_up[i].setBauteilnummer(i + 1);
				bauteil1_up[i].setBauteiltypnummer(2);
				bauteil1_up[i].setSubsegmentnummer(2);
				((Skin) bauteil1_up[i]).setIs_up_flag(1);

				bauteils.add(bauteil1_up[i]);

			}

			Bauteil_gen bauteil1_down[] = new Bauteil_gen[anzbauteil];
			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_down[i] = Skin.create();
				bauteil1_down[i].setBauteilnummer(i + 1);
				bauteil1_down[i].setBauteiltypnummer(2);
				bauteil1_down[i].setSubsegmentnummer(2);
				((Skin) bauteil1_down[i]).setIs_up_flag(0);

				bauteils.add(bauteil1_down[i]);
			}
		}

		return bauteils;
	}

	private int get_anzbauteil1(BauteilElement bauteilE) {
		int anzbauteil1 = 0;

		if (bauteilE instanceof RibElement) {
			anzbauteil1 = bauteilE.getSE().getAnzribtyp1();
		} else if (bauteilE instanceof StringerElement) {
			anzbauteil1 = bauteilE.getSE().getAnzstringertyp1();
		} else if (bauteilE instanceof SkinElement) {
			anzbauteil1 = bauteilE.getSE().getAnzskintyp1();
		}

		return anzbauteil1;
	}

	private int get_anzbauteil2(BauteilElement bauteilE) {
		int anzbauteil2 = 0;

		if (bauteilE instanceof RibElement) {
			anzbauteil2 = bauteilE.getSE().getAnzribtyp2();
		} else if (bauteilE instanceof StringerElement) {
			anzbauteil2 = bauteilE.getSE().getAnzstringertyp2();
		} else if (bauteilE instanceof SkinElement) {
			anzbauteil2 = bauteilE.getSE().getAnzskintyp2();
		}

		return anzbauteil2;
	}

	private ArrayList<Bauteil_gen> create_Bauteil1(BauteilElement bauteilE, int anzbauteil) {

		ArrayList<Bauteil_gen> bauteils = Lists.newArrayList();

		if (bauteilE instanceof RibElement) {
			Bauteil_gen bauteil1[] = new Bauteil_gen[anzbauteil];

			for (int i = 0; i < anzbauteil; i++) {
				bauteil1[i] = Rib.create();
				bauteil1[i].setBauteilnummer(i + 1);
				bauteil1[i].setBauteiltypnummer(1);
				bauteil1[i].setSubsegmentnummer(1);

				bauteils.add(bauteil1[i]);
			}
		} else if (bauteilE instanceof StringerElement) {
			Bauteil_gen bauteil1_up[] = new Bauteil_gen[anzbauteil];

			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_up[i] = Stringer.create();
				bauteil1_up[i].setBauteilnummer(i + 1);
				bauteil1_up[i].setBauteiltypnummer(1);
				bauteil1_up[i].setSubsegmentnummer(1);
				((Stringer) bauteil1_up[i]).setIs_up_flag(1);

				bauteils.add(bauteil1_up[i]);
			}

			Bauteil_gen bauteil1_down[] = new Bauteil_gen[anzbauteil];
			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_down[i] = Stringer.create();
				bauteil1_down[i].setBauteilnummer(i + 1);
				bauteil1_down[i].setBauteiltypnummer(1);
				bauteil1_down[i].setSubsegmentnummer(1);
				((Stringer) bauteil1_down[i]).setIs_up_flag(0);

				bauteils.add(bauteil1_down[i]);
			}
		} else if (bauteilE instanceof SkinElement) {
			Bauteil_gen bauteil1_up[] = new Bauteil_gen[anzbauteil];

			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_up[i] = Skin.create();
				bauteil1_up[i].setBauteilnummer(i + 1);
				bauteil1_up[i].setBauteiltypnummer(1);
				bauteil1_up[i].setSubsegmentnummer(1);
				((Skin) bauteil1_up[i]).setIs_up_flag(1);

				bauteils.add(bauteil1_up[i]);
			}

			Bauteil_gen bauteil1_down[] = new Bauteil_gen[anzbauteil];
			for (int i = 0; i < anzbauteil; i++) {
				bauteil1_down[i] = Skin.create();
				bauteil1_down[i].setBauteilnummer(i + 1);
				bauteil1_down[i].setBauteiltypnummer(1);
				bauteil1_down[i].setSubsegmentnummer(1);
				((Skin) bauteil1_down[i]).setIs_up_flag(0);

				bauteils.add(bauteil1_down[i]);
			}
		}

		return bauteils;
	}

}