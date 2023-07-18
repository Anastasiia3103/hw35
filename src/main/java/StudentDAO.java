import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private final SessionFactory sessionFactory;

    public StudentDAO() {
        sessionFactory = HibernateConfig.getSessionFactory();
    }

    public void addStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, "Failed to add student", e);
        }
    }

    public void deleteStudent(Student student) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession ()) {
            transaction = null;
            transaction = session.beginTransaction ();
            session.delete (student);
            transaction.commit ();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback ();
            }
            Logger.getLogger (StudentDAO.class.getName ()).log (Level.SEVERE, "Failed to delete student", e);
        }
    }


    public void updateStudent(Student student) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession ()) {
            transaction = null;
            transaction = session.beginTransaction ();
            session.update (student);
            transaction.commit ();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback ();
            }
            Logger.getLogger (StudentDAO.class.getName ()).log (Level.SEVERE, "Failed to update student", e);
        }
    }


    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        List<Student> students = null;

        try {
            students = session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, "Failed to retrieve all students", e);
        } finally {
            session.close();
        }

        return students;
    }

    public Student getStudentById(int id) {
        Session session = sessionFactory.openSession();
        Student student = null;

        try {
            student = session.get(Student.class, id);
        } catch (Exception e) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, "Failed to retrieve student by ID: " + id, e);
        } finally {
            session.close();
        }

        return student;
    }
}

