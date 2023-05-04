package vip.floatationdevice.dancegrade;

import lombok.extern.slf4j.Slf4j;
import vip.floatationdevice.dancegrade.data.DanceData;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
public class DanceGradeDataGenerator
{
    private static final Random r = new Random();
    private static final ArrayList<Student> students = new ArrayList<>(1024);
    private static final char[] nameChars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static int dataCount, studentCount;
    private static boolean isInitialized = false;

    public static class Student
    {
        public long studentId;
        public String name;
    }

    public static void initialize()
    {
        log.info("Initializing DanceGradeDataGenerator");
        studentCount = (dataCount = 10000 + r.nextInt(90001)) / 15;
        for(int i = 0; i != studentCount; i++)
        {
            Student student = new Student();
            StringBuilder name = new StringBuilder(10);
            for(int j = 0; j != 10; j++)
                name.append(nameChars[r.nextInt(nameChars.length)]);
            student.name = name.toString();
            student.studentId = 1000000000 + r.nextInt(1147483648);
            students.add(student);
            log.info("Added student " + student.name + ", ID=" + student.studentId);
        }
        log.info("DanceGradeDataGenerator initialization completed");
        isInitialized = true;
    }

    public static DanceData generateDanceData()
    {
        if(!isInitialized) throw new IllegalStateException("DanceGradeDataGenerator is not initialized");
        DanceData data = new DanceData();
        Student student = students.get(r.nextInt(students.size()));
        double[] scores = new double[5];
        double scoreAvg;
        int[] actions = new int[8 + r.nextInt(12)];

        for(int i = 0; i != actions.length; i++)
            actions[i] = r.nextInt(5);
        a:
        for(int i = 0; i != 5; i++)
        {
            for(int a : actions)
            {
                if(a == i)
                {
                    scores[i] = r.nextDouble(40, 100);
                    continue a;
                }
                scores[i] = 0;
            }
        }
        float scoreSum = 0;
        for(int i = 0; i != 5; i++)
            scoreSum += scores[i];
        scoreAvg = scoreSum / 5;

        data.setStudentId(student.studentId);
        data.setName(student.name);
        data.setScores(scores);
        data.setScoreAvg(scoreAvg);
        data.setActions(actions);
        log.info("Generated " + data);
        return data;
    }

    public static int getStudentCount()
    {
        if(!isInitialized) throw new IllegalStateException("DanceGradeDataGenerator is not initialized");
        return studentCount;
    }

    public static int getDataCount()
    {
        if(!isInitialized) throw new IllegalStateException("DanceGradeDataGenerator is not initialized");
        return dataCount;
    }

    public static void main(String[] args) throws Exception
    {
        initialize();
        for(int i = 0; i != dataCount; i++)
            generateDanceData();
        log.info("OK: generated " + studentCount + " students, " + dataCount + " data");
    }
}
