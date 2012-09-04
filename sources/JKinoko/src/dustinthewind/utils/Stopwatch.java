// JKinoko
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

package dustinthewind.utils;

/**
 * Provides a set of methods and properties that you can use to accurately measure elapsed time. It
 * used the System.nanoTime() method. The instance members are not thread safe.
 * 
 * @author Alez
 * 
 */
public class Stopwatch {

	/**
	 * The time when the @see Stopwatch was started last time. If it was never started, this value
	 * is 0.
	 */
	private long startNano;

	/**
	 * The time when the @see Stopwatch was stopped last time. If it was never stopped, this value
	 * is 0.
	 */
	private long stopNano;

	/**
	 * The difference between the @see #stopNano and @see #startNano values. This value is
	 * calculated one and stored for subsequent usage.
	 */
	private long elapsedNano;

	/**
	 * A value indicating whether the @see Stopwatch timer is running.
	 */
	private boolean isRunning;

	/**
	 * Gets a value indicating whether the @see Stopwatch timer is running.
	 * 
	 * @return true if the @see Stopwatch instance is currently running and measuring elapsed time
	 *         for an interval; otherwise, false.
	 */
	public boolean IsRunning() {
		return isRunning;
	}

	/**
	 * Initializes a new instance of the @see Stopwatch class.
	 */
	public Stopwatch() {
		startNano = 0;
		stopNano = 0;
		elapsedNano = 0;
		isRunning = true;
	}

	/**
	 * Initializes a new @see Stopwatch instance, sets the elapsed time to zero, and starts
	 * measuring elapsed time.
	 * 
	 * @return A @see Stopwatch that has just begun measuring elapsed time.
	 */
	public static Stopwatch startNew() {
		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();
		return stopwatch;
	}

	/**
	 * Stops time interval measurement, resets the elapsed time to zero, and starts measuring
	 * elapsed time.
	 */
	public void start() {
		startNano = System.nanoTime();
		isRunning = true;
	}

	/**
	 * Stops measuring elapsed time for an interval.
	 */
	public void stop() {
		stopNano = System.nanoTime();
		elapsedNano = stopNano - startNano;
		isRunning = false;
	}

	/**
	 * Gets the total elapsed time measured by the current instance, in nanoseconds.
	 * 
	 * @return A long integer representing the total number of nanoseconds measured by the current
	 *         instance.
	 */
	public long getElapsedNanoseconds() {
		if (isRunning) {
			elapsedNano = System.nanoTime() - startNano;
		}
		return elapsedNano;
	}

	/**
	 * Gets the total elapsed time measured by the current instance, in milliseconds.
	 * 
	 * @return A long integer representing the total number of milliseconds measured by the current
	 *         instance.
	 */
	public double getElapsedMilliseconds() {
		if (isRunning) {
			elapsedNano = System.nanoTime() - startNano;
		}
		return elapsedNano / (double) 1000000;
	}
}
