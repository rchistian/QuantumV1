package com.QuantumV1.QuantumV1;

import javax.servlet.annotation.WebServlet;

import com.modulo.componentes.ByosImagenes;
import com.modulo.inicio.moduloEncendido;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("QuantumV1")
public class QuantumV1 extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		Image FondoPantalla = new Image();
		FondoPantalla.setSizeFull();
		
		FondoPantalla.setIcon(ByosImagenes.icon[111]);
		layout.addComponent(FondoPantalla);
		setContent(layout);
		new moduloEncendido().openWindows();
		
    }

    @WebServlet(urlPatterns = "/*", name = "QuantumV1Servlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = QuantumV1.class, productionMode = false)
    public static class QuantumV1Servlet extends VaadinServlet {
    }
}
