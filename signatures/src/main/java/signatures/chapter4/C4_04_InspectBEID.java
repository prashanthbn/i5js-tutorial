package signatures.chapter4;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;

import com.itextpdf.smartcard.CardReaders;
import com.itextpdf.smartcard.SmartCard;
import com.itextpdf.smartcard.beid.BeIDFileFactory;
import com.itextpdf.smartcard.beid.pojos.AddressPojo;
import com.itextpdf.smartcard.beid.pojos.IdentityPojo;
import com.itextpdf.smartcard.beid.pojos.PhotoPojo;

public class C4_04_InspectBEID {

	public static final String PHOTO = "results/chapter4/photo.jpg"; 
	
	public static void main(String[] args) throws CardException, IOException {
		CardReaders readers = new CardReaders();
		for (CardTerminal terminal : readers.getReaders()) {
			System.out.println(terminal.getName());
		}
		for (CardTerminal terminal : readers.getReadersWithCard()) {
			System.out.println(terminal.getName());
			SmartCard card = new SmartCard(terminal);
			IdentityPojo id = BeIDFileFactory.getIdentity(card);
			System.out.println(id.toString());
			AddressPojo address = BeIDFileFactory.getAddress(card);
			System.out.println(address);
			PhotoPojo photo = BeIDFileFactory.getPhoto(card);
			FileOutputStream fos = new FileOutputStream(PHOTO);
			fos.write(photo.getPhoto());
			fos.flush();
			fos.close();
		}
	} 
}
