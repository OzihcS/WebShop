package servlet.user;

import DTO.UserDTO;
import org.junit.Before;
import org.junit.Test;
import service.UserService;
import service.captcha.SessionCaptchaService;
import util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    private RegistrationServlet servlet;
    private UserDTO user;
    private static final String SOME_CAPTCHA = "asd4a";

    @Before
    public void init() {
        servlet = spy(new RegistrationServlet());
        initUser();

        when(servlet.getCaptchaService()).thenReturn( mock(SessionCaptchaService.class));
        when(servlet.getUserService()).thenReturn( mock(UserService.class));
    }

    @Test
    public void testWithTrueCaptchaVerification() throws ServletException, IOException {
        HttpServletRequest request = initRequest();
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(servlet.getCaptchaService().verifyCaptcha(any(), any())).thenReturn(true);
        when(servlet.getUserService().add(any())).thenReturn(true);

        servlet.doPost(request, response);

        verify(servlet.getUserService()).add(any());
    }

    @Test
    public void testWithFalseCaptchaVerification() throws ServletException, IOException {
        HttpServletRequest request = initRequest();
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(servlet.getCaptchaService().verifyCaptcha(any(), any())).thenReturn(false);

        servlet.doPost(request, response);

        verify(servlet.getUserService(), never()).add(any());
    }

    @Test
    public void testWithFalseUserValidation() throws ServletException, IOException {
        initUserWithWrongInputParams();
        HttpServletRequest request = initRequest();
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(servlet.getCaptchaService().verifyCaptcha(any(), any())).thenReturn(true);

        servlet.doPost(request, response);

        verify(servlet.getUserService(), never()).add(any());
    }


    @Test
    public void testWhenUserAlreadyExist() throws ServletException, IOException {
        HttpServletRequest request = initRequest();
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(servlet.getCaptchaService().verifyCaptcha(any(), any())).thenReturn(true);
        when(request.getRequestDispatcher(anyString())).thenReturn(mock(RequestDispatcher.class));

        servlet.getUserService().add(user.convertToDomain());
        servlet.doPost(request, response);

        verify(servlet.getUserService()).exist(any());
    }

    private void initUser() {
        user = new UserDTO("John", "Smith", "asd@asd.asd",
                "11111", "11111");
    }

    private void initUserWithWrongInputParams() {
        user = new UserDTO("123", "Smith",
                "asd@asd.asd", "11111", "11111");
    }


    private HttpServletRequest initRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter(Constants.Attributes.User.FIRST_NAME)).thenReturn(user.getFirstName());
        when(request.getParameter(Constants.Attributes.User.LAST_NAME)).thenReturn(user.getLastName());
        when(request.getParameter(Constants.Attributes.User.EMAIL)).thenReturn(user.getEmail());
        when(request.getParameter(Constants.Attributes.User.PASSWORD)).thenReturn(user.getPassword());
        when(request.getParameter(Constants.Attributes.User.PASSWORD_CONFIRMATION)).thenReturn(user.getPasswordConfirmation());
        when(request.getParameter(Constants.Attributes.CAPTCHA)).thenReturn(SOME_CAPTCHA);
        when(request.getSession()).thenReturn(mock(HttpSession.class));

        return request;
    }

}