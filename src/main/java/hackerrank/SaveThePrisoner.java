package hackerrank;

public class SaveThePrisoner {
    // Complete the saveThePrisoner function below.
    static int saveThePrisoner(int n, int m, int s) {
        int a = (m + s) % n;
        return a == 0 ? n : a;
    }
}
