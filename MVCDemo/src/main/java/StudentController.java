public class StudentController {
    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view){
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name){
        model.setName(name);
    }

    public void setStudentRollNo(String rollNo){
        model.setRollNo(rollNo);
    }

    public void updateView(){
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}