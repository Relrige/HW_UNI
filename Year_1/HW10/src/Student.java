public class Student {

	/** 
	* Class constructor.
	*/
	public Student() {
		numberOfStudents++;
	}
	/** 
	* Class constructor with name and surname
	*/
	Student(String name, String surname) {
		this.name = name;
		this.surname = surname;
		numberOfStudents++;
	}
	/** 
	* Class constructor with everything
	*/
	Student(String name, String surname, int age, double avgMark, int course,
			String faculty) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.avgMark = avgMark;
		this.course = course;
		this.faculty = faculty;
		numberOfStudents++;
	}


	
	/**
    *
    * @return name of this student
    *
    */
   public String getName() {
       return name;
   }
   /**
   *
   * @set name of this student
   *
   */
  public void setName(String name) {
      this.name=name;
  }
  
   /**
   *
   * @set avg mark of this student
   *
   */
  public void setFaculty(String faculty) {
      this.faculty=faculty;
  }
	/**
    *
    * @return avg mark of this student
    *
    */
   public double getAvg() {
       return avgMark;
   }
   /**
   *
   * @return course number of this student
   *
   */
  public void setAvg(double avgMark) {
      this.avgMark=avgMark;
  }
    /**
     *
     * @return course number of this student
     *
     */
    public int getCourseNum() {
        return course;
    }
    /**
    *
    * @set course number of this student
    *
    */
   public void setCourseNum(int course) {
       this.course=course;
   }
    /**
     *
     * @return faculty of this student
     *
     */
    public String getFaculty() {
        return faculty;
    }


	
	
	
	 /**
     * @param faculty new faculty of student
     * @return change faculty
     *
     */
	   public void changeFaculty(String faculty){
	        this.faculty=faculty;
	        if (this.faculty.matches("")){
	            deleteStudent();
	        }
	    }
	   
	   /**
	     * @param cousre new courses student
	     * @return change faculty
	     *
	     */
	   public void changeCourse(){
		        course++;
		        if (course>4){
		            deleteStudent();
		        }
		    }
	   
	   /**
	     *
	     * @return delete all information about student
	     *
	     */
    private void deleteStudent(){
        name="";
        surname="";
        course=0;
        numberOfStudents--;
    }
    /**
    *
    * @param change abgMark;
    *
    */
	public void updateAverageMark(double newMark) {
        avgMark = newMark;
    }

	/**
    *
    * @return return number of stuents
    *
    */
    public static int getNumberOfStudents() {
        return numberOfStudents;
    }
    
    
    /**
    *
    * @return return all information about this student
    *
    */
	@Override
	public String toString() {
		return "Student: \n" + "name = " + name + ". Surname = " + surname
				+ '\n' + "age = " + age+". AvgMark= " + avgMark
				+ "\ncourseNum = " + course + ". Faculty = " + faculty;
	}
	
	private String faculty = "";
	public static int numberOfStudents = 0;
	private String name = "anonym";
	private String surname = "anonym";
	private int age;
	private double avgMark = 0;
	private int course = 1;
}
