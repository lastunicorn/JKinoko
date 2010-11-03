package dustinthewind.jkinoko.example;

import java.text.NumberFormat;

import dustinthewind.jkinoko.AfterTaskRunEventListener;
import dustinthewind.jkinoko.AfterTaskRunEventObject;
import dustinthewind.jkinoko.BeforeTaskRunEventListener;
import dustinthewind.jkinoko.BeforeTaskRunEventObject;
import dustinthewind.jkinoko.Kinoko;
import dustinthewind.jkinoko.KinokoResult;
import dustinthewind.jkinoko.KinokoTask;

/**
 * An example of usage for @see Kinoko class.
 * 
 * @author Alez
 * 
 */
public class Example {

	private static int sleepTime = 100;
	private static int repeatCount = 100;

	/**
	 * The main entry point of the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setGroupingUsed(true);

		System.out.println("Java Kinoko Usage Example");
		System.out.println("===============================================================================");
		System.out.println(String.format("This example is running a task that just sleeps for %s milliseconds.", intFormat.format(sleepTime)));
		System.out.println("Kinoko will measure a time a little bigger then the right one (around");
		System.out.println("1 milisecond bigger). It represents the measurement error cumulated from:");
		System.out.println("  1) the time necessary to actually call the task method;");
		System.out.println("  2) the measurement error of the Stopwatch class;");
		System.out.println("  3) the error of the Thread.Sleep() method");
		System.out.println();

		try {
			Kinoko kinoko = new Kinoko();
			kinoko.addBeforeTaskRunEventListener(new Kinoko_BeforeTaskRun());
			kinoko.addAfterTaskRunEventListener(new Kinoko_AfterTaskRun());
			kinoko.setTaskRunCount(repeatCount);
			kinoko.setTask(new Task());
			kinoko.Run();

			KinokoResult result = kinoko.getResult();

			System.out.println();
			System.out.println(String.format("Avarage time: %.2f milisec", result.getAverage()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Pause();
	}

	private static class Task implements KinokoTask {
		@Override
		public void run() {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static class Kinoko_BeforeTaskRun implements BeforeTaskRunEventListener {
		@Override
		public void handleEvent(BeforeTaskRunEventObject e) {
			System.out.print(String.format("Running: %2d", e.getStepIndex()));
		}
	}

	private static class Kinoko_AfterTaskRun implements AfterTaskRunEventListener {
		@Override
		public void handleEvent(AfterTaskRunEventObject e) {
			System.out.println(String.format(" - %7.2f", e.getTime()));
		}
	}

	private static void Pause() {
		try {
			System.out.println();
			System.out.print("Press any key to continue...");
			System.in.read();
			System.out.println();
		} catch (Exception ex) {
		}
	}
}
