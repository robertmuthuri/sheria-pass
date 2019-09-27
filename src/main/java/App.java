import static spark.Spark.*;

import com.google.gson.Gson;
import dao.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;
import models.*;

public class  App {
    public static void main(String[] args) {
        Connection con;
        Sql2oJudgeDao judgeDao;
        Sql2oCaselawDao caselawDao;
        Sql2oInterestedPartyDao interestespartyDao;
        Sql2oLawDao lawDao;
        Sql2oPetitionerDao petitionerDao;
        Sql2oRespondentDao respondentDao;
        Gson gson = new Gson();
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/sheria-pass.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        judgeDao = new Sql2oJudgeDao(sql2o);
        caselawDao = new Sql2oCaselawDao(sql2o);
        petitionerDao = new Sql2oPetitionerDao(sql2o);
        interestespartyDao = new Sql2oInterestedPartyDao(sql2o);
        lawDao = new Sql2oLawDao(sql2o);
        respondentDao = new Sql2oRespondentDao(sql2o);

        //Returns all judges from a db
        get("/judges", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(judgeDao.getAllJudges());//send it back to be displayed
        });

        //Gets all the caselaws from a database;
        get("/caselaw", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(caselawDao.getAll());//send it back to be displayed

        });

        //Returns all the petitioners from a database
        get("/petitioner", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(petitionerDao.getAllPetioners());//send it back to be displayed

        });

        //Getting all the interested parties from a database
        get("/interestedparty", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(interestespartyDao.getAllInterestedParties());//send it back to be displayed

        });

        //Getting all the laws from the store
        get("/laws", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(lawDao.getAllLaws());//send it back to be displayed

        });
        get("/respondent", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(respondentDao.getAllRespondent());//send it back to be displayed

        });
        get("/respondentscase/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int party_id = Integer.parseInt(req.params("id"));
            return gson.toJson(respondentDao.getAllCasesForResponded(party_id));//send it back to be displayed

        });


        //==================================POSTS==========================//


        post("/judges/new", "application/json", (req, res) -> { // here we accept a request in JSON
            Judge judge = gson.fromJson(req.body(), Judge.class);
            judgeDao.add(judge);
            res.type("application/json");
            res.status(201);// update the response status code
            return gson.toJson(judge);
        });

        post("/respondent/new", "application/json", (req, res) -> { // here we accept a request in JSON
            Respondent respondent= gson.fromJson(req.body(), Respondent.class);
            respondentDao.add(respondent);
            res.type("application/json");
            res.status(201);// update the response status code
            return gson.toJson(respondent);
        });







    }

}
