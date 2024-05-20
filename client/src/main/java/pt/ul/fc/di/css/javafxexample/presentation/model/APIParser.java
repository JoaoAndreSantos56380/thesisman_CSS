package pt.ul.fc.di.css.javafxexample.presentation.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class APIParser {

    public static List<DissertationTopicModel> parseDissertationTopics(String content) {
        try {
            JsonArray topicsArray = JsonParser.parseString(content).getAsJsonArray();
            List<DissertationTopicModel> dissertationTopics = new ArrayList<>();

            for (JsonElement topicElement : topicsArray) {
                JsonObject topicObject = topicElement.getAsJsonObject();
                DissertationTopicModel topic = parseDissertationTopic(topicObject);
                dissertationTopics.add(topic);
            }
            return dissertationTopics;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<ApplicationModel> parseApplications(String content) {
        try {
            JsonArray applicationsArray = JsonParser.parseString(content).getAsJsonArray();
            List<ApplicationModel> applications = new ArrayList<>();

            for (JsonElement applicationElement : applicationsArray) {
                JsonObject applicationObject = applicationElement.getAsJsonObject();

                long id = applicationObject.get("id").getAsLong();
                StudentModel student = parseStudent(applicationObject.getAsJsonObject("student"));
                DissertationTopicModel topic = parseDissertationTopic(applicationObject.getAsJsonObject("topic"));

                ApplicationModel application = new ApplicationModel(student, topic);
                application.setId(id);
                applications.add(application);
            }
            return applications;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<ThesisExecutionModel> parseThesisExecutions(String content) {
        try {
            JsonArray thesisExecutionsArray = JsonParser.parseString(content).getAsJsonArray();
            List<ThesisExecutionModel> thesisExecutions = new ArrayList<>();

            for (JsonElement thesisExecutionElement : thesisExecutionsArray) {
                JsonObject thesisExecutionObject = thesisExecutionElement.getAsJsonObject();
                ThesisExecutionModel thesisExecution = parseThesisExecution(thesisExecutionObject);
                thesisExecutions.add(thesisExecution);
            }
            return thesisExecutions;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<ThesisDefenseModel> parseThesisDefenses(String content) {
        try {
            JsonArray thesisDefensesArray = JsonParser.parseString(content).getAsJsonArray();
            List<ThesisDefenseModel> thesisDefenses = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            for (JsonElement thesisDefenseElement : thesisDefensesArray) {
                JsonObject thesisDefenseObject = thesisDefenseElement.getAsJsonObject();

                long id = thesisDefenseObject.get("id").getAsLong();
                ThesisExecutionModel thesisExecution = parseThesisExecution(thesisDefenseObject.getAsJsonObject("thesisExecution"));
                String location = thesisDefenseObject.get("location").getAsString();
                Date time = dateFormat.parse(thesisDefenseObject.get("time").getAsString());
                int grade = thesisDefenseObject.get("grade").getAsInt();

                ThesisDefenseModel thesisDefense = new ThesisDefenseModel(thesisExecution, location, time);
                thesisDefense.setId(id);
                thesisDefense.setGrade(grade);

                thesisDefenses.add(thesisDefense);
            }
            return thesisDefenses;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static DissertationTopicModel parseDissertationTopic(JsonObject topicObject) {
        long id = topicObject.get("id").getAsLong();
        String title = topicObject.get("title").getAsString();
        String description = topicObject.get("description").getAsString();
        double salary = topicObject.get("salary").getAsDouble();
        AppUserModel submitter = parseAppUser(topicObject.getAsJsonObject("submitter"));

        Set<MastersModel> compatibleMasters = new HashSet<>();
        JsonArray mastersArray = topicObject.getAsJsonArray("compatibleMasters");
        for (JsonElement masterElement : mastersArray) {
            MastersModel master = parseMasters(masterElement.getAsJsonObject());
            compatibleMasters.add(master);
        }

        DissertationTopicModel topic = new DissertationTopicModel(title, description, salary, submitter, compatibleMasters);
        topic.setId(id);
        return topic;
    }

    private static StudentModel parseStudent(JsonObject studentObject) {
        String username = studentObject.get("username").getAsString();
        String password = studentObject.get("password").getAsString();
        String name = studentObject.get("name").getAsString();
        int studentNumber = studentObject.get("studentNumber").getAsInt();
        double averageGrade = studentObject.get("averageGrade").getAsDouble();

        MastersModel masters = parseMasters(studentObject.getAsJsonObject("masters"));

        return new StudentModel(username, password, name, studentNumber, averageGrade, masters);
    }

    private static MastersModel parseMasters(JsonObject masterObject) {
        String name = masterObject.get("name").getAsString();
        ProfessorModel coordinator = parseProfessor(masterObject.getAsJsonObject("coordinator"));
        return new MastersModel(name, coordinator);
    }

    private static ProfessorModel parseProfessor(JsonObject professorObject) {
        String username = professorObject.get("username").getAsString();
        String password = professorObject.get("password").getAsString();
        String name = professorObject.get("name").getAsString();
        return new ProfessorModel(username, password, name);
    }

    private static AppUserModel parseAppUser(JsonObject userObject) {
        String username = userObject.get("username").getAsString();
        String password = userObject.get("password").getAsString();
        String name = userObject.get("name").getAsString();
        return new AppUserModel(username, password, name);
    }

    private static ThesisExecutionModel parseThesisExecution(JsonObject thesisExecutionObject) {
        long id = thesisExecutionObject.get("id").getAsLong();
        StudentModel student = parseStudent(thesisExecutionObject.getAsJsonObject("student"));
        DissertationTopicModel topic = parseDissertationTopic(thesisExecutionObject.getAsJsonObject("topic"));
        ProfessorModel internalAdvisor = parseProfessor(thesisExecutionObject.getAsJsonObject("internalAdvisor"));
        String yearOfExecution = thesisExecutionObject.get("yearOfExecution").getAsString();
        int finalGrade = thesisExecutionObject.get("finalGrade").getAsInt();

        ThesisExecutionModel thesisExecution = new ThesisExecutionModel(student, topic, yearOfExecution, internalAdvisor);
        thesisExecution.setId(id);
        thesisExecution.setFinalGrade(finalGrade);

        return thesisExecution;
    }
}
