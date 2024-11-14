package com.jofittech.cryptique;

import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("login-overlay-header")
public class LoginOverlayHeader extends Div {

    public LoginOverlayHeader() {
        // tag::snippet[]
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setTitle("Cryptique");
        loginOverlay.setDescription("Built with ♥");
        // end::snippet[]
        add(loginOverlay);
        loginOverlay.setOpened(true);
        // Prevent the example from stealing focus when browsing the
        // documentation
        loginOverlay.getElement().setAttribute("no-autofocus", "");
    }

}