import java.util.*;

class Student {

}

class Section {
    private String name;
    //private List<Student> students;

    public Section(String name) {
        this.name = name;
        //students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }


    public String toString() {
        return "Section: " + name;
    }

	public void updateName(String newSectionName) {
		name = newSectionName;
		
	}
}

class Department {
    private String name;
    private Map<String, Section> sections;

    public Department(String name) {
        this.name = name;
        sections = new HashMap<>();
    }

    public void addSection(String sectionName) {
        sections.put(sectionName, new Section(sectionName));
    }

    public Map<String, Section> getSections() {
        return sections;
    }

    public String getName() {
        return name;
    }
    
    public void updateName(String newSectionName) {
		name = newSectionName;
		
	}

    public String toString() {
        return "Department: " + name + ", Sections: " + sections.size();
    }
}

class College {
    private String name;
    private Map<String, Department> departments;

    public College(String name) {
        this.name = name;
        departments = new HashMap<>();
    }

    public void addDepartment(String departmentName) {
        departments.put(departmentName, new Department(departmentName));
    }

    public Map<String, Department> getDepartments() {
        return departments;
    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }


    public String toString() {
        return "College: " + name + ", Departments: " + departments.size();
    }
}

class University {
    private List<College> colleges;

    public University() {
        colleges = new ArrayList<>();
    }

    public void addCollege(String collegeName) {
        colleges.add(new College(collegeName));
    }

    public List<College> getColleges() {
        return colleges;
    }

