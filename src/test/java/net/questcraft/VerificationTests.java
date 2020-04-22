package net.questcraft;

import net.questcraft.account.Account;
import net.questcraft.verifier.VerificationUtil;
import org.junit.jupiter.api.Test;

import javax.mail.SendFailedException;

public class VerificationTests {
    @Test
    public void testEmailVerify() throws SendFailedException, WebError {
        VerificationUtil util = VerificationUtil.getInstance();
        Account account = new Account("Durgan", "ChestlyPassword", "Chestly", "", "none", "durganmcbroom@gmail.com", "", "durganmcbroom@gmail.com");
        util.verifyEmail(account);
    }

}
