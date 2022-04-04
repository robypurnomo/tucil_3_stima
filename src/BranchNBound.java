import java.util.PriorityQueue;
import java.time.LocalTime;
import java.time.Duration;

public class BranchNBound {

    PriorityQueue<Puzzle> queue;
    int simpulHidup;

    BranchNBound(Puzzle puzzle) {
        this.queue = new PriorityQueue<>(new PuzzleComparator());
        this.queue.add(puzzle);
        this.simpulHidup = 1;
    }

    public void solve() {
        Puzzle awal = this.queue.poll();
        Puzzle puzzle = awal;
        int kurang = puzzle.Kurang(true);
        int X = puzzle.X();
        LocalTime start = LocalTime.now();
        if ((kurang+X)%2 == 0) {
            this.queue.add(puzzle);
            while (!this.queue.isEmpty())
            {
                puzzle = this.queue.poll();
                int cost = puzzle.calculateCost();
                if (cost == 0)
                {
                    break;
                }
                else {
                    Puzzle newPuzzle;
                    if (puzzle.Down() != null) {
                        newPuzzle = puzzle.Down();
                        if (puzzle.lastmove != 0) {
                            this.queue.add(newPuzzle);
                            this.simpulHidup++;
                        }
                    } 
                    if (puzzle.Right() != null) {
                        newPuzzle = puzzle.Right();
                        if (puzzle.lastmove != 2) {
                            this.queue.add(newPuzzle);
                            this.simpulHidup++;
                        } 
                    } 
                    if (puzzle.Up() != null) {
                        newPuzzle = puzzle.Up();
                        if (puzzle.lastmove != 1) {
                            this.queue.add(newPuzzle);
                            this.simpulHidup++;
                        } 
                    } 
                    if (puzzle.Left() != null) {
                        newPuzzle = puzzle.Left();
                        if (puzzle.lastmove != 3) {
                            this.queue.add(newPuzzle);
                            this.simpulHidup++;
                        }  
                    } 
                }
            } 
            LocalTime end = LocalTime.now();
            Duration duration = Duration.between(start, end);
            System.out.println();
            System.out.println("Result : ");
            System.out.println();
            for (int i=0; i<puzzle.movesHistory.size(); i++) {
                if (puzzle.movesHistory.get(i) == 0) {
                    awal = awal.Up();
                } else if (puzzle.movesHistory.get(i) == 1) {
                    awal = awal.Down();
                } else if (puzzle.movesHistory.get(i) == 2) {
                    awal = awal.Left();
                } else {
                    awal = awal.Right();
                }
                awal.Show();
                System.out.println();
            }
            puzzle.Show();
            System.out.println();
            System.out.println("Puzzle moves  = " + puzzle.moves);
            System.out.println("Kurang(i)+X  = " + (kurang + X));
            System.out.println("Simpul Hidup = " + this.simpulHidup);
            System.out.println("Duration     = " + duration.toMillis() + " ms");
        } else {
            System.out.println();
            System.out.println("Result : ");
            System.out.println();
            System.out.println("Tidak ada solusi dengan nilai Kurang(i)+X = " + (kurang + X));
        }
    }
}