public class CipherFactory {
    public static Cipher getCipher(String cipherName) {
        if (cipherName.equalsIgnoreCase("caesar")) {
            // For demonstration purposes, we use a fixed shift value.
            return new CaesarCipher(3);
        }
        // Add more cipher types as needed.
        throw new IllegalArgumentException("Unsupported cipher: " + cipherName);
    }
}
