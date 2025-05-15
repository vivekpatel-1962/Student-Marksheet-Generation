import java.util.*;
import java.io.*;
import java.sql.*;

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ip";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String Driver = "con.mysql.jdbc.Driver";

    public static Connection getConnection() throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
    }
}

class student {
    int rollno;
    String fname;
    String lname;
    int standard ;
    String section ;
    
    public student(){}

    public student(String fname, String lname, int standard, String section) {
        this.fname = fname;
        this.lname = lname;
        this.standard = standard;
        this.section = section;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setStudentLL() throws Exception {
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM student");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String fname=rs.getString(2);
            String lname=rs.getString(3);
            int standard=rs.getInt(4);
            String section=rs.getString(5);
            insertAtLast(new student(fname,lname,standard,section));
        }
    }
    class node {
        node next;
        student s;

        node(student s) {
            this.s = s;
            next = null;
        }
    }

    node first = null;

    void insertAtFirst(student s) {
        node n = new node(s);
        if(first==null) {
            first =n;
        } else {
            n.next=first;
            first = n;
        }
    }

    void insertAtLast(student s) {
        if (first == null) {
            first = new node(s);
        } else {
            node n = new node(s);
            node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    void deletefirst() {
        if(first==null) {
            System.out.println("underflow"); 
        }
        else {
            first = first.next;
            
        }
    }

    void deletelast() {
        if(first == null) {
            System.out.println(" no elements in LL");
        } else {
            node temp = first;
            while(temp.next.next!=null) {
                temp = temp.next;
            }
            temp.next = null;
        }
    }

    void display1() {
        node temp = first;
        while (temp != null) {
            System.out.println("----------------------------\nFirstName: " + temp.s.fname + "\nLastName: " + temp.s.lname + "\nstandard: " + temp.s.standard + "\nsection: " + temp.s.section +"\n----------------------------");
            temp = temp.next;
        }
    }

    void display() throws Exception {
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM student");
        ResultSet rs = pst.executeQuery();
        boolean b = true; 
        while (rs.next()) {
            b= false;
            int rollno1 = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            int standard = rs.getInt(4);
            String section = rs.getString(5);
            System.out.println("\n----------------------------\nRollNO: " + rollno1 +"\nFirstName: " +fname+ "\nLastName: " + lname + "\nstandard: " + standard + "\nsection: " + section +"\n---------------------------- \n");
        }
        if(b){
            System.out.println("NO STUDENTS");
        }
    }
    
    public void addStudent(student st) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO student (firstname,lastname,standard,section) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,st.getFname());
        ps.setString(2,st.getLname());
        ps.setInt(3, st.getStandard());
        ps.setString(4, st.getSection());
       int f = ps.executeUpdate();
       if(f>0) {
        System.out.println("inserted successfully");
       } else {
        System.out.println(" unsuccessfull");
       }
       System.out.println();
    }

    public void removeStudent(int r) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        String query1="Delete from marks where rollno=?";
        PreparedStatement ps1 = connection.prepareStatement(query1);
        ps1.setInt(1, r);
        ps1.executeUpdate();
        String query = "Delete From student where rollno=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, r);
       int f = ps.executeUpdate();
       if(f>0) {
        System.out.println("removed successfully");
       } else {
        System.out.println(" roll no not found");
       }
       System.out.println();
        
    }

    void updateName(String FN, String LN, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update student SET frstname=?, lastname=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, FN);
        ps.setString(2, LN);
        ps.setInt(3, r);
        int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }

    void updateStandard(int S, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update student SET standard=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, S);
        ps.setInt(2, r);
        int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }

    void updateSection(String Se, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update student SET section=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Se);
        ps.setInt(2, r);
       int f = ps.executeUpdate();
       if(f>0) {
        System.out.println("updated successfully");
       } else {
        System.out.println(" roll no not found");
       }
       System.out.println();
    }
}

class marks {
    double physics;
    double chemistry;
    double maths;
    double english;
    double computer;
    int rollno;
    int marksid;
    
    public marks(){}

    public marks(double physics, double chemistry, double maths, double english, double computer,int rollno) {
        this.physics = physics;
        this.chemistry = chemistry;
        this.maths = maths;
        this.english = english;
        this.computer = computer;
        this.rollno=rollno;
    }

    public double getPhysics() {
        return physics;
    }

