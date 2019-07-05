package org.apache.commons.configuration2.event;


public class BaseEventSourceData implements Cloneable {
	private final Object lockDetailEventsCount = new Object();
	private int detailEvents;

	/**
	* Determines whether detail events should be generated. If enabled, some methods can generate multiple update events. Note that this method records the number of calls, i.e. if for instance {@code  setDetailEvents(false)}  was called three times, you will have to invoke the method as often to enable the details.
	* @param enable  a flag if detail events should be enabled or disabled
	*/
	public void setDetailEvents(final boolean enable) {
		synchronized (lockDetailEventsCount) {
			if (enable) {
				detailEvents++;
			} else {
				detailEvents--;
			}
		}
	}

	/**
	* Helper method for checking the current counter for detail events. This method checks whether the counter is greater than the passed in limit.
	* @param limit  the limit to be compared to
	* @return  <b>true</b> if the counter is greater than the limit, <b>false</b> otherwise
	*/
	public boolean checkDetailEvents(final int limit) {
		synchronized (lockDetailEventsCount) {
			return detailEvents > limit;
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return (BaseEventSourceData) super.clone();
	}
}