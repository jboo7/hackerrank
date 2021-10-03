package hackerrank;

import java.util.List;

public class DFSConnectedCellInAGrid {
    public static int maxRegion(List<List<Integer>> grid) {
        // Write your code here
        final int N = (grid = (grid != null ? grid : List.of())).size();
        final int M = N != 0 ? grid.get(0)
                                   .size() : 0;

        int[][] matrix = grid.stream()
                             .map(l -> l.stream()
                                        .mapToInt(Integer::intValue)
                                        .toArray())
                             .toArray(int[][]::new);
        int[][] u = new int[N][M];

        int max_region = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max_region = Math.max(max_region, get_region_size(matrix, u, i, j));
            }
        }
        return max_region;
    }

    private static int get_region_size(int[][] matrix, int[][] u, int i, int j) {
        final int n = matrix.length;
        final int m = n != 0 ? matrix[0].length : 0;

        if (0 <= i && i < n && 0 <= j && j < m && u[i][j] == 0 && matrix[i][j] == 1) {
            u[i][j] = 1;
            return 1 + get_region_size(matrix, u, i - 1, j - 1) //
                    + get_region_size(matrix, u, i - 1, j) //
                    + get_region_size(matrix, u, i - 1, j + 1) //
                    + get_region_size(matrix, u, i, j - 1)//
                    + get_region_size(matrix, u, i, j + 1)//
                    + get_region_size(matrix, u, i + 1, j - 1)//
                    + get_region_size(matrix, u, i + 1, j)//
                    + get_region_size(matrix, u, i + 1, j + 1);//
        }
        return 0;
    }
}
