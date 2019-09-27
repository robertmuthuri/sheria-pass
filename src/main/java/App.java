import static spark.Spark.*;

import com.google.gson.Gson;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;


import dao.Sql2oJudgeDao;
import  dao.Sql2oCaselawDao;
import  dao.Sql2oInterestedPartyDao;
import  dao.Sql2oLawDao;
import dao.JudgeDao;
import dao.PetitionerDao;
import dao.CaselawDao;

public class  App {
    public static void main(String[] args) {
        Connection con;
        Sql2oJudgeDao judgeDao;
        Sql2oCaselawDao caselawDao;
        Gson gson = new Gson();
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/sheria-pass.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        judgeDao = new Sql2oJudgeDao(sql2o);
        caselawDao = new Sql2oCaselawDao(sql2o);


        get("/judges", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(judgeDao.getAllJudges());//send it back to be displayed
        });
        get("/petitioner", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(caselawDao.getAll());//send it back to be displayed

        });
    }

}