    public void setPhysics(double physics) {
        this.physics = physics;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    public double getMaths() {
        return maths;
    }

    public void setMaths(double maths) {
        this.maths = maths;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getComputer() {
        return computer;
    }

    public void setComputer(double computer) {
        this.computer = computer;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public void setStudentMarksLL() throws Exception {
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM marks");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Double phy=rs.getDouble(2);
            Double chm=rs.getDouble(3);
            Double maths=rs.getDouble(4);
            Double eng=rs.getDouble(5);
            Double comp=rs.getDouble(6);
            int rollno=rs.getInt(7);
            insertAtLast(new marks(phy, chm, maths, eng, comp, rollno));

        }
    }
    class node {
        node next;
        marks m;

        node(marks m) {
            this.m = m;
            next = null;
        }
    }

    node first = null;

    void insertAtFirst(marks m) {
        node n = new node(m);
        if(first==null) {
            first =n;
        } else {
            n.next=first;
            first = n;
        }
    }

    void insertAtLast(marks m) {
        if (first == null) {
            first = new node(m);
        } else {
            node n = new node(m);
            node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    void deletefirst() {
        if(first==null) {
            System.out.println("underflow"); 
        }
        else {
            first = first.next;
            
        }
    }

    void deletelast() {
        if(first == null) {
            System.out.println(" no elements in LL");
        } else {
            node temp = first;
            while(temp.next.next!=null) {
                temp = temp.next;
            }
            temp.next = null;
        }
    }

    void display1() {
        node temp = first;
        while (temp != null) {
            System.out.println("\n----------------------------\nphysics: " + temp.m.physics + "\nchemistry: " + temp.m.chemistry + "\nmaths: " + temp.m.maths + "\nenglish: "+ temp.m.english +"\ncomputer: " + temp.m.computer + "\nrollno: " + temp.m.rollno + "\n----------------------------\n");
            temp = temp.next;
        }
    }

    void display() throws Exception {
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM marks");
        ResultSet rs = pst.executeQuery();
        boolean b = true; 
        while (rs.next()) {
            double physics = rs.getDouble(2);
            double chemistry = rs.getDouble(3);
            double maths = rs.getDouble(4);
            double english = rs.getDouble(5);
            double computer = rs.getDouble(6);
            int rollno = rs.getInt(7);
            System.out.println("\n----------------------------\nphysics: " + physics + "\nchemistry: " + chemistry + "\nmaths: " + maths + "\nenglish: "+ english +"\ncomputer: " + computer + "\nrollno: " + rollno + "\n----------------------------\n");
         }

    }

    
    public void addStudentMarks(marks ms) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO marks (physics,chemistry,maths,english,computer,rollno) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDouble(1, ms.getPhysics());
        ps.setDouble(2, ms.getChemistry());
        ps.setDouble(3, ms.getMaths());
        ps.setDouble(4, ms.getEnglish());
        ps.setDouble(5, ms.getComputer());
        ps.setInt(6, ms.getRollno());
        ps.executeUpdate();
        
        System.out.println("marks successfull inserted of the given roll no");
    }

    void updatemarksphysics(Double P, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update marks SET physics=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, P);
        ps.setInt(2, r);
       int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }

    void updatemarkschemistry(Double C, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update marks SET chemistry=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, C);
        ps.setInt(2, r);
       int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }

    void updatemarksmaths(Double M, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update marks SET maths=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, M);
        ps.setInt(2, r);
        int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }

