public class SudokuService {

    public boolean isValid(Board board, int row, int col, int value) {
        return checkRow(board, row, value) &&
               checkColumn(board, col, value) &&
               checkSubgrid(board, row, col, value);
    }

    private boolean checkRow(Board board, int row, int value) {
        for (int c = 0; c < 9; c++) {
            if (board.getSpace(row, c).getValue() == value) return false;
        }
        return true;
    }

    private boolean checkColumn(Board board, int col, int value) {
        for (int r = 0; r < 9; r++) {
            if (board.getSpace(r, col).getValue() == value) return false;
        }
        return true;
    }

    private boolean checkSubgrid(Board board, int row, int col, int value) {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board.getSpace(r, c).getValue() == value) return false;
            }
        }
        return true;
    }
}
