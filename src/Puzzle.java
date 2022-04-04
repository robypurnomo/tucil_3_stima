import java.util.*;

public class Puzzle {
    private int mat[][];
    final int length = 4;
    public int lastmove;
    public List<Integer> movesHistory;
    public int moves;
    public int cost;
    
    Puzzle () {
        this.mat = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.mat[i][j] = (length*i)+j+1;
            }
        }
        this.lastmove = -1;
        this.movesHistory = new ArrayList<>();
        this.moves = 0;
        this.cost = this.calculateCost() + this.moves;
    }

    Puzzle (int[][] mat) {
        this.mat = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.mat[i][j] = mat[i][j];
            }
        }
        this.lastmove = -1;
        this.movesHistory = new ArrayList<>();
        this.moves = 0;
        this.cost = this.calculateCost() + this.moves;
    }

    public boolean isSolved() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.mat[i][j] != (length*i)+j+1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSame(Puzzle p) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.mat[i][j] != p.mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getIndex(int x) {
        int[] idx = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.mat[i][j] == x) {
                    idx[0] = i;
                    idx[1] = j;
                    break;
                }
            }
        }
        return idx;
    }

    public int Kurang(boolean print) {
        int count = 0;
        int countTemp;
        if (print) {
            System.out.println();
            System.out.println("Nilai dari Kurang(i) : ");
            System.out.println();
        }
        for (int x = 1; x <= 16; x++) {
            int idx[] = this.getIndex(x); 
            int idxi = idx[0]; int idxj = idx[1];
            countTemp = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if ((this.mat[i][j] < x) && ((length*i + j + 1) > (length*idxi + idxj + 1))) {
                        countTemp++;
                    }
                }
            }
            count += countTemp;
            if (print) {
                if (x > 9) {
                    System.out.println(x + " : " + countTemp);
                } else {
                    System.out.println(x + "  : " + countTemp);
                }
            }
        }
        return count;
    }

    public int X() {
        int idx[] = this.getIndex(16); 
        int idxi = idx[0]; int idxj = idx[1];
        if ((idxi%2 == 0 && idxj%2 == 1) || (idxi%2 == 1 && idxj%2 == 0)) {
            return 1;
        }
        return 0;
    }

    public Puzzle Up() {
        Puzzle newPuzzle = new Puzzle(this.mat);
        int idx[] = this.getIndex(16); 
        int i = idx[0]; int j = idx[1];
        if (i > 0) {
            int temp = newPuzzle.mat[i][j];
            newPuzzle.mat[i][j] = newPuzzle.mat[i-1][j];
            newPuzzle.mat[i-1][j] = temp;
            newPuzzle.lastmove = 0;
            newPuzzle.moves = this.moves + 1;
            newPuzzle.cost = newPuzzle.calculateCost() + newPuzzle.moves;
            for (int k = 0; k < this.movesHistory.size(); k++) {
                newPuzzle.movesHistory.add(this.movesHistory.get(k));
            }
            newPuzzle.movesHistory.add(0);
            return newPuzzle;
        } else {
            return null;
        }
    }

    public Puzzle Down() {
        Puzzle newPuzzle = new Puzzle(this.mat);
        int idx[] = this.getIndex(16); 
        int i = idx[0]; int j = idx[1];
        if (i < 3) {
            int temp = newPuzzle.mat[i][j];
            newPuzzle.mat[i][j] = newPuzzle.mat[i+1][j];
            newPuzzle.mat[i+1][j] = temp;
            newPuzzle.lastmove = 1;
            newPuzzle.cost = newPuzzle.calculateCost() + newPuzzle.moves;
            newPuzzle.moves = this.moves + 1;
            for (int k = 0; k < this.movesHistory.size(); k++) {
                newPuzzle.movesHistory.add(this.movesHistory.get(k));
            }
            newPuzzle.movesHistory.add(1);
            return newPuzzle;
        } else {
            return null;
        }
    }

    public Puzzle Left() {
        Puzzle newPuzzle = new Puzzle(this.mat);
        int idx[] = this.getIndex(16); 
        int i = idx[0]; int j = idx[1];
        if (j > 0) {
            int temp = newPuzzle.mat[i][j];
            newPuzzle.mat[i][j] = newPuzzle.mat[i][j-1];
            newPuzzle.mat[i][j-1] = temp;
            newPuzzle.lastmove = 2;
            newPuzzle.cost = newPuzzle.calculateCost() + newPuzzle.moves;
            newPuzzle.moves = this.moves + 1;
            for (int k = 0; k < this.movesHistory.size(); k++) {
                newPuzzle.movesHistory.add(this.movesHistory.get(k));
            }
            newPuzzle.movesHistory.add(2);
            return newPuzzle;
        } else {
            return null;
        }
    }

    public Puzzle Right() {
        Puzzle newPuzzle = new Puzzle(this.mat);
        int idx[] = this.getIndex(16); 
        int i = idx[0]; int j = idx[1];
        if (j < 3) {
            int temp = newPuzzle.mat[i][j];
            newPuzzle.mat[i][j] = newPuzzle.mat[i][j+1];
            newPuzzle.mat[i][j+1] = temp;
            newPuzzle.lastmove = 3;
            newPuzzle.cost = newPuzzle.calculateCost() + newPuzzle.moves;
            newPuzzle.moves = this.moves + 1;
            for (int k = 0; k < this.movesHistory.size(); k++) {
                newPuzzle.movesHistory.add(this.movesHistory.get(k));
            }
            newPuzzle.movesHistory.add(3);
            return newPuzzle;
        } else {
            return null;
        }
    }

    public int calculateCost()
    {
        int count = 0;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                if (this.mat[i][j] != (length*i + j+1) && this.mat[i][j] != 16)
                count++;
        return count;
    }

    public void Show() {
        for (int i = 0; i < length; i++) {
            // Loop through all elements of current row
            for (int j = 0; j < length; j++) {
                if (j != length-1) {
                    if (mat[i][j] == 16) {
                        System.out.print("- ");
                    } else {
                        System.out.print(mat[i][j]);
                    }
                    if (mat[i][j] < 10) {
                        System.out.print(" ");
                    }
                    System.out.print("  ");
                } else {
                    if (mat[i][j] == 16) {
                        System.out.println("- ");
                    } else {
                        System.out.println(mat[i][j]);
                    }
                }
            }
        }
    }

    public Puzzle shufflePuzzle() {
        Puzzle newPuzzle = this;
        int move = 60;
        for (int i = 0; i<move; i++) {
            int rand = new Random().nextInt(4);
            if (rand == 0) {
                if (newPuzzle.Up() != null) {
                    newPuzzle = newPuzzle.Up();
                }
            } else if (rand == 1) {
                if (newPuzzle.Down() != null) {
                    newPuzzle = newPuzzle.Down();
                }
            } else if (rand == 2) {
                if (newPuzzle.Left() != null) {
                    newPuzzle = newPuzzle.Left();
                }
            } else {
                if (newPuzzle.Right() != null) {
                    newPuzzle = newPuzzle.Right();
                }
            }
        }
        newPuzzle.moves = 0;
        newPuzzle.cost = newPuzzle.calculateCost() + newPuzzle.moves;
        newPuzzle.lastmove = -1;
        newPuzzle.movesHistory = new ArrayList<>();
        return newPuzzle;
    }
}

class PuzzleComparator implements Comparator<Puzzle>{
    public int compare(Puzzle p1, Puzzle p2) {
        if (p1.cost > p2.cost)
            return 1;
        else if (p1.cost < p2.cost)
            return -1;
        else
            if (p1.Kurang(false) > p2.Kurang(false)) {
                return 1;
            } else if (p1.Kurang(false) < p2.Kurang(false)) {
                return -1;
            } else {
                return 0;
            }
    }
}