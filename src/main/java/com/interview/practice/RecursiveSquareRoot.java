public class RecursiveSquareRoot {

    // Helper method to start the recursive process
    public static double sqrt(double number) {
        return sqrtHelper(number, number / 2);
    }

    // Recursive method to calculate square root
    private static double sqrtHelper(double number, double guess) {
        // Base case: if the guess is close enough to the actual square root
        if (Math.abs(guess * guess - number) < 0.0001) {
            return guess;
        }
        // Recursive case: improve the guess
        double betterGuess = (guess + number / guess) / 2;
        return sqrtHelper(number, betterGuess);
    }

    public static void main(String[] args) {
        double number = 25;
        double result = sqrt(number);
        System.out.println("Square root of " + number + " is: " + result);
    }
}