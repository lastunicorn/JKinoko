What is Kinoko?
Kinoko is a framework that is measuring the time in which a task is performed.

How the measurement is done? 
The specified task is run multiple times. For every run, the measured time is stored in an array and an average is calculated at the end.

How a task should look?
The task that Kinoko can test is a class that implements KinokoTask interface.
This interface has one method: run()
Kinoko will measure the time needed for thin method to run. 