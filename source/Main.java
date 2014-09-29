import gameEngine.*;

public class Main {
	public static void main(String[] args) {
		GameResult result = new ConnectFourGame().play();
		System.out.println(result);
	}
}