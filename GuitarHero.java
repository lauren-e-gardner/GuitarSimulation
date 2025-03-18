/*******************************************************************************
 *
 * This class uses the objects (Keyboard, RingBuffer, and GuitarString) to create
 * A keyboard that manipulates an array of GuitarStrings.
 *
 ******************************************************************************/

public class GuitarHero {

    public static void main(String[] args) {
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int length = keyboardString.length();
        // creates an array of guitar strings with length = keyboardString
        GuitarString[] strings = new GuitarString[length];

        for (int i = 0; i < length; i++) {
            // calculates frequency for initialization of string objects
            strings[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {
            double sample = 0.0;
            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                // pluck the corresponding string
                for (int i = 0; i < length; i++) {
                    if (keyboardString.indexOf(key) == i) {
                        strings[i].pluck();
                    }
                }

            }
            // iterate through strings and update sample
            for (int i = 0; i < length; i++) sample += strings[i].sample();
            StdAudio.play(sample);
            // iterate through strings and update tics
            for (int i = 0; i < length; i++) strings[i].tic();
        }

    }
}
