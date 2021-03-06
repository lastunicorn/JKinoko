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

package dustinthewind.jkinoko;

import java.util.ArrayList;

import dustinthewind.utils.Math;

/**
 * Contains the results of a test performed by Kinoko.
 * 
 * @author Alez
 * 
 */
public class KinokoResult {

	/**
	 * The list of time measurements for every run of the task.
	 */
	private ArrayList<Double> measurements;

	/**
	 * The average value of the values contained by the 'times' list. This field is calculated by
	 * the calculate() method.
	 */
	private double average;

	/**
	 * Gets the average value of the values contained by the 'times' list. Before calling this
	 * method call the 'calculate()' method.
	 * 
	 * @return A double value representing the calculated average from the measured times.
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * Initializes a new instance of the KinokoResult class.
	 */
	public KinokoResult() {
		measurements = new ArrayList<Double>();
		average = 0D;
	}

	/**
	 * Adds a new time measurement to the list.
	 * 
	 * @param time
	 *            The time in milliseconds.
	 */
	public void addMeasurement(double time) {
		measurements.add(time);
	}

	/**
	 * Calculates the average of the values from the time measurements list.
	 */
	public void calculate() {
		average = Math.average(measurements.toArray(new Double[0]));
	}
}
