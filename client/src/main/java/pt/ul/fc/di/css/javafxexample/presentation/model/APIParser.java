package pt.ul.fc.di.css.javafxexample.presentation.model;

import java.util.ArrayList;
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
            // Parse the JSON response
            JsonArray topicsArray = JsonParser.parseString(content).getAsJsonArray();
            
            // List to hold the DissertationTopicModel instances
            List<DissertationTopicModel> dissertationTopics = new ArrayList<>();
            
            // Process each topic
            for (JsonElement topicElement : topicsArray) {
                JsonObject topicObject = topicElement.getAsJsonObject();
                
                long id = topicObject.get("id").getAsLong();
                String title = topicObject.get("title").getAsString();
                String description = topicObject.get("description").getAsString();
                double salary = topicObject.get("salary").getAsDouble();
                
                // Parse submitter
                JsonObject submitterObject = topicObject.getAsJsonObject("submitter");
                String submitterName = submitterObject.get("name").getAsString();
                String submitterUsername = submitterObject.get("username").getAsString();
                String submitterPassword = submitterObject.get("password").getAsString();
                
                // Create submitter model
                AppUserModel submitter = new AppUserModel(submitterUsername, submitterPassword, submitterName);
                
                // Parse compatible masters
                Set<MastersModel> compatibleMasters = new HashSet<>();
                JsonArray mastersArray = topicObject.getAsJsonArray("compatibleMasters");
                for (JsonElement masterElement : mastersArray) {
                    JsonObject masterObject = masterElement.getAsJsonObject();
                    String masterName = masterObject.get("name").getAsString();
                    
                    // Parse coordinator
                    JsonObject coordinatorObject = masterObject.getAsJsonObject("coordinator");
                    String coordinatorName = coordinatorObject.get("name").getAsString();
                    String coordinatorUsername = coordinatorObject.get("username").getAsString();
                    String coordinatorPassword = coordinatorObject.get("password").getAsString();
                    
                    // Create coordinator model
                    ProfessorModel coordinator = new ProfessorModel(coordinatorUsername, coordinatorPassword, coordinatorName);
                    
                    // Create master model
                    MastersModel master = new MastersModel(masterName, coordinator);
                    compatibleMasters.add(master);
                }
                
                // Create dissertation topic model
                DissertationTopicModel dissertationTopic = new DissertationTopicModel(title, description, salary, submitter, compatibleMasters);
                dissertationTopic.setId(id);
                
                // Add to the list
                dissertationTopics.add(dissertationTopic);
            } 
            return dissertationTopics;
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static List<ApplicationModel> parseApplications(String content) {
        try {
            // Parse the JSON response
            JsonArray applicationsArray = JsonParser.parseString(content).getAsJsonArray();
          
            // Create a list to hold the ApplicationModel instances
            List<ApplicationModel> applications = new ArrayList<>();
            
            // Process each application
            for (JsonElement applicationElement : applicationsArray) {
                JsonObject applicationObject = applicationElement.getAsJsonObject();
                
                long id = applicationObject.get("id").getAsLong();
                
                // Parse student
                JsonObject studentObject = applicationObject.getAsJsonObject("student");
                String studentId = studentObject.get("id").getAsString();
                String studentUsername = studentObject.get("username").getAsString();
                String studentPassword = studentObject.get("password").getAsString();
                String studentName = studentObject.get("name").getAsString();
                int studentNumber = studentObject.get("studentNumber").getAsInt();
                double averageGrade = studentObject.get("averageGrade").getAsDouble();
                
                // Parse student's masters
                JsonObject masterObject = studentObject.getAsJsonObject("masters");
                long masterId = masterObject.get("id").getAsLong();
                String masterName = masterObject.get("name").getAsString();
                
                // Parse master's coordinator
                JsonObject coordinatorObject = masterObject.getAsJsonObject("coordinator");
                long coordinatorId = coordinatorObject.get("id").getAsLong();
                String coordinatorUsername = coordinatorObject.get("username").getAsString();
                String coordinatorPassword = coordinatorObject.get("password").getAsString();
                String coordinatorName = coordinatorObject.get("name").getAsString();
                
                // Create coordinator model
                ProfessorModel coordinator = new ProfessorModel(coordinatorUsername, coordinatorPassword, coordinatorName);
                
                // Create master's model
                MastersModel master = new MastersModel(masterName, coordinator);
                
                // Create student model
                StudentModel student = new StudentModel(studentUsername, studentPassword, studentName, studentNumber, averageGrade, null);
                
                // Parse topic
                JsonObject topicObject = applicationObject.getAsJsonObject("topic");
                long topicId = topicObject.get("id").getAsLong();
                String topicTitle = topicObject.get("title").getAsString();
                String topicDescription = topicObject.get("description").getAsString();
                double topicSalary = topicObject.get("salary").getAsDouble();
                
                // Parse submitter
                JsonObject submitterObject = topicObject.getAsJsonObject("submitter");
                long submitterId = submitterObject.get("id").getAsLong();
                String submitterUsername = submitterObject.get("username").getAsString();
                String submitterPassword = submitterObject.get("password").getAsString();
                String submitterName = submitterObject.get("name").getAsString();
                
                // Create submitter model
                AppUserModel submitter = new AppUserModel(submitterUsername, submitterPassword, submitterName);
                
                // Parse compatible masters
                Set<MastersModel> compatibleMasters = new HashSet<>();
                JsonArray mastersArray = topicObject.getAsJsonArray("compatibleMasters");
                for (JsonElement masterElement : mastersArray) {
                    JsonObject compatibleMasterObject = masterElement.getAsJsonObject();
                    long compatibleMasterId = compatibleMasterObject.get("id").getAsLong();
                    String compatibleMasterName = compatibleMasterObject.get("name").getAsString();
                    
                    // Parse coordinator of compatible master
                    JsonObject compatibleCoordinatorObject = compatibleMasterObject.getAsJsonObject("coordinator");
                    long compatibleCoordinatorId = compatibleCoordinatorObject.get("id").getAsLong();
                    String compatibleCoordinatorUsername = compatibleCoordinatorObject.get("username").getAsString();
                    String compatibleCoordinatorPassword = compatibleCoordinatorObject.get("password").getAsString();
                    String compatibleCoordinatorName = compatibleCoordinatorObject.get("name").getAsString();
                    
                    // Create coordinator model
                    ProfessorModel compatibleCoordinator = new ProfessorModel(compatibleCoordinatorUsername, compatibleCoordinatorPassword, compatibleCoordinatorName);
                    
                    // Create compatible master model
                    MastersModel compatibleMaster = new MastersModel(compatibleMasterName, compatibleCoordinator);
                    compatibleMasters.add(compatibleMaster);
                }
                
                // Create dissertation topic model
                DissertationTopicModel topic = new DissertationTopicModel(topicTitle, topicDescription, topicSalary, submitter, compatibleMasters);
                topic.setId(topicId);
                
                // Create application model
                ApplicationModel application = new ApplicationModel(student, topic);
                application.setId(id);
                
                // Add to the list
                applications.add(application);
                return applications;
            }
        } catch(Exception e) {
            return new ArrayList<ApplicationModel>();
        }
        return new ArrayList<ApplicationModel>();
    }

    public static List<ThesisExecutionModel> parseThesisExecutions(String content) {
        try {
            // Parse the JSON response
            JsonArray thesisExecutionsArray = JsonParser.parseString(content).getAsJsonArray();
          
            // Create a list to hold the ThesisExecutionModel instances
            List<ThesisExecutionModel> thesisExecutions = new ArrayList<>();
            
            // Process each thesis execution
            for (JsonElement thesisElement : thesisExecutionsArray) {
                JsonObject thesisObject = thesisElement.getAsJsonObject();
                
                long id = thesisObject.get("id").getAsLong();
                
                // Parse student
                JsonObject studentObject = thesisObject.getAsJsonObject("student");
                String studentId = studentObject.get("id").getAsString();
                String studentUsername = studentObject.get("username").getAsString();
                String studentPassword = studentObject.get("password").getAsString();
                String studentName = studentObject.get("name").getAsString();
                int studentNumber = studentObject.get("studentNumber").getAsInt();
                double averageGrade = studentObject.get("averageGrade").getAsDouble();
                
                // Parse student's masters
                JsonObject masterObject = studentObject.getAsJsonObject("masters");
                long masterId = masterObject.get("id").getAsLong();
                String masterName = masterObject.get("name").getAsString();
                
                // Parse master's coordinator
                JsonObject coordinatorObject = masterObject.getAsJsonObject("coordinator");
                long coordinatorId = coordinatorObject.get("id").getAsLong();
                String coordinatorUsername = coordinatorObject.get("username").getAsString();
                String coordinatorPassword = coordinatorObject.get("password").getAsString();
                String coordinatorName = coordinatorObject.get("name").getAsString();
                
                // Create coordinator model
                ProfessorModel coordinator = new ProfessorModel(coordinatorUsername, coordinatorPassword, coordinatorName);
                
                // Create master's model
                MastersModel master = new MastersModel(masterName, coordinator);
                
                // Create student model
                StudentModel student = new StudentModel(studentUsername, studentPassword, studentName, studentNumber, averageGrade, null);
                
                // Parse topic
                JsonObject topicObject = thesisObject.getAsJsonObject("topic");
                long topicId = topicObject.get("id").getAsLong();
                String topicTitle = topicObject.get("title").getAsString();
                String topicDescription = topicObject.get("description").getAsString();
                double topicSalary = topicObject.get("salary").getAsDouble();
                
                // Parse submitter
                JsonObject submitterObject = topicObject.getAsJsonObject("submitter");
                long submitterId = submitterObject.get("id").getAsLong();
                String submitterUsername = submitterObject.get("username").getAsString();
                String submitterPassword = submitterObject.get("password").getAsString();
                String submitterName = submitterObject.get("name").getAsString();
                
                // Create submitter model
                AppUserModel submitter = new AppUserModel(submitterUsername, submitterPassword, submitterName);
                
                // Parse compatible masters
                Set<MastersModel> compatibleMasters = new HashSet<>();
                JsonArray mastersArray = topicObject.getAsJsonArray("compatibleMasters");
                for (JsonElement masterElement : mastersArray) {
                    JsonObject compatibleMasterObject = masterElement.getAsJsonObject();
                    long compatibleMasterId = compatibleMasterObject.get("id").getAsLong();
                    String compatibleMasterName = compatibleMasterObject.get("name").getAsString();
                    
                    // Parse coordinator of compatible master
                    JsonObject compatibleCoordinatorObject = compatibleMasterObject.getAsJsonObject("coordinator");
                    long compatibleCoordinatorId = compatibleCoordinatorObject.get("id").getAsLong();
                    String compatibleCoordinatorUsername = compatibleCoordinatorObject.get("username").getAsString();
                    String compatibleCoordinatorPassword = compatibleCoordinatorObject.get("password").getAsString();
                    String compatibleCoordinatorName = compatibleCoordinatorObject.get("name").getAsString();
                    
                    // Create coordinator model
                    ProfessorModel compatibleCoordinator = new ProfessorModel(compatibleCoordinatorUsername, compatibleCoordinatorPassword, compatibleCoordinatorName);
                    
                    // Create compatible master model
                    MastersModel compatibleMaster = new MastersModel(compatibleMasterName, compatibleCoordinator);
                    compatibleMasters.add(compatibleMaster);
                }
                
                // Create dissertation topic model
                DissertationTopicModel topic = new DissertationTopicModel(topicTitle, topicDescription, topicSalary, submitter, compatibleMasters);
                topic.setId(topicId);
                
                // Parse internal advisor
                JsonObject internalAdvisorObject = thesisObject.getAsJsonObject("internalAdvisor");
                long internalAdvisorId = internalAdvisorObject.get("id").getAsLong();
                String internalAdvisorUsername = internalAdvisorObject.get("username").getAsString();
                String internalAdvisorPassword = internalAdvisorObject.get("password").getAsString();
                String internalAdvisorName = internalAdvisorObject.get("name").getAsString();
                
                // Create internal advisor model
                ProfessorModel internalAdvisor = new ProfessorModel(internalAdvisorUsername, internalAdvisorPassword, internalAdvisorName);
                
                // Parse year of execution and final grade
                String yearOfExecution = thesisObject.get("yearOfExecution").getAsString();
                int finalGrade = thesisObject.get("finalGrade").getAsInt();
                
                // Create thesis execution model
                ThesisExecutionModel thesisExecution = new ThesisExecutionModel(student, topic, yearOfExecution, internalAdvisor);
                thesisExecution.setId(id);
                thesisExecution.setFinalGrade(finalGrade);
                
                // Add to the list
                thesisExecutions.add(thesisExecution);
            }
            return thesisExecutions;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
