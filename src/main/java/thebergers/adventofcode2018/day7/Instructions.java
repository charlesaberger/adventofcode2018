package thebergers.adventofcode2018.day7;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Instructions {

	private static final Logger LOG = LoggerFactory.getLogger(Instructions.class);
	
	private Map<String, Step> allSteps;
	
	private Map<Integer, Worker> workers;

	private Map<String, Step> readySteps = new HashMap<>();
	
	private Map<String, Step> startedSteps = new HashMap<>();
	
	private List<Step> completedSteps = new LinkedList<>();
	
	private Instructions() {
		this.allSteps = new HashMap<>();
		this.workers = new HashMap<>();
	}
	
	public static Instructions getInstance(List<String> stepList, Integer stepMinDuration) {
		Instructions instructions = new Instructions();
		for (String stepStr : stepList) {
			String[] parts = stepStr.split(" ");
			String firstStepName = parts[1];
			String secondStepName = parts[7];
			Step firstStep = instructions.getStep(firstStepName);
			if (firstStep == null) {
				firstStep = new Step(firstStepName, stepMinDuration);
				instructions.addStep(firstStep);
			}
			Step secondStep = instructions.getStep(secondStepName);
			if (secondStep == null) {
				secondStep = new Step(secondStepName, stepMinDuration);
				instructions.addStep(secondStep);
			}
			firstStep.addFollowOnStep(secondStep);
			secondStep.addPrerequisiteStep(firstStep);
		}
		return instructions;
	}
	
	public Step getStep(String stepName) {
		return allSteps.get(stepName);
	}

	public void addStep(Step step) {
		allSteps.put(step.getName(), step);
		readySteps.put(step.getName(), step);
	}

	public String orderSteps() {
		StringBuilder result = new StringBuilder();
		while (true) {
			Step nextStep = findNextAvailableStep();
			if (nextStep == null) {
				break;
			}
			result.append(nextStep.getName());
			nextStep.markComplete();
		}
		return result.toString();
	}

	private Step findNextAvailableStep() {
		List<Step> availableSteps = new LinkedList<>();
		Set<String> allStepNames = allSteps.keySet();
		for (String stepName : allStepNames) {
			Step step = allSteps.get(stepName);
			if (step.isAvailable()) {
				availableSteps.add(step);
			}
		}
		if (availableSteps.isEmpty()) {
			return null;
		}
		Collections.sort(availableSteps, Collections.reverseOrder());
		return availableSteps.get(0);
	}

	public Integer calculateDuration(Integer numberOfWorkers) {
		resetSteps();
		Integer secondsElapsed = 0;
		for (Integer i = 1; i <= numberOfWorkers; i++) {
			workers.put(i, new Worker(i));
		}
		String completedStepsStr = "";
		while (true) {
			completedStepsStr = checkForCompletedSteps(secondsElapsed);
			Queue<Step> availableSteps = findReadySteps();
			if (availableSteps.isEmpty() && allStepsComplete()) {
				LOG.info("Second: {}, {}, Done: {}", secondsElapsed, whoseDoingWhat(), completedStepsStr);
				break;
			}
			Queue<Worker> availableWorkers = findAvailableWorkers(availableSteps.size());
			allocateStepsToWorkers(availableSteps, availableWorkers, secondsElapsed);
			LOG.info("Second: {}, {}, Done: {}", secondsElapsed, whoseDoingWhat(), completedStepsStr);
			secondsElapsed++;
		}
		return secondsElapsed;
	}

	private void resetSteps() {
		Set<String> allStepNames = allSteps.keySet();
		allStepNames.forEach(stepName -> allSteps.get(stepName).reset() );
	}
	
	private boolean allStepsComplete() {
		return completedSteps.size() == allSteps.size();
	}

	private String checkForCompletedSteps(Integer secondsElapsed) {
		Set<String> stepNames = startedSteps.keySet();
		List<Step> stepsToRemove = new LinkedList<>();
		for (String stepName : stepNames) {
			Step step = startedSteps.get(stepName);
			if (step.isInProgress() && step.durationHasElapsed(secondsElapsed)) {
				step.completeWork();
				completedSteps.add(step);
				stepsToRemove.add(step);
			}
		}
		for (Step step : stepsToRemove) {
			startedSteps.remove(step.getName());
		}
		StringBuilder completedStepsStr = new StringBuilder();
		for (Step step : completedSteps) {
			completedStepsStr.append(step.getName());
		}
		return completedStepsStr.toString();
	}

	private Queue<Step> findReadySteps() {
		List<Step> availableSteps = new LinkedList<>();
		Set<String> readyStepNames = readySteps.keySet();
		for (String stepName : readyStepNames) {
			Step step = readySteps.get(stepName);
			if (step.isAvailable()) {
				availableSteps.add(step);
			}
		}
		Collections.sort(availableSteps, Collections.reverseOrder());
		Queue<Step> stepQueue = new LinkedList<>();
		availableSteps.forEach(step -> stepQueue.add(step));
		return stepQueue;
	}

	private Queue<Worker> findAvailableWorkers(Integer numRequired) {
		Set<Integer> workerIds = workers.keySet();
		Queue<Worker> availableWorkers = new LinkedList<>();
		for (Integer workerId : workerIds) {
			Worker worker = workers.get(workerId);
			if (worker.isAvailable()) {
				availableWorkers.add(worker);
			}
			if (availableWorkers.size() == numRequired) {
				break;
			}
		}
		return availableWorkers;
	}

	private void allocateStepsToWorkers(Queue<Step> steps, Queue<Worker> workers, Integer second) {
		while (true) {
			Step step = steps.poll();
			Worker worker = workers.poll();
			if (step == null || worker == null) {
				break;
			}
			worker.setCurrentStep(step);
			step.startWork(second, worker);
			readySteps.remove(step.getName());
			startedSteps.put(step.getName(), step);
		}
	}

	private String whoseDoingWhat() {
		Set<Integer> workerIds = workers.keySet();
		StringBuilder tasks = new StringBuilder();
		int i = 0;
		for (Integer workerId : workerIds) {
			tasks.append(String.format("%s%s", (i > 0 ? ", " : ""), workers.get(workerId)));
			i++;
		}
		return tasks.toString();
	}
}
