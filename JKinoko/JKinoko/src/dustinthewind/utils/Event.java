package dustinthewind.utils;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

public class Event<L extends EventListener<E>, E extends EventObject> {

//	/**
//	 * The owner of the event.
//	 */
//	private Object owner;
//
//	/**
//	 * Initializes a new instance of the EventInternal class.
//	 * 
//	 * @param owner
//	 *            The owner of the event.
//	 */
//	public EventInternal(Object owner) {
//		if (owner == null)
//			throw new NullPointerException("'owner' parameter can not be null.");
//
//		this.owner = owner;
//	}

	/**
	 * The list of listeners for the event.
	 */
	private List<L> eventListeners = new ArrayList<L>();

	/**
	 * Adds a new listener for the event.
	 * 
	 * @param listener
	 *            The new listener to be added to the event.
	 */
	public synchronized void addEventListener(L listener) {
		eventListeners.add(listener);
	}

	/**
	 * Removes a listener from the event.
	 * 
	 * @param listener
	 *            The new listener to be removed from the event.
	 */
	public synchronized void removeEventListener(L listener) {
		eventListeners.remove(listener);
	}

	/**
	 * Raises the event.
	 * 
	 * @param event
	 *            The event data that will be sent to all the listeners.
	 */
	public synchronized void fireEvent(E event) {
		Iterator<L> i = eventListeners.iterator();
		while (i.hasNext()) {
			i.next().handleEvent(event);
		}
	}
}
