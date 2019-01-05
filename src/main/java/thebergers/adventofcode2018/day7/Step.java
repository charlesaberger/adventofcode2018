package thebergers.adventofcode2018.day7;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Step implements Comparable<Step> {
	
	private static final Integer ASCII_OFFSET = 64;

	private final String name;
	
	private final Integer minDuration;
	
	private List<Step> prerequisites = new LinkedList<>();
	
	private List<Step> doAfterComplete = new LinkedList<>();
	
	private State state = State.READY;

	private Integer startSecond = null;
	
	private Worker worker;
	
	public Step(String name, Integer minDuration) {
		this.name = name;
		this.minDuration = minDuration;
	}

	public void reset() {
		state = State.READY;
		startSecond = null;
		worker = null;
	}

	public String getName() {
		return name;
	}

	public List<Step> getPrerequisites() {
		return prerequisites;
	}
	
	public void addPrerequisiteStep(Step step) {
		prerequisites.add(step);
	}
	
	public boolean isPrerequisite(Step step) {
		return prerequisites.contains(step);
	}
	
	public boolean hasPrerequisites() {
		return !prerequisites.isEmpty();
	}

	public Step getAvailablePrerequisite() {
		Collections.sort(prerequisites, Collections.reverseOrder());
		for (Step step : prerequisites) {
			if (step.isAvailable()) {
				return step;
			}
		}
		return null;
	}

	public void addFollowOnStep(Step step) {
		doAfterComplete.add(step);
	}

	public List<Step> getDoAfterComplete() {
		return doAfterComplete;
	}
	
	public boolean isAvailable() {
		if (isComplete() || getStartSecond() != null) {
			return false;
		}
		for (Step step : prerequisites) {
			if (!step.isComplete()) {
				return false;
			}
		}
		return true;
	}

	public boolean isComplete() {
		return this.state == State.COMPLETE;
	}
	
	public void markComplete() {
		this.state = State.COMPLETE;
	}

	public boolean hasFollowOns() {
		for (Step step : doAfterComplete) {
			if (step.isAvailable()) {
				return true;
			}
		}
		return false;
	}

	public Step getNextStep() {
		Collections.sort(doAfterComplete, Collections.reverseOrder());
		for (Step step : doAfterComplete) {
			if (step.isAvailable()) {
				return step;
			}
		}
		return null;
	}

	public Integer getDuration() {
		Character ch = name.charAt(0);
		return minDuration + ((int)ch - ASCII_OFFSET);
	}

	public void startWork(Integer second, Worker worker) {
		this.startSecond = second;
		this.state = State.STARTED;
		this.worker = worker;
	}
	
	public Integer getStartSecond() {
		return startSecond;
	}

	public boolean isInProgress() {
		return state == State.STARTED;
	}

	public boolean durationHasElapsed(Integer secondsElapsed) {
		if (!isInProgress()) {
			return false;
		}
		return (secondsElapsed - getDuration() >= getStartSecond());
	}

	public void completeWork() {
		this.markComplete();
		this.worker.makeAvailable();
	}

	@Override
	public int compareTo(Step o) {
		return o.getName().compareTo(this.name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Step [name=" + name + "]";
	}
	
	private enum State {
		READY,
		STARTED,
		COMPLETE;
	}
}
