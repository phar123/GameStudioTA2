package sk.tuke.gamestudio.minesweeper;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.*;
import sk.tuke.gamestudio.service.CountryServiceJPA;
import sk.tuke.gamestudio.service.StudentGroupServiceJPA;
import sk.tuke.gamestudio.service.StudentServiceJPA;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@Transactional
public class PlaygroundJPA {
    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private Throwable ex;
    @PersistenceContext
    private EntityManager entityManager;

  /*  public void play() {


        entityManager.persist(new Country("France"));
        entityManager.persist(new Country("Germany"));
        entityManager.persist(new Country("England"));

        entityManager.persist(new Occupation("ziak"));
        entityManager.persist(new Occupation("student"));
        entityManager.persist(new Occupation("zamestnanec"));
        entityManager.persist(new Occupation("zivnostnik"));
        entityManager.persist(new Occupation("nezamestnany"));
        entityManager.persist(new Occupation("dochodca"));
        entityManager.persist(new Occupation("invalid"));


    }*/

  /*  public void play() {
        System.out.println("Opening JPA playground.");
        String game = "minesweeper";
        String user = "Jojo";
        int ratingValue = 4;

        entityManager.persist(new Rating(game, user, ratingValue, new Date()));

        Rating rating2Write = null;
        try {
            rating2Write = (Rating) entityManager.createQuery("select r from Rating r where r.username = :user and r.game = :game")
                    .setParameter("user", user)
                    .setParameter("game", game)
                    .getSingleResult();

            rating2Write.setRating(ratingValue);
            rating2Write.setRatedon(new Date());

        } catch (NoResultException e) {
            rating2Write = new Rating(game, user, ratingValue, new Date());
            entityManager.persist(rating2Write);


        }
        System.out.println(rating2Write);
/*entityManager.persist(new Score("minesweeper","Peter", 120, new Date()));
entityManager.persist(new Score("minesweeper", "Stevo", 62, new Date()));

String game = "minesweeper";
List<Score> bestScores =  entityManager.createQuery("select sc from Score sc where sc.game = :myGame order by sc.points desc")
        .setParameter("myGame", game)
        .getResultList();

        System.out.println(bestScores);
        System.out.println("Closing JPA playground.");
    }

 */
        //Student St.Group
        // @PersistenceContext
  /* private EntityManager entityManager;
   public void play(){

       System.out.println("Opening JPA playground");
      /* entityManager. persist(new StudyGroup("zakladna"));
       entityManager. persist(new StudyGroup("mierne pokrocila"));
       entityManager. persist(new StudyGroup("pokrocila"));*/


     /*  String firstName = "";//"Raweel";
       String lastName = "";//"Powick";
       int group = 1;



       List<StudyGroup> studyGroups=entityManager.createQuery("select g from StudyGroup g")
               .getResultList();
       int noOfGroups = studyGroups.size();

       for (int i = 0; i<noOfGroups;i++)
           System.out.println(i+" "+studyGroups.get(i));
//INPUT FROM USER
     System.out.println("Please enter firstName(max.255: ");

     firstName = readLine();

       System.out.println("Please enter lasttName(max.255): ");
       lastName = readLine();

       System.out.println("Please enter study_group (1-3): ");
       group = Integer.parseInt(readLine());



       entityManager.persist(new Student(firstName,lastName,studyGroups.get(group)));




       List<Student> students = entityManager.createQuery("select s from Student s")
               .getResultList();
       System.out.println(students);

       System.out.println("Closing JPA playground.");

    }*/

      /*  private String readLine () {
            try {
                return input.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        @Autowired
        private StudentServiceJPA studentServiceJPA;
        @Autowired
        private StudentGroupServiceJPA studentGroupServiceJPA;

        public void play() {
            System.err.println("Opening JPA");
            newStudyGroup();
            addNewStudent();
            getAllStudents();
            System.err.println("Closing JPA");

        }

        private void newStudyGroup(){
            System.out.println("Add a new study group (Y)es/(N)o: ");
            String newStudyGroup = readLine();
            if (newStudyGroup.toLowerCase().equals("y")) {
                System.out.println("Study group name: ");
                String studyGroupName = readLine();

                try {
                    studentGroupServiceJPA.addStudyGroup(new StudyGroup(studyGroupName));
                } catch (Exception e) {
                    System.err.println("Problem with writing into DB "+ e.getMessage());
                }
            }
        }

        private void addNewStudent() {
            System.out.println("Enter First Name: ");
            String firstName = readLine();
            System.out.println("Enter Last Name");
            String lastName = readLine();

            if (firstName.length() > 0 && lastName.length() > 0) {
                try {
                    List<StudyGroup> studyGroups = studentGroupServiceJPA.getAllStudyGroups();
                    System.out.println("Study groups available");
                    for (int i = 0; i < studyGroups.size(); i++)
                        System.out.println(i + " " + studyGroups.get(i));
                    System.out.println("Enter Study Group");
                    int studyGroup = Integer.parseInt(readLine());
                    try {
                        studentServiceJPA.addStudent(new Student(firstName, lastName, studyGroups.get(studyGroup)));
                    } catch (Exception e) {
                        System.err.println("Problem with reading from DB " + e.getMessage());
                    }

                    studentServiceJPA.addStudent(new Student());
                } catch (Exception e) {
                    System.err.println("Problem with reading from DB " + e.getMessage());
                }
            }
        }
                private void getAllStudents() {
                    try {
                        List<Student> students = studentServiceJPA.getAllStudents();
                        System.out.println(students);
                        for (int i = 0 ;i < students.size(); i++) {
                            System.out.printf("%2d. %10s %10s : %15s\n",
                                    students.get(i).getIdent(),
                                    students.get(i).getFirstName(),
                                    students.get(i).getLastName(),
                                    students.get(i).getStudyGroup());
                        }
                    } catch (Exception e) {
                        System.err.println("Problem with reading from DB " + e.getMessage());

                    }
                }

   /* public void play() {
        System.out.println("Opening JPA playground.");
        String firstName = "Raweel";
        String lastName = "Powick";
        int group = 1;

        List<StudyGroup> studyGroups = entityManager.createQuery("select g from StudyGroup g")
                .getResultList();

        int noOfGroups = studyGroups.size();

        for (int i = 0; i < noOfGroups; i++) {
            System.out.println(i + " " + studyGroups.get(i));
        }

        entityManager.persist(new Student(firstName, lastName, studyGroups.get(group)));

        List<Student> students = entityManager.createQuery("select s from Student s")
                .getResultList();

        System.out.println(students);

    }*/
}



