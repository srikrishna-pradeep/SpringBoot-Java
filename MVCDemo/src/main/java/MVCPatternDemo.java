public class MVCPatternDemo {
    public static void main(String[] args) {

        //fetch student record based on his roll no from the database
        StudentModel model  = retriveStudentFromDatabase();

        //Create a view : to write student details on console
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        //update model data
        controller.setStudentName("Aravind");
        controller.setStudentRollNo("5");

        controller.updateView();
    }

    private static StudentModel retriveStudentFromDatabase(){
        StudentModel studentModel = new StudentModel();
        studentModel.setName("Teja");
        studentModel.setRollNo("10");
        return studentModel;
    }
}