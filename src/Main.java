import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int choice = -1;
        while(!(choice == 1 || choice == 2)) {
            System.out.println("Pilih salah satu : ");
            System.out.println("1. Puzzle automatis");
            System.out.println("2. Input File");
            System.out.println();
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            Puzzle puzzle;
            if (choice == 1) {
                System.out.println();
                puzzle = new Puzzle().shufflePuzzle();
            } else {
                System.out.println();
                System.out.print("Path File >> ");
                input = new Scanner(System.in);
                String path = input.next();
                System.out.println();
                ReadFile readFile = new ReadFile();
                if (readFile.read(path) == null) {
                    break;
                } else {
                    puzzle = readFile.read(path);
                }
            }
            System.out.println("Puzzle awal :");
            System.out.println();
            puzzle.Show();
            BranchNBound solver = new BranchNBound(puzzle);
            solver.solve();
        }
    }
}