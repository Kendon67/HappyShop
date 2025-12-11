package ci553.happyshop.client.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class LoginViewTest {

    @Test
    void startLogin() {
    }

    @Test
    void userLoginPage() {
        assertAll(() -> assertDoesNotThrow(this::userLoginPage));
    }

    @Test
    void openCustomerClient() {;
    }
}