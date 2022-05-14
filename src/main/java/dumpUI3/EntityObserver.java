package dumpUI3;

import java.util.List;

public interface EntityObserver<T extends Comparable<T>> {

	public void entityUpdated(List<T> items);

	public void entityAdded(List<T> items);

	public void entityRemoved(List<T> items);

	public abstract void entityRefresh();
}