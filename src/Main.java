import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    // Cores para o terminal (Cyan e Reset)
    public static final String CYAN = "\033[0;36m";
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(RED + "Erro: Argumentos de inicialização não fornecidos." + RESET);
            return;
        }

        // 1. Inicialização dos componentes
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        SudokuService service = new SudokuService();

        try {
            // 2. Parsing dos argumentos (Ex: "0,0;4,false 1,0;7,false...")
            String[] configurations = args[0].split(" ");
            for (String config : configurations) {
                // Split por vírgula ou ponto-e-vírgula
                String[] parts = config.split("[,;]");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                boolean isFixed = Boolean.parseBoolean(parts[3]);

                board.addSpace(row, col, value, isFixed);
            }
        } catch (Exception e) {
            System.out.println(RED + "Erro ao processar argumentos. Verifique o formato." + RESET);
            return;
        }

        System.out.println(CYAN + "=== BEM-VINDO AO SUDOKU JAVA CLI ===" + RESET);

        // 3. Loop Principal do Jogo
        while (!board.isComplete()) {
            board.printBoard();
            
            System.out.println("\nDigite sua jogada (formato: linha coluna valor) ou 'sair':");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("sair")) break;

            try {
                String[] move = input.split(" ");
                int r = Integer.parseInt(move[0]);
                int c = Integer.parseInt(move[1]);
                int v = Integer.parseInt(move[2]);

                // Validações
                if (v < 1 || v > 9) {
                    System.out.println(RED + "Valor deve ser entre 1 e 9!" + RESET);
                    continue;
                }

                if (board.getSpace(r, c).isFixed()) {
                    System.out.println(RED + "Não é possível alterar um número fixo!" + RESET);
                } else if (service.isValid(board, r, c, v)) {
                    board.getSpace(r, c).setValue(v);
                    System.out.println(CYAN + "Jogada aceita!" + RESET);
                } else {
                    System.out.println(RED + "Jogada inválida para as regras do Sudoku!" + RESET);
                }

            } catch (Exception e) {
                System.out.println(RED + "Entrada inválida. Use números (Ex: 0 5 9)." + RESET);
            }
        }

        if (board.isComplete()) {
            board.printBoard();
            System.out.println(CYAN + "\n🏆 PARABÉNS! Você completou o Sudoku!" + RESET);
        }

        scanner.close();
    }
}
