package dustinthewind.utils;

public interface EventListener<E> extends java.util.EventListener {
	public void handleEvent(E e);
}
