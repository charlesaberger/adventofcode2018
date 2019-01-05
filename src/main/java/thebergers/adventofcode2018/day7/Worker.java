package thebergers.adventofcode2018.day7;

public class Worker {

	private final Integer number;
	
	private final String name;
	
	private Step currentStep;
	
	public Worker(Integer number) {
		this.number = number;
		this.name = String.format("Worker %d", number);
	}

	public Step getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(Step currentStep) {
		this.currentStep = currentStep;
	}

	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Worker [name=" + name + ", currentStep=" + (currentStep != null ? currentStep : ".") + "]";
	}

	public boolean isAvailable() {
		return currentStep == null;
	}

	public void makeAvailable() {
		this.currentStep = null;
	}
}
