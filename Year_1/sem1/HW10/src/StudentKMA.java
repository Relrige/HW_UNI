
public class StudentKMA extends Student{
	/**
     * Default constructor for the {@code StudentKMA} class. Initializes the OOP and Networks knowledge levels to 0.
     */
	public StudentKMA() {
		super();
		oopKnowledgeLevel=0;
		networksKnowledgeLevel=0;
	}
    /**
     * Constructor for the {@code StudentKMA} class with name and surname parameters. Initializes the OOP and Networks knowledge levels to 0.
     *
     * @param name     The student's name.
     * @param surname  The student's surname.
     */
	public StudentKMA(String name,String surname) {
		super(name, surname);
		oopKnowledgeLevel=0;
		networksKnowledgeLevel=0;
	}
	 /**
     * Constructor for the {@code StudentKMA} class with additional parameters. Initializes the OOP and Networks knowledge levels to 0.
     *
     * @param name               The student's name.
     * @param surname            The student's surname.
     * @param age                The student's age.
     * @param avg                The student's average grade.
     * @param course             The student's course.
     * @param faculty            The student's faculty.
     */
	public StudentKMA(String name,String surname,int age,double avg,int course,String faculty) {
		super(name, surname,age,avg,course,faculty);
		oopKnowledgeLevel=0;
		networksKnowledgeLevel=0;
	}

	
    /**
    *
    * @param change oppKnowledge;
    *
    */
	public void updateOppKnowledge(int oppKnowledge) {
		oopKnowledgeLevel = oppKnowledge;
    }
	
    /**
    *
    * @param change networkKnowledge;
    *
    */
	public void updateNetworkKnowledge(int networkKnowledge) {
		networksKnowledgeLevel = networkKnowledge;
    }
	 /**
     * Increases the knowledge level in Object-Oriented Programming (OOP) by a specified amount.
     *
     * @param increaseAmount The amount by which to increase the knowledge level in OOP.
     */
    public void increaseOopKnowledge(int increaseAmount) {
        oopKnowledgeLevel += increaseAmount;
    }

    /**
     * Increases the knowledge level in Networks by a specified amount.
     *
     * @param increaseAmount The amount by which to increase the knowledge level in Networks.
     */
    public void increaseNetworksKnowledge(int increaseAmount) {
        networksKnowledgeLevel += increaseAmount;
    }
	
	
	
	
	/**
    *
    * @return return all information about this KMA student
    *
    */
	@Override
	public String toString(){
		return "Студент - " + getName()+", має середній бал " + getAvg()+", прослухав - " + getCourseNum()+" курсів. Знання ООП - "+oopKnowledgeLevel+", знання мережних технологій - "+networksKnowledgeLevel;
	}
	
	private int oopKnowledgeLevel;
	private int networksKnowledgeLevel;
}
