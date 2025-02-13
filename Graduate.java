package GUIExample;

public class Graduate extends Student{
	
	private String program;

	public Graduate(String name, int id, String program) {
		super(name, id);
		this.program = program;
	}

	@Override
	public String toString() {
		return "Graduate: "+getName()+", ID: "+getId()+", Program: "+this.program;
	}

	public String getProgram() {
		return program;
	}


}
