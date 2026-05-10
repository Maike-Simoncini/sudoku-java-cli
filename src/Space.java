public class Board {
    private Space[][] slots = new Space[9][9];
    public static final String CYAN = "\033[0;36m";
    public static final String RESET = "\033[0m";
    public static final String WHITE_BOLD = "\033[1;37m";

    public Board() {
        // Inicializa a matriz com espaços vazios para evitar NullPointerException
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                slots[i][j] = new Space(0, false);
            }
        }
    }

    public void addSpace(int row, int col, int value, boolean isFixed) {
        if (row >= 0 && row < 9 && col >= 0 && col < 9) {
            slots[row][col] = new Space(value, isFixed);
        }
    }

    public Space getSpace(int row, int col) {
        return slots[row][col];
    }

    public void printBoard() {
        System.out.println("\n    0 1 2   3 4 5   6 7 8"); // Índices das colunas
        System.out.println("  +-------+-------+-------+");

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("  +-------+-------+-------+");
            }
            
            System.out.print(i + " | "); // Índice da linha
            
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                
                int val = slots[i][j].getValue();
                String display = (val == 0) ? "." : String.valueOf(val);
                
                // Números fixos aparecem em Cyan Negrito, inseridos pelo usuário em Branco
                if (slots[i][j].isFixed()) {
                    System.out.print(CYAN + display + RESET + " ");
                } else {
                    System.out.print(WHITE_BOLD + display + RESET + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  +-------+-------+-------+");
    }

    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (slots[i][j].getValue() == 0) return false;
            }
        }
        return true;
    }
}
