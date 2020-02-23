package Config.Utilities;

import java.util.Random;

public class RandomDataWork {
    public long generateRandomNumber(long startRange, long endRange) {
        Random rn = new Random();
        long range = endRange - startRange + 1;
        long randomNum = rn.nextInt((int) range) + startRange;
        return randomNum;
    }

    public String generateRandomString(int stringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public int generateRandomNumber(int numberLength) {
        int m = (int) Math.pow(10, numberLength - 1);
        return m + new Random().nextInt(9 * m);
    }
}
