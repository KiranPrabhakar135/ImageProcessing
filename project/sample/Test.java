public class Test {
    public static void main(String[] args) {
        Crypter c = new Crypter("key.png"); // Key picture.
        
        //Crypt
        //c.encrypt("Hello i've just encrypted text into image!", "secret.png");
        
        //Decrypt
        System.out.println(c.deCrypt("secret.png"));
        
                
    }
}