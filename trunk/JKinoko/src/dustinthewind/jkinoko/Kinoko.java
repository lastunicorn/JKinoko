// Kinoko
// Copyright (C) 2010 Dust in the Wind
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package dustinthewind.jkinoko;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dustinthewind.utils.Stopwatch;

/**
 * Runs a task and measures the time necessary to finish. The task is run multiple times and an
 * average is calculated.
 * 
 * @author Alez
 * 
 */
public class Kinoko {

	/**
	 * The list of listeners for the AfterTaskRun event.
	 */
	private List<AfterTaskRunEventListener> afterTaskRunEventListeners = new ArrayList<AfterTaskRunEventListener>();

	/**
	 * Adds a new listener for the AfterTaskRun event.
	 * 
	 * @param listener
	 *            The new listener to be added to the AfterTaskRun event.
	 */
	public synchronized void addAfterTaskRunEventListener(AfterTaskRunEventListener listener) {
		afterTaskRunEventListeners.add(listener);
	}

	/**
	 * Removes a listener from the AfterTaskRun event.
	 * 
	 * @param listener
	 *            The new listener to be removed from the AfterTaskRun event.
	 */
	public synchronized void removeAfterTaskRunEventListener(AfterTaskRunEventListener listener) {
		afterTaskRunEventListeners.remove(listener);
	}

	/**
	 * Raises the AfterTaskRun event.
	 * 
	 * @param event
	 *            The event data that will be sent to all the listeners.
	 */
	private synchronized void fireAfterTaskRunEvent(AfterTaskRunEventObject event) {
		Iterator<AfterTaskRunEventListener> i = afterTaskRunEventListeners.iterator();
		while (i.hasNext()) {
			i.next().handleEvent(event);
		}
	}

	/**
	 * The list of listeners for the BeforeTaskRun event.
	 */
	private List<BeforeTaskRunEventListener> beforeTaskRunEventListeners = new ArrayList<BeforeTaskRunEventListener>();

	/**
	 * Adds a new listener for the BeforeTaskRun event.
	 * 
	 * @param listener
	 *            The new listener to be added to the BeforeTaskRun event.
	 */
	public synchronized void addBeforeTaskRunEventListener(BeforeTaskRunEventListener listener) {
		beforeTaskRunEventListeners.add(listener);
	}

	/**
	 * Removes a listener from the BeforeTaskRun event.
	 * 
	 * @param listener
	 *            The new listener to be removed from the BeforeTaskRun event.
	 */
	public synchronized void removeBeforeTaskRunEventListener(BeforeTaskRunEventListener listener) {
		beforeTaskRunEventListeners.remove(listener);
	}

	/**
	 * Raises the BeforeTaskRun event.
	 * 
	 * @param event
	 *            The event data that will be sent to all the listeners.
	 */
	private synchronized void fireBeforeTaskRunEvent(BeforeTaskRunEventObject event) {
		Iterator<BeforeTaskRunEventListener> i = beforeTaskRunEventListeners.iterator();
		while (i.hasNext()) {
			i.next().handleEvent(event);
		}
	}

	
	/**
	 * The task that is tested by Kinoko.
	 */
	private KinokoTask task;

	/**
	 * Gets the task that is tested by Kinoko.
	 * 
	 * @return An KinokoTask object representing the task that is tested by Kinoko.
	 */
	public KinokoTask getTask() {
		return task;
	}

	/**
	 * Sets the task that will be tested by Kinoko.
	 * 
	 * @param value
	 *            An KinokoTask object representing the task that will be tested by Kinoko.
	 */
	public void setTask(KinokoTask value) {
		task = value;
	}

	/**
	 * The number of times the task is run within the test. (To minimize the measurement errors.)
	 */
	private int taskRunCount = 1;

	/**
	 * Gets the number of times the task is run within the test. The task should be run multiple
	 * times to minimize the measurement errors.
	 * 
	 * @return The number of times the task is run.
	 */
	public int getTaskRunCount() {
		return taskRunCount;
	}

	/**
	 * Sets the number of times the task is run within the test. The task should be run multiple
	 * times to minimize the measurement errors.
	 * 
	 * @param value
	 *            The number of times the task is run.
	 * @throws Exception
	 *             The value is less then 1.
	 */
	public void setTaskRunCount(int value) throws Exception {
		if (value < 1)
			throw new Exception("The task run count should be an integer greater then 0.");
		taskRunCount = value;
	}

	/**
	 * The results of the test. It is null if no test was run.
	 */
	private KinokoResult result;

	/**
	 * Gets the results of the test. It is null if no test was run.
	 * 
	 * @return An KinokoResult object containing the test's results.
	 */
	public KinokoResult getResult() {
		return result;
	}

	/**
	 * Runs the task and measures the times.
	 * 
	 * @throws Exception
	 *             No task was set to be tested.
	 */
	public void Run() throws Exception {
		if (task == null)
			throw new Exception("No task was set to be tested.");

		KinokoResult result = new KinokoResult();

		// The Task is run multiple times and then an average is calculated.

		for (int i = 0; i < taskRunCount; i++) {
			// Announce that the Task is about to be run.
			fireBeforeTaskRunEvent(new BeforeTaskRunEventObject(this, i));

			// Run the Task.
			Stopwatch stopwatch = Stopwatch.startNew();
			task.run();
			stopwatch.stop();

			// Store the time in which the task run.
			double milis = stopwatch.getElapsedMilliseconds();
			result.addValue(milis);

			// Announce that the Task was run.
			fireAfterTaskRunEvent(new AfterTaskRunEventObject(this, i, milis));
		}

		result.calculate();
		this.result = result;
	}
}
