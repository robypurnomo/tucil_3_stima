import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    public Puzzle read(String path) {
        int[][] mat = new int[4][4];
        int j = 0; int k = 0;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int i = 0; i<data.length(); i++) {
                    int x;
                    if (data.charAt(i) != ' ') {
                        if (toInt(data.charAt(i)) == 1) {
                            if (data.charAt(i+1) >= 48 && data.charAt(i+1) <= 57) {
                                x = toInt(data.charAt(i))*10 + toInt(data.charAt(i+1));
                                i++;
                            } else {
                                x = 1;
                            }
                        } else if (data.charAt(i) == '-') {
                            x = 16;
                        } else {
                            x = toInt(data.charAt(i));
                        }
                        mat[j][k] = x;
                        if (k == 3) {
                            k = 0;
                            j++;
                        } else {
                            k++;
                        }
                    }
                }
            }
            Puzzle puzzle = new Puzzle(mat);
            myReader.close();
            return puzzle;
        } catch (FileNotFoundException e) {
            System.out.println("Gagal memasukkan file.");
            e.printStackTrace();
            return null;
        }
    }

    public int toInt(char c) {
        return c-48;
    }
}