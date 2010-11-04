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

/**
 * Base exception used by "Kinoko" framework.
 * 
 * @author Alez
 * 
 */
public class KinokoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3222191749871690032L;

	private static String MESSAGE = "Internal error in Kinoko.";

	/**
	 * Initializes a new instance of the @see KinokoException class.
	 */
	public KinokoException() {
		super(MESSAGE);
	}

	/**
	 * Initializes a new instance of the @see KinokoException class with a specified error message.
	 * 
	 * @param message
	 *            A message that describes the error.
	 */
	public KinokoException(String message) {
		super(message);
	}

	/**
	 * Initializes a new instance of the @see KinokoException class with a reference to the inner
	 * exception that is the cause of this exception.
	 * 
	 * @param cause
	 *            The exception that is the cause of the current exception.
	 */
	public KinokoException(Throwable cause) {
		super(MESSAGE, cause);
	}

	/**
	 * Initializes a new instance of the @see KinokoException class with a specified error message
	 * and a reference to the inner exception that is the cause of this exception.
	 * 
	 * @param message
	 *            The error message that explains the reason for the exception.
	 * @param cause
	 *            The exception that is the cause of the current exception.
	 */
	public KinokoException(String message, Exception cause) {
		super(message, cause);
	}
}
