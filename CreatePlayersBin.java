import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreatePlayersBin {

	public static void main(String[] args) {
		
		Player player1 = new Player ("BlackRanger", "password2", 1000);
		Player player2 = new Player ("BlueKnight", "password3", 1500);
		Player player3 = new Player ("IcePeak", "password4", 100);
		Player player4 = new Player ("GoldDigger", "password5", 2200);
		
		try {
			
			FileOutputStream file = new FileOutputStream("players.bin");
			ObjectOutputStream opStream = new ObjectOutputStream(file);
			
			opStream.writeObject(player1);
			opStream.writeObject(player2);
			opStream.writeObject(player3);
			opStream.writeObject(player4);
			opStream.close();
		}
		
		catch (IOException ex){
			
			ex.printStackTrace();
			
			
			
		}
	}
}
