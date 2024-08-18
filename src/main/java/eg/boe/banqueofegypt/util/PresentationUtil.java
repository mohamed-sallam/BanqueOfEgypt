package eg.boe.banqueofegypt.util;

public interface PresentationUtil {
    static String formatWithPostfix(Long number) {
        final String[] postfixes = {"", "k", "M", "G", "T", "P", "E"};
        final int step = 1000;

        if (number < step)
            return String.valueOf(number);

        int magnitude = (int) (Math.log10(number) / Math.log10(step));
        double normalizedNumber = number / Math.pow(step, magnitude);

        return String.format("%.1f%s", normalizedNumber, postfixes[magnitude]);
    }
}
