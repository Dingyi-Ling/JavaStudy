public class CaesarCipher implements Cipher {
    private int shift;
    
    public CaesarCipher(int shift) {
        this.shift = shift;
    }
    
    @Override
    public String encode(String plainText) {
        StringBuilder encoded = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                // Preserve letter case.
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encoded.append((char)(((c - base + shift) % 26) + base));
            } else {
                // Non-letter characters are left unchanged.
                encoded.append(c);
            }
        }
        return encoded.toString();
    }
    
    @Override
    public String decode(String cipherText) {
        StringBuilder decoded = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                decoded.append((char)(((c - base - shift + 26) % 26) + base));
            } else {
                decoded.append(c);
            }
        }
        return decoded.toString();
    }
}
