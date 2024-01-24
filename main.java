import java.util.Scanner;

class Student{
	private String id;
	private String name;
	private int prf;
	private int dbm;

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPrf(int prf){
		this.prf = prf;
	}
	public int getPrf(){
		return prf;
	}
	public void setDbm(int dbm){
		this.dbm = dbm;
	}
	public int getDbm(){
		return dbm;
	}
}

class StudentList{
	Student[] student;
	int index;

	public StudentList() {
		student = new Student[0];
		index = -1;
	}
	
	public void add(Student data) {
		grow(student);
		student[++index] = data;
	}
	
	public void grow(Student[] oldArr){
		Student[] newArr = new Student[oldArr.length + 1];
		for(int i = 0;i < oldArr.length;i++){
			newArr[i] = oldArr[i];
		}
		student = newArr;
	}
}

public class main {

	static Scanner input = new Scanner(System.in);
	static StudentList sl = new StudentList();

	private final static void clearConsole(){
		final String os = System.getProperty("os.name");
		try {
			if (os.equals("Linux")) {
			System.out.print("\033\143");
		} else if (os.equals("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} else {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
		} catch (final Exception e) {
			//handle the exception
			System.err.println(e.getMessage());
		}
	}

	public static void deleteElement(Student[] oldArr,int index){
		Student[] newArr = new Student[oldArr.length-1];
		
		for(int i = 0,j = 0;i < oldArr.length;i++){
			if(i == index){
				continue;
			}
			newArr[j++] = oldArr[i];
		}
		sl.student = newArr;
	}


	public static void addStudent(){
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|\t\t\t\tADD NEW STUDENT\t\t\t\t|");
		System.out.println("+-----------------------------------------------------------------------+");
		
		char another = 'Y';
		boolean add;

		do{
			add = true;
			System.out.print("\nStudent ID : ");
			String id = new Scanner(System.in).next();

			for(int i = 0;i < sl.student.length;i++){
				if(sl.student[i].getId().equals(id)){
					add = false;
					break;
				} 
			}

			if(add == true){
				Student s1 = new Student();
				s1.setId(id);
	
				System.out.print("Student Name : ");
				String name = new Scanner(System.in).next();
				s1.setName(name);
				
				int prf , dbm;
				do{
					System.out.print("\nProgramming Fundamentals Marks : ");
					prf = input.nextInt();
					if(prf >= 0 && prf <= 100){
						 s1.setPrf(prf);
					} else {
						System.out.println("Invalid marks.please enter correct marks.");
					}
				}while(!(prf >= 0 && prf <= 100));			
				
				do{
					System.out.print("\nDatabase Management System marks : ");
					dbm = input.nextInt();
					if(dbm >= 0 && dbm <= 100){
						s1.setDbm(dbm);
					} else {
						System.out.println("Invalid marks.please enter correct marks.");
					}
				}while(!(dbm >= 0 && dbm <= 100));
				
				
				sl.add(s1);
				
					System.out.print("added successfully! Do you want to add new Student(Y/N)? ");
					another = input.next().toLowerCase().charAt(0);
		
			} else {
				System.out.println("This Student Id already exists!");
			}
		}while(add != true);
		
			
			switch(another){
				case 'y':
					clearConsole();
					addStudent();
					break;
				case 'n':
					clearConsole();
					homePage();
				default:
					System.out.println("Invalid option");
					System.exit(0);
			}
		
	}

	public static void updateStudentDetail(){
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|\t\t\t   UPDATE STUDENT DETAILS   \t\t\t|");
		System.out.println("+-----------------------------------------------------------------------+");
		
		char another = 'Y';
		boolean add;
		int index = -1;

		do{
			add = false;
			System.out.print("\nEnter Student ID : ");
			String id = new Scanner(System.in).next();

			for(int i = 0;i < sl.student.length;i++){
				if(sl.student[i].getId().equals(id)){
					add = true;
					index = i;
					break;
				} 
			}

			if(add == true){
				System.out.println("Student Name  : " + sl.student[index].getName());
				System.out.print("\nEnter the new student name : ");
				String name = input.next();
				sl.student[index].setName(name);
				System.out.println("\nStudent detail has been updated successfully.");
				System.out.print("Do you want to update another student details? (Y/n) ");
				another = input.next().toLowerCase().charAt(0);
			} else{
				System.out.println("This Student Id Invalid!");
			}
		}while(add != true);

		switch(another){
				case 'y':
					clearConsole();
					updateStudentDetail();
					break;
				case 'n':
					clearConsole();
					homePage();
				default:
					System.out.println("Invalid option");
					System.exit(0);
			}
	}

	public static void updateMarks(){
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|\t\t\t   UPDATE STUDENT MARKS   \t\t\t|");
		System.out.println("+-----------------------------------------------------------------------+");
		
		char another = 'Y';
		boolean add;
		int index = -1;

		do{
			add = false;
			System.out.print("\nEnter Student ID : ");
			String id = new Scanner(System.in).next();

			for(int i = 0;i < sl.student.length;i++){
				if(sl.student[i].getId().equals(id)){
					add = true;
					index = i;
					break;
				} 
			}

			if(add == true){
				System.out.println("Student Name  : " + sl.student[index].getName());
				System.out.println("\nProgramming Fundamentals Marks   : " + sl.student[index].getPrf());
				System.out.println("Database Management System marks : " + sl.student[index].getDbm());
				System.out.print("\nNew Programming Fundamentals Marks : ");
				int prf = input.nextInt();
				sl.student[index].setPrf(prf);
				System.out.print("New Database Management System marks : ");
				int dbm = input.nextInt();
				sl.student[index].setDbm(dbm);
				System.out.println("\nMarks has been updated successfully.");
				System.out.print("Do you want to update marks for another student? (Y/n) ");
				another = input.next().toLowerCase().charAt(0);
			} else{
				System.out.println("This Student Id Invalid!");
			}
		}while(add != true);

		switch(another){
			case 'y':
				clearConsole();
				updateMarks();
				break;
			case 'n':
				clearConsole();
				homePage();
			default:
				System.out.println("Invalid option");
				System.exit(0);
		}
	}

	public static void deleteStudent(){
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|\t\t\t\tDELETE STUDENT\t\t\t\t|");
		System.out.println("+-----------------------------------------------------------------------+");
		
		char another =  'Y';
		boolean delete;
		int index = 0;
		
		L1: do{
			delete = false;
			System.out.print("\nStudent ID : ");
			String id = input.next();
			
			for(int i = 0;i < sl.student.length;i++){
				if(sl.student[i].getId().equals(id)){
					delete = true;
					index = i;
					break;
				}
			}
			
			if(delete == true){
					deleteElement(sl.student,index);
					System.out.print("Deleted Successfully! Do you want to delete another student?(Y/N) ");
					another = input.next().toLowerCase().charAt(0);
					
				} else {
						System.out.print("Invalid Student Id. Do you want to search again? (Y/n)");
						another = input.next().toLowerCase().charAt(0);	
						switch(another){
							case 'y':
								continue L1;
							case 'n':
								clearConsole();
								homePage();
							default:
								System.out.println("Invalid option");
								System.exit(0);
						}
				}
			
		} while(delete != true);

		switch(another){
			case 'y':
				clearConsole();
				deleteStudent();
				break;
			case 'n':
				clearConsole();
				homePage();
			default:
				System.out.println("Invalid option");
				System.exit(0);
		}
	}
	
	public static void viewStudentDetail(){
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|\t\t\t   VIEW STUDENT DETAILS   \t\t\t|");
		System.out.println("+-----------------------------------------------------------------------+");
		
		
		System.out.println();
		System.out.printf("+--------------+-------------+-----------+------------+-----------+%n");
		System.out.printf("|  %s  |    %s     |    %s    |    %s     |   %s   |%n","Student ID","Name","PRF","DBM","Total");
		System.out.printf("+--------------+-------------+-----------+------------+-----------+%n");
		for(int i = 0;i < sl.student.length;i++){
			System.out.printf("|    %5s     | %10s  |    %3d    |     %3d    |    %3d    |%n",sl.student[i].getId(),sl.student[i].getName(),sl.student[i].getPrf(),sl.student[i].getDbm(),(sl.student[i].getPrf() + sl.student[i].getDbm()));
		}
		System.out.printf("+--------------+-------------+-----------+------------+-----------+%n");
		
		char menu = 'y';
		do{
			System.out.print("\nDo you want to go back to main menu (Y/n) ");
			menu = input.next().toLowerCase().charAt(0);

			switch(menu){
				case 'y':
					clearConsole();
					homePage();
					break;
			}
		}while(menu != 'y');
	}

	public static void homePage(){
		System.out.println("+-----------------------------------------------------------------------+");
		System.out.println("|\t\t  WELCOME TO GDSE MARKS MANAGEMENT SYSTEM  \t\t|");
		System.out.println("+-----------------------------------------------------------------------+");
				
		System.out.println("\n[1] Add New Student With Marks\t\t[2] Update Student Details");
		System.out.println("[3] Update Marks\t\t\t[4] Delete Student");
		System.out.println("[5] View Student Details\t\t[6] Exit System");
		System.out.print("\nEnter an option to continue > ");
		int option = new Scanner(System.in).nextInt();
		
		switch(option){
			case 1:
				clearConsole();
				addStudent();
				break;
			case 2:
				clearConsole();
				updateStudentDetail();
				break;
			case 3:
				clearConsole();
				updateMarks();
				break;
			case 4:
				clearConsole();
				deleteStudent();
				break;
			case 5:
			 	clearConsole();
				viewStudentDetail();
				break;
			case 6:
				clearConsole();
				System.exit(0);
				break;
			
		}
	}

	public static void main(String args[]){
		
	  
		homePage();
	}
}
