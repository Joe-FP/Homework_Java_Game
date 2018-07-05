package db;

import models.Console;
import models.Game;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBConsole {

    private static Session session;

    public static void addGameToConsole(Console console, Game game){
        console.addGame(game);
        game.addConsole(console);
        DBHelper.update(game);
    }

    public static List<Game> availableGames(Console console){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Game> games = null;
        try {
            Criteria cr = session.createCriteria(Game.class);
            cr.createAlias("consoles", "console");
            cr.add(Restrictions.eq("console.id", console.getId()));
            games = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return games;
    }

}