    void updatemarksenglish(Double E, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update marks SET english=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, E);
        ps.setInt(2, r);
        int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }

    void updatemarkscomputer(Double CM, int r) throws Exception {
        Connection con = DatabaseConnection.getConnection();
        String sql = "Update marks SET computer=? where rollno=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, CM);
        ps.setInt(2, r);
        int f = ps.executeUpdate();
        if(f>0) {
            System.out.println("updated successfully");
           } else {
            System.out.println(" roll no not found");
           }
           System.out.println();
    }
}
class marksheet{
    void genratemarksheet(int r) throws Exception{
        String filename=r + ".txt";
        FileWriter fw=new FileWriter(filename);
        BufferedWriter bw=new BufferedWriter(fw);
        PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM student join marks on student.rollno = marks.rollno where student.rollno=?");
        pst.setInt(1, r);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            String fname=rs.getString(2);
            String lname=rs.getString(3);
            int standard=rs.getInt(4);
            char section = rs.getString(5).charAt(0);
            bw.write("Name of Student :   " + fname + " " + lname);
            bw.newLine();
            bw.newLine();
            bw.write("RollNo : " + r);
            bw.write("                Standard :     " + standard+section);
            bw.newLine();
            bw.newLine();
      
            Double phy=rs.getDouble(7);
            Double chm=rs.getDouble(8);
            Double maths=rs.getDouble(9);
            Double eng=rs.getDouble(10);
            Double comp=rs.getDouble(11);
            String grade;
            double percentage;
            percentage = (phy+chm+maths+eng+comp)/5;
            if (percentage>=85 && percentage<=100) {
                grade="A";
            }else if (percentage>=75 && percentage<85) {
                grade="B";
            }else if (percentage>=65 && percentage<75) {
                grade="C";
            }else if (percentage>=50 && percentage<65) {
                grade="D";
            }else if(percentage>=35 && percentage<50){
                grade="E";
            }else{
                grade="F";
            }
            String status;
            if(grade=="A" || grade=="B" || grade=="C" || grade=="D" || grade=="E"){
                status="Pass";
            }else{
                status="Fail";
            }
            bw.write("SubName" + "            Obtained Marks" + "    Total Marks");
            bw.newLine();
            bw.write("Physics :               " + phy + "             100");
            bw.newLine();
            bw.write("Chemistry :             " + chm + "             100");
            bw.newLine();
            bw.write("Maths :                 " + maths + "             100");
            bw.newLine();
            bw.write("English :               " + eng + "             100");
            bw.newLine();
            bw.write("Computer :              " + comp + "             100");
            bw.newLine();
            bw.newLine();
            bw.write("Over All Percentage :  " + percentage);
            bw.write("              Grade :  " + grade);
            bw.newLine();
            bw.write("Status  :  " + status);
        }
        bw.close();
    }
}

class studenthandling {
    Scanner sc = new Scanner(System.in);
    student st=new student();

    public studenthandling() throws Exception {
        st.setStudentLL();
    }
    
    void everything() throws Exception {
        System.out.println("1. Insert Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Update Student Details");
        System.out.println("4. Display Student Details");
        System.out.println("Enter choice");
        int c=sc.nextInt();
        switch (c) {
            case 1:
                System.out.println("You selected insert student");
                System.out.println("Enter First Name");
                String fname=sc.next().toUpperCase();
                System.out.println("Enter Last Name");
                String lname=sc.next().toUpperCase();
                System.out.println("Enter Standard");
                int standard=sc.nextInt();
                if (standard < 1 || standard > 12) {
                    System.out.println("Invalid standard. Please enter a value between 1 and 12.");
                    return;
                }
                System.out.println("Enter section");
                String section=sc.next().toUpperCase();
                student S=new student(fname, lname, standard, section);
                st.addStudent(S);
                st.insertAtLast(S);
                break;
            
            case 2:
                System.out.println("Enter rollno to remove");
                int rn=sc.nextInt();
                st.removeStudent(rn);
                break;

            case 3:
                System.out.println("You choose Update StudentDetails");
                System.out.println("Enter rollno to be updated");
                int roll=sc.nextInt();
                boolean b=true;
                while (b) {
                    System.out.println("1. update name");
                    System.out.println("2. update standard");
                    System.out.println("3. update section");
                    System.out.println("4. Exit updatation");
                    System.out.println("Enter choise");
                    int ec=sc.nextInt();
                    switch(ec){
                        case 1:
                            System.out.println("Enter new first name");
                            String fn=sc.next();
                            System.out.println("Enter new last name");
                            String ln=sc.next();
                            st.updateName(fn, ln, roll);
                            break;
                        case 2:
                            System.out.println("Enter new standard");
                            int ns=sc.nextInt();
                            if (ns < 1 || ns > 12) {
                                System.out.println("Invalid standard. Please enter a value between 1 and 12.");
                                return;
                            }
                            st.updateStandard(ns, roll);
                            break;
                        case 3:
                            System.out.println("Enter new section");
                            String nse=sc.next();
                            st.updateSection(nse, roll);
                            break;
                        case 4:
                            System.out.println("exit updatation");
                            b=false;
                            break;
                        default:
                            System.out.println("Invalid Choice. Please enter a valid choice.");
                            System.out.println();
                            break;
                    }
                }
                break;

            case 4:
                st.display();
                break;
            default:
                System.out.println("Invalid Choice. Please enter a valid choice.");
                System.out.println();
                break;
        }
    }

}

class markshandling {
    Scanner sc = new Scanner(System.in);
    marks mk=new marks();

    public markshandling() throws Exception {
        mk.setStudentMarksLL();
    }

