
import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {


    // MAIN PROGRAM, run this to play a game of TicTacToe
    public static void main(String[] args) {

        TicTacToeBoard game;
        
        game = new TicTacToeBoard();
        System.out.println("Enter what space you want to play where 1 is the top-left space, and 9 is the bottom-right space");

		boolean flag = true;
		while (flag) {
			System.out.println(game);
			CellValue playing = game.nextCellValue();
			Scanner input = new Scanner(System.in);
			System.out.print(playing+" to play: ");
			try {
				int index = input.nextInt()-1;
				if (index < 0 || index >= 9) 
					System.out.println("The value should be between 1 and "+9);	
				else if (game.valueAt(index) != CellValue.EMPTY)
					System.out.println("This space has already been played");
				else {
					game.play(index);
					if (game.getGameState() != GameState.PLAYING) {
						System.out.println(game);
						System.out.println("Result: "+game.getGameState());
						flag = false;
					}
				}				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter integer between 1 and 9");
			}
		}
    }
}