package com.powerliftsafesolution.appaid.ws.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PasswordUtils {

    private final int LOG_ROUNDS = 12;

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(this.LOG_ROUNDS));
    }

    public boolean verifyPassword(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }

    public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        if (BCrypt.checkpw(password, hash)) {
            int rounds = getRounds(hash);
            // It might be smart to only allow increasing the rounds.
            // If someone makes a mistake the ability to undo it would be nice though.
            if (rounds < LOG_ROUNDS) {
                String newHash = this.hashPassword(password);
                return updateFunc.apply(newHash);
            }
            return true;
        }
        return false;
    }

    private int getRounds(String salt) {
        char minor = (char)0;
        int off = 0;

        if (salt.charAt(0) != '$' || salt.charAt(1) != '2')
            throw new IllegalArgumentException ("Invalid salt version");
        if (salt.charAt(2) == '$')
            off = 3;
        else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$')
                throw new IllegalArgumentException ("Invalid salt revision");
            off = 4;
        }

        // Extract number of rounds
        if (salt.charAt(off + 2) > '$')
            throw new IllegalArgumentException ("Missing salt rounds");
        return Integer.parseInt(salt.substring(off, off + 2));
    }
}
