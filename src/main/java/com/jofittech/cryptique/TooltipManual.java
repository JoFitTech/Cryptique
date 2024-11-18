package com.jofittech.cryptique;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("tooltip-manual")
public class TooltipManual extends Div {

    @SuppressWarnings("unused")
    public TooltipManual() {
        TextField textField = new TextField();
        textField.setPlaceholder("Search");
        textField.setPrefixComponent(new Icon("lumo", "search"));
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        textField.setSuffixComponent(button);
        // tag::snippet[]
        textField.setTooltipText("Test");
        Tooltip tooltip = textField.getTooltip().withManual(true);
        button.addClickListener(event -> {
            tooltip.setOpened(!tooltip.isOpened());
        });
        // end::snippet[]
        add(textField);
    }

    public Tooltip createTooltipManual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTooltipManual'");
    }

}