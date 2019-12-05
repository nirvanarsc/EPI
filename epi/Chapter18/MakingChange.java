package epi.Chapter18;

public final class MakingChange {

    public static int changeMaking(int cents) {
        final int[] COINS = { 100, 50, 25, 10, 5, 1 };
        int numCoins = 0;
        for (int coin : COINS) {
            numCoins += cents / coin;
            cents %= coin;
        }

        return numCoins;
    }

    public static void main(String[] args) {
        System.out.println(changeMaking(100));
        System.out.println(changeMaking(240));
    }

    private MakingChange() {}
}