    public String toString() {
        return "University: " + colleges.size() + " colleges";
    }
}

public class UniversityManagementSystem {
    public static void main(String[] args) {
        University university = new University();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("University Management System");
            System.out.println("1. Add College");
            System.out.println("2. List Colleges");
            System.out.println("3. Update College");
            System.out.println("4. Delete College");
            System.out.println("5. Manage Departments");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
            case 1:
                System.out.print("Enter college name: ");
                String collegeName = scanner.nextLine();
                university.addCollege(collegeName);
                System.out.println("College added successfully.");
                break;
            case 2:
                System.out.println("Colleges:");
                List<College> colleges = university.getColleges();
                for (int i = 0; i < colleges.size(); i++) {
                    System.out.println((i + 1) + ". " + colleges.get(i).getName());
                }
                break;
            case 3:
                System.out.print("Enter college index to update: ");
                int collegeIndex = scanner.nextInt();
                scanner.nextLine(); 
                if (collegeIndex >= 1 && collegeIndex <= university.getColleges().size()) {
                    System.out.print("Enter new college name: ");
                    String newCollegeName = scanner.nextLine();
                    College collegeToUpdate = university.getColleges().get(collegeIndex - 1);
                    collegeToUpdate.setName(newCollegeName);
                    System.out.println("College updated successfully.");
                } else {
                    System.out.println("Invalid college index.");
                }
                break;
            case 4:
                System.out.print("Enter college index to delete: ");
                int collegeIndexToDelete = scanner.nextInt();
                scanner.nextLine();
                if (collegeIndexToDelete >= 1 && collegeIndexToDelete <= university.getColleges().size()) {
                    university.getColleges().remove(collegeIndexToDelete - 1);
                    System.out.println("College deleted successfully.");
                } else {
                    System.out.println("Invalid college index.");
                }
                break;
                case 5:
                    manageDepartments(scanner, university);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void manageDepartments(Scanner scanner, University university) {
        System.out.println("Select a college:");
        List<College> colleges = university.getColleges();
        for (int i = 0; i < colleges.size(); i++) {
            System.out.println((i + 1) + ". " + colleges.get(i).getName());
        }
        System.out.print("Enter college index: ");
        int collegeIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (collegeIndex >= 1 && collegeIndex <= colleges.size()) {
            College college = colleges.get(collegeIndex - 1);
            while (true) {
                System.out.println("College: " + college.getName());
                System.out.println("1. Add Department");
                System.out.println("2. List Departments");
                System.out.println("3. Update Department");
                System.out.println("4. Delete Department");
                System.out.println("5. Managae Sections");
                System.out.println("6. Back to Main Menu");
                System.out.print("Select an option: ");
                int departmentChoice = scanner.nextInt();
                scanner.nextLine(); 
                Map<String, Department> departments = college.getDepartments();
                switch (departmentChoice) {
                    case 1:
                        System.out.print("Enter department name: ");
                        String departmentName = scanner.nextLine();
                        college.addDepartment(departmentName);
                        System.out.println("Department added successfully.");
                        break;
                    case 2:
                  
                        for (Department department : departments.values()) {
                            System.out.println("Department: " + department.getName());
                        }
                        break;
                    case 3:
                        System.out.print("Enter department name to update: ");
                        String oldDepartmentName = scanner.nextLine();
                        System.out.print("Enter new department name: ");
                        String newDepartmentName = scanner.nextLine();

                        departments = college.getDepartments();
                        if (departments.containsKey(oldDepartmentName)) {
                            Department departmentToUpdate = departments.get(oldDepartmentName);
                            departmentToUpdate.updateName(newDepartmentName);
                            System.out.println("Department updated successfully.");
                        } else {
                            System.out.println("Department not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter department name to delete: ");
                        String departmentToDelete = scanner.nextLine();
                        if (departments.containsKey(departmentToDelete)) {
                            departments.remove(departmentToDelete);
                            System.out.println("Department deleted successfully.");
                        } else {
                            System.out.println("Department not found.");
                        }
                        break;
                    case 5:
                        manageSections(scanner, college);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            }
        } else {
            System.out.println("Invalid college index.");
        }
    }

private static void manageSections(Scanner scanner, College college) {
    System.out.println("Select a department:");
    Map<String, Department> departments = college.getDepartments();
    List<String> departmentNames = new ArrayList<>(departments.keySet());
    for (int i = 0; i < departmentNames.size(); i++) {
        System.out.println((i + 1) + ". " + departmentNames.get(i));
    }
    System.out.print("Enter department index: ");
    int departmentIndex = scanner.nextInt();
    scanner.nextLine();

    if (departmentIndex >= 1 && departmentIndex <= departmentNames.size()) {
        Department department = departments.get(departmentNames.get(departmentIndex - 1));
        while (true) {
            System.out.println("Department: " + department.getName());
            System.out.println("1. Add Section");
            System.out.println("2. List Sections");
            System.out.println("3. Update Section");
            System.out.println("4. Delete Section");
            System.out.println("5. Back to Department Menu");
            System.out.print("Select an option: ");
            int sectionChoice = scanner.nextInt();
            scanner.nextLine(); 
            Map<String, Section> sections = department.getSections(); 
            switch (sectionChoice) {
                case 1:
                    System.out.print("Enter section name: ");
                    String sectionName = scanner.nextLine();
                    department.addSection(sectionName);
                    System.out.println("Section added successfully.");
                    break;
                case 2:
                		for (Section section : sections.values()) {
                        System.out.println("Section: " + section.getName() );
                    }
                    break;
                case 3:
                    System.out.print("Enter section name to update: ");
                    String oldSectionName = scanner.nextLine();
                    System.out.print("Enter new section name: ");
                    String newSectionName = scanner.nextLine();

                    sections = department.getSections();
                    if (sections.containsKey(oldSectionName)) {
                        Section sectionToUpdate = sections.get(oldSectionName);
                        sectionToUpdate.updateName(newSectionName);
                        System.out.println("Section updated successfully.");
                    } else {
                        System.out.println("Section not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter section name to delete: ");
                    String sectionToDelete = scanner.nextLine();
                    if (sections.containsKey(sectionToDelete)) {
                        sections.remove(sectionToDelete);
                        System.out.println("Section deleted successfully.");
                    } else {
                        System.out.println("Section not found.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    } else {
        System.out.println("Invalid department index.");
    }
}
}

