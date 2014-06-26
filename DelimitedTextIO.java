import java.util.Scanner;
public interface DelimitedTextIO{
	public String toText(char delimiter);
	public void toObject(Scanner input);
}
