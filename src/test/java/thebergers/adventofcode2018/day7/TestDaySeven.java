package thebergers.adventofcode2018.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

public class TestDaySeven {

	private static final Integer STEP_MIN_DURATION = 0;

	private List<String> tasks = new LinkedList<>();
	
	private Instructions instructions;
	
	private Worker worker;
	
	@Before
	public void setup() {
		tasks.add("Step F must be finished before step E can begin.");
		tasks.add("Step C must be finished before step A can begin.");
		tasks.add("Step B must be finished before step E can begin.");
		tasks.add("Step C must be finished before step F can begin.");
		tasks.add("Step A must be finished before step D can begin.");
		tasks.add("Step A must be finished before step B can begin.");
		tasks.add("Step D must be finished before step E can begin.");
		instructions = Instructions.getInstance(tasks, STEP_MIN_DURATION);
		worker = new Worker(1);
	}
	
	@Test
	public void testOrderTasks() {
		assertThat(instructions.orderSteps()).as("Determine order of execution of tasks").isEqualTo("CABDFE");
	}
	
	@Test
	public void testStepDuration() {
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(instructions.getStep("A").getDuration()).as("Step A").isEqualTo(1);
			softly.assertThat(instructions.getStep("B").getDuration()).as("Step B").isEqualTo(2);
			softly.assertThat(instructions.getStep("C").getDuration()).as("Step C").isEqualTo(3);
			softly.assertThat(instructions.getStep("D").getDuration()).as("Step D").isEqualTo(4);
			softly.assertThat(instructions.getStep("E").getDuration()).as("Step E").isEqualTo(5);
			softly.assertThat(instructions.getStep("F").getDuration()).as("Step F").isEqualTo(6);
		});
	}
	
	@Test
	public void testStepInProgressNotStarted() {
		Step step = instructions.getStep("A");
		assertThat(step.isInProgress()).as("Step is not in progress").isFalse();
	}
	@Test
	public void testStepInProgressStarted() {
		Step step = instructions.getStep("A");
		step.startWork(20, worker);
		assertThat(step.isInProgress()).as("Step is in progress").isTrue();
	}
	
	@Test
	public void testStepIsCompleted() {
		Step step = instructions.getStep("A");
		step.startWork(20, worker);
		step.markComplete();
		assertThat(step.isInProgress()).as("Step is complete").isFalse();
	}
	
	@Test
	public void testDurationHasNotElapsed() {
		Step step = instructions.getStep("C");
		step.startWork(0, worker);
		assertThat(step.durationHasElapsed(2)).as("Duration has not elapsed").isFalse();
	}
	
	@Test
	public void testDurationHasElapsed() {
		Step step = instructions.getStep("C");
		step.startWork(0, worker);
		assertThat(step.durationHasElapsed(3)).as("Duration has elapsed").isTrue();
	}
	
	@Test
	public void testCalculateDuration() {
		Integer numberOfWorkers = 2;
		assertThat(instructions.calculateDuration(numberOfWorkers)).as("Calculate task duration").isEqualTo(15);
	}
}
