package mx.edu.uteq.idgs09.idgs09_01.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SecurityLogger {

    private static final Logger logger = LoggerFactory.getLogger("SECURITY");

    public void loginSuccess(String username) {
        logger.info(" Login successful for user: {}", username);
    }

    public void loginFailure(String username, String reason) {
        logger.warn(" Login failed for user: {} | Reason: {}", username, reason);
    }

    public void exceptionCaught(String endpoint, Exception e) {
        logger.error(" Exception at {}: {}", endpoint, e.getMessage(), e);
    }
}
