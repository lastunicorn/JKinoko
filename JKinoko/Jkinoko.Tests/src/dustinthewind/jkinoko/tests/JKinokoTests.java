package dustinthewind.jkinoko.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dustinthewind.jkinoko.Kinoko;
import dustinthewind.jkinoko.KinokoException;
import dustinthewind.jkinoko.KinokoTask;

public class JKinokoTests {

	private Kinoko kinoko;

	@Before
	public void setUp() throws Exception {
		kinoko = new Kinoko();
	}

	@Test
	public void testConstructor() {

	}

	@Test
	public void testInitial_Task() {
		assertEquals(null, kinoko.getTask());
	}

	@Test
	public void TestInitial_TaskRunCount() {
		assertEquals(1, kinoko.getTaskRunCount());
	}

	@Test
	public void TestInitial_Result() {
		assertEquals(null, kinoko.getResult());
	}

	@Test
	public void TestTask_SetGet() {
		KinokoTask task = new KinokoTask() {
			@Override
			public void run() {
			}
		};
		kinoko.setTask(task);

		assertEquals(task, kinoko.getTask());
	}

	@Test
	public void TestTaskRunCount_SetGet() throws Exception {
		kinoko.setTaskRunCount(10);

		assertEquals(10, kinoko.getTaskRunCount());
	}

	@Test(expected = KinokoException.class)
	public void TestTaskRunCount_InvalidValue() throws Exception {
		kinoko.setTaskRunCount(0);
	}
}