    void everything() throws Exception{
        System.out.println("1. Insert Marks");
        System.out.println("2. update marks");
        System.out.println("3. display marks");
        System.out.println("Enter choise");
        int choice=sc.nextInt();
        switch (choice) {
            case 1:
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM student where rollno = ?");
                System.out.println("Enter rollno to be inserted marks");
                int rollno=sc.nextInt();
                pst.setInt(1, rollno);
                ResultSet rs = pst.executeQuery();
                if(rs.next()) {
                System.out.println("Enter physics marks");
                Double phy=sc.nextDouble();
                if (phy < 0 || phy > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    return;
                }
                System.out.println("Enter chemistry marks");
                Double chm=sc.nextDouble();
                if (chm < 0 || chm > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    return;
                }
                System.out.println("Enter maths marks");
                Double maths=sc.nextDouble();
                if (maths < 0 || maths > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    return;
                }
                System.out.println("Enter english marks");
                Double eng=sc.nextDouble();
                if (eng < 0 || eng > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    return;
                }
                System.out.println("Enter computer marks");
                Double comp=sc.nextDouble();
                if (comp < 0 || comp > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    return;
                }
                
                marks ms=new marks(phy, chm, maths, eng, comp, rollno);
                mk.addStudentMarks(ms);
                mk.insertAtLast(ms);
            } else {
                System.out.println(" no student of entered roll no is in the school");
            }
                break;
            case 2:
                System.out.println("You selected Updatation");
            PreparedStatement pst1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM student where rollno = ?");
                System.out.println("enter rollno in which you want to update marks");
                int m=sc.nextInt();
                pst1.setInt(1, m);
                ResultSet rs1 = pst1.executeQuery();
                if(rs1.next()) {
                boolean  b=true;
                while(b){
                    System.out.println("1. update physics marks");
                    System.out.println("2. update chemistry marks");
                    System.out.println("3. update maths marks");
                    System.out.println("4. update english marks");
                    System.out.println("5. update computer marks");
                    System.out.println("6. Exit");
                    System.out.println("Enter choise");
                    int chs=sc.nextInt();
                    switch (chs) {
                        case 1:
                            System.out.println("Enter New marks");
                            double p=sc.nextDouble();
                            if (p < 0 || p > 100) {
                                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                                return;
                            }
                            mk.updatemarksphysics(p, m);
                            break;
                        case 2:
                            System.out.println("Enter New marks");
                            double p1=sc.nextDouble();
                            if (p1 < 0 || p1 > 100) {
                                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                                return;
                            }
                            mk.updatemarkschemistry(p1, m);
                            break;
                        case 3:
                            System.out.println("Enter New marks");
                            double p2=sc.nextDouble();
                            if (p2 < 0 || p2 > 100) {
                                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                                return;
                            }
                            mk.updatemarksphysics(p2, m);
                            break;
                        case 4:
                            System.out.println("Enter New marks");
                            double p3=sc.nextDouble();
                            if (p3 < 0 || p3 > 100) {
                                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                                return;
                            }
                            mk.updatemarksphysics(p3, m);
                            break;
                        case 5:
                            System.out.println("Enter New marks");
                            double p4=sc.nextDouble();
                            if (p4 < 0 || p4 > 100) {
                                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                                return;
                            }
                            mk.updatemarksphysics(p4, m);
                            break;
                        case 6:
                            System.out.println("Thank you.");
                            b=false;
                            break;
                        default:
                            System.out.println("Invalid Choice. Please enter a valid choice.");
                            System.out.println();
                            break;
                    }
                }
            } else {
                System.out.println(" no roll no found to be update");
            }
                break;
                case 3:
                    mk.display();
                    break;
                default:
                    System.out.println("Invalid Choice. Please enter a valid choice.");
                    System.out.println();
                    break;
        }
        
    }

}

class main {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
       studenthandling sh = new studenthandling();
       markshandling mh = new markshandling();
       marksheet mks=new marksheet();
       
       boolean b1=true;
        while (b1) {
            System.out.println("choose 1 for student");
            System.out.println("choose 2 for marks");
            System.out.println("choose 3 to generate marksheet");
            System.out.println("choose 4 for exit");
            System.out.println("Enter choise");
            int ch=sc.nextInt();
            System.out.println();
            switch (ch) {
                case 1: sh.everything();
                    break;
                case 2:
                   mh.everything();
                    break;
                case 3:
                    System.out.println("Enter rollno to generate marksheet");
                    int roll=sc.nextInt();
                   mks.genratemarksheet(roll);
                    break;
                case 4:
                    System.out.println("Thank You. Visit Again.");
                    b1=false;
                    break;
                default:
                    System.out.println("Invalid Choice. Please enter a valid choice.");
                    System.out.println();
                    break;
            }
        }
    }
}

