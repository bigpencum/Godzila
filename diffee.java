import java.math.BigInteger;
import java.security.SecureRandom;

 class DiffieHellmanKeyExchange {
    public static void main(String[] args) {
        // Shared prime number and base (both parties must agree on these)
        BigInteger prime = new BigInteger("23");
        BigInteger base = new BigInteger("5");

        // Alice's private key and public key
        BigInteger alicePrivate = new BigInteger("6");
        BigInteger alicePublic = base.modPow(alicePrivate, prime);

        // Bob's private key and public key
        BigInteger bobPrivate = new BigInteger("15");
        BigInteger bobPublic = base.modPow(bobPrivate, prime);

        // Shared secret computation
        BigInteger aliceSharedSecret = bobPublic.modPow(alicePrivate, prime);
        BigInteger bobSharedSecret = alicePublic.modPow(bobPrivate, prime);

        // Display results
        System.out.println("Alice's private key: " + alicePrivate);
        System.out.println("Alice's public key: " + alicePublic);
        System.out.println("Bob's private key: " + bobPrivate);
        System.out.println("Bob's public key: " + bobPublic);
        System.out.println("Shared secret computed by Alice: " + aliceSharedSecret);
        System.out.println("Shared secret computed by Bob: " + bobSharedSecret);
    }
}

