package jakarta.tutorial.dukesbookstore.components;

import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.UICommand;
import jakarta.tutorial.dukesbookstore.listeners.AreaSelectedEvent;

/**
 * <p>{@link MapComponent} is a JavaServer Faces component that corresponds to a
 * client-side image map. It can have one or more children of type {@link AreaComponent}, each
 * representing hot spots, which a user can click on and mouse over.</p>
 *
 * <p>This component is a source of {@link AreaSelectedEvent} events, which are
 * fired whenever the current area is changed.</p>
 *
 * <p>Use of the javax.faces.component.StateHelper interface allows the use of
 * expressions and also makes it unnecessary to implement saveState() and restoreState().</p>
 */
@FacesComponent("DemoMap") public class MapComponent extends UICommand {

    private final String current = null;

    public MapComponent() {
        super();
    }

    /**
     * @return the alternate text label for the currently selected child {@link AreaComponent}
     */
    public String getCurrent() {
        return (String) getStateHelper().eval(PropertyKeys.current, null);
    }

    /**
     * <p>Set the alternate text label for the currently selected child. If this
     * is different from the previous value, fire an {@link AreaSelectedEvent} to interested
     * listeners.</p>
     *
     * @param current The new alternate text label
     */
    public void setCurrent(String current) {
        if (this.getParent() == null) {
            return;
        }

        String previous = (String) getStateHelper().get(current);
        getStateHelper().put(PropertyKeys.current, current);

        // Fire an {@link AreaSelectedEvent} if appropriate
        if ((previous == null) && (current == null)) {
            // do nothing
        } else if ((previous != null) && (current != null) && (previous.equals(current))) {
            // do nothing
        } else {
            this.queueEvent(new AreaSelectedEvent(this));
        }
    }

    /**
     * @return the component family for this component
     */
    @Override public String getFamily() {
        return ("Map");
    }

    private enum PropertyKeys {
        current;
    }
}
